## A Python-based Mock Data Producer application. 

Produces mock data points with eventual anomalies.

You can run the scripts manually, or deploy the docker container for continuous data generation.

Note: Created to 

## Local Setup

All required libraries are listed in the `requirements.txt`.

Install all libraries by running `pip install -r requirements.txt`

Congrats, you are all set to run the scripts!

## Container deploy

Check the Docker folder for the Dockerfile and sample compose file.

## Features

### Producing Single Values

Run the script `RabbitMqSingleValueDataProducer.py` to produce a single value to a single queue.

Useful for local one-off tests.

Note: Not supported on the docker container.

Supports a list of CLI args:

| Arg | Type | Default Value | Desc |
|---|---|---|---| 
| --RabbitHost | String | localhost | Host for RabbitMQ |
| --RabbitPort | String | 5672 | Port for RabbitMQ |
| --RabbitUsername | String | guest | Username for RabbitMQ |
| --RabbitPassword | String | guest | Password for RabbitMQ |
| --Value | Float | 2.5 | The value that will be sent |
| --TargetQueue | String | inbound | The queue to send the data to |

### Producing Normal Distribution Values

Run the script `RabbitMqContinuousDataProducer` to produce a sequence of numbers that compose a normal distribution.

The script will print the dataset, the mean and the standard deviation, to support validating the Consumer application.

Supports a list of CLI args:
| Arg | Type | Default Value | Desc |
|---|---|---|---| 
| --RabbitHost | String | localhost | Host for RabbitMQ |
| --RabbitPort | String | 5672 | Port for RabbitMQ |
| --RabbitUsername | String | guest | Username for RabbitMQ |
| --RabbitPassword | String | guest | Password for RabbitMQ |
| --TargetQueue | String | inbound | The queue to send the data to |
| --AnomalyOdds | Float | 0.05 (5%) | The odds of an anomalous data point |
| --MessageDelay | Float | 0.2 (200ms) | The time in seconds between messages |
