print("Learn the basics of strings")

name=input((("Enter your name")))
print(len(name)) #prints number of letters in your name

print("Indexing:")
# Yourname
# 012345678
str="Yourname"
print(str[0]) #Y

print("Slicing:")
#accessing parts of a string
print(str[1:3]) #ou (ending index is not included)
print(str[:3]) #You
print(str[1:]) #same as str[1:len(str)]

print("negative index:")
print(str[-3:-1]) #am

print("String functions:")
str1= "open source"
str2=str1.capitalize() #capitalizes 1st char
print(str2)
str3=str1.replace("e","a") #replaces all occurrences of old with new
print(str3)
str4=str1.find("open") #returns 1st index of 1st occurrence
print(str4)
str6=str.endswith("name")
print(str6)

print("Enjoy exploring many more string fuctions!")
