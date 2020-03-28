package Commands;
import MainPack.VirtualFileSystem;
import UtilityPackage.Utility;
import UtilityPackage.VFSError;

public class CreateFolderCommand implements ICommand
{
    private String[] args;
    VirtualFileSystem vfs;
    VFSError error;

    public CreateFolderCommand(String[] args, VirtualFileSystem vfs)
    {
        this.args = args;
        this.vfs = vfs;
        error = VFSError.OK;
    }

    private void verify()
    {
        if (args.length != 1)
        {
            error = VFSError.NUM_ARGUMENTS;
            return;
        }
    }

    @Override
    public VFSError execute()
    {
        verify();
        if (error == VFSError.OK)
            error = vfs.createDirectory(args[0]);
        Utility.printError(error);
        return error;
    }
}
