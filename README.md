# Real-Time Data Anomaly Detector

A Real-Time Data Anomaly Detector. 

The application is a Message Broker Consumer written in Java with Spring. 

Currently supported behavior:
- The Consumer will read a single number (data point) from a single queue. 1 message = 1 number.
- A rolling window of the N most recent data points is kept.
- The rolling window of N data points is used to calculate the Mean, Standard Deviation, and Anomaly Detection using the Z-score test (https://en.wikipedia.org/wiki/Z-test).
- Data points consumed are logged as either Normal or Anomaly, with anomalies logged as Warning.

This monorepo also includes files for the supported message brokers, and a Python-based Data Producer to mock data generation.

When adding changes, utilize Pull Requests instead of pushing directly to `develop`. 
Existing GitHub actions support this workflow by running a build check on the Data-Anomaly-Detector app.

Note: Currently does NOT support Horizontal Scaling; multiple instances will NOT coordinate with one another.

## Stack

| Tech | Version | Info |
|---|---|---|
| Java | 25 | Anomaly Detector Consumer |
| Gradle | 9.3.1 | Anomaly Detector Consumer |
| Spring | 4.0.4 | Anomaly Detector Consumer |
| Python | 3.12.10 | Mock Data Producer |


## RabbitMQ as Message Broker

RabbitMQ is a lightweight message broker that is well known in the market. Key points for the choice:
- Well known in the market, facilitates maintenance
- Uses a queue system with acknowledgement for messages, which provides greater reliability
- Lightweight


Redis Pub/Sub was considered, but it's message broker structure is more similar to broadcasting, meaning there is a chance messages can be lost.


## Mock Data Producer 

A Mock Data Producer written in Python. Continuously produces data to a target queue following a normal distribution, with eventual anomalous data points.

More details on the Data Producer Mock folder.

## Ready to use

Check the Docker folder for the docker-compose files and use cases.

Check the Chart folder for the Helm Chart files.

## Up Next

Development plans to increment this project:

- Increase unit test coverage to at least 80%. JaCoCo (Java) has been added to facilitate tracking.
- Consider adding SonarQube or similar when adding this application to an enterprise setting. 
- Unit test coverage % should be added as a Pull Request Check, eventually becoming a blocking check.
- Improve credentials security between Consumer and RabbitMQ, so compose files don't need to pass the password in plaintext.
- Improve Helm Chart files to add link to secrets manager and vault structures, to replace direct usage of Message Broker Credentials.
- Add support to Kafka as Message Broker. Kafka is a market standard for use-cases involving greater data volume (100K+ per second). This addition increases the scope of usages for this project.
- The project is missing a Performance Baseline: target throughput, memory footprint, if it should support multiple queues at the same time, etc. This will provide usage insights, help evaluate the project against real world scenarios, and also help evaluate future roadmap.
- Add support for other data anomaly detection algorithms.
- Restrict commits to `develop` branch; make Pull Requests mandatory, and make existing PR Actions as required to be passing.
- Add more comprehensive GitHub Actions to test behavior and quality of Data Consumer and Producer.
- Add GitHub Action trigger on Release to push a new tag to a Container Registry.
