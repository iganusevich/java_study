package pk.test;

import org.testng.Assert;
import org.testng.annotations.Test;


public class AreaTest {
    @Test
    public void testArea(){
        pk.test.Square s = new pk.test.Square(5);
        Assert.assertEquals(s.area(), 25.0);


    }
}
