package cover;

public class InfiniteSet extends Set
{
    private int first;
    private int step;

    public InfiniteSet(int first, int step)
    {
        this.first = first;
        this.step = step;
    }

    public boolean checkForExistence(int element)
    {
        return (element >= first && (element - first)%step == 0);
    }

}
