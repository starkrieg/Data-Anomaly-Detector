#!/usr/bin/env python

from producers.RabbitMqProducer import RabbitMqProducer

producer = RabbitMqProducer('inbound', 'localhost')

value = '3'

producer.publishToTarget(value)

print(f" Sent ${value}")

# Make sure to close at the end for correct network buffer behavior
producer.close()