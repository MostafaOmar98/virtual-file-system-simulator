public abstract class DiskController {
    DiskStatus diskStatus;
    public abstract AllocatedBlocks allocate(int size);

    public void free(AllocatedBlocks blocks) {}

}
