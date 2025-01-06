// Function to validate the login form
function validateLoginForm(event) {
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();

    if (username === '' || password === '') {
        alert('Both username and password are required.');
        event.preventDefault(); // Prevent form submission
    }
}

// Function to validate the registration form
function validateRegisterForm(event) {
    const username = document.getElementById('username').value.trim();
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value.trim();

    if (username === '' || email === '' || password === '') {
        alert('All fields are required.');
        event.preventDefault(); // Prevent form submission
    } else if (!validateEmail(email)) {
        alert('Please enter a valid email address.');
        event.preventDefault(); // Prevent form submission
    }
}

// Function to validate email format
function validateEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}

// Function to validate the add transaction form
function validateTransactionForm(event) {
    const amount = document.getElementById('amount').value.trim();
    const category = document.getElementById('category').value.trim();

    if (amount === '' || category === '') {
        alert('Both amount and category are required.');
        event.preventDefault(); // Prevent form submission
    } else if (isNaN(amount) || parseFloat(amount) <= 0) {
        alert('Please enter a valid positive number for the amount.');
        event.preventDefault(); // Prevent form submission
    }
}

// Attach event listeners to forms
document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.querySelector('form[action="/login"]');
    const registerForm = document.querySelector('form[action="/register"]');
    const transactionForm = document.querySelector('form[action="/addTransaction"]');

    if (loginForm) loginForm.addEventListener('submit', validateLoginForm);
    if (registerForm) registerForm.addEventListener('submit', validateRegisterForm);
    if (transactionForm) transactionForm.addEventListener('submit', validateTransactionForm);
});
