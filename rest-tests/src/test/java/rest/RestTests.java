package rest;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;




public class RestTests {

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = getIssues();
        Issue newIssue = new Issue();
        int issueId = createIssue();
        Set<Issue> newIssues = getIssues();
        Assert.assertEquals(newIssues, oldIssues.add(newIssue.withId(issueId)));
        
    }



    private Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
                .returnContent().asString();
        return null;
    }

    private Executor getExecutor(){
        return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed","");
    }

    private int createIssue() {
        return 0;
    }
}
