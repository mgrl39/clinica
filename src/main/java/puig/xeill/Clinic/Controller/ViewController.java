package puig.xeill.Clinic.Controller;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ViewController {

    @GetMapping("/")
    public String root() {
        return "check-auth";  // Esto buscará check-auth.html en templates/
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    // Panel de acciones de usuarios
    @GetMapping("/users/dashboard")
    public String usersDashboard() {
        return "users/dashboard";
    }

    // Listado de usuarios
    @GetMapping("/users/list")
    public String usersList() {
        return "users/list";
    }

    // Alta de usuarios
    @GetMapping("/users/create")
    public String usersCreate() {
        return "users/create";
    }

    // ODONTÓLOGOS (nuevas rutas)
    @GetMapping("/dentists")
    public String dentistsDashboard() {
        return "dentists/dashboard";
    }

    @GetMapping("/dentists/list")
    public String dentistsList() {
        return "dentists/list";
    }

    @GetMapping("/dentists/create")
    public String dentistsCreate() {
        return "dentists/create";
    }

    // PACIENTES (nuevas rutas)
    @GetMapping("/patients")
    public String patientsDashboard() {
        return "patients/dashboard";
    }

    @GetMapping("/patients/list")
    public String patientsList() {
        return "patients/list";
    }

    @GetMapping("/patients/create")
    public String patientsCreate() {
        return "patients/create";
    }

    // VISITAS (nuevas rutas)
    @GetMapping("/visits/list")
    public String visitsList() {
        return "visits/list";
    }

    @GetMapping("/visits/dashboard")
    public String visitsDashboard() {
        return "visits/dashboard";
    }
}
