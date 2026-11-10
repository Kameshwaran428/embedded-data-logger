package com.devops.datalogger;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SensorLoggerTest {

    private final List<SensorData> testData = Arrays.asList(
        new SensorData("10:00", 28.0, "Valid"),
        new SensorData("10:01", 30.0, "Valid"),
        new SensorData("10:02", 32.0, "Valid"),
        new SensorData("10:03", null, "Missing"),
        new SensorData("10:04", 35.0, "Valid")
    );

    @Test
    public void testCalculateMin() {
        assertEquals(28.0, SensorLogger.calculateMin(testData), 0.01);
    }

    @Test
    public void testCalculateMax() {
        assertEquals(35.0, SensorLogger.calculateMax(testData), 0.01);
    }

    @Test
    public void testCalculateAverage() {
        assertEquals(31.25, SensorLogger.calculateAverage(testData), 0.01);
    }

    @Test
    public void testCountMissingData() {
        assertEquals(1, SensorLogger.countMissingData(testData));
    }
}