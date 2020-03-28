package Controllers;

public class DiskStatus {
    private int n;
    private boolean[] isAllocated;
    private int BLOCK_SIZE = 1; // in KBs
    public DiskStatus(int n)
    {
        this.n = n;
        isAllocated = new boolean[n];
    }

    public int getN()
    {
        return n;
    }

    public boolean[] getIsAllocated()
    {
        return isAllocated;
    }

    public int getBlockSize()
    {
        return BLOCK_SIZE;
    }

    /*
        Returns block index of the first block in a contigous free space of 'count' blocks
        Returns -1 if no such space exists
         */
    public int getContigousBlocks(int count)
    {
        int i = 0;
        while(i < n)
        {
            if (!isAllocated[i])
            {
                int st = i;
                while(i < n && !isAllocated[i])
                    i++;
                if (i - st > count)
                    return st;
            }
            else
                i++;
        }
        return -1;
    }

    /*
    Returns index of first free block
    returns -1 if no free blocks exist
     */
    public int getFreeBlock()
    {
        for (int i = 0; i < n; ++i)
        {
            if (!isAllocated[i])
                return i;
        }
        return -1;
    }


    public boolean isBlockFree(int i)
    {
        return !isAllocated[i];
    }

    boolean isContigousBlocksFree(int start, int count)
    {
        boolean ok = start + count - 1 < n; // check enough space
        for (int i = start, en = Math.min(start + count, n); i < en; ++i)
            ok &= isBlockFree(i);
        return ok;
    }

    /*
    Allocates block at index i
    Returns true if block was initially free, false otherwise
     */
    public boolean allocate(int i)
    {
        if (isBlockFree(i))
        {
            isAllocated[i] = true;
            return true;
        }
        return false;
    }

    public boolean allocateContigousBlocks(int start, int count)
    {
        if (isContigousBlocksFree(start, count))
        {
            for (int i = start, en = start + count; i < start + count; ++i)
                allocate(i);
            return true;
        }
        return false;
    }

    public boolean free(int i)
    {
        if (isAllocated[i])
        {
            isAllocated[i] = false;
            return true;
        }
        return false;
    }

    public void showStatus()
    {
        System.out.print("Allocated Blocks: ");
        int countAlloc = 0;
        for (int i = 0; i < n; ++i)
        {
            if (!isBlockFree(i))
            {
                System.out.print(i + " ");
                countAlloc++;
            }
        }
        System.out.print("\nFree Blocks: ");
        for (int i = 0; i < n; ++i)
        {
            if(isBlockFree(i))
                System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("Allocated Space: " + (countAlloc * BLOCK_SIZE) + "KB");
        System.out.println("Free Space: " + ((n - countAlloc) * BLOCK_SIZE) + "KB");
    }
}
