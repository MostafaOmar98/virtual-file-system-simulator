import Controllers.Contiguous.ContiguousController;
import Controllers.DiskController;
import Controllers.DiskStatus;
import Controllers.Indexed.IndexedController;
import DirectoryStructurePackage.DirectoryStructure;
import MainPack.Interpreter;
import MainPack.VirtualFileSystem;
import PersistenceManager.FileManager;
import PersistenceManager.IndexedFileManager;

import java.util.Scanner;

public class Main {

    /*
    TODO:
    -saving and loading this to file
    -back to display structure
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Type of allocation?\n" +
                "1- Contiguous\n" +
                "2- Indexed\n" +
                "Choice: ");
        int choice;
        choice = sc.nextInt();
        sc.nextLine();
        String vsfFilePath = null;
        FileManager fm = null;
        VirtualFileSystem vfs = null;
        if (choice == 1)
        {
            //
        }
        else if (choice == 2)
        {
            vsfFilePath = "C:\\Users\\MostafaOmar\\IdeaProjects\\VirtualFileSystemSimulator\\src\\IndexedDiskStructure.vsf";
            fm = new IndexedFileManager(vsfFilePath, vfs);
        }

        fm.load();
        vfs = fm.getVirtualFileSystem();
        while(true)
        {
            String cmd;
            cmd = sc.nextLine();
            Interpreter interpreter = new Interpreter(cmd, vfs);
            interpreter.run();
            if (cmd.equals("quit"))
                break;
        }
        fm.save();
    }
}
