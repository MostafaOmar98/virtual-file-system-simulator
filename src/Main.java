import Controllers.Contiguous.ContiguousController;
import Controllers.DiskController;
import Controllers.DiskStatus;
import Controllers.Indexed.IndexedController;
import DirectoryStructurePackage.DirectoryStructure;
import MainPack.Interpreter;
import MainPack.VirtualFileSystem;

import java.util.Scanner;

public class Main {

    /*
    TODO:
    -saving and loading this to file
    -The space thing
    -Removing root bug
    -back to display structure
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DiskStatus st = new DiskStatus(32);
        DiskController dc = new IndexedController(st);
        DirectoryStructure ds = new DirectoryStructure();

        VirtualFileSystem vfs = new VirtualFileSystem(ds, dc);
        while(true)
        {
            String cmd;
            cmd = sc.nextLine();
            Interpreter interpreter = new Interpreter(cmd, vfs);
            interpreter.run();
        }
    }
}
