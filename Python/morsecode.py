#this program translates text to morse code

#dictionary representing the morse code chart
MORSE_CODE_DICT = { 'A':'.-', 'B':'-...', 'C':'-.-.',
                    'D':'-..', 'E':'.', 'F':'..-.',
                    'G':'--.', 'H':'....', 'I':'..',
                    'J':'.---', 'K':'-.-', 'L':'.-..',
                    'M':'--', 'N':'-.', 'O':'---',
                    'P':'.--.', 'Q':'--.-', 'R':'.-.',
                    'S':'...', 'T':'-', 'U':'..-',
                    'V':'...-', 'W':'.--', 'X':'-..-',
                    'Y':'-.--', 'Z':'--..',
                    '1':'.----', '2':'..---', '3':'...--',
                    '4':'....-', '5':'.....', '6':'-....',
                    '7':'--...', '8':'---..', '9':'----.',
                    '0':'-----', ', ':'--..--', '.':'.-.-.-',
                    '?':'..--..', '/':'-..-.', '-':'-....-',
                    '(':'-.--.', ')':'-.--.-'}

#function to encrypt the string
def encrypt(text):
    cipher = ''
    for letter in text:
        if letter != ' ':
            cipher += MORSE_CODE_DICT[letter] + ' '
        else:
            cipher += ' '
    return cipher

#main function
def main():
    text = input("Enter text to convert to morse code: ")
    text = text.upper()
    result = encrypt(text)
    print(result)

main()