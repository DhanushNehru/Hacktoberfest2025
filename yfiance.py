# stock_predictor.py

import numpy as np
import pandas as pd
import yfinance as yf
from sklearn.preprocessing import MinMaxScaler
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import LSTM, Dense, Dropout
import matplotlib.pyplot as plt

# -------------------------
# CONFIGURATION
# -------------------------
TICKER = "AAPL"       # Stock symbol (Apple as example)
START_DATE = "2015-01-01"
END_DATE = "2025-01-01"
LOOKBACK = 60         # Days to look back for prediction

# -------------------------
# FETCH DATA
# -------------------------
def fetch_data(ticker, start, end):
    data = yf.download(ticker, start=start, end=end)
    return data[['Close']]

# -------------------------
# PREPROCESS DATA
# -------------------------
def preprocess_data(data, lookback=60):
    scaler = MinMaxScaler(feature_range=(0, 1))
    scaled_data = scaler.fit_transform(data)

    X, y = [], []
    for i in range(lookback, len(scaled_data)):
        X.append(scaled_data[i-lookback:i, 0])
        y.append(scaled_data[i, 0])

    X, y = np.array(X), np.array(y)
    X = np.reshape(X, (X.shape[0], X.shape[1], 1))
    return X, y, scaler

# -------------------------
# BUILD LSTM MODEL
# -------------------------
def build_model(input_shape):
    model = Sequential()
    model.add(LSTM(units=50, return_sequences=True, input_shape=input_shape))
    model.add(Dropout(0.2))
    model.add(LSTM(units=50, return_sequences=False))
    model.add(Dropout(0.2))
    model.add(Dense(units=25))
    model.add(Dense(units=1))  # Next closing price
    model.compile(optimizer="adam", loss="mean_squared_error")
    return model

# -------------------------
# MAIN PIPELINE
# -------------------------
if __name__ == "__main__":
    print(f"Fetching {TICKER} data...")
    data = fetch_data(TICKER, START_DATE, END_DATE)

    print("Preprocessing data...")
    X, y, scaler = preprocess_data(data, LOOKBACK)

    split = int(len(X) * 0.8)
    X_train, X_test = X[:split], X[split:]
    y_train, y_test = y[:split], y[split:]

    print("Building and training LSTM model...")
    model = build_model((X_train.shape[1], 1))
    model.fit(X_train, y_train, batch_size=32, epochs=20, validation_data=(X_test, y_test))

    print("Making predictions...")
    predictions = model.predict(X_test)
    predictions = scaler.inverse_transform(predictions.reshape(-1, 1))
    y_test_scaled = scaler.inverse_transform(y_test.reshape(-1, 1))

    # Plot results
    plt.figure(figsize=(12, 6))
    plt.plot(y_test_scaled, color="blue", label="Actual Price")
    plt.plot(predictions, color="red", label="Predicted Price")
    plt.title(f"{TICKER} Stock Price Prediction")
    plt.xlabel("Time")
    plt.ylabel("Price")
    plt.legend()
    plt.show()

    # Predict next day
    last_lookback = data[-LOOKBACK:].values
    last_scaled = scaler.transform(last_lookback)
    X_input = np.reshape(last_scaled, (1, LOOKBACK, 1))
    next_day_pred = model.predict(X_input)
    next_day_price = scaler.inverse_transform(next_day_pred)[0][0]

    print(f"Predicted next day {TICKER} closing price: ${next_day_price:.2f}")
