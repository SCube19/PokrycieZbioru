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

    public int getElement(int index)
    {
        return first + index * step;
    }

    public String toString()
    {
        String rString = "[ ";
        for(int i = 0; i < 3; i++)
            rString += (first + step*i) + ", ";
        rString += "...]";

        return rString;
    }

    public boolean checkForExistence(int element)
    {
        return (element >= first && (element - first)%step == 0);
    }

}
