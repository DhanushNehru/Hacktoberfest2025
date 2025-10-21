function factorial(n) {
  if (n < 0) return "Invalid input";
  let result = 1;
  for (let i = 2; i <= n; i++) {
    result *= i;
  }
  return result;
}

console.log("5! =", factorial(5));
console.log("0! =", factorial(0));
