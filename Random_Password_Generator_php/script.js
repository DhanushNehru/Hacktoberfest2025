document.getElementById('passwordForm').addEventListener('submit', function(event) {
  event.preventDefault();

  const length = document.getElementById('length').value;
  const includeUppercase = document.getElementById('includeUppercase').checked;
  const includeLowercase = document.getElementById('includeLowercase').checked;
  const includeNumbers = document.getElementById('includeNumbers').checked;
  const includeSymbols = document.getElementById('includeSymbols').checked;

  const xhr = new XMLHttpRequest();
  xhr.open('POST', 'generate_password.php', true);
  xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  xhr.onload = function() {
    if (xhr.status === 200) {
      document.getElementById('generatedPassword').innerText = xhr.responseText;
    }
  };
  xhr.send(`length=${length}&includeUppercase=${includeUppercase}&includeLowercase=${includeLowercase}&includeNumbers=${includeNumbers}&includeSymbols=${includeSymbols}`);
});
