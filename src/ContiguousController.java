public class ContiguousController extends DiskController {

    @Override
    public AllocatedBlocks allocate(int size) {
        int start = diskStatus.getContigousBlocks(size);
        if (start == -1)
            return null;
        diskStatus.allocateContigousBlocks(start, size);
        AllocatedBlocks blocks = new ContiguousBlocks(start, size);
        return blocks;
    }
}
