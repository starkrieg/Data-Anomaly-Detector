# Real-Time Data Anomaly Detector

A Real-Time Data Anomaly Detector.

The application is a Message Broker Consumer written in Java with Spring (greater adoption, easier maintenance). 

Currently supported behavior:
- The consumer will read a single number (data point) from a single queue. 1 message = 1 number.
- A rolling window of the N most recent data points is kept.
- The rolling window of N data points is used to calculate the Mean, Standard Deviation, and Anomaly Detection using the Z-score test (https://en.wikipedia.org/wiki/Z-test).
- Data points consumed are logged as either Normal or Anomaly.

This monorepo also includes files for the supported message brokers, and a Python script to simulate a Producer of data points.

Note: Currently does NOT support Horizontal Scaling; the multiple instances will NOT coordinate with one another.

### RabbitMQ as Message Broker

RabbitMQ is a lightweight message broker that is well known in the market. Key points for the choice:
- Well known in the market, facilitates maintenance
- Uses a queue system with acknowledgement for messages, which provides greater reliability
- Lightweight

Redis Pub/Sub was considered, but it's message broker structure is more similar to broadcasting, meaning there is a chance messages can be lost.


### Up Next

- Increase unit test coverage to at least 80%. JaCoCo (Java) is enough for simpler maintenance, but should consider SonarQube or similar when adding this application to an enterprise setting. Additionally, unit test coverage % should be added as a requirement for Pull Requests.
- Add a Helm Chart file for deployment with Kubernetes (K8S). The structure should support a Vault/Secrets Manager for the Message Broker credentials.
- Add support to Kafka as Message Broker. Kafka is a market standard for use-cases involving greater data volume (100K+ per second). This addition increases the scope of usages for this project.
- Define a minimum benchmark target. At this point the project lacks a proper benchmark test and a definitive goal for number of simultaneous queues consumed and throughput. This will provide usage insights, help evaluate the project against real world scenarios, and also help evaluate future roadmap.
- Evaluate adding other data anomaly detection algorithms to increase use-cases supported.


CI/CD pipelines for build automation
branch naming patterns
use of Releases and Tags
require Pull Requests with at least 1 Approval
push new docker image version to Container Registry
