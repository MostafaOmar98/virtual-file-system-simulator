import java.util.ArrayList;
import java.util.List;

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

    private String[] splitPath(String path)
    {
        String[] sPath = path.split(DELIMETER);
        if (!sPath[0].equals(ROOT_NAME))
            return null;
        return sPath;
    }

    private boolean fileExists(String[] sPath)
    {
        return root.getFile(Utility.subarray(sPath, 1, sPath.length - 1)) != null; // already exists
    }

    private boolean directoryExists(String[] sPath)
    {
        return root.getDirectory(Utility.subarray(sPath, 1, sPath.length - 1)) != null; // already exists
    }

    public boolean createFile(String path, AllocatedBlocks blocks)
    {
        String[] sPath = splitPath(path);
        if (sPath == null)
            return false;

        if (fileExists(sPath)) // already exists
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
        String[] sPath = splitPath(path);
        if (sPath == null)
            return false;

        if (directoryExists(sPath)) // already exists
            return false;

        Directory dir = root.getDirectory(Utility.subarray(sPath, 1, sPath.length - 2));
        if (dir == null)
            return false;
        Directory childDir = new Directory(Utility.back(sPath), dir.getLevel() + 1, dir);
        dir.addDirectory(childDir);
        return true;
    }

    public AllocatedBlocks deleteFile(String path)
    {
        String[] sPath = splitPath(path);
        if (sPath == null)
            return null;

        File f = root.getFile(Utility.subarray(sPath, 1, sPath.length - 1));
        if (f == null)
            return null;
        return f.delete();
    }

    public List<AllocatedBlocks> deleteDirectory(String path)
    {
        String[] sPath = splitPath(path);
        if (sPath == null)
            return null;

        Directory dir = root.getDirectory(Utility.subarray(sPath, 1, sPath.length - 1));
        if (dir == null)
            return null;
        return dir.delete();
    }

}
