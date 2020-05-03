package cover;

public class ElementSet extends Set
{
    int element;

    public ElementSet(int element)
    {
        this.element = element;
    }

    public int getElement(int index)
    {
        if(index == 1)
            return element;
        return -1;
    }

    public String toString()
    {
        return  "[" + element + "]";
    }
}
