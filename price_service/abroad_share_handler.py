import finnhub
from security_interface import SecurityInterface
from security_response import SecurityResponse


class AbroadShareHandler(SecurityInterface):
    def __init__(self, api_key):
        self.__finnhub_client = finnhub.Client(api_key=api_key)

    def get_price(self, security_code):
        if security_code is None or len(security_code) == 0:
            return SecurityResponse(0, "Share code is empty")

        try:
            response_obj = self.__finnhub_client.quote(security_code)
            return SecurityResponse(response_obj['c'], None)
        except:
            return SecurityResponse(0, "Something went wrong")
