package addressbook.tests;

import addressbook.models.GroupData;
import addressbook.models.Groups;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test
    public void testGroupCreationTests() {
        app.goTo().groups();
        Groups before = app.groups().all();
        GroupData group = new GroupData().withName("test1").withHeader("logo").withFooter("footer");
        app.groups().create(group);
        Groups after = app.groups().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

}