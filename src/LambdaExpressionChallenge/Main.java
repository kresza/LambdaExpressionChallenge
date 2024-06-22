package LambdaExpressionChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        String[] names = {"Anna", "Bob", "Tim", "Eddy"};
        Arrays.setAll(names, i-> names[i].toUpperCase());
        String[] randomlist = randomSelectedValues(6,
                names,
                () -> new Random().nextInt(0, names.length));


        Arrays.setAll(randomlist, (i) -> randomlist[i] + " " +
                (char) (new Random().nextInt(26) + 'a') + "." +
                " " + getReversedName(randomlist[i]));


        System.out.println(Arrays.toString(randomlist));
        System.out.println("-----LIST----");
        List<String> list = Arrays.asList(randomlist);
        list.forEach(s -> System.out.println(s));

        List<String> finalList = new ArrayList<>(List.of(randomlist));
//        finalList.removeIf(s -> s.substring(0,s.indexOf(" ")).equals(s.substring(s.lastIndexOf(" ") + 1)));
        finalList.removeIf(s -> {
           String first = s.substring(0,s.indexOf(" "));
           String last = s.substring(s.lastIndexOf(" ") + 1);
           return first.equals(last);
        });

        System.out.println(finalList);

        System.out.println("Function for random char");
        System.out.println(getRandomChar('A','C'));
    }

    public static char getRandomChar(char startChar, char endChar){
        return (char) new Random().nextInt((int) startChar, (int) endChar +1);
    }
    public static String[] randomSelectedValues(int count,
                                                String[] values,
                                                Supplier<Integer> supplier){
        String[] selectedValues = new String[count];
        for (int i = 0; i < count; i++) {
            selectedValues[i] = values[supplier.get()];
        }
        return selectedValues;
    }

    public static String getReversedName(String name){
        return new StringBuilder().append(name).reverse().toString();

    }
}
