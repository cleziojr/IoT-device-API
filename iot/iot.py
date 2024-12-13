import random
import requests
import pytz
from time import sleep
from datetime import datetime

SERVER_URL = "http://localhost:8080/api/v1/"
SENSOR_URL = SERVER_URL + "sensor/"
INTERVAL = 15 # Variável para controlar o tempo que vai demorar para o dispositivo enviar os dados para o servidor

def get_ph() -> float:
    return round(random.uniform(6.0, 8.0), 2)

def get_water_temperature() -> int:
    return random.randint(25, 30)

def get_ambient_temperature() -> int:
    return random.randint(26, 32)

def get_humidity() -> int: # Retorna em porcentagem Ex. 90%
    return round(random.uniform(50.0, 70.0), 2)

def get_water_level() -> float: # Retorna em metros
    return round(random.uniform(0, 0.7), 2)

def get_timestamp() -> str:
    timezone = pytz.timezone("America/Fortaleza")
    return datetime.now(timezone).isoformat()

def build_sensor_data_json():
    json = {
        "ph":get_ph(),
        "water_temperature": get_water_temperature(),
        "ambient_temperature": get_ambient_temperature(),
        "humidity": get_humidity(),
        "water_level": get_water_level(),
        "timestamp": get_timestamp()
    }

    return json

def send_sensor_data():
    try:
        response = requests.post(SENSOR_URL, json=build_sensor_data_json())
        
        if response.status_code == 200:
            print("Dados enviados com sucesso")
        else:
            print(f"Falha ao enviar dados: {response.status_code}, {response.text}")
            
    except requests.exceptions.RequestException as err:
        print(f"Erro de conexão: {err}")


if __name__ == "__main__":
    while True:
        send_sensor_data()
        sleep(INTERVAL)