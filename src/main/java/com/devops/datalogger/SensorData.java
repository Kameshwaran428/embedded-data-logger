package com.devops.datalogger;

/**
 * Model class representing a single timestamped sensor reading.
 */
public class SensorData {
    private String timestamp;
    private Double temperature;
    private String status;

    public SensorData(String timestamp, Double temperature, String status) {
        this.timestamp = timestamp;
        this.temperature = temperature;
        this.status = status;
    }

    public String getTimestamp() { 
        return timestamp; 
    }

    public Double getTemperature() { 
        return temperature; 
    }

    public String getStatus() { 
        return status; 
    }

    public boolean isValid() { 
        return "Valid".equalsIgnoreCase(status) && temperature != null; 
    }
}