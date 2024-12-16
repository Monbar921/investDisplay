def validate_security_code(security_code):
    if security_code is None or len(security_code) == 0:
        return "Share code is empty"
    return None

