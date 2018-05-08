package addressbook.generators;

import addressbook.models.ContactData;
import addressbook.models.GroupData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator  {
    @Parameter(names = "-c", description = "Group Count")
    public int count;
    @Parameter  (names = "-f", description = "Target File")
    public String file;
    @Parameter  (names = "-d", description = "Data Format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    protected void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if(format.equals("json")){
            saveJSON(contacts,new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }

    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for(int i = 0; i < count; i++){
            contacts.add(new ContactData().withFirstName(String.format("test %s", i))
                    .withLastName(String.format("LastName %s", i)).withCompany(String.format("Company %s", i))
                    .withAddress(String.format("Address %s", i)).withHome(String.format("123234 %s", i))
                    .withMobile(String.format("56756456334 %s", i)).withEmail(String.format("test%s@email.com", i)));
        }
        return contacts;
    }

    private void saveJSON(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        String json = gson.toJson(contacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }
}
