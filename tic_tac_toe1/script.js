document.addEventListener('DOMContentLoaded', () => {
    const startGameButton = document.getElementById('startGameButton');
    const tryAgainButton = document.getElementById('tryAgainButton');
    const gameBoard = document.getElementById('gameBoard');
    const playerInput = document.getElementById('playerInput');
    const cells = document.querySelectorAll('.cell');
    const currentPlayerDisplay = document.getElementById('currentPlayer');
    const winnerMessage = document.getElementById('winnerMessage');
    const finalScore = document.getElementById('finalScore');
    const gameOverScreen = document.getElementById('gameOverScreen');

    let board = ['', '', '', '', '', '', '', '', ''];
    let currentPlayer = 'X';
    let player1Name, player2Name;
    let player1Color, player2Color;
    let isGameActive = true;

    // Update color previews when color inputs change
    document.getElementById('player1Color').addEventListener('input', (e) => {
        document.getElementById('player1ColorPreview').style.backgroundColor = e.target.value;
    });

    document.getElementById('player2Color').addEventListener('input', (e) => {
        document.getElementById('player2ColorPreview').style.backgroundColor = e.target.value;
    });

    startGameButton.addEventListener('click', () => {
        player1Name = document.getElementById('player1Name').value;
        player2Name = document.getElementById('player2Name').value;
        player1Color = document.getElementById('player1Color').value;
        player2Color = document.getElementById('player2Color').value;

        document.getElementById('player1ColorPreview').style.backgroundColor = player1Color;
        document.getElementById('player2ColorPreview').style.backgroundColor = player2Color;

        if (player1Name && player2Name) {
            playerInput.classList.add('hidden');
            gameBoard.classList.remove('hidden');
            currentPlayerDisplay.textContent = `Current Player: ${player1Name} (X)`;
            currentPlayerDisplay.style.color = player1Color;
            updateBoard();
        }
    });

    cells.forEach(cell => {
        cell.addEventListener('click', (e) => {
            const index = e.target.getAttribute('data-index');

            if (board[index] === '' && isGameActive) {
                board[index] = currentPlayer;
                e.target.textContent = currentPlayer;
                e.target.classList.add(currentPlayer.toLowerCase());
                if (checkWinner()) {
                    isGameActive = false;
                    winnerMessage.textContent = `Player ${currentPlayer === 'X' ? player1Name : player2Name} wins!`;
                    winnerMessage.classList.remove('hidden');
                    gameOverScreen.classList.remove('hidden');
                    finalScore.textContent = calculateScore();
                } else if (board.every(cell => cell !== '')) {
                    winnerMessage.textContent = 'It\'s a draw!';
                    winnerMessage.classList.remove('hidden');
                    gameOverScreen.classList.remove('hidden');
                    isGameActive = false;
                } else {
                    currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
                    currentPlayerDisplay.textContent = `Current Player: ${currentPlayer === 'X' ? player1Name : player2Name} (${currentPlayer})`;
                    currentPlayerDisplay.style.color = currentPlayer === 'X' ? player1Color : player2Color;
                }
            }
        });
    });

    tryAgainButton.addEventListener('click', () => {
        location.reload(); // Refresh the page to restart the game
    });

    function checkWinner() {
        const winPatterns = [
            [0, 1, 2],
            [3, 4, 5],
            [6, 7, 8],
            [0, 3, 6],
            [1, 4, 7],
            [2, 5, 8],
            [0, 4, 8],
            [2, 4, 6]
        ];

        return winPatterns.some(pattern => {
            const [a, b, c] = pattern;
            return board[a] && board[a] === board[b] && board[a] === board[c];
        });
    }

    function calculateScore() {
        return 100; // Placeholder score
    }
});
