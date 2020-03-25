import java.util.ArrayList;
import java.util.List;

public class Directory extends FileSystemComponent {
    List<FileSystemComponent> children;

    public Directory(String path)
    {
        super(path);
        children = new ArrayList<>();
    }

    public Directory(String path, int level)
    {
        super(path, level);
        children = new ArrayList<>();
    }

    @Override
    public void print()
    {
        printInfo();
        System.out.println("/");
        for (FileSystemComponent child : children)
            child.print();
    }

    public boolean createFile(String path, AllocatedBlocks blocks) // todo return error number
    {
        String[] splittedPath = path.split(DirectoryStructure.DELIMETER);
        if (splittedPath.length == 1)
        {
            if (getChildFile(path) != null)
                return false;
            FileSystemComponent file = new File(path, this.level + 1, blocks);
            children.add(file);
            return true;
        }
        Directory next = getChildDirectory(splittedPath[0]);
        if (next == null)
            return false;
        return next.createFile(Utility.join(splittedPath, DirectoryStructure.DELIMETER), blocks);
    }

    public boolean createDirectory(String path)
    {
        String[] splittedPath = path.split(DirectoryStructure.DELIMETER);
        if (splittedPath.length == 1)
        {
            if (getChildDirectory(path) != null)
                return false;
            FileSystemComponent dir = new Directory(path, this.level + 1);
            children.add(dir);
            return true;
        }
        Directory next = getChildDirectory(splittedPath[0]);
        if (next == null)
            return false;
        return next.createDirectory(Utility.join(splittedPath, DirectoryStructure.DELIMETER));
    }

    public AllocatedBlocks deleteFile(String path)
    {
        String[] splittedPath = path.split(DirectoryStructure.DELIMETER);
        if (splittedPath.length == 1)
        {
            File file = removeChild(path);
            if (file == null)
                return null;
            return file.getAllocatedBlocks();
        }
        Directory next = getChildDirectory(splittedPath[0]);
        if (next == null)
            return null;
        return next.deleteFile(Utility.join(splittedPath, DirectoryStructure.DELIMETER));

    }

    File removeChild(String path)
    {
        int i = 0;
        for (FileSystemComponent item : children)
        {
            if (item.name.equals(path))
            {
                File f = (File)item;
                children.remove(i);
                return f;
            }
            ++i;
        }
        return null;
    }

    Directory getChildDirectory(String name)
    {
        for (FileSystemComponent item : children)
            if (item.name.equals(name))
                return (Directory)item;
        return null;
    }

    File getChildFile(String name)
    {
        for (FileSystemComponent item : children)
            if (item.name.equals(name))
                return (File)item;
        return null;

    }
}
