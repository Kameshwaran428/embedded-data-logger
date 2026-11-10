package com.devops.datalogger;

import java.util.List;

/**
 * Core processing logic to calculate min, max, average, and count missing data.
 */
public class SensorLogger {

    public static double calculateMin(List<SensorData> logs) {
        return logs.stream()
                .filter(SensorData::isValid)
                .mapToDouble(SensorData::getTemperature)
                .min()
                .orElse(0.0);
    }

    public static double calculateMax(List<SensorData> logs) {
        return logs.stream()
                .filter(SensorData::isValid)
                .mapToDouble(SensorData::getTemperature)
                .max()
                .orElse(0.0);
    }

    public static double calculateAverage(List<SensorData> logs) {
        return logs.stream()
                .filter(SensorData::isValid)
                .mapToDouble(SensorData::getTemperature)
                .average()
                .orElse(0.0);
    }

    public static long countMissingData(List<SensorData> logs) {
        return logs.stream()
                .filter(data -> !data.isValid() || "Missing".equalsIgnoreCase(data.getStatus()))
                .count();
    }
}