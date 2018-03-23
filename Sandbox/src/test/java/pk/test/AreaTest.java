package pk.test;

import org.testng.annotations.Test;

public class AreaTest {
    @Test
    public void testArea(){
        Square s = new Square(5);
        assert s.area() == 25;
        
        
    }
}
