package co.ripple.ripple;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestRequest{
    @Test
    public void testSerialization() {
        Request R = new Request(4, "HW Help", "I need help with problem 5!");
        String serialized = R.serialize();
        Request D = new Request(serialized);
        assertEquals(R.getChannelId(), D.getChannelId());
        assertEquals(R.getTitle(), D.getTitle());
        assertEquals(R.getBody(), D.getBody());
        assertEquals(R.getSenderAddr(), D.getSenderAddr());
        assertEquals(R.getSenderPort(), D.getSenderPort());
    }

    @Test
    public void testDeserialization() {

    }
}