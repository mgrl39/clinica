package puig.xeill.Clinic.Controller;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    // Panel de acciones de usuarios
    @GetMapping("/users")
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
}
