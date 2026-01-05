'use strict';

const fs = require('fs');
const readline = require('readline');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
    terminal: false
});

const timeConversion = (timeStr) => {
    const trimmed = timeStr.trim().toUpperCase();
    if (!/^(0[0-9]|1[0-2]):[0-5][0-9]:[0-5][0-9](AM|PM)$/.test(trimmed)) {
        throw new Error('Invalid time format. Expected hh:mm:ssAM or hh:mm:ssPM');
    }

    const period = trimmed.slice(-2);
    let [hour, minute, second] = trimmed.slice(0, -2).split(':').map(Number);

    if (period === 'AM' && hour === 12) hour = 0;
    if (period === 'PM' && hour !== 12) hour += 12;

    const formattedHour = hour.toString().padStart(2, '0');
    return `${formattedHour}:${minute.toString().padStart(2, '0')}:${second.toString().padStart(2, '0')}`;
};

const main = async () => {
    for await (const line of rl) {
        try {
            const result = timeConversion(line);
            console.log(result);
        } catch (error) {
            console.error(error.message);
        }
        rl.close();
    }
};

main();
