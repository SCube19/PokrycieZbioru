package cover;
import java.util.ArrayList;
import java.util.Scanner;

public class Interactive {

    public static boolean read(DataWrapper data, Scanner scanner)
    {
        int input;

        while(scanner.hasNextInt())
        {
            input = scanner.nextInt();

            if(input > 0)
            {
                //System.out.println("I've read: " + input);
                makeSet(data.getFamily(), scanner, input);
            }

            else if(input < 0)
            {
                //System.out.println("Going out by: " + input);
                data.makeTargetSet(1, 1, -input);
                input = scanner.nextInt();
              //  System.out.println("And: " + input);

                data.createSolution(input);

                return true;
            }

            else if(input == 0)
            {
              // System.out.println("Empty set detected: " + input);
                data.getFamily().createEmptySet();
            }

        }
        //System.out.println("has no next");
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
            //System.out.println("ElementSet for now or 0: " + a);
            if(a == 0)
                return;

            b = scanner.nextInt();
            //System.out.println("InfiniteSet for now or 0: " + b);
            if(b >= 0)
                r.addToLast(new ElementSet(a));

            if(b == 0)
                return;
            if(b > 0)
            {
                input = b;
                continue;
            }


            c = scanner.nextInt();
           // System.out.println("FiniteSet or 0: " + c);
            if(c >= 0)
                r.addToLast(new InfiniteSet(a, -b));

            if(c == 0)
                return;
            if(c > 0)
            {
                input = c;
                continue;
            }

            if(a <= -c)
                r.addToLast(new FiniteSet(a, -b, -c));
        }
    }

    public static void printSets(ArrayList<Integer> setNumerals)
    {
       // System.out.println("printing solution");
        String rString = new String();

        for(Integer x: setNumerals)
            rString += x + " ";

        rString = rString.substring(0, rString.length() - 1);
        System.out.println(rString);
    }
}
