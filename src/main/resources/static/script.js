// script.js
// script.js

const fixedBoardId = 1; // Replace with your actual fixed board ID
const base_url = 'http://localhost:8080/api/boards/1'; // Define the base API URL

// script.js

// Function to fetch and display the board title
async function displayBoardTitle() {
    try {
        const response = await fetch(`http://localhost:8080/api/boards/1`);
        if (!response.ok) {
            throw new Error(`Failed to fetch board title. Status: ${response.status} ${response.statusText}`);
        }
        const board = await response.json();
        console.log('Board Data:', board); // Log the board data
        const boardTitleElement = document.getElementById("editable-title");
        boardTitleElement.textContent = board.name;
        updateTitle(); // Update the browser title with the new board title
    } catch (error) {
        console.error('Error fetching board title:', error);
        alert('An error occurred while fetching the board title.');
    }
}



// Function to update the board title
function updateTitle() {
    const newTitle = document.getElementById('editable-title').textContent;
    document.title = newTitle;


    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
        "name": newTitle
    });

    var requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch("http://localhost:8080/api/boards/1", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));





}

// Function to update the board title using the input field
function updateBoardTitle() {
    const newBoardTitle = document.getElementById('newBoardTitle').value;
    if (newBoardTitle !== '') {
        document.getElementById('editable-title').textContent = newBoardTitle;
        updateTitle();
    }
}

// Function to send a request to the API to update the board title
function sendUpdateTitleRequest(newTitle) {
    const apiEndpoint = `http://localhost:8080/api/board/1`;

    fetch(apiEndpoint, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ title: newTitle }),
    })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch(error => console.error('Error:', error));
}


// Call this function when the "Add Card" button is clicked
function addCustomCard() {
    const apiEndpoint = `${base_url}/cards`; // Use the correct API endpoint
    const cardTitle = document.getElementById('cardTitle').value;
    const cardDescription = document.getElementById('cardDescription').value;
    const taskPriority = document.getElementById('cardStatus').value;
    let cardSection;

    switch (taskPriority) {
        case "todo":
            cardSection = 1;
            break;
        case "in-progress":
            cardSection = 2;
            break;
        case "done":
            cardSection = 3;
            break;
        default:
            cardSection = 1; // Default to "high" priority
            break;
    }

    const requestBody = {
        section: cardSection,
        title: cardTitle,
        description: cardDescription,
    };

    fetch(apiEndpoint, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestBody),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Failed to create card. Status: ${response.status} ${response.statusText}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Card created:', data);

            // Call the displayCardsBySection function to refresh the UI
            displayCardsBySection();
        })
        .catch(error => {
            console.error('Error:', error);
        });
}





// Call this function when the "Delete Cards" button is clicked
function deleteSelectedCard() {
    const selectedID = document.getElementById('IDNumber').value;

    var requestOptions = {
        method: 'DELETE',
        redirect: 'follow'
    };

    fetch(`http://localhost:8080/api/boards/1/cards/${selectedID}`, requestOptions)
        .then(response => response.text())
        .then(result => {
            console.log(result)
            location.reload();
        })
        .catch(error => console.log('error', error));




}




// Function to get status text based on status code
function getStatusText(statusCode) {
    switch (statusCode) {
        case "1":
            return "TODO";
        case "2":
            return "In Progress";
        case "3":
            return "Done";
        default:
            return "Unknown";
    }
}



