package PersistenceManager;

import Controllers.Contiguous.ContiguousBlocks;
import Controllers.Contiguous.ContiguousController;
import MainPack.VirtualFileSystem;
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
public class ContiguousFileManager implements FileManager
{
    String path;
    VirtualFileSystem vfs;
    public ContiguousFileManager(String path, VirtualFileSystem vfs)
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
            ContiguousController dc = new ContiguousController(diskStatus);
            DirectoryStructure ds = new DirectoryStructure();
            while((line = reader.readLine())!= null)
            {
                String[] s = line.split(" ");
                if (s[0].equals("1")) // file
                {
                    AllocatedBlocks blocks = loadFile(Utility.subarray(s, 2, s.length - 2));
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

    private AllocatedBlocks loadFile(String s[])
    {
        int start = Integer.valueOf(s[0]), count = Integer.valueOf(s[1]);
        return new ContiguousBlocks(start, count);
    }
    @Override
    public VirtualFileSystem getVirtualFileSystem()
    {
        return vfs;
    }
}
