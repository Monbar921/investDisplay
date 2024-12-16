from flask import Flask, request, jsonify
from security_interface import SecurityInterface


class SecurityController:
    def __init__(self, app, en_share_handler: SecurityInterface, ru_share_handler: SecurityInterface):
        self.app = app
        self.en_share_handler = en_share_handler
        self.ru_share_handler = ru_share_handler
        if app is not None:
            self.init_app(app)

    def init_app(self, app):
        app.add_url_rule('/shares/en/price/<string:share_code>', view_func=self.get_en_share_price, methods=['GET'])
        app.add_url_rule('/shares/ru/price/<string:share_code>', view_func=self.get_ru_share_price, methods=['GET'])

    def get_ru_share_price(self, share_code):
        response = self.ru_share_handler.get_price(share_code)

        if response.error_msg is None:
            return jsonify(response.to_dict()), 200
        else:
            return jsonify({"error": response.error_msg}), 404

    def get_en_share_price(self, share_code):
        response = self.en_share_handler.get_price(share_code)

        if response.error_msg is None:
            return jsonify(response.to_dict()), 200
        else:
            return jsonify({"error": response.error_msg}), 404
