package addressbook.tests;

import addressbook.models.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationTests() {
        app.goTo().groups();
        Set<GroupData> before = app.groups().all();
        GroupData group = new GroupData().withName("test1").withHeader("logo").withFooter("footer");
        app.groups().create(group);
        Set<GroupData> after = app.groups().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(after, before);
    }

}