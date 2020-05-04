package cover;

import java.util.ArrayList;
import java.util.Scanner;

public class Interactive {

    //klasa z interfejsem czytania wejscia i wypisywania wyniku

    //Zwraca false jesli nie przeczytalo zadnego wejscia, true w przeciwnym wypadku
    public static boolean read(DataWrapper data, Scanner scanner)
    {
        int input;

        while(scanner.hasNextInt())
        {
            input = scanner.nextInt();

            if(input > 0)
                makeSet(data.getFamily(), scanner, input); //tworzymy zbior, az nie napotkamy 0

            else if(input < 0)
            {
                data.makeTargetSet(1, 1, -input);

                input = scanner.nextInt();

                data.createSolution(input);

                return true;
            }

            else if(input == 0)
                data.getFamily().createEmptySet();

        }

        return false;
    }

    //tworzy nowy zbior w rodzinie r
    private static void makeSet(SetFamily r, Scanner scanner, int input)
    {
        r.createEmptySet();
        int a, b, c;

        while(scanner.hasNextInt())
        {
            if(input != 0) //przeczytalismy skladnik a wczesniej / dopiero weszlismy do funckji
            {
                a = input;
                input = 0;
            }
            else
                a = scanner.nextInt(); //nie przeczytalismy wczesniej a / poprzedni stworzony zbiory byl obiektem FiniteSet

            if(a == 0)
                return;

            b = scanner.nextInt();

            if(b >= 0) //d d|0
                r.addToLast(new ElementSet(a));

            if(b == 0)//d 0
                return;
            if(b > 0)//d d
            {
                input = b;
                continue;
            }


            c = scanner.nextInt();

            if(c >= 0)//d u d|0
                r.addToLast(new InfiniteSet(a, -b));

            if(c == 0)//d u 0
                return;
            if(c > 0)//d u d
            {
                input = c;
                continue;
            }

            if(a <= -c)//d u u
                r.addToLast(new FiniteSet(a, -b, -c));
        }
    }

    public static void printSets(ArrayList<Integer> setNumerals)
    {
        String rString = new String();

        for(Integer x: setNumerals)
            rString += x + " ";
        rString = rString.substring(0, rString.length() - 1); //usuwamy " " z konca stringa

        System.out.println(rString);
    }
}
