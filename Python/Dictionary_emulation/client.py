# client.py

import socket

def start_client():
    HOST = '127.0.0.1'
    PORT = 12345

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        try:
            s.connect((HOST, PORT))
            print("Connected to the dictionary server. Type 'exit' to quit.")
            print("Available commands:")
            print("  i             - (re)initializes the dictionary")
            print("  l             - lists the entire dictionary content")
            print("  a <word>      - adds a new word")
            print("  d <word>      - adds/modifies a definition for a word")
            print("  s <word>      - deletes a word")
            
        except ConnectionRefusedError:
            print("ERROR: Could not connect to the server. Make sure server.py is running.")
            return

        while True:
            try:
                user_input = input("> ")
            except KeyboardInterrupt:
                break
                
            if not user_input or user_input.lower() == 'exit':
                break
            
            try:
                s.sendall(user_input.encode('utf-8'))
            except socket.error:
                print("ERROR: Connection to the server was lost.")
                break

            command = user_input.split(' ', 1)[0].lower()

            try:
                if command == 'd':
                    initial_response = s.recv(1024).decode('utf-8')
                    
                    if initial_response == "READY_FOR_DEF":
                        definition = input("Enter definition: ")
                        s.sendall(definition.encode('utf-8'))
                        final_response = s.recv(4096).decode('utf-8')
                        print(f"Server: {final_response}")
                    else:
                        print(f"Server: {initial_response}")
                else:
                    response = s.recv(4096).decode('utf-8')
                    print(f"Server: {response}")
            except socket.error:
                print("ERROR: Connection to the server was lost.")
                break

    print("\nDisconnected from the server.")

if __name__ == "__main__":
    start_client()