package Controllers.Indexed;

import Controllers.Iterator;

public class IndexedIterator implements Iterator
{
    int[] indexBlocks, dataBlocks;
    int blockSize;
    int curData = 0, curIndex = 0;
    boolean takeIndex = true;
    public IndexedIterator(int[] indexBlocks, int[] dataBlocks)
    {
        super();
        this.indexBlocks = indexBlocks;
        this.dataBlocks = dataBlocks;
        blockSize = (dataBlocks.length + indexBlocks.length - 1)/indexBlocks.length; // ceil division
    }

    @Override
    public Boolean hasNext()
    {
        return curData < dataBlocks.length;
    }

    @Override
    public Object getNext()
    {
        if (takeIndex)
        {
            takeIndex = false;
            return indexBlocks[curIndex++];
        }
        if ((curData + 1)%blockSize == 0)
            takeIndex = true;
        return dataBlocks[curData++];
    }
}
