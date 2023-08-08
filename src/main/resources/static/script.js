// script.js
// script.js

const fixedBoardId = 1; // Replace with your actual fixed board ID
const base_url = 'http://localhost:8080'; // Define the base API URL

// script.js

// Function to fetch and display the board title
async function displayBoardTitle() {
    try {
        const response = await fetch(`${base_url}/api/board/${fixedBoardId}`);
        if (!response.ok) {
            throw new Error(`Failed to fetch board title. Status: ${response.status} ${response.statusText}`);
        }
        const board = await response.json();
        const boardTitleElement = document.getElementById('editable-title');
        boardTitleElement.textContent = board.title;
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
}

// Function to update the board title using the input field
function updateBoardTitle() {
    const newBoardTitle = document.getElementById('newBoardTitle').value;
    if (newBoardTitle !== '') {
        document.getElementById('editable-title').textContent = newBoardTitle;
        updateTitle();
        sendUpdateTitleRequest(newBoardTitle);
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
// Call this function when the "Add Card" button is clicked
// Call this function when the "Add Card" button is clicked
function addCustomCard() {
    const apiEndpoint = `${base_url}/card/1`; // Use the correct API endpoint
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
    const apiEndpoint = `http://localhost:8080/card/${selectedID}`;

    fetch(apiEndpoint, {
        method: 'DELETE',
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`Failed to delete card. Status: ${response.status} ${response.statusText}`);
        }
        return response.json();
    })
    .then(data => {
        console.log('Card deleted:', data);
        // Call the displayCardsBySection function to refresh the UI
        displayCardsBySection();
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

function updateSelectedCard() {
    const cardID = document.getElementById('CardID').value;
    const newCardTitle = document.getElementById('updateCardTitle').value;
    const newCardDescription = document.getElementById('updateCardDescription').value;
    const newCardStatus = document.getElementById('updateCardStatus').value;

    const requestBody = {
        title: newCardTitle,
        description: newCardDescription,
        section: newCardStatus,
    };

    const apiEndpoint = `${base_url}/card/${cardID}`;

    fetch(apiEndpoint, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestBody),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error(`Failed to update card. Status: ${response.status} ${response.statusText}`);
        }
        return response.json();
    })
    .then(data => {
        console.log('Card updated:', data);

        // Update the card element on the UI with the updated data
        const cardElement = document.querySelector(`[data-card-id="${cardID}"]`);
        if (cardElement) {
            cardElement.querySelector('.card-title').textContent = newCardTitle;
            cardElement.querySelector('.card-description').textContent = newCardDescription;
        } else {
            console.error('Card element not found:', cardID);
        }

        // Call the displayCardsBySection function to refresh the UI
        displayCardsBySection();
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

// Function to display cards in each section
async function displayCardsBySection() {
    try {
        const response = await fetch(`${base_url}/card`); // Use the correct API endpoint
        if (!response.ok) {
            throw new Error(`Failed to fetch cards. Status: ${response.status} ${response.statusText}`);
        }
        const cards = await response.json();

        // Populate the delete dropdown menu with card IDs
        const deleteDropdown = document.getElementById('IDNumber');
        deleteDropdown.innerHTML = ''; // Clear existing options

        cards.forEach((card) => {
            const option = document.createElement('option');
            option.value = card.card_id;
            option.textContent = `Card ID: ${card.card_id}`;
            deleteDropdown.appendChild(option);
        });

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
                        <h3 data-card-id="${card.card_id}">Card ID: ${card.card_id}</h3>
                        <p class="card-title">Title: ${card.title}</p>
                        <p class="card-description">Description: ${card.description}</p>
                    `;

                    // Add the card to the corresponding container based on the section
                    if (card.section === "1") {
                        todoContainer.appendChild(cardElement);
                    } else if (card.section === "2") {
                        inProgressContainer.appendChild(cardElement);
                    } else if (card.section === "3") {
                        doneContainer.appendChild(cardElement);
                    }
                });
            } catch (error) {
                console.error('Error fetching cards:', error);
                alert('An error occurred while fetching cards.');
            }
        }


// Call the initialization function when the DOM is ready
document.addEventListener('DOMContentLoaded', () => {
    initializeUI();
    displayBoardTitle(); // Fetch and display the board title when the page loads
    displayCardsBySection(); // Fetch and display cards when the page loads
});




// Call the initialization function when the DOM is ready
document.addEventListener('DOMContentLoaded', () => {
    initializeUI();
    displayCardsBySection(); // Fetch and display cards when the page loads
});



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

    const updateCardButton = document.getElementById('updateCardButton'); // Add this line
    if (updateCardButton) {
        updateCardButton.addEventListener('click', updateSelectedCard); // Add this line
    }
}

// Call the initialization function when the DOM is ready
document.addEventListener('DOMContentLoaded', () => {
    initializeUI();
});
