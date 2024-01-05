import requests
import time
import os

ELASTICSEARCH_HOST = os.environ.get("ELASTICSEARCH_HOST", "elasticsearch")
ELASTICSEARCH_PORT = int(os.environ.get("ELASTICSEARCH_PORT", 9200))

def wait_for_elasticsearch():
    url = f"http://{ELASTICSEARCH_HOST}:{ELASTICSEARCH_PORT}"

    while True:
        try:
            response = requests.get(url)
            response.raise_for_status()
            print("Elasticsearch is up and running!")
            break
        except requests.ConnectionError:
            print("Waiting for Elasticsearch to start...")
            time.sleep(3)

def create_index():
    url = f"http://{ELASTICSEARCH_HOST}:{ELASTICSEARCH_PORT}/hotels"
    url2 = f"http://{ELASTICSEARCH_HOST}:{ELASTICSEARCH_PORT}/hotel"

    index_settings = {
        "settings": {
            "number_of_shards": 2,
            "number_of_replicas": 1
        }
    }

    response = requests.put(url, json=index_settings)
    response2 = requests.put(url2, json=index_settings)
    response.raise_for_status()
    response2.raise_for_status()
    print("Index 'hotels' created successfully!")

if __name__ == "__main__":
    wait_for_elasticsearch()
    create_index()
