package different;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

public class DistanceTests {
    @Test
    public void testDistabce(){
    Point p1 = new Point(0, 0);
    Point p2 = new Point(3, 4);
    Assert.assertEquals(Point.distance(p1,p2), 5.0);
    }
    
}
