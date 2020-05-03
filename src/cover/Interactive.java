package cover;
import java.util.Scanner;

public class Interactive {

    public static boolean read(FiniteSet z, SetFamily r, Solution x)
    {
        Scanner scanner = new Scanner(System.in);
        int input;
        while(scanner.hasNextInt())
        {
            input = scanner.nextInt();

            if(input > 0)
               makeSet(r, scanner, input);

            else if(input < 0)
            {
                z = new FiniteSet(1, 1, -input);
                input = scanner.nextInt();

                if(input == 1)
                    x = new Precise();
                else if(input == 2)
                    x = new Greedy();
                else
                    x = new Naive();

                return true;
            }

            else
                return false;
        }
        return false;
    }

    private static void makeSet(SetFamily r, Scanner scanner, int input)
    {
        r.createEmptySet();
        int a, b, c;

        while(scanner.hasNextInt())
        {
            if(input != 0)
            {
                a = input;
                input = 0;
            }
            else
                a = scanner.nextInt();

            if(a == 0)
                return;

            b = scanner.nextInt();
            if(b == 0)
            {
                r.addToLast(new ElementSet(a));
                return;
            }

            c = scanner.nextInt();
            if(c == 0)
            {
                r.addToLast(new InfiniteSet(a, b));
                return;
            }

            r.addToLast(new FiniteSet(a, b, c));
        }
    }

    public static void printSets(int[] setNumerals)
    {
        String rString = new String();

        for(int i = 0; i < setNumerals.length; i++)
            rString += setNumerals[i] + " ";

        System.out.println(rString);
    }
}
