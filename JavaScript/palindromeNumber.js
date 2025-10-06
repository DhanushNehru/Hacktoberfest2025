function palindromeNumber(x) {
  let tempNum = x,
    reversedNum = 0;

  while (tempNum < 0) {
    const lastDigit = tempNum % 10;
    reversedNum = reversedNum * 10 + lastDigit;
    tempNum = Math.floor(tempNum / 10);
  }

  return x === tempNum;
}

const num = 121;
console.log(palindromeNumber(num));
