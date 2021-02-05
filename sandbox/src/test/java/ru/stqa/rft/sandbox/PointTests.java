package ru.stqa.rft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistanse(){
        Point p1 = new Point( 3, 3);
        Point p2 = new Point(1, 8);
        Assert.assertEquals(p1.distance(p2), 5.385164807134504);

        Point a1 = new Point(4, -8);
        Point a2 = new Point(-5,12);
        Assert.assertEquals(a1.distance(a2), 21.93171219946131);

        Point q1 = new Point(0,0);
        Point q2 = new Point(0,0);
        Assert.assertEquals(q1.distance(q2), 0.0);

        Point f1 = new Point(-1, -1);
        Point f2 = new Point(-1, -1);
        Assert.assertEquals(f1.distance(f2), 0.0);


    }
}
