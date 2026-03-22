package com.application.detector.interfaces;

public interface DataAnomalyDetector {

    // A standard method that validates a single data point against a dataset
    void validateDataPoint(double dataPoint);

}
