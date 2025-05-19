document.addEventListener("DOMContentLoaded", function () {
  const loginForm = document.querySelector("form");
  const loginButton = document.querySelector("button[type='submit']");
  const errorMessage = document.createElement("div");
  errorMessage.className = "error-message";
  errorMessage.style.color = "red";
  errorMessage.style.marginTop = "10px";
  errorMessage.style.display = "none";

  if (loginForm) {
    // Add error message element after the form
    loginForm.after(errorMessage);

    // Ensure form doesn't submit normally
    loginForm.setAttribute("method", "post");
    loginForm.setAttribute("action", "javascript:void(0);");

    loginForm.addEventListener("submit", function (event) {
      // Prevent default form submission
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
      fetch("/dentists/login", {
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
              // Store token in localStorage
              localStorage.setItem("authToken", token);

              // Determine role based on token (could be enhanced with actual role from backend)
              localStorage.setItem("userRole", "dentist");

              // Redirect to home page
              window.location.href = "/home";
            } else {
              throw new Error("Error de autenticación");
            }
          })
          .catch((error) => {
            console.error("Error:", error);
            errorMessage.textContent = error.message || "Error al iniciar sesión";
            errorMessage.style.display = "block";

            // Reset button state
            loginButton.disabled = false;
            loginButton.textContent = "Ingresar";
          });

      // Return false to prevent normal form submission
      return false;
    });
  }
});
document.addEventListener("DOMContentLoaded", function () {
  const loginForm = document.querySelector("form");
  const loginButton = document.querySelector("button[type='submit']");
  const errorMessage = document.createElement("div");
  errorMessage.className = "error-message";
  errorMessage.style.color = "red";
  errorMessage.style.marginTop = "10px";
  errorMessage.style.display = "none";

  if (loginForm) {
    // Add error message element after the form
    loginForm.after(errorMessage);

    // Ensure form doesn't submit normally
    loginForm.setAttribute("method", "post");
    loginForm.setAttribute("action", "javascript:void(0);");

    loginForm.addEventListener("submit", function (event) {
      // Prevent default form submission
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
      fetch("/dentists/login", {
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
              // Store token in localStorage
              localStorage.setItem("authToken", token);

              // Determine role based on token (could be enhanced with actual role from backend)
              localStorage.setItem("userRole", "dentist");

              // Redirect to home page
              window.location.href = "/home";
            } else {
              throw new Error("Error de autenticación");
            }
          })
          .catch((error) => {
            console.error("Error:", error);
            errorMessage.textContent = error.message || "Error al iniciar sesión";
            errorMessage.style.display = "block";

            // Reset button state
            loginButton.disabled = false;
            loginButton.textContent = "Ingresar";
          });

      // Return false to prevent normal form submission
      return false;
    });
  }
});
