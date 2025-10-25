def calculate_bmi(weight_kg, height_cm):
    """
    Calculate BMI given weight in kilograms and height in centimeters.
    BMI formula: weight (kg) / (height (m) ** 2)
    """
    height_m = height_cm / 100  # convert cm to meters
    bmi = weight_kg / (height_m ** 2)
    return round(bmi, 2)  # rounded to 2 decimal places

def categorize_bmi(bmi):
    """
    Categorize BMI according to WHO standards.
    """
    if bmi < 18.5:
        return "Underweight"
    elif 18.5 <= bmi < 24.9:
        return "Normal weight"
    elif 25 <= bmi < 29.9:
        return "Overweight"
    else:
        return "Obese"

def main():
    # User input
    try:
        weight = float(input("Enter your weight in kg: "))
        height = float(input("Enter your height in cm: "))

        bmi = calculate_bmi(weight, height)
        category = categorize_bmi(bmi)

        print(f"
Your BMI is: {bmi}")
        print(f"Category: {category}")
    except ValueError:
        print("Invalid input! Please enter numeric values for weight and height.")

if __name__ == "__main__":
    main()