// Function to display cards in each section
async function displayCardsBySection() {
    try {
        const response = await fetch(`${base_url}/cards`);
        if (!response.ok) {
            throw new Error(`Failed to fetch cards. Status: ${response.status} ${response.statusText}`);
        }
        const cards = await response.json();

        // Get the containers for each section
        const todoContainer = document.querySelector(".todo-column");
        const inProgressContainer = document.querySelector(".in-progress-column");
        const doneContainer = document.querySelector(".done-column");

        // Clear previous content in containers
        todoContainer.innerHTML = '<h2>TODO</h2>';
        inProgressContainer.innerHTML = '<h2>IN PROGRESS</h2>';
        doneContainer.innerHTML = '<h2>DONE</h2>';

        // Loop through each card and create card elements
        cards.forEach((card) => {
            const cardElement = document.createElement('div');
            cardElement.classList.add('task-card'); // Add the 'task-card' class for styling
            cardElement.innerHTML = `
                    <div class="card">
                   <h3 data-card-id="${card.card_id}" class="card-id">Card ID: ${card.card_id}</h3>
                   <p class="card-title" id="title-${card.card_id}">Title: ${card.title}</p>
                   <p class="card-description" id="description-${card.card_id}">Description: ${card.description}</p>
                   <p class="card-status">Status: ${getStatusText(card.section)}</p>
                   </div>
               `;

            console.log('Card Status:', card.section); // Log the status
            console.log('Status Text:', getStatusText(card.section)); // Log the status text


            // Add the card to the corresponding container based on the section
            if (card.section === "1") {
                todoContainer.appendChild(cardElement);
            } else if (card.section === "2") {
                inProgressContainer.appendChild(cardElement);
            } else if (card.section === "3") {
                doneContainer.appendChild(cardElement);
            }
        });

        // Populate the delete dropdown menu with card IDs
        const deleteDropdown = document.getElementById('IDNumber');
        deleteDropdown.innerHTML = ''; // Clear existing options

        cards.forEach((card) => {
            const option = document.createElement('option');
            option.value = card.card_id;
            option.textContent = `Card ID: ${card.card_id}`;
            deleteDropdown.appendChild(option);
        });
    } catch (error) {
        console.error('Error fetching cards:', error);
        alert('An error occurred while fetching cards.');
    }
    displayBoardTitle();
}




// Function to update a selected card
function updateSelectedCard() {
    const selectedID = document.getElementById('CardID').value;
    let updatedCardTitle;
    if(document.getElementById('updateCardTitle').value === ""){
        updatedCardTitle = document.getElementById("title-"+selectedID).textContent;
    }
    else{
        updatedCardTitle = document.getElementById('updateCardTitle').value;
    }

    let updatedCardDescription;
    if(document.getElementById('updateCardDescription').value === ""){
        updatedCardDescription = document.getElementById("description-"+selectedID).textContent;
    }
    else{
        updatedCardDescription = document.getElementById('updateCardDescription').value;
    }

    const updatedCardStatus = document.getElementById('updateCardStatus').value; // Get the integer value

    // const apiEndpoint = `http://localhost:8080/card/${selectedID}`;
    //-------------------------------------------------------------

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
        "title": updatedCardTitle,
        "description": updatedCardDescription,
        "section": updatedCardStatus
    });

    var requestOptions = {
        method: 'PUT',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch(`http://localhost:8080/api/boards/1/cards/${selectedID}`, requestOptions)
        .then(response => response.text())
        .then(result => location.reload())
        .catch(error => console.log('error', error));




}

// Initialize the UI and set event listeners
function initializeUI() {
    const updateBoardTitleButton = document.getElementById('updateBoardTitleButton');
    if (updateBoardTitleButton) {
        updateBoardTitleButton.addEventListener('click', updateBoardTitle);
    }

    const addCardButton = document.getElementById('addCardButton');
    if (addCardButton) {
        addCardButton.addEventListener('click', addCustomCard);
    }

    const deleteCardsButton = document.getElementById('deleteCardsButton');
    if (deleteCardsButton) {
        deleteCardsButton.addEventListener('click', deleteSelectedCard);
    }

    // const updateCardButton = document.getElementById('updateCardButton');
    // if (updateCardButton) {
    //     updateCardButton.addEventListener('click',updateSelectedCard() );
    // }
}

// Call the initialization function when the DOM is ready
document.addEventListener('DOMContentLoaded', () => {
    initializeUI();
    displayCardsBySection(); // Fetch and display cards when the page loads
    displayBoardTitle(); // Fetch and display the board title after the cards are displayed
});

