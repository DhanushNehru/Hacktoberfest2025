'use strict';

const fs = require('fs');

process.stdin.resume();
process.stdin.setEncoding('utf-8');

let inputString = '';
let currentLine = 0;

process.stdin.on('data', function(inputStdin) {
    inputString += inputStdin;
});

process.stdin.on('end', function() {
    inputString = inputString.trim().split('\n');
    main();
});

function readLine() {
    return inputString[currentLine++];
}

function timeConversion(s) {
    const period = s.slice(-2);  // Extract AM or PM
    let hour = parseInt(s.slice(0, 2));  // Extract hour as an integer
    const rest = s.slice(2, 8);  // Extract the remaining time part (minutes and seconds)

    if (period === 'AM') {
        if (hour === 12) {
            hour = 0;  // Midnight case: 12 AM -> 00
        }
    } else {  // PM case
        if (hour !== 12) {
            hour += 12;  // Convert PM hour (except 12 PM)
        }
    }

    const hourStr = hour < 10 ? '0' + hour : hour.toString();  // Ensure two-digit hour format
    return hourStr + rest;
}

function main() {
    const s = readLine();
    const result = timeConversion(s);
    console.log(result);
}