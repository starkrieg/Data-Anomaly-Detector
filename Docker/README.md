## Compose files to support various use cases

### Full Scenario

Deploy a complete set of apps for full use-case scenario. Use it to test RabbitMQ configs, change Data Producer settings, and test Consumer algorithm and behavior.

Includes:
- RabbitMQ on port 5672 and admin console on 15672, default user/pass are guest/guest
- Mock Data Producer that continuously produces normal data, with eventual anomalies.
- Consumer application, all included.
- Dozzle (https://dozzle.dev/) for simple monitoring of the containers and their logs, very useful to track both data being Produced and Consumed at the same time.


### Consumer with RabbitMQ

Deploy just a RabbitMQ and the Consumer app. Then just plug any data producer app you want, from wherever you want.

The compose also includes a Dozzle instance for easier monitoring of this stack.


### Just Consumer

A sample compose that deploys just the Consumer app. Fit it wherever you want.
