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

    
    if ($characters === '') return 'Please select at least one character set.';

    $password = '';
    $charLen = strlen($characters); 
    for ($i = 0; $i < $length; $i++) {
        $password .= $characters[random_int(0, $charLen - 1)];
    }
    
    return $password;
}
$length = isset($_POST['length']) ? (int)$_POST['length'] : 12;
$includeUppercase = $_POST['includeUppercase'] ?? 'false';
$includeLowercase = $_POST['includeLowercase'] ?? 'false';
$includeNumbers = $_POST['includeNumbers'] ?? 'false';
$includeSymbols = $_POST['includeSymbols'] ?? 'false';

echo generatePassword($length, $includeUppercase, $includeLowercase, $includeNumbers, $includeSymbols);
?>
