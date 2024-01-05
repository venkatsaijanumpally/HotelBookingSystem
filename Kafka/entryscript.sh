#!/bin/sh

# Change working directory to Kafka home
cd $KAFKA_HOME

# Start Kafka in the background
kafka-server-start.sh config/server.properties &

# Endless loop to check Kafka server availability
while true; do
    kafka-topics.sh --list --bootstrap-server localhost:9092 > /dev/null 2>&1
    if [ $? -eq 0 ]; then
        echo "Kafka server is up. Creating topics..."
        kafka-topics.sh --create --topic hotel_topic --partitions 2 --replication-factor 1 --bootstrap-server localhost:9092
        kafka-topics.sh --create --topic hotels_topic --partitions 2 --replication-factor 1 --bootstrap-server localhost:9092
        break
    fi

    echo "Waiting for Kafka server to be available. Retrying..."
    sleep 5
done

# Keep the script running
tail -f /dev/null
