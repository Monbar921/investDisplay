import pandas as pd
import numpy as np
import requests
import json
from datetime import datetime
from security_interface import SecurityInterface
from security_response import SecurityResponse
import security_checker as sec_check


class RussianShareHandler(SecurityInterface):
    def __init__(self, base_url: str):
        self.__base_url = base_url

    @staticmethod
    def __get_price_from_json(json_response):
        price_pos = 0
        price_col_name = 'LAST'
        try:
            marketdata = json_response['marketdata']
            columns = marketdata['columns']
            data = marketdata['data']

            for col in columns:
                if col == price_col_name:
                    break
                price_pos = price_pos + 1
            return SecurityResponse(data[0][price_pos], None)
        except:
            return SecurityResponse(0, "Something went wrong")

    def get_price(self, security_code: str):
        error_mess = sec_check.validate_security_code(security_code)
        if error_mess is None:
            share_url = self.__base_url.format(secid=security_code)

            broker_response = requests.get(share_url)
            if broker_response.status_code == 200:
                result = json.loads(broker_response.text)
                share_response = self.__get_price_from_json(result)
            else:
                share_response = SecurityResponse(0, "Something went wrong")
        else:
            share_response = SecurityResponse(0, error_mess)
        return share_response

# base_url = "http://iss.moex.com/iss/engines/{engine}/markets/{market}/boards/{board}}/securities/{secid}\.json"
# response = requests.get(base_url) #Базовый адрес и дата
# result = json.loads(response.text)
# col_name = result['history']['columns'] #Задаем имена колонок
# data = pd.DataFrame(columns = col_name)

# url_date = np.datetime_as_string(i, unit='D')  # Передаем дату в виде строки формата ГГГГ-ММ-ДД
# url_one_page = base_url + '?date=' + url_date
# response = requests.get(url_one_page)  # Базовый адрес и дата
# result = json.loads(response.text)
# total_line = result['history.cursor']['data'][0][
#     1]  # Узнаем количество строк, если их 0 (нет торгов), то берем следующий день
# if total_line == 0:
#     continue
# step = total_line // 100  # Узнаем количество сотен
# ost = total_line % 100  # Узнаем количество едениц
# resp_date = result['history']['data']  # Извлекаем полученные на 1 странице данные
# data_p_one = pd.DataFrame(resp_date, columns=col_name)  # Полеченные данные записываем в датафрейм
# data = pd.concat([data, data_p_one], ignore_index=True)  # Записываем в изначальный датафрейм полученные данные
# # Цикл загрузки остальных строк
# for j in range(1, step + 1):
#     page_line = '&start=' + str(j * 100)
#     url_next_page = url_one_page + page_line
#     response = requests.get(url_next_page)
#     result = json.loads(response.text)
#     resp_date = result['history']['data']
#     data_next_page = pd.DataFrame(resp_date, columns=col_name)
#     data = pd.concat([data, data_next_page], ignore_index=True)
#     time.sleep(3)