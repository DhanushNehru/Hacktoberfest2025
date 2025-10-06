import pyqrcode 
from pyqrcode import QRCode 
  
# String which represents the QR code 
link= input("Enter your link please\n")
filename=input("\nplease enter your filename to store QR CODE without any extension\n" )
# Generate QR code 
newqr= pyqrcode.create(link) 
  
# Create the file and save the qr code generated"
print("\nloading.....\n")
newqr.svg(filename+".svg", scale = 8)


print("\nYour QR CODE was successfully generated")
