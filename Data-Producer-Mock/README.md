## A Python-based Message Producer application. 

Also a collection of standalone for one-off tests.

## Initial Setup

All required libraries are listed in the `requirements.txt`.

Install all libraries by running `pip install -r requirements.txt`

Congrats, you are all set to run the individual scripts!

## Features

### Producing Single Values

Run the script `rabbitmq-producer-single-value.py` to produce a single value to a single queue.

Use it for one-off tests.

### Producing Normal Distribution Values

Run the script `rabbitmq-producer-normal-distribution` to product a sequence of numbers that compose a normal distribution.

The script will print the dataset, the mean and the standard deviation, to support validating the Consumer application.

