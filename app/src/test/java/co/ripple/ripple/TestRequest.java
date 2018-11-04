package co.ripple.ripple;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestRequest{
    @Test
    public void testSerialization() {
        Request R = new Request(4, "HW Help", "I need help with problem 5!");
        String serialized = R.serialize();
        System.out.println(serialized);
    }

    @Test
    public void testDeserialization() {

    }
}