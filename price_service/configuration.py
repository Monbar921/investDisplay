import os
from dotenv import load_dotenv


class Configuration:
    def __init__(self):
        self.api_key = None
        self.rus_share_url = None

    def configure(self):
        load_dotenv()
        self.api_key = os.getenv("API_KEY")
        self.rus_share_url = os.getenv("RUS_SHARE_URL")
