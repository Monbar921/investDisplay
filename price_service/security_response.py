class SecurityResponse:
    def __init__(self, current_price, error_msg):
        self.current_price = current_price
        self.error_msg = error_msg

    def to_dict(self):
        return {
            "current_price": self.current_price,
            "error_msg": self.error_msg
        }
