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
