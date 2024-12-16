from abroad_share_handler import AbroadShareHandler
from configuration import Configuration
from security_controller import SecurityController
from russian_share_handler import RussianShareHandler
from flask import Flask


conf = Configuration()
conf.configure()

abroad_share_handler = AbroadShareHandler(conf.api_key)
russian_share_handler = RussianShareHandler(conf.rus_share_url)
# Flask app initialization
app = Flask(__name__)

controller = SecurityController(app, abroad_share_handler, russian_share_handler)

if __name__ == '__main__':
    app.run(debug=True)


# pip install apimoex
# pip3 install flask
# pip3 install python-dotenv


