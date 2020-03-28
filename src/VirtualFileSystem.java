import java.util.List;

public class VirtualFileSystem
{
    private DirectoryStructure directoryStructure;
    private DiskController diskController;

    VirtualFileSystem(DirectoryStructure directoryStructure, DiskController diskController)
    {
        this.directoryStructure = directoryStructure;
        this.diskController = diskController;
    }

    public void showStatus()
    {
        diskController.showStatus();
    }

    public boolean createFile(String path, int size) // todo make it return error number
    {
        AllocatedBlocks blocks = diskController.allocate(size);
        if (blocks == null) // no enough space
            return false;
        if (!directoryStructure.createFile(path, blocks)) // no such directory
        {
            diskController.free(blocks);
            return false;
        }
        return true;
    }

    public boolean createDirectory(String path)
    {
        return directoryStructure.createDirectory(path);
    }

//    public boolean deleteFile(String path) // has bug when file has the same name as directory
//    {
//        AllocatedBlocks blocks = directoryStructure.deleteFile(path);
//        if (blocks != null)
//        {
//            diskController.free(blocks);
//            return true;
//        }
//        return false;
//    }

//    public boolean deleteDirectory(String path)
//    {
//        List<AllocatedBlocks> blocksList = directoryStructure.deleteDirectory(path);
//        if (blocksList != null)
//        {
//            for (AllocatedBlocks blocks : blocksList)
//                diskController.free(blocks);
//            return true;
//        }
//        return false;
//    }


    public void showStructure()
    {
        directoryStructure.showStructure();
    }


}
