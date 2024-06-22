package MethodReferenceChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;

public class Main {
    private record Person(String first){
        public String last(String s){
            return first + " " + s.substring(0,s.indexOf(" "));
        }
    }
    public static void main(String[] args) {
        String[] names = {"Tim", "Bob", "Eddy", "Allie"};

        Person Tim = new Person("Tim");
        List<UnaryOperator<String>> list = new ArrayList<>(List.of(
           String::toUpperCase,
                s-> s += " " + getRandomCHar('D', 'M') + ".",
                s -> s += " " + reverse(s,0,s.indexOf(" ")),
                Main::reverse,
                String::new,
//                s-> new String("--> " + s),
                String::valueOf,
                Tim::last,
                (new Person("Marry"))::last
        ));

        applyChanges(names,list);
    }

    private static void applyChanges(String[] names,
                                     List<UnaryOperator<String>> stringFunctions){

        List<String> backedByArray = Arrays.asList(names); // tworzymy liste z imion
        for(var function: stringFunctions){ // iterujemy po funkcjach przekazanych w argumencie stringFunctions
            backedByArray.replaceAll(s-> s.transform(function));
            System.out.println(Arrays.toString(names));
        }

    }

    private static char getRandomCHar(char startChar, char endChar){
        return (char) new Random().nextInt((int) startChar, (int) endChar+1);
    }

    private static String reverse(String s){
        return reverse(s,0,s.length());
    }

    private static String reverse(String s, int start, int end){
        return new StringBuilder(s.substring(start, end)).reverse().toString();
    }
}
