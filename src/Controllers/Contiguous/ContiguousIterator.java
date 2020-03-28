package Controllers.Contiguous;

import Controllers.Iterator;

public class ContiguousIterator implements Iterator
{
    int count, current, start;

    ContiguousIterator(int start, int count)
    {
        this.current = 0;
        this.start = start;
        this.count = count;
    }

    @Override
    public Boolean hasNext()
    {
        return current < count;
    }

    @Override
    public Integer getNext()
    {
        return (start + current++);
    }

}
