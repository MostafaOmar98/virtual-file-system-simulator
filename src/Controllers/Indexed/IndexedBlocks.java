package Controllers.Indexed;

import Controllers.AllocatedBlocks;
import Controllers.Iterator;

import java.util.ArrayList;

public class IndexedBlocks implements AllocatedBlocks
{
    int[] indexBlocks, dataBlocks;
    public IndexedBlocks(int[] indexBlocks, int[] dataBlocks)
    {
        this.indexBlocks = indexBlocks;
        this.dataBlocks = dataBlocks;
    }

    public IndexedBlocks(ArrayList<Integer> indexBlocks, ArrayList<Integer> dataBlocks)
    {
        this.indexBlocks = new int[indexBlocks.size()];
        this.dataBlocks = new int[dataBlocks.size()];
        for (int i = 0; i < indexBlocks.size(); ++i)
            this.indexBlocks[i] = indexBlocks.get(i);
        for (int i = 0; i < dataBlocks.size(); ++i)
            this.indexBlocks[i] = dataBlocks.get(i);
    }

    @Override
    public Iterator getIterator()
    {
        return new IndexedIterator(indexBlocks, dataBlocks);
    }
}
