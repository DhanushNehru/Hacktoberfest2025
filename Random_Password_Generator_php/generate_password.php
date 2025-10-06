<?php
function generatePassword($length, $includeUppercase, $includeLowercase, $includeNumbers, $includeSymbols) {
    $uppercase = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    $lowercase = 'abcdefghijklmnopqrstuvwxyz';
    $numbers = '0123456789';
    $symbols = '!@#$%^&*()_+-=[]{}|;:,.<>?';
    
    $characters = '';
    if ($includeUppercase === 'true') $characters .= $uppercase;
    if ($includeLowercase === 'true') $characters .= $lowercase;
    if ($includeNumbers === 'true') $characters .= $numbers;
    if ($includeSymbols === 'true') $characters .= $symbols;

    // Ensure there are characters to select from
    if ($characters === '') return 'Please select at least one character set.';

    $password = '';
    for ($i = 0; $i < $length; $i++) {
        $password .= $characters[random_int(0, strlen($characters) - 1)];
    }
    
    return $password;
}

// Get form data
$length = isset($_POST['length']) ? (int)$_POST['length'] : 12;
$includeUppercase = $_POST['includeUppercase'] ?? 'false';
$includeLowercase = $_POST['includeLowercase'] ?? 'false';
$includeNumbers = $_POST['includeNumbers'] ?? 'false';
$includeSymbols = $_POST['includeSymbols'] ?? 'false';

// Generate and output the password
echo generatePassword($length, $includeUppercase, $includeLowercase, $includeNumbers, $includeSymbols);
?>
