function convertTemperature() {
}
const tempInput = document.getElementById('temperature');
const unitSelect = document.getElementById('unit');
const resultBox = document.getElementById('result');
const clearBtn = document.getElementById('clearBtn');

function showResult(text, isError = false) {
    resultBox.textContent = text;
    resultBox.classList.remove('error');
    void resultBox.offsetWidth; 
    resultBox.classList.add('show', 'pulse');
    if (isError) resultBox.classList.add('error');
    setTimeout(() => resultBox.classList.remove('pulse'), 900);
}

function clearResult() {
    resultBox.textContent = '';
    resultBox.classList.remove('show', 'pulse', 'error');
}

function convertTemperature() {
    const val = tempInput.value.trim();
    if (val === '') {
        showResult('Please enter a temperature value.', true);
        return;
    }

    const temperature = parseFloat(val);
    if (Number.isNaN(temperature)) {
        showResult('Invalid number. Try again.', true);
        return;
    }

    const unit = unitSelect.value;

    let resultC, resultF, resultK;

    if (unit === 'celsius') {
        resultF = (temperature * 9 / 5) + 32; 
        resultK = temperature + 273.15; 
        showResult(`${temperature.toFixed(2)} °C = ${resultF.toFixed(2)} °F • ${resultK.toFixed(2)} K`);
    } else if (unit === 'fahrenheit') {
        resultC = (temperature - 32) * 5 / 9; 
        resultK = resultC + 273.15; 
        showResult(`${temperature.toFixed(2)} °F = ${resultC.toFixed(2)} °C • ${resultK.toFixed(2)} K`);
    } else if (unit === 'kelvin') {
        resultC = temperature - 273.15; 
        resultF = (resultC * 9 / 5) + 32;
        showResult(`${temperature.toFixed(2)} K = ${resultC.toFixed(2)} °C • ${resultF.toFixed(2)} °F`);
    } else {
        showResult('Unknown unit selected.', true);
    }
}


clearBtn.addEventListener('click', () => {
    tempInput.value = '';
    clearResult();
    tempInput.focus();
});


tempInput.addEventListener('keydown', (e) => {
    if (e.key === 'Enter') convertTemperature();
});


clearResult();

