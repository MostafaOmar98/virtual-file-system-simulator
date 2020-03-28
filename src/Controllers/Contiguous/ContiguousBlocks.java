package Controllers.Contiguous;

import Controllers.AllocatedBlocks;
import Controllers.Iterator;

public class ContiguousBlocks implements AllocatedBlocks
{
    int start, count;

    ContiguousBlocks(int start, int count)
    {
        this.start = start;
        this.count = count;
    }

    @Override
    public Iterator getIterator()
    {
        return new ContiguousIterator(start, count);
    }
}
