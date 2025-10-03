"""
datetime_utils.py

A collection of date and time utility functions.
Useful for scheduling, logging, and general projects.

Author: Soumya
"""

from datetime import datetime, timedelta
from typing import List


def current_datetime() -> str:
    """
    Get the current date and time as a formatted string.

    Returns:
        str: Current datetime in "YYYY-MM-DD HH:MM:SS" format.
    """
    return datetime.now().strftime("%Y-%m-%d %H:%M:%S")


def days_between(date1: str, date2: str) -> int:
    """
    Calculate the number of days between two dates.

    Args:
        date1 (str): First date in "YYYY-MM-DD" format.
        date2 (str): Second date in "YYYY-MM-DD" format.

    Returns:
        int: Number of days between the two dates.
    """
    d1 = datetime.strptime(date1, "%Y-%m-%d")
    d2 = datetime.strptime(date2, "%Y-%m-%d")
    return abs((d2 - d1).days)


def add_days(date: str, days: int) -> str:
    """
    Add days to a given date.

    Args:
        date (str): Input date in "YYYY-MM-DD" format.
        days (int): Number of days to add.

    Returns:
        str: New date in "YYYY-MM-DD" format.
    """
    d = datetime.strptime(date, "%Y-%m-%d")
    return (d + timedelta(days=days)).strftime("%Y-%m-%d")


def week_dates(start_date: str) -> List[str]:
    """
    Get all dates in a week starting from a given date.

    Args:
        start_date (str): Input date in "YYYY-MM-DD" format.

    Returns:
        List[str]: List of 7 dates in the week.
    """
    d = datetime.strptime(start_date, "%Y-%m-%d")
    return [(d + timedelta(days=i)).strftime("%Y-%m-%d") for i in range(7)]


def is_weekend(date: str) -> bool:
    """
    Check if a given date falls on a weekend.

    Args:
        date (str): Input date in "YYYY-MM-DD" format.

    Returns:
        bool: True if weekend, False otherwise.
    """
    d = datetime.strptime(date, "%Y-%m-%d")
    return d.weekday() >= 5  # Saturday=5, Sunday=6


if __name__ == "__main__":
    # Example usage
    print("Current datetime:", current_datetime())
    print("Days between 2025-01-01 and 2025-10-02:", days_between("2025-01-01", "2025-10-02"))
    print("Add 10 days to 2025-10-02:", add_days("2025-10-02", 10))
    print("Week dates from 2025-10-01:", week_dates("2025-10-01"))
    print("Is 2025-10-05 weekend?", is_weekend("2025-10-05"))
