//if user adds a note, add it to the local storage
let addBtn = document.getElementById('addBtn');
addBtn.addEventListener('click', function (e) {
    let addTxt = document.getElementById('addText');
    let notes = localStorage.getItem('notes'); //getting notes from local storage
    
    if (notes == null) {
        notesObj = []; //empty array
    } else {
        notesObj = JSON.parse(notes); //converting string to object
    }
    notesObj.push(addTxt.value);
    localStorage.setItem('notes', JSON.stringify(notesObj)); //converting object to string
    addTxt.value = '';
    console.log(notesObj);
    showNotes();
})

document.addEventListener('DOMContentLoaded', (event) => {
    showNotes();
});

//show added notes
function showNotes(){
    let notes = localStorage.getItem('notes'); //getting notes from local storage
    if (notes == null) {
        notesObj = []; //empty array
    } else {
        notesObj = JSON.parse(notes); //converting string to object
    }
    let html = '';
    notesObj.forEach(function(element, index){ //element is the note and index is the index of the note
        html += `
        <div class="noteCard my-2 mx-2 card" style="width: 18rem;">
        <div class="card-body">
            <h5 class="card-title">Note ${index+1}</h5>
            <p class="card-text">${element}</p>
            <button id="${index}" onclick="deleteNode(${index})" class="btn btn-primary">Delete Note</button>
        </div>
    </div>`; //adding notes to the html
    });
    let notesElm = document.getElementById('notes');
    if(notesObj.length != 0){
        notesElm.innerHTML = html;
    }else{
        notesElm.innerHTML = `Nothing to show! Use "Add a Note" section above to add notes.`;
    }
}

//delete a note
function deleteNode(index){
    // console.log('I am deleting', index);

    let notes = localStorage.getItem('notes');
    if (notes == null) {
        notesObj = []; //empty array
    } else {
        notesObj = JSON.parse(notes); //converting string to object
    }
    notesObj.splice(index, 1); //deleting the note
    localStorage.setItem('notes', JSON.stringify(notesObj)); //converting object to string
    showNotes();
}

//search notes
let search = document.getElementById('searchTxt');
search.addEventListener('input', function(){
    let inputVal = search.value.toLowerCase();
    // console.log('Input event fired!', inputVal);

    let noteCards = document.getElementsByClassName('noteCard');
    Array.from(noteCards).forEach(function(element){
        let cardTxt = element.getElementsByTagName('p')[0].innerText;
        if(cardTxt.includes(inputVal)){
            element.style.display = 'block';
        }else{
            element.style.display = 'none';
        }
        // console.log(cardTxt);
    })
})
