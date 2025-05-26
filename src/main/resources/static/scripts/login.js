document.addEventListener("DOMContentLoaded", function () {
  // Verificar si ya está logueado
  const authToken = localStorage.getItem('authToken');
  if (authToken) {
    window.location.href = '/home';
    return;
  }

  const loginForm = document.querySelector("form");
  const loginButton = document.querySelector("button[type='submit']");
  const errorMessage = document.getElementById("errorMessage");

  if (loginForm) {
    loginForm.addEventListener("submit", function (event) {
      event.preventDefault();
      event.stopPropagation();

      // Show loading state
      loginButton.disabled = true;
      loginButton.textContent = "Ingresando...";
      errorMessage.style.display = "none";

      const username = document.querySelector('input[name="username"]').value;
      const password = document.querySelector('input[name="password"]').value;

      // Create user object to send to backend
      const userData = {
        user: username,
        password: password,
      };

      // Send login request to backend
      fetch("/users/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(userData),
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Credenciales inválidas");
          }
          return response.text();
        })
        .then((token) => {
          if (token) {
            localStorage.setItem("authToken", token);
            localStorage.setItem("userRole", "dentist");
            window.location.href = "/home";
          } else {
            throw new Error("Error de autenticación");
          }
        })
        .catch((error) => {
          console.error("Error:", error);
          errorMessage.textContent = error.message || "Error al iniciar sesión";
          errorMessage.style.display = "block";
          loginButton.disabled = false;
          loginButton.textContent = "Ingresar";
        });
    });
  }
});
