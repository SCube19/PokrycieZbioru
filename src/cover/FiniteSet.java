package cover;

public class FiniteSet extends Set
{
    private int first;
    private int step;
    private int limit;

    public FiniteSet(int first, int step, int limit)
    {
        this.first = first;
        this.step = step;
        this.limit = limit;
    }

    public int getElement(int index)
    {
        int rVal = first + index * step;

        if(rVal <= limit)
            return rVal;

        return -1;
    }

    public boolean checkForExistence(int element)
    {
        return (element >= first && element <= limit && (element - first)%step == 0);
    }

    public int getLimit()
    {
        return limit;
    }

}
