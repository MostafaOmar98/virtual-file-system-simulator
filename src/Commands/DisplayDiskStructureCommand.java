package Commands;
import MainPack.VirtualFileSystem;
import UtilityPackage.Utility;
import UtilityPackage.VFSError;

public class DisplayDiskStructureCommand implements ICommand
{
    private String[] args;
    VirtualFileSystem vfs;
    VFSError error;

    public DisplayDiskStructureCommand(String[] args, VirtualFileSystem vfs)
    {
        this.args = args;
        this.vfs = vfs;
        error = VFSError.OK;
    }

    private void verify()
    {
        if (args.length != 0)
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
            vfs.showStructure();
        Utility.printError(error);
        return error;
    }
}
