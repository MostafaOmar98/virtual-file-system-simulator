package Controllers;

public abstract class DiskController {
    protected DiskStatus diskStatus;

    public DiskController(DiskStatus diskStatus)
    {
        this.diskStatus = diskStatus;
    }

    public String getBitmap()
    {
        return diskStatus.toString();
    }

    public abstract AllocatedBlocks allocate(int size);

    public void free(AllocatedBlocks blocks)
    {
        Iterator it = blocks.getIterator();
        while(it.hasNext())
            diskStatus.free((Integer) it.getNext());
    }

    public void showStatus()
    {
        diskStatus.showStatus();
    }
}
