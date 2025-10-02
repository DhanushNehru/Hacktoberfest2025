import math

def haversine(lat1, lon1, lat2, lon2, unit="km"):
    """
    Calculate the Haversine distance between two points on Earth.
    
    Parameters:
        lat1, lon1: Latitude and Longitude of point 1 (in decimal degrees)
        lat2, lon2: Latitude and Longitude of point 2 (in decimal degrees)
        unit: "km" for kilometers, "mi" for miles
        
    Returns:
        Distance between the two points in the given unit.
    """
    # Convert latitude and longitude from degrees to radians
    lat1, lon1, lat2, lon2 = map(math.radians, [lat1, lon1, lat2, lon2])
    
    # Haversine formula
    dlat = lat2 - lat1
    dlon = lon2 - lon1
    a = math.sin(dlat/2)**2 + math.cos(lat1) * math.cos(lat2) * math.sin(dlon/2)**2
    c = 2 * math.asin(math.sqrt(a))
    
    # Radius of Earth: 6371 km or 3956 miles
    radius = 6371 if unit == "km" else 3956
    
    return c * radius


# Example usage
if __name__ == "__main__":
    # Manila, Philippines (14.5995° N, 120.9842° E)
    lat1, lon1 = 14.5995, 120.9842
    # Kuala Lumpur, Malaysia (3.1390° N, 101.6869° E)
    lat2, lon2 = 3.1390, 101.6869

    distance_km = haversine(lat1, lon1, lat2, lon2, "km")
    distance_mi = haversine(lat1, lon1, lat2, lon2, "mi")

    print(f"Distance: {distance_km:.2f} km")
    print(f"Distance: {distance_mi:.2f} miles")
