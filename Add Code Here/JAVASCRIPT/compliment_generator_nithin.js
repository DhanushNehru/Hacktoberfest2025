// LANGUAGE: JavaScript
// ENV: Node.js / Browser
// AUTHOR: Nithin S
// GITHUB: https://github.com/NITHIN-S-18

// Random Compliment Generator for Hacktoberfest 2025
const compliments = [
    "You are amazing!",
    "Your code is elegant.",
    "Hacktoberfest is lucky to have you!",
    "Keep shining, coder! 😎",
    "Your contributions inspire others!"
];

function giveCompliment() {
    const randomIndex = Math.floor(Math.random() * compliments.length);
    console.log(compliments[randomIndex]);
}

// Generate 3 random compliments
for (let i = 0; i < 3; i++) {
    giveCompliment();
}
