import java.util.ArrayList;

public class DirectoryStructure {
    Directory root;
    static String ROOT_NAME = "root", DELIMETER = "/";
    DirectoryStructure()
    {
        root = new Directory(ROOT_NAME);
    }

    public void showStructure()
    {
        root.print();
    }

    public boolean createFile(String path, AllocatedBlocks blocks)
    {
        String[] sPath = path.split(DELIMETER);
        if (!sPath[0].equals(ROOT_NAME))
            return false;
        if (root.getFile(Utility.subarray(sPath, 1, sPath.length - 1)) != null) // already exists
            return false;
        Directory dir = root.getDirectory(Utility.subarray(sPath, 1, sPath.length - 2));
        if (dir == null)
            return false;
        File f = new File(Utility.back(sPath), dir.getLevel() + 1, blocks, dir);
        dir.addFile(f);
        return true;
    }

    public boolean createDirectory(String path)
    {
        String[] sPath = path.split(DELIMETER);
        if (!sPath[0].equals(ROOT_NAME))
            return false;
        if (root.getDirectory(Utility.subarray(sPath, 1, sPath.length - 1)) != null) // already exists
            return false;
        Directory dir = root.getDirectory(Utility.subarray(sPath, 1, sPath.length - 2));
        if (dir == null)
            return false;
        Directory childDir = new Directory(Utility.back(sPath), dir.getLevel() + 1, dir);
        dir.addDirectory(childDir);
        return true;
    }
}
