package addressbook.tests;

import addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationTests() {
        app.goTo().groups();
        List<GroupData> before = app.groups().list();
        GroupData group = new GroupData("test1", "logo", "footer");
        app.groups().create(group);

        List<GroupData> after = app.groups().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.setId(after.stream().max((o1, o2)->Integer.compare(o1.getId(),o2.getId())).get().getId());
        before.add(group);

        Comparator<? super GroupData> byId = (g1,g2) -> Integer.compare(g1.getId(),g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }

}