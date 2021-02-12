package ru.stqa.rft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EquationTests {

    @Test
    public void tests0() {
        Equation e = new Equation(1,1,1);
        Assert.assertEquals(e.rootNumber(), 0);
    }
    @Test
    public  void tests1(){
        Equation e = new Equation(1,2,1);
        Assert.assertEquals(e.rootNumber(), 1);
    }
    @Test
    public void tests2(){
        Equation e = new Equation(1,5,6);
        Assert.assertEquals(e.rootNumber(), 2);
    }
    @Test
    public void testLiner(){
        Equation e = new Equation(0,1,1);
        Assert.assertEquals(e.rootNumber(), 1);
    }
    @Test
    public void testConstant() {
        Equation e = new Equation(0, 0, 1);
        Assert.assertEquals(e.rootNumber(), 0);
    }
    @Test
    public void testZero() {
        Equation e = new Equation(0, 0, 0);
        Assert.assertEquals(e.rootNumber(), -1);
    }
}
