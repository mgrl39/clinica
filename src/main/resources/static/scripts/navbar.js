document.addEventListener("DOMContentLoaded", function() {
    // Obtener elementos del DOM
    const usernameElement = document.getElementById('username');
    const logoutBtn = document.getElementById('logoutBtn');
    
    // Obtener usuario del localStorage
    const userRole = localStorage.getItem('userRole');
    const authToken = localStorage.getItem('authToken');
    
    // Por ahora vamos a mostrar un nombre genérico + el rol
    // Idealmente obtendríamos el nombre real del usuario desde el backend o token decodificado
    if (userRole) {
        usernameElement.textContent = userRole.charAt(0).toUpperCase() + userRole.slice(1);
        
        // Opcional: obtener información adicional del usuario desde el backend
        // usando el token para una solicitud autenticada
    } else {
        usernameElement.textContent = 'Usuario';
    }
    
    // Evento para cerrar sesión
    if (logoutBtn) {
        logoutBtn.addEventListener('click', function(e) {
            e.preventDefault();
            
            // Eliminar datos de autenticación del localStorage
            localStorage.removeItem('authToken');
            localStorage.removeItem('userRole');
            
            // Redireccionar a la página de login
            window.location.href = '/login';
        });
    }
}); 