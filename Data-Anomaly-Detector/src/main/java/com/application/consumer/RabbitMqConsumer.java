package com.application.consumer;

import com.application.consumer.interfaces.Consumer;
import com.application.detector.ZScoreAnomalyDetector;
import com.application.detector.interfaces.DataAnomalyDetector;
import org.slf4j.Logger;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.amqp.autoconfigure.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumer implements Consumer {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationConfig applicationConfig;

    /*
        Anomaly Detector has Scope Prototype,
        meaning a new instance will be created for every new RabbitMqConsumer
    */
    @Autowired
    private DataAnomalyDetector dataAnomalyDetector;

    public RabbitMqConsumer(ApplicationConfig applicationConfig, DataAnomalyDetector dataAnomalyDetector) {
        logger.warn(applicationConfig.toString());
        this.applicationConfig = applicationConfig;
        this.dataAnomalyDetector = dataAnomalyDetector;
    }

    /**
     * Handle consumption of messages for a specific queue.
     *
     * Will validate the data point in the message using the anomaly detector
     * defined at the creation of this consumer.
     *
     * @param message a string number with decimal value
     */
    @RabbitListener(queues = "${application.queue.name}")
    public void handle(String message) {
        try {
            // Validate message input format

            double dataPoint = Double.parseDouble(message);

            dataAnomalyDetector.validateDataPoint(dataPoint);
        } catch (NumberFormatException ex) {
            logger.error("Could not treat consumed message as a numbered value with decimals.", ex);
            if (logger.isTraceEnabled()) {
                logger.trace(String.format("Consumed message is: %s", message));
            }
        }
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,
                                                                               SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public Queue queue() {
        return new Queue( applicationConfig.getName(), false, false, false);
    }


}
