package different;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main(String[] arg) {
        String[] langth = {"Java", "C#", "Python", "PHP"};;


        List<String> languadges = new ArrayList<String>();
        List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");
        for (String l : languages ) {
            System.out.println("I want learn " + l);
        }

    }
}
