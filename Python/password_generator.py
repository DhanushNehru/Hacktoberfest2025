import random
import string

def generate_password(length=12):
    # Define the character set: lowercase, uppercase, digits, and special characters
    characters = string.ascii_letters + string.digits + string.punctuation
    
    # Generate a random password
    password = ''.join(random.choice(characters) for i in range(length))
    
    return password

# Example usage
print(generate_password())
