# Real-Time Data Anomaly Detector

A Real-Time Data Anomaly Detector. 

The application is a Message Broker Consumer written in Java with Spring. 

Currently supported behavior:
- The Consumer will read a single number (data point) from a single queue. 1 message = 1 number.
- A rolling window of the N most recent data points is kept.
- The rolling window of N data points is used to calculate the Mean, Standard Deviation, and Anomaly Detection using the Z-score test (https://en.wikipedia.org/wiki/Z-test).
- Data points consumed are logged as either Normal or Anomaly, with anomalies logged as Warning.

This monorepo also includes files for the supported message brokers, and a Python-based Data Producer to mock data generation.

Note: Currently does NOT support Horizontal Scaling; multiple instances will NOT coordinate with one another.

## RabbitMQ as Message Broker

RabbitMQ is a lightweight message broker that is well known in the market. Key points for the choice:
- Well known in the market, facilitates maintenance
- Uses a queue system with acknowledgement for messages, which provides greater reliability
- Lightweight


Redis Pub/Sub was considered, but it's message broker structure is more similar to broadcasting, meaning there is a chance messages can be lost.


## Mock Data Producer 

A Mock Data Producer written in Python. Continuously produces data to a target queue.

More details on the Data Producer Mock folder.

## Ready to use

Check the Docker folder for the docker-compose files and use cases.

## Up Next

Development plans to increment this project:

- Increase unit test coverage to at least 80%. JaCoCo (Java) is enough for simpler maintenance, but should consider SonarQube or similar when adding this application to an enterprise setting. Additionally, unit test coverage % should be added as a requirement for Pull Request Checks.
- Improve credentials security between Consumer and RabbitMQ, so compose files don't need to pass the password in plaintext.
- Add a Helm Chart file for deployment with Kubernetes (K8S). The structure should support a Vault/Secrets Manager for the Message Broker credentials.
- Add support to Kafka as Message Broker. Kafka is a market standard for use-cases involving greater data volume (100K+ per second). This addition increases the scope of usages for this project.
- Define a minimum benchmark target. At this point the project lacks a proper benchmark test and a definitive goal for number of simultaneous queues consumed and throughput. This will provide usage insights, help evaluate the project against real world scenarios, and also help evaluate future roadmap.
- Evaluate adding other data anomaly detection algorithms to increase use-cases supported.
- Restrict commits to `develop` branch and require Pull Requests for proper code review on changes.
- Add PR checks through GitHub Actions, such as builds with no errors and code coverage.
- Add GitHub Action trigger on Release to push a new tag to a Container Registry.
