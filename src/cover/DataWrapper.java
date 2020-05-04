package cover;

public class DataWrapper
{
    //klasa przechowujaca aktualne dane uzywane w obliczeniu rozwiazania
    private SetFamily family;
    private FiniteSet targetSet;
    private Solution solution;

    public DataWrapper()
    {
        family = new SetFamily();
        targetSet = null;
        solution = null;
    }

    public void makeTargetSet(int first, int step, int limit)
    {
        targetSet = new FiniteSet(first, step, limit);
    }

    public void createSolution(int choice)
    {
        if(choice == 1)
            solution = new Precise();
        else if(choice == 2)
            solution = new Greedy();
        else
            solution = new Naive();

    }

    public SetFamily getFamily()
    {
        return family;
    }
    public FiniteSet getTargetSet()
    {
        return targetSet;
    }
    public Solution getSolution()
    {
        return solution;
    }


}
