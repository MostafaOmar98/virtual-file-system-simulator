package Controllers.Indexed;

import Controllers.AllocatedBlocks;
import Controllers.DiskController;
import Controllers.DiskStatus;

import java.util.ArrayList;

public class IndexedController extends DiskController
{
    int indexSize; // number of blocks it can save
    public IndexedController(DiskStatus diskStatus)
    {
        super(diskStatus);
        indexSize = diskStatus.getBlockSize() * 1024 / 4;
    }

    @Override
    public AllocatedBlocks allocate(int size)
    {
        int countIndices = (size + indexSize - 1)/indexSize; // ceil division
        int[] indexBlocks = new int[countIndices];
        int[] dataBlocks = new int[size];
        for (int i = 0; i < countIndices && size > 0; ++i)
        {
            int indexBlock = diskStatus.getFreeBlock();
            if (indexBlock == -1)
                return null;
            indexBlocks[i] = indexBlock;

            for (int j = 0; j < indexSize && size > 0; ++j, --size)
            {
                int dataBlock = diskStatus.getFreeBlock();
                if (dataBlock == -1)
                    return null;
                dataBlocks[i * indexSize + j] = dataBlock;
            }
        }
        return new IndexedBlocks(indexBlocks, dataBlocks);
    }
}
