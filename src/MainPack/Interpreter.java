package MainPack;

import UtilityPackage.Utility;

public class Interpreter {
    String[] args;
    String cmd;
    Interpreter(String command)
    {
        args = command.split(" ");
        cmd = args[0];
        args = Utility.subarray(args, 1, args.length - 1);
    }

//    public VFSError run()
//    {
//        ICommand command = null;
//        switch(cmd)
//        {
//            case "CreateFile":
//                command = new CreateFileCommand(args);
//                break;
//            case "DeleteFile":
//                command = new DeleteFileCommand(args);
//                break;
//            case "CreateFolder":
//                command = new CreateFolderCommand(args);
//                break;
//            case "DeleteFolder":
//                command = new DeleteFolderCommand(args);
//                break;
//            default:
//                System.out.println("Invalid Command");
//                return VFSError.INV_COMMAND;
//                break;
//        }
//        if (command != null)
//            return command.execute();
//        return VFSError.OK;
//    }
}
