package com.application.consumer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.queue")
public class ApplicationConfig {
    private String name;

    // Field dataset-size
    private int datasetSize;

    // Field anomaly-threshold
    private double anomalyThreshold;

    @Override
    public String toString() {
        return "Configs: [ Queue=" + name + "] [ DatasetSize=" + datasetSize + "] [ Anomaly Threshold=" + anomalyThreshold + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDatasetSize() {
        return datasetSize;
    }

    public void setDatasetSize(int datasetSize) {
        this.datasetSize = datasetSize;
    }

    public double getAnomalyThreshold() {
        return anomalyThreshold;
    }

    public void setAnomalyThreshold(double anomalyThreshold) {
        this.anomalyThreshold = anomalyThreshold;
    }
}
