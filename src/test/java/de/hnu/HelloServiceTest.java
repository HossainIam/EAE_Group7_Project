package de.hnu;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

public class HelloServiceTest {
    private HelloService out;

    @Before
    public void setup() {
        out = new HelloService();
    }

    @Test
    public void testSayHello() {
        String r = out.sayHello("Susi");
        assertTrue("Hello, Susi Susi".equals(r));
    }

    @Test
    public void testSayHelloNull() {
        String r = out.sayHello(null);
        assertTrue(r == null);
    }

    
    @Test
    public void testSum1() {
        int sum = out.sumUp(2,3);
        assertTrue(sum == 5);
    }

    @Test
    public void testSum2() {
        int sum = out.sumUp(2,-1);
        assertTrue(sum == 0);
    }

    @Test
    public void testSum3() {
        int sum = out.sumUp(-1,3);
        assertTrue(sum == 0);
    }

    @Test
    public void testSum4() {
        int sum = out.sumUp(-1,-1);
        assertTrue(sum == 0);
    }
}
