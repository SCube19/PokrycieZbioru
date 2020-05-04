package cover;

public class ElementSet extends Set
{
    private int element;

    public ElementSet(int element)
    {
        this.element = element;
    }

    public boolean checkForExistence(int element)
    {
        return (this.element == element);
    }

}
