package com.equinity.tennis.points;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PointsTest {

    @Test
    public void getByName() {
        String love = Points.getRepresentationByValue(0);
        String fifteen = Points.getRepresentationByValue(1);
        String thirty = Points.getRepresentationByValue(2);
        String forty = Points.getRepresentationByValue(3);
        String advantage = Points.getRepresentationByValue(4);


        assertEquals("0", love);
        assertEquals("15", fifteen);
        assertEquals("30", thirty);
        assertEquals("40", forty);
        assertEquals("AD", advantage);
    }
}