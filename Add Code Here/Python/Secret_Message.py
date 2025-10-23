
# define a function that takes a character as input
# and returns the encrypted character
def encrypt(character):

    # if the character is 'z',
    # assign 'a' to secret
    if character=='z':
        secret = 'a'

    # if the character is 'Z',
    # assign 'A' to secret
    elif character=='Z':
        secret="A"
        
    # if the character is not 'z',
    # convert the character in ASCII format, add 1 to it
    # again convert it to character and assign to secret
    else:
        secret = chr(ord(character)+1)

    # return the secret string
    return secret


# define the main function
def main_runner(string):

    # initialize the encrypted string
    result = ''

    # loop through each character in the string
    for ch in string:

        # if the character is the alphabet,
        # call the encrypt function and concat the result to result
        if ch.isalpha():
            result += encrypt(ch)

        # if the character is not an alphabet,
        # concat the character as it is to result
        else:
            result +=ch

    # return the result
    return result


# encrypt message in lowercase
encrypted = main_runner('i love hacktoberfest')
print(encrypted)

# encrypt message in uppercase
encrypted = main_runner('I LOVE HACKTOBERFEST')
print(encrypted)