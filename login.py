import mysql.connector

# Connect to database
conn = mysql.connector.connect(
    host="localhost",
    user="root",
    password="yourpassword",
    database="login_db"
)

cursor = conn.cursor()

# Get login input
username = input("Enter username: ")
password = input("Enter password: ")

# Check credentials
cursor.execute("SELECT * FROM users WHERE username=%s AND password=%s", (username, password))
result = cursor.fetchone()

if result:
    print("✅ Login successful!")
else:
    print("❌ Invalid username or password.")

conn.close()
