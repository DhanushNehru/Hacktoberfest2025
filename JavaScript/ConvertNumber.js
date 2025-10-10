function ConvertNumber(num) {
  return num.toString().replace(/(\d)(?=(\d{3})+(?!\d))/g, "$1,");
}

console.log(ConvertNumber(123456789));
