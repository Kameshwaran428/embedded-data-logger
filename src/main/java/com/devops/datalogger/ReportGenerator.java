package com.devops.datalogger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Main execution class to process dataset and output HTML report.
 */
public class ReportGenerator {

    public static void main(String[] args) {
        // Dataset defined in Problem No. 26
        List<SensorData> dataset = Arrays.asList(
            new SensorData("10:00", 28.0, "Valid"),
            new SensorData("10:01", 30.0, "Valid"),
            new SensorData("10:02", 32.0, "Valid"),
            new SensorData("10:03", null, "Missing"),
            new SensorData("10:04", 35.0, "Valid")
        );

        double min = SensorLogger.calculateMin(dataset);
        double max = SensorLogger.calculateMax(dataset);
        double avg = SensorLogger.calculateAverage(dataset);
        long missingCount = SensorLogger.countMissingData(dataset);

        generateHtmlReport(dataset, min, max, avg, missingCount);
    }

    public static void generateHtmlReport(List<SensorData> dataset, double min, double max, double avg, long missingCount) {
        File reportDir = new File("reports");
        if (!reportDir.exists()) {
            reportDir.mkdir();
        }

        File reportFile = new File(reportDir, "index.html");

        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html><html><head><title>Embedded Data Logger Report</title>")
            .append("<style>")
            .append("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f6f9; }")
            .append("h2 { color: #2c3e50; }")
            .append("table { width: 60%; border-collapse: collapse; background: #fff; margin-bottom: 20px; }")
            .append("th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }")
            .append("th { background-color: #007bff; color: white; }")
            .append(".card { background: #fff; padding: 15px; width: 56%; border-left: 5px solid #007bff; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }")
            .append("</style></head><body>")
            .append("<h2>Embedded Data Logger Analysis Report</h2>")
            .append("<h3>Dataset Log Summary</h3>")
            .append("<table><tr><th>Timestamp</th><th>Temperature (°C)</th><th>Status</th></tr>");

        for (SensorData data : dataset) {
            String tempDisplay = data.getTemperature() != null ? data.getTemperature() + " °C" : "N/A";
            html.append("<tr>")
                .append("<td>").append(data.getTimestamp()).append("</td>")
                .append("<td>").append(tempDisplay).append("</td>")
                .append("<td>").append(data.getStatus()).append("</td>")
                .append("</tr>");
        }

        html.append("</table>")
            .append("<div class='card'>")
            .append("<h3>Statistical Summary</h3>")
            .append("<p><b>Minimum Temperature:</b> ").append(min).append(" °C</p>")
            .append("<p><b>Maximum Temperature:</b> ").append(max).append(" °C</p>")
            .append("<p><b>Average Temperature:</b> ").append(avg).append(" °C</p>")
            .append("<p><b>Missing Data Count:</b> ").append(missingCount).append("</p>")
            .append("</div></body></html>");

        try (FileWriter writer = new FileWriter(reportFile)) {
            writer.write(html.toString());
            System.out.println("HTML Analysis Report generated successfully at: " + reportFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}