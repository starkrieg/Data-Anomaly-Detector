
from producers.model.RabbitMqProducer import RabbitMqProducer
import numpy as np
import time

isStopped = False

def stop():
    # define references to the global control variables
    global isStopped

    if not isStopped:
        isStopped = True
        print("Continuous producer stopped!")
    #
# end stop

def start(producer: RabbitMqProducer, odds_of_anomaly: float, message_delay: float = 0.5):
    # define references to the global control variables
    global isStopped
    
    dataset_size = 1000

    anomaly_threshold = dataset_size * odds_of_anomaly

    # Defines a Randon Number Generator (RNG) structure
    rng = np.random.default_rng()

    # Create a new dataset of size X with Normal values every loop
    dataset = rng.normal(size=dataset_size)

    # Keep track of the max value for this current dataset of values
    max = dataset.max()

    isStopped = False

    print('Sending values...')

    while (not isStopped):
        # Send all values from this dataset
        # Dataset is not recreated
        for value in dataset:
            isAnomaly = False
            # intentionally cause anomalies in the dataset
            if rng.integers(low=1, high=dataset_size) < anomaly_threshold:
                # Min and Max values already have the highest deviation from the mean
                # Therefore, use it as base to push for an anomaly
                value = max * rng.integers(low=7, high=13)
                isAnomaly = True
            # end if

            producer.publishToTarget(str(value))
            
            print(f"   Sent {value} { "(Anomaly!)" if isAnomaly else "" } ")
            
            # use sleep to control message delay
            time.sleep(message_delay)
        # end for
    #end loop
# end start