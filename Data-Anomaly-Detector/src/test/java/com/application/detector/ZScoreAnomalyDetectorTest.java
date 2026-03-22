package com.application.detector;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ZScoreAnomalyDetectorTest {

    @Test
    public void testConstructorWithWindowBelowOne_Fail() {
        try {
            ZScoreAnomalyDetector zScoreAnomalyDetector = new ZScoreAnomalyDetector(0, 3.5);
            Assertions.fail("Expected exception when creating object with invalid args");
        } catch (Exception ex) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    public void testConstructorWithThresholdBelowZero_Fail() {
        try {
            ZScoreAnomalyDetector zScoreAnomalyDetector = new ZScoreAnomalyDetector(50, -1.5);
            Assertions.fail("Expected exception when creating object with invalid args");
        } catch (Exception ex) {
            Assertions.assertTrue(true);
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
        ZScoreAnomalyDetector zScoreAnomalyDetector = new ZScoreAnomalyDetector(50, 3.5);

        // TODO - Change this test to properly validate the internals of the calculation
        // actual Z-Score and the Normal vs Anomaly status logs

        // For now, just perform a series of validations and check that no errors occur
        zScoreAnomalyDetector.validateDataPoint(2.50);
        zScoreAnomalyDetector.validateDataPoint(2.50);
        zScoreAnomalyDetector.validateDataPoint(2.50);
        zScoreAnomalyDetector.validateDataPoint(2.50);
        zScoreAnomalyDetector.validateDataPoint(2.50);
        zScoreAnomalyDetector.validateDataPoint(2.50);
        zScoreAnomalyDetector.validateDataPoint(2.50);
        zScoreAnomalyDetector.validateDataPoint(2.50);
        zScoreAnomalyDetector.validateDataPoint(2.50);
        zScoreAnomalyDetector.validateDataPoint(3.50);
        zScoreAnomalyDetector.validateDataPoint(3.50);
        zScoreAnomalyDetector.validateDataPoint(3.50);
        zScoreAnomalyDetector.validateDataPoint(1.50);
        zScoreAnomalyDetector.validateDataPoint(1.50);
        zScoreAnomalyDetector.validateDataPoint(1.50);
        zScoreAnomalyDetector.validateDataPoint(2.50);
        zScoreAnomalyDetector.validateDataPoint(2.50);
        zScoreAnomalyDetector.validateDataPoint(3.50);
        zScoreAnomalyDetector.validateDataPoint(3.50);
        zScoreAnomalyDetector.validateDataPoint(3.50);
        zScoreAnomalyDetector.validateDataPoint(4.50);
        zScoreAnomalyDetector.validateDataPoint(5.50);
        zScoreAnomalyDetector.validateDataPoint(3.50);
        zScoreAnomalyDetector.validateDataPoint(3.50);
        zScoreAnomalyDetector.validateDataPoint(3.50);
        zScoreAnomalyDetector.validateDataPoint(3.50);
        zScoreAnomalyDetector.validateDataPoint(2.50);
        zScoreAnomalyDetector.validateDataPoint(2.50);
        zScoreAnomalyDetector.validateDataPoint(3.50);
        zScoreAnomalyDetector.validateDataPoint(3.50);
        zScoreAnomalyDetector.validateDataPoint(4.50);
        zScoreAnomalyDetector.validateDataPoint(4.50);
        zScoreAnomalyDetector.validateDataPoint(3.50);
        zScoreAnomalyDetector.validateDataPoint(3.50);
        zScoreAnomalyDetector.validateDataPoint(2.50);
        zScoreAnomalyDetector.validateDataPoint(-0.83); // Status: ANOMALY DETECTED
        zScoreAnomalyDetector.validateDataPoint(1.37);
        zScoreAnomalyDetector.validateDataPoint(1.28);
        zScoreAnomalyDetector.validateDataPoint(0.47);
        zScoreAnomalyDetector.validateDataPoint(-0.28);
        zScoreAnomalyDetector.validateDataPoint(-0.42);
        zScoreAnomalyDetector.validateDataPoint(-1.36);
        zScoreAnomalyDetector.validateDataPoint(31.88); // Status: ANOMALY DETECTED
        zScoreAnomalyDetector.validateDataPoint(-0.84);
        zScoreAnomalyDetector.validateDataPoint(1.17);
        zScoreAnomalyDetector.validateDataPoint(-0.42);
        zScoreAnomalyDetector.validateDataPoint(0.84);
        zScoreAnomalyDetector.validateDataPoint(-0.30);
        zScoreAnomalyDetector.validateDataPoint(1.15);
        zScoreAnomalyDetector.validateDataPoint(-0.29);
        zScoreAnomalyDetector.validateDataPoint(0.35);
        zScoreAnomalyDetector.validateDataPoint(-0.27);
        zScoreAnomalyDetector.validateDataPoint(-1.16);
        zScoreAnomalyDetector.validateDataPoint(-1.60);
        zScoreAnomalyDetector.validateDataPoint(-0.25);
        zScoreAnomalyDetector.validateDataPoint(0.22);
        zScoreAnomalyDetector.validateDataPoint(-0.13);
        zScoreAnomalyDetector.validateDataPoint(0.04);
        zScoreAnomalyDetector.validateDataPoint(-1.26);
        zScoreAnomalyDetector.validateDataPoint(-1.54);

        // If no errors until here, Pass
        Assertions.assertTrue(true);
    }

}
