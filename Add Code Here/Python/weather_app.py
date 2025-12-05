# weather_app.py
# A simple weather app using OpenWeatherMap API

import requests

def get_weather(city: str, api_key: str) -> None:
    """Fetch and display weather details for a city."""
    base_url = "https://api.openweathermap.org/data/2.5/weather"
    params = {
        "q": city,
        "appid": api_key,
        "units": "metric"
    }
    
    response = requests.get(base_url, params=params)
    
    if response.status_code == 200:
        data = response.json()
        print(f"\n🌤 Weather in {city}:")
        print(f"Temperature: {data['main']['temp']}°C")
        print(f"Feels like: {data['main']['feels_like']}°C")
        print(f"Weather: {data['weather'][0]['description'].capitalize()}")
    else:
        print("❌ City not found or API error")

if __name__ == "__main__":
    print("=== Simple Weather App ===")
    city_name = input("Enter city name: ")
    
    # Get your free API key from: https://openweathermap.org/api
    api_key = "your_api_key_here"
    
    get_weather(city_name, api_key)
