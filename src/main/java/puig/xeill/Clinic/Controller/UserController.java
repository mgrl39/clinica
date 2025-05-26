package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import puig.xeill.Clinic.Model.DTO.DentistDTO;
import puig.xeill.Clinic.Model.Persons.Admin;
import puig.xeill.Clinic.Model.Persons.Dentist;
import puig.xeill.Clinic.Repository.AdminRepository;
import puig.xeill.Clinic.Repository.DentistRepository;
import puig.xeill.Clinic.Repository.SpecialtyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import puig.xeill.Clinic.Security.JwtUtil;
import puig.xeill.Clinic.Security.Security;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

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
    SpecialtyRepository specialtyRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    JwtUtil jwtUtil = new JwtUtil();

    @GetMapping("/get")
    public Page<Object> get(@RequestParam int page) {
        List<Dentist> dentistList = dentistRepository.findAll();
        List<DentistDTO> dentistDTOList = new ArrayList<>();

        dentistList.forEach(dentist -> {
            DentistDTO dentistDTO = new DentistDTO();
            dentistDTO.setUser(dentist.getUser());
            try {
                dentistDTO.setName(Security.decrypt(dentist.getName()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            dentistDTO.setIdSchedule(dentist.getIdSchedule());
            List specialtyLongList = new ArrayList<>();
            dentist.getSpecialties().forEach(specialty -> {
                //SpecialtyDTO specialtyDTO = new SpecialtyDTO(specialty.getId(), specialty.getName());
                specialtyLongList.add(specialty.getId());
            });
            dentistDTO.setSpecialties(specialtyLongList);
            dentistDTOList.add(dentistDTO);
        });

        List<Admin> adminList = adminRepository.findAll();

        adminList.forEach(admin -> {
            try {
                admin.setName(Security.decrypt(admin.getName()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        List<Object> combinedList = new ArrayList<>();
        System.out.println(adminList.toString());
        System.out.println(dentistDTOList.toString());

        combinedList.addAll(dentistDTOList);
        combinedList.addAll(adminList);

        Pageable pageable = PageRequest.of(page, 10);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), combinedList.size());
        List<Object> filterList = combinedList.subList(start, end);

        Page<Object> pagedResult = new PageImpl<>(filterList, pageable, combinedList.size());


        return pagedResult;
    }


    @PostMapping("login")
    public String login(@RequestBody Dentist user) throws NoSuchAlgorithmException, KeyStoreException {
        // Primero buscar si es dentista
        Optional<Dentist> dentistOptional = dentistRepository.findByUser(user.getUser());
        if (dentistOptional.isPresent()) {
            System.out.println(user.getPassword());
            if (passwordEncoder.matches(user.getPassword(), dentistOptional.get().getPassword())) {
                return jwtUtil.generateToken(user.getUser());
            }
            return "aaaa";
        }
        // Si no es dentista, buscar si es admin
        Optional<Admin> adminOptional = adminRepository.findByUser(user.getUser());
        if (adminOptional.isPresent()) {
            if (passwordEncoder.matches(user.getPassword(), adminOptional.get().getPassword())) {
                return jwtUtil.generateToken(user.getUser());
            }
        }
        // Si no es ni dentista ni admin, o la contraseña es incorrecta
        return null;
    }



}
