package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import puig.xeill.Clinic.Model.Persons.Admin;
import puig.xeill.Clinic.Model.Persons.Dentist;
import puig.xeill.Clinic.Repository.AdminRepository;
import puig.xeill.Clinic.Repository.DentistRepository;
import puig.xeill.Clinic.Security.JwtUtil;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    DentistRepository dentistRepository;

    @Autowired
    AdminController adminController;

    @Autowired
    PasswordEncoder passwordEncoder;

    //@Autowired
    JwtUtil jwtUtil = new JwtUtil();


    @PostMapping("login/dentist")
    public HashMap<String, String> loginDentist(@RequestBody Dentist user) throws NoSuchAlgorithmException, KeyStoreException {

        // Buscar dentista por nombre de usuario
        Optional<Dentist> dentistOptional = dentistRepository.findByUser(user.getUser());

        // Si no se encuentra, devolver null
        if (!dentistOptional.isPresent()) {
            return null;
        }

        // Verificar si la contraseña coincide
        if (passwordEncoder.matches(user.getPassword(), dentistOptional.get().getPassword())) {
            // Generar token
            String token = jwtUtil.generateToken(user.getUser());

            // Crear el mapa con el token y rol
            HashMap<String, String> data = new HashMap<>();
            data.put("token", token);
            data.put("rol", "dentist");

            return data;
        }

        // Si las contraseñas no coinciden
        return null;
    }

    @PostMapping("admin/login")
    public HashMap<String, String> loginAdmin(@RequestBody Admin user) throws NoSuchAlgorithmException, KeyStoreException {

        // Buscar admin por nombre de usuario
        Optional<Admin> optionalAdmin = adminRepository.findByUser(user.getUser());

        // Si no se encuentra el admin, devolver null
        if (!optionalAdmin.isPresent()) {
            return null;
        }

        // Verificar si la contraseña coincide
        if (passwordEncoder.matches(user.getPassword(), optionalAdmin.get().getPassword())) {
            // Generar token
            String token = jwtUtil.generateToken(user.getUser());

            // Crear el mapa con el token y rol
            HashMap<String, String> data = new HashMap<>();
            data.put("token", token);
            data.put("rol", "admin");

            return data;
        }

        // Si las contraseñas no coinciden
        return null;
    }
}
