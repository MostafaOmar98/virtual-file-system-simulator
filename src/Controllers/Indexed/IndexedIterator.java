package Controllers.Indexed;

import Controllers.Iterator;

public class IndexedIterator implements Iterator
{
    int[] indexBlocks, dataBlocks;
    int blockSize;
    int curData = 0, curIndex = 0;
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
        if (curData%blockSize == 0)
            return indexBlocks[curIndex++];
        return dataBlocks[curData++];
    }
}
