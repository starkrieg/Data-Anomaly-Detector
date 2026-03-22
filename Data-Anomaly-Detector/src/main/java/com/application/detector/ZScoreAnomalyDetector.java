package com.application.detector;

import com.application.detector.interfaces.DataAnomalyDetector;
import org.slf4j.Logger;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A Data Anomaly Detector based on Z-Score test
 */
public class ZScoreAnomalyDetector implements DataAnomalyDetector {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    // A Queue to hold the float data points
    // While queue size below queue window size, add to queue
    // When queue is at limit, remove oldest from queue
    // Note: this object can be used for concurrent additions of data points
    // As such, it must support concurrency
    private final ConcurrentLinkedQueue<Double> dataPointQueue = new ConcurrentLinkedQueue<>();

    // Define the max window size for the queue
    private final int queueWindowSize;

    // Keep a sum of the data points in the queue
    // Add to it when a new data point is added
    // Subtract when a data point leaves the window
    // With this, there is no need to sum all queue numbers every time there is need to recalculate the mean
    private double queueSum;

    // Mean value for all values inside the queue
    // The Mean is the sum of all numbers in a list, divided by the size of the list
    // Recalculated every time a new data point is added/removed
    private double mean;

    // Standard deviation for the values inside the queue
    // Recalculated every time a new data point is added/removed
    private double standardDeviation;

    // The Z-Score threshold
    // New data points will have their own Z-Score calculated based on the updated Mean and Standard Deviation
    // If their Z-Score is above this threshold, the data point will be considered an Anomaly
    private final double zetaScoreThreshold;

    public ZScoreAnomalyDetector(int queueWindowSize, double zetaScoreThreshold) {
        if (queueWindowSize < 1) {
            throw new IllegalArgumentException("ZScore Anomaly Detector cannot have Queue Window Size below 1");
        }

        if (zetaScoreThreshold < 0) {
            throw new IllegalArgumentException("ZScore Anomaly Detector cannot have Z-Score Threshold below 0");
        }

        this.queueWindowSize = queueWindowSize;
        this.zetaScoreThreshold = zetaScoreThreshold;
    }

    /**
     * Validates a data point for anomaly using Z-Score testing.
     * All data points are added to an internal dataset, which is
     * used for anomaly detection.
     *
     * Proper use of this anomaly detector requires continuous streaming of data points
     *
     * This method is synchronized so the addition of the data point and
     * anomaly detection are not affected by concurrent calls
     * @param dataPoint a double value that represents a data point
     */
    public synchronized void validateDataPoint(double dataPoint) {
        // If queue already at limit size
        // Then remove the oldest data point to add the new now
        if (this.dataPointQueue.size() >= this.queueWindowSize) {
            Double oldestPoint = this.dataPointQueue.remove();
            // Remove oldest value from sum
            this.queueSum -= oldestPoint;
        }

        // Add newest data point
        this.dataPointQueue.add(dataPoint);
        this.queueSum += dataPoint; // Add newest value

        // Update mean
        this.mean = this.queueSum / this.dataPointQueue.size();

        // TODO - Check if Standard Deviation and Z-Score Analysis can be done outside the synchronized block
        // since these calculations might take longer

        // Update standard deviation
        updateStandardDeviation();

        // Determine if new data point is an anomaly
        checkIfDataAnomaly(dataPoint);
    }

    /**
     * Checks the Z-Score of a passed data point.
     *
     * The method assumed that the Mean and Standard Deviation
     * have already been calculated.
     *
     * @param dataPoint the data point that will be checked for Z-Score
     */
    private void checkIfDataAnomaly(double dataPoint) {
        // The Z-Score is calculating by:
        // 1. The absolute value from subtracting the Mean from the new data point
        // 2. The absolute value is divided by the standard deviation

        // Note: if Standard Deviation is 0, such as when all data points are the same value
        // The Z-Score cannot be calculated because it's a division by 0
        // In this case, default a Z-Score of 0

        if (this.standardDeviation > 0) {
            double zetaScore = Math.abs(dataPoint - this.mean) / this.standardDeviation;

            // Z-Score test then evaluates the zetaScore for the new data point
            // against a Z-Score threshold
            // If it exceeds the threshold, then it's an anomaly
            boolean isAnomaly = zetaScore > this.zetaScoreThreshold;

            if (isAnomaly) {
                // Log Anomaly
                logger.warn(String.format("Data Point: %.2f | Status: ANOMALY DETECTED " +
                        "| Z-score: %.2f | ALERT: Significant deviation detection", dataPoint, zetaScore));
            } else {
                // Log Normal
                logger.info(String.format("Data Point: %.2f | Status: OK " +
                        "| Z-score: %.2f ", dataPoint, zetaScore));
            }
        } else {
            // Log Normal
            logger.info(String.format("Data Point: %.2f | Status: OK " +
                    "| Z-score: %.2f ", dataPoint, 0.00));
        }
    }

    /**
     * Performs the calculation to update the Standard Deviation
     * based on the current values for the data point queue.
     */
    private void updateStandardDeviation() {
        // Standard Deviation calculation:
        // 1. Subtract the Mean from each value in the queue;
        // this will find the distance from the mean
        // 2. Find the square (x^2) for each of the 'distances'
        // 3. Sum all values from step 2
        // 4. Divide the sum by the size of the Queue
        // 5. Take the square root

        // Copy values to a list to prevent messing with the actual queue
        List<Double> dataPointList = dataPointQueue.stream().toList();

        var summedSquaredDistances = dataPointList.stream()
                .map(
                        // Find the 'distance' and square it for each data point
                        dp -> Math.pow(dp - this.mean, 2)
                )
                .reduce(Double::sum)
                // return 0.0 if no value is present
                .orElse(0.0);

        this.standardDeviation = Math.sqrt(summedSquaredDistances / dataPointList.size());
    }

}
