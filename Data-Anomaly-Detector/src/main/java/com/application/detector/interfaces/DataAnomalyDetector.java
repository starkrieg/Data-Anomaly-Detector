package com.application.detector.interfaces;

import org.springframework.context.annotation.Scope;

/*
Scope Prototype means a new instance will be created every time it is called by a Bean
Use this so a new instance is created every time a new consumer is created
So consumers don't share the same anomaly detector
* */
@Scope(value = "prototype")
public interface DataAnomalyDetector {

    // A standard method that validates a single data point against a dataset
    void validateDataPoint(double dataPoint);

}
