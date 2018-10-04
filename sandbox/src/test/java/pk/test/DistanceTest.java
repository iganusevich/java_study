package pk.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTest {

    
    @Test
    public void DistanceTest(){
        pk.test.Point p1 =  new pk.test.Point(0,0);
        pk.test.Point p2 =  new pk.test.Point(3,4);
        Assert.assertEquals(p1.distance(p1,p2), 5.0);


    }

}
