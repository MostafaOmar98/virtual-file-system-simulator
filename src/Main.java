public class Main {

    public static void main(String[] args) {
        DiskStatus st = new DiskStatus(32);
        DiskController dc = new ContiguousController(st);
        DirectoryStructure ds = new DirectoryStructure();

        VirtualFileSystem fs = new VirtualFileSystem(ds, dc);
        fs.showStatus();
        fs.createFile("", 10);
        fs.showStatus();
        fs.createFile("", 5);
        fs.showStatus();
        fs.createFile("", 3);
        fs.showStatus();
    }
}
