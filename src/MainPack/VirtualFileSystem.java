package MainPack;

import Controllers.AllocatedBlocks;
import Controllers.DiskController;
import DirectoryStructurePackage.DirectoryStructure;
import UtilityPackage.VFSError;

import java.util.List;

public class VirtualFileSystem
{
    private DirectoryStructure directoryStructure;
    private DiskController diskController;

    public VirtualFileSystem(DirectoryStructure directoryStructure, DiskController diskController)
    {
        this.directoryStructure = directoryStructure;
        this.diskController = diskController;
    }

    public void showStatus()
    {
        diskController.showStatus();
    }

    public VFSError createFile(String path, int size) // todo make it return error number
    {
        AllocatedBlocks blocks = diskController.allocate(size);
        if (blocks == null) // no enough space
            return VFSError.NO_SPACE;
        if (directoryStructure.createFile(path, blocks) != VFSError.OK) // no such directory
        {
            diskController.free(blocks);
            return VFSError.FOLDER_NOT_EXIST;
        }
        return VFSError.OK;
    }

    public VFSError createDirectory(String path)
    {
        return directoryStructure.createDirectory(path);
    }

    public VFSError deleteFile(String path) // has bug when file has the same name as directory
    {
        AllocatedBlocks blocks = directoryStructure.deleteFile(path);
        if (blocks != null)
        {
            diskController.free(blocks);
            return VFSError.OK;
        }
        return VFSError.FILE_NOT_EXIST;
    }

    public VFSError deleteDirectory(String path)
    {
        List<AllocatedBlocks> blocksList = directoryStructure.deleteDirectory(path);
        if (blocksList != null)
        {
            for (AllocatedBlocks blocks : blocksList)
                diskController.free(blocks);
            return VFSError.OK;
        }
        return VFSError.FOLDER_NOT_EXIST;
    }


    public void showStructure()
    {
        directoryStructure.showStructure();
    }


}
