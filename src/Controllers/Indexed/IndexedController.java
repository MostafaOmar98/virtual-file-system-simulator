package Controllers.Indexed;

import Controllers.AllocatedBlocks;
import Controllers.DiskController;
import Controllers.DiskStatus;

import java.util.Arrays;

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
        Arrays.fill(indexBlocks, -1);
        Arrays.fill(dataBlocks, -1);
        boolean ok = true;
        for (int i = 0; i < countIndices && size > 0; ++i)
        {
            int indexBlock = diskStatus.getFreeBlock();
            if (indexBlock == -1)
            {
                ok = false;
                break;
            }
            indexBlocks[i] = indexBlock;
            diskStatus.allocate(indexBlock);
            for (int j = 0; j < indexSize && size > 0; ++j, --size)
            {
                int dataBlock = diskStatus.getFreeBlock();
                if (dataBlock == -1)
                {
                    ok = false;
                    break;
                }
                dataBlocks[i * indexSize + j] = dataBlock;
                diskStatus.allocate(dataBlock);
            }
        }
        if (!ok)
        {
            for (int i = 0; i < indexBlocks.length; ++i)
            {
                if (indexBlocks[i] != -1)
                    diskStatus.free(indexBlocks[i]);
            }
            for (int i = 0; i < dataBlocks.length; ++i)
            {
                if (dataBlocks[i] != -1)
                    diskStatus.free(dataBlocks[i]);
            }
            return null;
        }
        return new IndexedBlocks(indexBlocks, dataBlocks);
    }
}
