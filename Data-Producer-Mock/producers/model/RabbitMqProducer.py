import pika
# https://pika.readthedocs.io/en/stable/

class RabbitMqProducer:
    connection: pika.BlockingConnection
    channel: any
    target_queue: str

    def __init__(self, targetQueue, rabbitMqHost = 'localhost', rabbitMqPort = 5672, rabbitMqUser = 'guest', rabbitMqPass = 'guest'):
        self.target_queue = targetQueue
        self.connection = pika.BlockingConnection(
            pika.ConnectionParameters(
                rabbitMqHost, 
                rabbitMqPort, 
                credentials=pika.credentials.PlainCredentials(rabbitMqUser, rabbitMqPass)))
        self.channel = self.connection.channel()

        pass
    # end init

    def declareQueue(self):
        # Queue name, durability, and arguments, must match actual target queue
        self.channel.queue_declare(queue=self.target_queue, durable=False, arguments={'x-queue-type': 'classic'})
    # end declareQueue
    
    def publishToTarget(self, content):
        self.channel.basic_publish(exchange='',
                      routing_key=self.target_queue, # so it routes to the target queue
                      body=content)
    # end publishToTarget

    def close(self):
        self.connection.close()
    # end close

# end class