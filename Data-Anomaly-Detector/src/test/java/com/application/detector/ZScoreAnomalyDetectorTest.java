package com.application.detector;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ZScoreAnomalyDetectorTest {

    @Test
    public void testConstructorWithWindowBelowOne_Fail() {
        try {
            ZScoreAnomalyDetector zScoreAnomalyDetector = new ZScoreAnomalyDetector(0, 3.5);
            Assertions.fail("Expected exception when creating object with invalid args");
        } catch (IllegalArgumentException ex) {
            Assertions.assertTrue(true);
        } catch (Exception ex) {
            Assertions.fail("Got wrong type of exception, expected IllegalArgumentException");
        }
    }

    @Test
    public void testConstructorWithThresholdBelowZero_Fail() {
        try {
            ZScoreAnomalyDetector zScoreAnomalyDetector = new ZScoreAnomalyDetector(50, -1.5);
            Assertions.fail("Expected exception when creating object with invalid args");
        } catch (IllegalArgumentException ex) {
            Assertions.assertTrue(true);
        } catch (Exception ex) {
            Assertions.fail("Got wrong type of exception, expected IllegalArgumentException");
        }
    }

    @Test
    public void testConstructorWithValidValues_Success() {
        try {
            ZScoreAnomalyDetector zScoreAnomalyDetector = new ZScoreAnomalyDetector(50, 3.5);
            Assertions.assertTrue(true);
        } catch (Exception ex) {
            Assertions.fail("Expected no errors when creating object with valid args");
        }
    }

    @Test
    public void testZScoreCalculation_Success() {
        int datasetSize = 50;
        double zetaScoreThreshold = 3.5;

        // Instance of the class that will be tested
        ZScoreAnomalyDetector zScoreAnomalyDetector = new ZScoreAnomalyDetector(datasetSize, zetaScoreThreshold);

        // An instance of a Naive implementation of the tested class
        // Used to validate the outputs of the tested class
        ZScoreAnomalyDetectorNaive zScoreNaiveTest = new ZScoreAnomalyDetectorNaive(datasetSize);

        // A sequence of data points that is BIGGER than the dataset size defined previously
        // This way the removal of old values can also be tested
        double[] values = new double[]{
                2.50                ,2.50                ,2.50                ,2.50                ,2.50
                ,2.50                ,2.50                ,2.50                ,2.50                ,3.50
                ,3.50                ,3.50                ,1.50                ,1.50                ,1.50
                ,2.50                ,2.50                ,3.50                ,3.50                ,3.50
                ,4.50                ,5.50                ,3.50                ,3.50                ,3.50
                ,3.50                ,2.50                ,2.50                ,3.50                ,3.50
                ,4.50                ,4.50                ,3.50                ,3.50                ,2.50
                ,-0.83 // Status: ANOMALY DETECTED
                ,1.37                ,1.28                ,0.47                ,-0.28                ,-0.42
                ,-1.36                ,31.88 // Status: ANOMALY DETECTED
                ,-0.84                ,1.17                ,-0.42                ,0.84                ,-0.30
                ,1.15                ,-0.29                ,0.35                ,-0.27                ,-1.16
                ,-1.60                ,-0.25                ,0.22                ,-0.13                ,0.04
                ,-1.26                ,-1.54
        };

        // Confirm that we are using a big enough array of data points
        Assertions.assertTrue(values.length > datasetSize, "Array of data points is not big enough");

        // TODO - Add test for Normal vs Anomaly status logs, probably using verify or stub

        for (double dataPoint : values) {
            // Add datapoint to the baseline algorithm
            zScoreNaiveTest.validateDataPoint(dataPoint);

            // Add datapoint to the algorithm to be validated
            zScoreAnomalyDetector.validateDataPoint(dataPoint);

            // Perform comparison of the internal calculation steps below

            // Because of the algorithms used, there can be minimal differences on the trailing floating points between
            // the Means and Variations
            // This is inherent to floating point numbers in calculations, that doesn't mean the calculation is wrong
            // Using simple double == double or even String.format("%.2f") will eventually fail
            //
            // Validation of these values will be done by checking if they are "nearly" identical
            // Meaning we will use an error margin or tolerance
            //
            // Tolerance = 1e-12 // 0,000.000.000.000.1 (12 zeros)
            // A very low tolerance since this is for statistical analysis
            double tolerance = 1e-12;

            // Assert that the Mean is the same
            double differenceMean = Math.abs(zScoreNaiveTest.getMean() - zScoreAnomalyDetector.getMean());
            Assertions.assertTrue(differenceMean < tolerance,
                    "Calculated mean is over the tolerance of " + tolerance);

            // Assert that the Mean is the same
            double differenceVariation = Math.abs(zScoreNaiveTest.getStandardDeviation() - zScoreAnomalyDetector.getStandardDeviation());
            Assertions.assertTrue(differenceVariation < tolerance,
                    "Calculated Standard Deviation is over the tolerance of " + tolerance);

            // Assert that the Z-Score is the same
            double differenceZScore = Math.abs(zScoreNaiveTest.getLastZScore() - zScoreAnomalyDetector.getLastZScore());
            Assertions.assertTrue(differenceZScore < tolerance,
                    "Calculated Z-Score is over the tolerance of " + tolerance);
        }

    }

}
