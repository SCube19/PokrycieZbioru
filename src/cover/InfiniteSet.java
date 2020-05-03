package cover;

public class InfiniteSet extends Set
{
    int first;
    int step;

    public InfiniteSet(int first, int step)
    {
        this.first = first;
        this.step = step;
    }

    public int getElement(int index)
    {
        return first + (index-1)*step;
    }

    public String toString()
    {
        String rString = "[ ";
        for(int i = 0; i < 3; i++)
            rString += (first + step*i) + ", ";
        rString += "...]";

        return rString;
    }

}
