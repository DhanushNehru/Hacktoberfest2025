from itertools import permutations

def get_all_permutations_itertools(items):
  """
  Generates all permutations of a given iterable using itertools.permutations.

  Args:
    items: An iterable (e.g., list, string, tuple).

  Returns:
    A list of all permutations as tuples.
  """
  return list(permutations(items))

# Example usage:
my_list = [1, 2, 3]
all_perms = get_all_permutations_itertools(my_list)
print(f"Permutations of {my_list}: {all_perms}")

my_string = "abc"
all_string_perms = ["".join(p) for p in permutations(my_string)]
print(f"Permutations of '{my_string}': {all_string_perms}")
