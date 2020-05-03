package cover;

public class ElementSet extends Set
{
    private int element;

    public ElementSet(int element)
    {
        this.element = element;
    }

    public int getElement(int index)
    {
        if(index == 0)
            return element;
        return -1;
    }

    public String toString()
    {
        return  "[" + element + "]";
    }

    public boolean checkForExistence(int element)
    {
        return (this.element == element);
    }

}
