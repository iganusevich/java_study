package mantis.tests;

import mantis.models.Issue;
import mantis.models.Project;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase{

    @Test
    public void testGetProjects() throws MalformedURLException, RemoteException, ServiceException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project p : projects) {
            System.out.println(p.getName());
        }

    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixed(3);
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("Summary1").withDescription("Test description1")
                .withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        Assert.assertEquals(issue.getSummary(), created.getSummary());
        
    }
}

