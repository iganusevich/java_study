package training.selenium.generators;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import training.selenium.models.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


public class UserDataGenerator {

    public static void main(String args[]) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);
        List<User> users = generateUsers(count);
        saveJSON(users, file);

    }


    private static void saveJSON(List<User> users, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(users);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private static List<User> generateUsers(int count) {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < count; i++) {
            users.add(new User().withFirst_name(String.format("test %s", i))
                    .withLast_name(String.format("last name %s", i)).withEmail(String.format("test%s_random@test.com", i))
                    .withCountry("US").withPostal_code("12345").withState("CO")
                    .withPassword("QWE123QWE!"));
        }
        return users;
    }
}
