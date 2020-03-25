public class DiskStatus {
    int n;
    boolean[] isAllocated;

    DiskStatus(int n)
    {
        this.n = n;
        isAllocated = new boolean[n];
    }

    /*
    Returns block index of the first block in a contigous free space of 'count' blocks
    Returns -1 if no such space exists
     */
    int getContigousBlocks(int count)
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
    int getFreeBlock()
    {
        for (int i = 0; i < n; ++i)
        {
            if (!isAllocated[i])
                return i;
        }
        return -1;
    }


    boolean isBlockFree(int i)
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
    boolean allocate(int i)
    {
        if (isBlockFree(i))
        {
            isAllocated[i] = true;
            return true;
        }
        return false;
    }

    boolean allocateContigousBlocks(int start, int count)
    {
        if (isContigousBlocksFree(start, count))
        {
            for (int i = start, en = start + count; i < start + count; ++i)
                allocate(i);
            return true;
        }
        return false;
    }
}
