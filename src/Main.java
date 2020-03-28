import Controllers.ContiguousController;
import Controllers.DiskController;
import Controllers.DiskStatus;
import DirectoryStructurePackage.DirectoryStructure;
import MainPack.VirtualFileSystem;

public class Main {

    /*
    TODO:
    -saving and loading this to file
    -interpreter
     */
    public static void main(String[] args) {
        DiskStatus st = new DiskStatus(32);
        DiskController dc = new ContiguousController(st);
        DirectoryStructure ds = new DirectoryStructure();

        VirtualFileSystem fs = new VirtualFileSystem(ds, dc);
        fs.createDirectory("root/dir1");
        fs.createDirectory("root/dir1/dir2");
        fs.createFile("root/file1.txt", 5);
        fs.createFile("root/dir1/file1.txt", 5);
        fs.createFile("root/dir1/file1.txt", 5);
        fs.createFile("root/dir1/file2.txt", 5);
        fs.createFile("root/dir1/file3.txt", 5);
        fs.createFile("root/dir1/dir2/file3.txt", 5);
        fs.createFile("root/dir1/dir2/file4.txt", 5);
        fs.createFile("root/dir1/dir2/file4.txt", 5);
        fs.createFile("root/dir1/dir2/file5.txt", 5);
        fs.showStructure();
        fs.showStatus();
        System.out.println();

        fs.deleteDirectory("root/file1.txt");

//        fs.deleteDirectory("root/"); // return error

        fs.showStructure();
        fs.showStatus();
        System.out.println();

    }
}
