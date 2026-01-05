# model_utils_original.py
# This is the original code without type hints.

def calculate_f1_score(precision, recall):
    """
    Calculates the F1 score from precision and recall.
    
    This function currently doesn't check the input types,
    leading to potential runtime errors if non-numeric inputs are provided.
    """
    if precision + recall == 0:
        return 0.0
    return 2 * (precision * recall) / (precision + recall)

def normalize_data(data):
    """
    Normalizes a list of numeric data to a 0-1 range.
    """
    min_val = min(data)
    max_val = max(data)
    
    if max_val == min_val:
        # Handle case where all values are the same
        return [0.0] * len(data)
        
    return [(x - min_val) / (max_val - min_val) for x in data]

# Example usage (will not be included in the contribution):
# print(calculate_f1_score(0.8, 0.7))
# print(normalize_data([10, 20, 30, 40]))
# model_utils_typed.py
# 'TypeGuard AI' Contribution: Code with Python Type Hinting

from typing import List, Union

def calculate_f1_score(precision: Union[int, float], recall: Union[int, float]) -> float:
    """
    Calculates the F1 score from precision and recall.

    Args:
        precision: The precision value (between 0 and 1).
        recall: The recall value (between 0 and 1).

    Returns:
        The calculated F1 score as a float.
    """
    # Type hints ensure static analysis tools (like MyPy) can check types
    # before runtime, improving code stability.
    if precision + recall == 0:
        return 0.0
    return 2 * (precision * recall) / (precision + recall)

def normalize_data(data: List[Union[int, float]]) -> List[float]:
    """
    Normalizes a list of numeric data to a 0-1 range.

    Args:
        data: A list containing integers or floats.

    Returns:
        A new list containing normalized values as floats.
    """
    if not data:
        return []
        
    min_val: Union[int, float] = min(data)
    max_val: Union[int, float] = max(data)
    
    if max_val == min_val:
        return [0.0] * len(data)
        
    return [(x - min_val) / (max_val - min_val) for x in data]

# Example usage (for testing, not part of the final contribution file):
if __name__ == '__main__':
    # Type hints help developers understand expected input/output
    f1: float = calculate_f1_score(precision=0.9, recall=0.8)
    print(f"F1 Score: {f1}")

    normalized: List[float] = normalize_data(data=[1, 5, 10, 25])
    print(f"Normalized Data: {normalized}") 
