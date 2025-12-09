import pandas as pd

# Load data
df = pd.read_csv('data.csv')

# Handle missing values
df.dropna(inplace=True)           # Remove rows with missing values
df.fillna(value=0, inplace=True)  # Fill missing values

# Remove duplicates
df.drop_duplicates(inplace=True)

# Correct data types
df['date'] = pd.to_datetime(df['date'])  # Convert to datetime   
