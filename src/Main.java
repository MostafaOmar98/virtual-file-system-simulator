public class Main {

    public static void main(String[] args) {
        DiskStatus st = new DiskStatus(32);
        DiskController dc = new ContiguousController(st);
        DirectoryStructure ds = new DirectoryStructure();

        VirtualFileSystem fs = new VirtualFileSystem(ds, dc);
        fs.showStructure();
        fs.showStatus();
        fs.createFile("root/firstFile", 5);
        fs.createFile("root/firstFile", 5);
        fs.createFile("root/firstFile", 5);
        fs.createFile("root/firstFile", 5);
        fs.showStructure();
        fs.showStatus();
    }
}
