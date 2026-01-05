message = input("Enter the message to encrypted: ")
k = int(input("Enter the value of k: "))
encrypted_message=""
for letter in message:
	X = chr(ord(letter)+k)
	encrypted_message+=X
print(encrypted_message)
