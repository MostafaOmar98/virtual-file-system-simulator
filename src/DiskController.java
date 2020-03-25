public abstract class DiskController {
    protected DiskStatus diskStatus;

    DiskController(DiskStatus diskStatus)
    {
        this.diskStatus = diskStatus;
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
