from abc import ABC, abstractmethod


class SecurityInterface(ABC):
    @abstractmethod
    def get_price(self, security_code):
        pass
