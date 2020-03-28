package DirectoryStructurePackage;

import java.nio.file.Files;
import java.util.List;
import Controllers.AllocatedBlocks;
import UtilityPackage.Utility;
import UtilityPackage.VFSError;

public class DirectoryStructure {
    Directory root;
    static String ROOT_NAME = "root", DELIMETER = "/";
    public DirectoryStructure()
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

    boolean exists(String[] sPath)
    {
        return fileExists(sPath) || directoryExists(sPath);
    }

    public VFSError createFile(String path, AllocatedBlocks blocks)
    {
        String[] sPath = splitPath(path);
        if (sPath == null)
            return VFSError.FOLDER_NOT_EXIST;

        if (exists(sPath)) // already exists
            return VFSError.ALREADY_EXISTS;

        Directory dir = root.getDirectory(Utility.subarray(sPath, 1, sPath.length - 2));
        if (dir == null)
            return VFSError.FOLDER_NOT_EXIST;

        File f = new File(Utility.back(sPath), dir.getLevel() + 1, blocks, dir);
        dir.addFile(f);
        return VFSError.OK;
    }

    public VFSError createDirectory(String path)
    {
        String[] sPath = splitPath(path);
        if (sPath == null)
            return VFSError.FOLDER_NOT_EXIST;

        if (exists(sPath)) // already exists
            return VFSError.ALREADY_EXISTS;

        Directory dir = root.getDirectory(Utility.subarray(sPath, 1, sPath.length - 2));
        if (dir == null)
            return VFSError.FOLDER_NOT_EXIST;
        Directory childDir = new Directory(Utility.back(sPath), dir.getLevel() + 1, dir);
        dir.addDirectory(childDir);
        return VFSError.OK;
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

    public List<Directory> getDirs()
    {
        return root.getSubDirs();
    }

    public List<File> getFiles()
    {
        return root.getSubFiles();
    }
}
