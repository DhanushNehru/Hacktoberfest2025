'use strict';

process.stdin.resume();
process.stdin.setEncoding('utf-8');

let inputString = '';
let currentLine = 0;

process.stdin.on('data', function(inputStdin) {
    inputString += inputStdin;
});

process.stdin.on('end', function() {
    inputString = inputString.split('\n');
    main();
});

function readLine() {
    return inputString[currentLine++];
}


function miniMaxSum(arr) {
    // Sort the array to get the smallest and largest elements easily
    arr.sort((a, b) => a - b);
    
    // Calculate the minimum sum (sum of the first four elements)
    const minSum = arr.slice(0, 4).reduce((sum, num) => sum + num, 0);
    
    // Calculate the maximum sum (sum of the last four elements)
    const maxSum = arr.slice(1).reduce((sum, num) => sum + num, 0);
    
    // Print the results
    console.log(minSum, maxSum);
}

function main() {
    const arr = readLine().replace(/\s+$/g, '').split(' ').map(arrTemp => parseInt(arrTemp, 10));
    miniMaxSum(arr);
}
