package addressbook.tests;

import addressbook.models.GroupData;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationTests() {
        app.goTo().groups();
        Set<GroupData> before = app.groups().all();
        GroupData group = new GroupData().withName("test1").withHeader("logo").withFooter("footer");
        app.groups().create(group);
        Set<GroupData> after = app.groups().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        assertThat(after, equalTo(before));
    }

}