package Controllers.Indexed;

import Controllers.AllocatedBlocks;
import Controllers.Iterator;

public class IndexedBlocks implements AllocatedBlocks
{
    int[] indexBlocks, dataBlocks;
    public IndexedBlocks(int[] indexBlocks, int[] dataBlocks)
    {
        this.indexBlocks = indexBlocks;
        this.dataBlocks = dataBlocks;
    }

    @Override
    public Iterator getIterator()
    {
        return new IndexedIterator(indexBlocks, dataBlocks);
    }
}
