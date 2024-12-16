import random
import requests
import pytz
import logging
from time import sleep
from datetime import datetime

# Configuração do logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

# SERVER_URL = "http://localhost:8080/api/v1/"
# SENSOR_URL = SERVER_URL + "sensor/"
INTERVAL = 15  # Tempo em segundos entre envios

# Valores iniciais para simulação
current_ph = 7.0
current_water_temperature = 28
current_ambient_temperature = 30
current_humidity = 87.0
current_water_level = 1.05

def generate_realistic_value(current_value: float, min_value: float, max_value: float) -> float:
    # Gera um novo valor entre 90% e 110% do valor atual, respeitando os limites mínimo e máximo
    lower_bound = max(min_value, current_value * 0.95)
    upper_bound = min(max_value, current_value * 1.05)
    return round(random.uniform(lower_bound, upper_bound), 2)

def get_ph() -> float:
    global current_ph
    current_ph = generate_realistic_value(current_ph, 6.0, 8.0)
    logging.info(f"Valor de pH gerado: {current_ph}")
    return current_ph

def get_water_temperature() -> int:
    global current_water_temperature
    current_water_temperature = generate_realistic_value(current_water_temperature, 25, 37)
    logging.info(f"Temperatura da água gerada: {current_water_temperature}")
    return current_water_temperature

def get_ambient_temperature() -> int:
    global current_ambient_temperature
    current_ambient_temperature = generate_realistic_value(current_ambient_temperature, 26, 31)
    logging.info(f"Temperatura ambiente gerada: {current_ambient_temperature}")
    return current_ambient_temperature

def get_humidity() -> int:
    global current_humidity
    current_humidity = generate_realistic_value(current_humidity, 80.0, 100.0)
    logging.info(f"Umidade gerada: {current_humidity}")
    return round(current_humidity)

def get_water_level() -> float:
    global current_water_level
    current_water_level = generate_realistic_value(current_water_level, 1.0, 1.2)
    logging.info(f"Nível de água gerado: {current_water_level} metros")
    return round(current_water_level, 2)

def get_timestamp() -> str:
    timezone = pytz.timezone("America/Fortaleza")
    return datetime.now(timezone).isoformat()

def build_sensor_data_json():
    json = {
        "ph": get_ph(),
        "water_temperature": get_water_temperature(),
        "ambient_temperature": get_ambient_temperature(),
        "humidity": get_humidity(),
        "water_level": get_water_level(),
        "timestamp": get_timestamp()
    }
    
    logging.info(f"Dados do sensor preparados para envio: {json}")
    
    return json

def send_sensor_data():
    try:
        response = requests.post(SENSOR_URL, json=build_sensor_data_json())
        
        if response.status_code == 200:
            logging.info("Dados enviados com sucesso")
        else:
            logging.error(f"Falha ao enviar dados: {response.status_code}, {response.text}")
            
    except requests.exceptions.RequestException as err:
        logging.error(f"Erro de conexão: {err}")

if __name__ == "__main__":
    while True:
        # Gera e imprime os valores dos sensores
        print("Valor de pH: " + str(get_ph()))
        print("Valor da temperatura da água: " + str(get_water_temperature()))
        print("Valor do nível da água: " + str(get_water_level()))
        print("Valor da temperatura ambiente: " + str(get_ambient_temperature()))
        print("Valor da umidade: " + str(get_humidity()))
        print("\n\n\n")

        #Chama a função para fazer as requisições
        send_sensor_data()
        print(str(send_sensor_data())) # Log

        # Aguarda o intervalo definido antes de gerar novos valores
        sleep(INTERVAL)

