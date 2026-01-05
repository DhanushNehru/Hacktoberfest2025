# server.py

import socket

def start_server():
    HOST = '127.0.0.1'
    PORT = 12345

    dictionary = None

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind((HOST, PORT))
        s.listen()
        print(f"Server has started and is listening on {HOST}:{PORT}")

        while True:
            conn, addr = s.accept()
            with conn:
                print(f"New client connected: {addr}")

                while True:
                    try:
                        data = conn.recv(1024).decode('utf-8').strip()
                    except ConnectionResetError:
                        break

                    if not data:
                        break

                    print(f"Client {addr} sent: {data}")

                    parts = data.split(' ', 1)
                    command = parts[0].lower()
                    arg = parts[1] if len(parts) > 1 else ""
                    
                    response = ""

                    if command == 'i':
                        dictionary = {}
                        response = "OK: The dictionary has been initialized."
                    elif dictionary is None:
                        response = "ERROR: The dictionary has not been initialized. Use the 'i' command first."
                    
                    elif command == 'l':
                        if not dictionary:
                            response = "The dictionary is empty."
                        else:
                            response_lines = ["Dictionary content:"]
                            for word, definition in dictionary.items():
                                definition_text = definition if definition else "[no definition]"
                                response_lines.append(f"- {word}: {definition_text}")
                            response = "\n".join(response_lines)
                    
                    elif command == 'a':
                        if not arg:
                            response = "ERROR: A word must be specified. Example: a <word>"
                        elif arg in dictionary:
                            response = f"ERROR: The word '{arg}' already exists in the dictionary."
                        else:
                            dictionary[arg] = ""
                            response = f"OK: The word '{arg}' has been added."
                    
                    elif command == 'd':
                        if not arg:
                            response = "ERROR: A word must be specified. Example: d <word>"
                        elif arg not in dictionary:
                            response = f"ERROR: The word '{arg}' was not found."
                        else:
                            conn.sendall("READY_FOR_DEF".encode('utf-8'))
                            definition = conn.recv(4096).decode('utf-8')
                            dictionary[arg] = definition
                            response = f"OK: The definition for '{arg}' has been added/modified."
                    
                    elif command == 's':
                        if not arg:
                            response = "ERROR: A word must be specified. Example: s <word>"
                        elif arg in dictionary:
                            del dictionary[arg]
                            response = f"OK: The word '{arg}' and its definition have been deleted."
                        else:
                            response = f"ERROR: The word '{arg}' was not found."
                    
                    else:
                        response = "ERROR: Unknown command."

                    conn.sendall(response.encode('utf-8'))

                print(f"Client {addr} has disconnected.")

if __name__ == "__main__":
    start_server()