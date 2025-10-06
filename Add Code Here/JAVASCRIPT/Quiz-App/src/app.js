console.log('Moazam');
const question = [
  {
    ques: 'Which of the following is the markup Language?',
    a: 'HTML',
    b: 'CSS',
    c: 'JavaScript',
    d: 'PHP',
    correct: 'a',
  },
  //  2 nd qs
  {
    ques: 'In Which JavaScript was launched?',
    a: '1996',
    b: '1995',
    c: '1998',
    d: '2000',
    correct: 'b',
  },
  {
    ques: 'What does CSS stands for?',
    a: 'Cascade Style Sheet',
    b: 'Cascading Style Sheet',
    c: 'Hyper Text Markup Lanaguage',
    d: 'Jason object Notation',
    correct: 'b',
  },
];

const total = question.length;
const display = document.querySelector('.box');
const ques = document.querySelector('.Ques');
const options = document.querySelectorAll('.options');
const btn = document.querySelector('.submit');
const again = document.querySelector('.again');
let index = 0;
let right = 0;
let wrong = 0;

// for without result
// const emptyQues = function () {
//   display.innerHTML = '';
//   loadQues();
// };

const loadQues = function () {
  if (index !== total) {
    console.log(index);
    let data = question[index];
    console.log(data);
    /*
    const html = ` <h1 class="Ques">Q${index + 1}) ${data.ques} </h1>
  <div class="row">
    <input
      type="radio"
      class="options"
      id="option1"
      value="a"
      name="ques"
    />
    <label for="option">${data.a}</label>
  </div>
  <div class="row">
    <input
      type="radio"
      class="options"
      id="option2"
      value="b"
      name="ques"
    />
    <label for="option">${data.b}</label>
  </div>
  <div class="row">
    <input
      type="radio"
      class="options"
      id="option3"
      value="c"
      name="ques"
    />
    <label for="option">${data.c}</label>
  </div>

  <div class="row">
    <input
      type="radio"
      class="options"
      id="option4"
      value="d"
      name="ques"
    />
    <label for="option">${data.d}</label>
  </div>
  <button class="btn submit"   onclick="getResult()">Submit</button>`;
  */

    //////// this is for without try again button
    ques.innerText = `Q${index + 1}) ${data.ques}`;

    options[0].nextElementSibling.innerText = data.a;
    options[1].nextElementSibling.innerText = data.b;
    options[2].nextElementSibling.innerText = data.c;
    options[3].nextElementSibling.innerText = data.d;

    // for without result correct
    // display.innerHTML = html;
  } else {
    endQuiz();
  }
};
const getResult = function () {
  let data = question[index];
  console.log(data);
  const answer = checkAnswer();
  if (answer == data.correct) {
    right++;
  } else {
    wrong++;
  }
  index++;
  reset();
  loadQues();
  return;
};
const reset = () => {
  options.forEach(input => {
    input.checked = false;
  });
};

const checkAnswer = function () {
  let answer;
  options.forEach(input => {
    if (input.checked) {
      answer = input.value;
    }
  });
  return answer;
};
const endQuiz = () => {
  if (index !== total) {
    console.log(display);
    display.innerHTML = '';
  }
  let html = '';
  if (right === total) {
    html = `Excellent👏`;
  } else if (right == total - 1) {
    html = `Good 👍`;
  } else if (right == total - 2) {
    html = `Satisfactory `;
  } else {
    html = `Better Luck Next time`;
  }
  //   console.log(display);
  // again.classList.remove('hidden');
  display.innerHTML = `
  
  <h2 class="head">Thank you for solving the quiz!! </h2>
    <h3 class="marks">Correct Options: ${right}/${total}
    </h3>
    <h2 class="result">${html}</h2>
`;
  // without result
  //    <button class="btn again" onclick="againStart()">
  // Try Again
  // </button>
};

// withput result
// function againStart() {
//   console.log(index);
//   index = 0;
//   right = 0;
//   emptyQues();
// }

// in start load Question
loadQues();
// this is for without try agan button
btn.addEventListener('click', getResult);
// console.log(again);
