public class VirtualFileSystem {
    private DirectoryStructure directoryStructure;
    private DiskController diskController;

    VirtualFileSystem (DirectoryStructure directoryStructure, DiskController diskController)
    {
        this.directoryStructure = directoryStructure;
        this.diskController = diskController;
    }

    public void showStatus()
    {
        diskController.showStatus();
    }

    public void createFile(String path, int size)
    {
        diskController.allocate(size);
    }
}
