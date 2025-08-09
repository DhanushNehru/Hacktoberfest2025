<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Real-Time Typing Speed Tracker</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      text-align: center;
      margin: 20px;
    }
    textarea {
      width: 90%;
      height: 100px;
      font-size: 18px;
      padding: 10px;
    }
    .stats {
      margin-top: 20px;
    }
    #wpm {
      font-size: 24px;
      color: green;
    }
  </style>
</head>
<body>

  <h1>Real-Time Typing Speed Tracker</h1>
  <p>Type in the box below and see your words per minute (WPM) update in real-time!</p>
  
  <textarea id="typingArea" placeholder="Start typing..."></textarea>
  
  <div class="stats">
    <p>Words per Minute (WPM): <span id="wpm">0</span></p>
    <p>Time Elapsed: <span id="timeElapsed">0</span> seconds</p>
  </div>

  <script>
    const typingArea = document.getElementById('typingArea');
    const wpmDisplay = document.getElementById('wpm');
    const timeElapsedDisplay = document.getElementById('timeElapsed');
    
    let startTime = null;
    let timerInterval = null;

    typingArea.addEventListener('input', handleTyping);

    function handleTyping() {
      if (!startTime) {
        startTime = new Date().getTime();
        timerInterval = setInterval(updateStats, 1000); // Update stats every second
      }
      
      const currentText = typingArea.value;
      const wordsTyped = countWords(currentText);
      const timeElapsedInSeconds = (new Date().getTime() - startTime) / 1000;
      const wordsPerMinute = calculateWPM(wordsTyped, timeElapsedInSeconds);
      
      wpmDisplay.textContent = Math.floor(wordsPerMinute);
    }

    function updateStats() {
      const timeElapsedInSeconds = (new Date().getTime() - startTime) / 1000;
      timeElapsedDisplay.textContent = Math.floor(timeElapsedInSeconds);
    }

    function countWords(text) {
      return text.trim().split(/\s+/).filter(word => word.length > 0).length;
    }

    function calculateWPM(words, seconds) {
      return (words / seconds) * 60;
    }
  </script>

</body>
</html>
