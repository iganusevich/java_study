package addressbook;


import org.testng.annotations.Test;


public class GroupDeletionTests extends TestBase{


    @Test
    public void  testGroupDeletion (){
        
       
        goToGroupPage();
        selectGroup();
        deleteSelectedGroup();
        returnToGroupPage();
    }


}
