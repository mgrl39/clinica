document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.querySelector("form");

    if (loginForm) {
        loginForm.addEventListener("submit", function (event) {
            event.preventDefault();

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
                        throw new Error("Login failed");
                    }
                    return response.json();
                })
                .then((data) => {
                    if (data && data.token) {
                        // Store token in localStorage
                        localStorage.setItem("authToken", data.token);
                        localStorage.setItem("userRole", data.rol);

                        // Redirect based on role
                        if (data.rol === "admin") {
                            window.location.href = "/home";
                        } else if (data.rol === "dentist") {
                            window.location.href = "/home";
                        } else {
                            alert("Unknown user role");
                        }
                    } else {
                        alert("Login failed: Invalid credentials");
                    }
                })
                .catch((error) => {
                    console.error("Error:", error);
                    alert("Login failed: " + error.message);
                });
        });
    }
});
