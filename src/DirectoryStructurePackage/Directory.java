package DirectoryStructurePackage;

import java.util.ArrayList;
import java.util.List;
import Controllers.*;
import UtilityPackage.Utility;

public class Directory implements IPrintable
{
    private String name;
    private Directory parent;
    private int level;
    private FSSubComponents children;

    public Directory(String name)
    {
        this.name = name;
        parent = null;
        level = 0;
        children = new FSSubComponents();
    }

    public String getName()
    {
        return name;
    }

    public Directory getParent()
    {
        return parent;
    }

    public int getLevel()
    {
        return level;
    }

    public FSSubComponents getChildren()
    {
        return children;
    }

    public Directory(String name, int level, Directory parent)
    {
        this.name = name;
        this.level = level;
        this.parent = parent;
        children = new FSSubComponents();
    }

    @Override
    public void print()
    {
        Utility.printLevel(level, "-" + name + "/\n");
        for (Directory dir : children.getDirs())
            dir.print();
        for (File f : children.getFiles())
            f.print();
    }

    File getFile(String[] sPath)
    {
        if (sPath.length == 0)
            return null;
        if (sPath.length == 1)
            return children.getFile(sPath[0]);
        Directory dir = children.getDirectory(sPath[0]);
        return dir == null ? null : dir.getFile(Utility.subarray(sPath, 1, sPath.length - 1));
    }

    Directory getDirectory(String[] sPath)
    {
        if (sPath.length == 0)
            return this;
        Directory dir = children.getDirectory(sPath[0]);
        return dir == null ? null : dir.getDirectory(Utility.subarray(sPath, 1, sPath.length - 1));
    }

    public void addFile(File f)
    {
        children.addFile(f);
    }

    public void addDirectory(Directory dir)
    {
        children.addDirectory(dir);
    }

    public void removeFile(File f)
    {
        children.removeFile(f.getName());
    }

    public void removeDirectory(Directory dir)
    {
        children.removeDirectory(dir.getName());
    }

    public List<AllocatedBlocks> delete()
    {
        List<AllocatedBlocks> deletedBlocks = new ArrayList<AllocatedBlocks>();
        while(children.hasFiles())
            deletedBlocks.add(children.getFrontFile().delete());
        while(children.hasDirectories())
            deletedBlocks.addAll(children.getFrontDirectory().delete());
        parent.removeDirectory(this);
        return deletedBlocks;
    }
}
