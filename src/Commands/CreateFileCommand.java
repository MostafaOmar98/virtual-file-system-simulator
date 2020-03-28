package Commands;
import MainPack.VirtualFileSystem;
import UtilityPackage.Utility;
import UtilityPackage.VFSError;

public class CreateFileCommand implements ICommand
{
    private String[] args;
    VirtualFileSystem vfs;
    VFSError error;

    public CreateFileCommand(String[] args, VirtualFileSystem vfs)
    {
        this.args = args;
        this.vfs = vfs;
        error = VFSError.OK;
    }

    private void verify()
    {
        if (args.length != 2)
        {
            error = VFSError.NUM_ARGUMENTS;
            return;
        }
        try{
            Integer.parseInt(args[1]);
        } catch(NumberFormatException e){
            error = VFSError.INV_ARGUMENT;
            return;
        }
    }

    @Override
    public VFSError execute()
    {
        verify();
        if (error == VFSError.OK)
            error = vfs.createFile(args[0], Integer.parseInt(args[1]));
        Utility.printError(error);
        return error;
    }
}
