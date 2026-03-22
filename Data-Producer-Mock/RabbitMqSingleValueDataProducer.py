#!/usr/bin/env python

from producers.model.RabbitMqProducer import RabbitMqProducer

import argparse

parser = argparse.ArgumentParser()

# The -- makes the argument into optional
parser.add_argument("--TargetQueue", 
    help="The name of the target queue where the Producer will send the data points. Defaults to 'inbound'", 
    default='inbound', type=str)

# The -- makes the argument into optional
parser.add_argument("--RabbitHost", 
    help="The host for RabbitMQ. Defaults to 'localhost'.", 
    default='localhost', type=str)

# The -- makes the argument into optional
parser.add_argument("--RabbitPort", 
    help="The port for RabbitMQ. Defaults to '5672'.", 
    default='5672', type=str)

# The -- makes the argument into optional
parser.add_argument("--RabbitUsername", 
    help="The username for RabbitMQ. Defaults to 'guest'.", 
    default='guest', type=str)

# The -- makes the argument into optional
parser.add_argument("--RabbitPassword", 
    help="The password for RabbitMQ. Defaults to 'guest'.", 
    default='guest', type=str)

# The -- makes the argument into optional
parser.add_argument("--Value", 
    help="The numeric value that the Producer will send. Supports floating point numbers. Defaults to 2.5", 
    default='2.5', type=float)

args = parser.parse_args()

producer = RabbitMqProducer(args.TargetQueue, args.RabbitHost, args.RabbitPort, args.RabbitUsername, args.RabbitPassword)

producer.declareQueue()

value = args.Value

print("Starting Continuous Data Producer for RabbitMQ")
print(f"Rabbit Host: {args.RabbitHost}")
print(f"Rabbit Port: {args.RabbitPort}")
print(f"Rabbit User: {args.RabbitUsername}")
print(f"Rabbit Pass: ***")
print(f"Queue Target: {args.TargetQueue}")
print(f"Value: {args.Value}")

producer.publishToTarget(str(value))

print(f" Sent {value}")

# Make sure to close at the end for correct network buffer behavior
producer.close()