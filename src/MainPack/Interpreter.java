package MainPack;

import UtilityPackage.Utility;
import UtilityPackage.VFSError;
import Commands.*;

public class Interpreter {
    String[] args;
    String cmd;
    VirtualFileSystem vfs;
    public Interpreter(String command, VirtualFileSystem vfs)
    {
        args = command.split(" ");
        cmd = args[0];
        args = Utility.subarray(args, 1, args.length - 1);
        this.vfs = vfs;
    }

    public VFSError run()
    {
        ICommand command = null;
        switch(cmd)
        {
            case "CreateFile":
                command = new CreateFileCommand(args, vfs);
                break;
            case "DeleteFile":
                command = new DeleteFileCommand(args, vfs);
                break;
            case "CreateFolder":
                command = new CreateFolderCommand(args, vfs);
                break;
            case "DeleteFolder":
                command = new DeleteFolderCommand(args, vfs);
                break;
            case "DisplayDiskStatus":
                command = new DisplayDiskStatusCommand(args, vfs);
                break;
            case "DisplayDiskStructure":
                command = new DisplayDiskStructureCommand(args, vfs);
                break;
            default:
                Utility.printError(VFSError.INV_COMMAND);
                return VFSError.INV_COMMAND;
        }
        return command.execute();
    }
}
