document.addEventListener('DOMContentLoaded', () => {
  // Registration form validation
  const regForm = document.getElementById('form');
  if (regForm) {
    regForm.addEventListener('submit', event => {
      let valid = true;
      // Username: alphanumeric, 3-16 chars
      const user = document.getElementById('user');
      const userPattern = /^[a-zA-Z0-9]{3,16}$/;
      const userMsg = document.getElementById('control_username');
      if (!userPattern.test(user.value)) {
        userMsg.textContent = 'Username non valido (3-16 caratteri alfanumerici)';
        valid = false;
      } else {
        userMsg.textContent = '';
      }
      // Email: basic pattern
      const email = document.getElementById('email');
      const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      const emailMsg = document.getElementById('control_email');
      if (!emailPattern.test(email.value)) {
        emailMsg.textContent = 'Email non valida';
        valid = false;
      } else {
        emailMsg.textContent = '';
      }
      // Password: min 6 chars
      const pass = document.getElementById('password');
      const passMsg = document.getElementById('pa');
      if (pass.value.length < 6) {
        passMsg.textContent = 'Password troppo corta (min 6 caratteri)';
        valid = false;
      } else {
        passMsg.textContent = '';
      }
      if (!valid) {
        event.preventDefault();
      }
    });
  }
});
