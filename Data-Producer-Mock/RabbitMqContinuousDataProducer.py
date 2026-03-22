#!/usr/bin/env python

from producers.model.RabbitMqProducer import RabbitMqProducer
from producers import ContinuousProducer

import argparse

parser = argparse.ArgumentParser()

# The -- makes the argument into optional
parser.add_argument("--AnomalyOdds",
    help="A floating point number that determines how often a data anomaly will be sent by the Producer. Defaults to '0.05' or 5%",
    default='0.05', type=float)

# The -- makes the argument into optional
parser.add_argument("--MessageDelay", 
    help="A timer for delay between messages procudes. It's a floating point number that is read as delay in Seconds. Defaults to '0.5' or 500ms delay", 
    default='0.5', type=float)

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

args = parser.parse_args()

# lower this number to increase number of messages sent per second
message_delay = args.MessageDelay

# increase this number to increase anomalous data points
odds_of_anomaly = args.AnomalyOdds

print("Starting Continuous Data Producer for RabbitMQ")
print(f"Rabbit Host: {args.RabbitHost}")
print(f"Rabbit Port: {args.RabbitPort}")
print(f"Rabbit User: {args.RabbitUsername}")
print(f"Rabbit Pass: ***")
print(f"Queue Target: {args.TargetQueue}")
print(f"Anomaly Odds: {args.AnomalyOdds}")
print(f"Message Delay: {args.MessageDelay}")

# Instance of the producer that will continuously send the data points
producer = RabbitMqProducer(args.TargetQueue, args.RabbitHost, args.RabbitPort, args.RabbitUsername, args.RabbitPassword)

# Producer will run until the thread is killed
ContinuousProducer.start(producer=producer, odds_of_anomaly=odds_of_anomaly, message_delay=message_delay)