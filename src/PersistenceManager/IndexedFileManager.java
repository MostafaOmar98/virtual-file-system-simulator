package PersistenceManager;

import Controllers.AllocatedBlocks;
import Controllers.DiskController;
import Controllers.DiskStatus;
import Controllers.Indexed.IndexedBlocks;
import Controllers.Indexed.IndexedController;
import DirectoryStructurePackage.Directory;
import DirectoryStructurePackage.File;
import DirectoryStructurePackage.DirectoryStructure;
import MainPack.VirtualFileSystem;
import UtilityPackage.Utility;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IndexedFileManager implements FileManager
{
    String path;
    VirtualFileSystem vfs;

    public IndexedFileManager(String path, VirtualFileSystem vfs)
    {
        this.path = path;
        this.vfs = vfs;
    }

    @Override
    public void save()
    {
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(path, false));
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        String s = vfs.getBitmap() + "\n";
        List<Directory> dirs = vfs.getDirs();
        for (Directory dir : dirs)
            s += dir.toString() + "\n";
        List<File> files = vfs.getFiles();
        for (File f : files)
            s += f.toString() + "\n";
        try
        {
            writer.write(s);
            writer.flush();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void load()
    {
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        try
        {
            String line = reader.readLine(); // bitmap
            DiskStatus diskStatus = new DiskStatus(line);
            IndexedController dc = new IndexedController(diskStatus);
            DirectoryStructure ds = new DirectoryStructure();
            while((line = reader.readLine())!= null)
            {
                String[] s = line.split(" ");
                if (s[0].equals("1")) // file
                {
                    AllocatedBlocks blocks = loadFile(Utility.subarray(s, 2, s.length - 2), dc.getIndexSize());
                    ds.createFile(s[1], blocks);
                }
                else
                    ds.createDirectory(s[1]);
            }
            vfs = new VirtualFileSystem(ds, dc);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private AllocatedBlocks loadFile(String s[], int indexSize)
    {
        ArrayList<Integer> indexBlocks = new ArrayList<>(), dataBlocks = new ArrayList<>();
        for (int i = 0; i < s.length; ++i)
        {
            if (i%indexSize == 0)
                indexBlocks.add(Integer.valueOf(s[i]));
            else
                dataBlocks.add(Integer.valueOf(s[i]));
        }
        AllocatedBlocks blocks = new IndexedBlocks(indexBlocks, dataBlocks);
        return blocks;
    }

    @Override
    public VirtualFileSystem getVirtualFileSystem()
    {
        return vfs;
    }
}
