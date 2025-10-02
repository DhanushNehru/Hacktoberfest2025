/**
 * Evaluate the strength of a given password.
 * 
 * Rules:
 *  - +1 point if length >= 8
 *  - +1 point if length >= 12 (bonus for longer passwords)
 *  - +1 point if contains lowercase
 *  - +1 point if contains uppercase
 *  - +1 point if contains digits
 *  - +1 point if contains special characters
 * 
 * Returns: "Very Weak", "Weak", "Moderate", "Strong", "Very Strong"
 */
function checkPasswordStrength(password) {
    if (typeof password !== "string") {
        throw new TypeError("Password must be a string");
    }

    const rules = [
        { test: (pwd) => pwd.length >= 8 },
        { test: (pwd) => pwd.length >= 12 }, // length bonus
        { test: (pwd) => /[a-z]/.test(pwd) },
        { test: (pwd) => /[A-Z]/.test(pwd) },
        { test: (pwd) => /\d/.test(pwd) },
        { test: (pwd) => /[^A-Za-z0-9]/.test(pwd) }
    ];

    // Count how many rules passed
    let score = rules.reduce((acc, rule) => acc + (rule.test(password) ? 1 : 0), 0);

    // Normalize score (cap at 5 for readability)
    score = Math.min(score, 5);

    const levels = ["Very Weak", "Weak", "Moderate", "Strong", "Very Strong"];
    return levels[score];
}
