public abstract class DiskController {
    DiskStatus diskStatus;
    public abstract AllocatedBlocks allocate(int size);

    public void free(AllocatedBlocks blocks)
    {
        Iterator it = blocks.getIterator();
        while(it.hasNext())
            diskStatus.free((Integer) it.getNext());
    }

}
