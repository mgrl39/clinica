package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import puig.xeill.Clinic.Model.DTO.DentistDTO;
import puig.xeill.Clinic.Model.Persons.Admin;
import puig.xeill.Clinic.Model.Persons.Dentist;
import puig.xeill.Clinic.Model.Specialty;
import puig.xeill.Clinic.Repository.AdminRepository;
import puig.xeill.Clinic.Repository.DentistRepository;
import puig.xeill.Clinic.Repository.SpecialtyRepository;
import puig.xeill.Clinic.Security.JwtUtil;
import puig.xeill.Clinic.Security.Security;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("dentists")
public class DentistController {

    @Autowired
    DentistRepository dentistRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    SpecialtyRepository specialtyRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    JwtUtil jwtUtil = new JwtUtil();


    public Long edit(@RequestBody Dentist dentist) {
        return 0L;
    }


    @GetMapping("/get")
    public Page<DentistDTO> getDentist (@RequestParam int page) {
        List<Dentist> dentistList  = dentistRepository.findAll();
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
        Pageable pageable = PageRequest.of(page, 10);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), dentistDTOList.size());

        List<DentistDTO> filterList = dentistDTOList.subList(start, end);

        Page<DentistDTO> pagedResult = new PageImpl<>(filterList, pageable, dentistDTOList.size());

        return  pagedResult;
        //return dentistRepository.findAll(Pageable.ofSize(10).withPage(page));
    }

    @PostMapping("/create")
    public DentistDTO register(@RequestBody DentistDTO dentistDTO, @RequestHeader String token) throws Exception {

            dentistDTO.setPassword(passwordEncoder.encode(dentistDTO.getPassword()));
            List<Specialty> specialties = new ArrayList<Specialty>();

            dentistDTO.getSpecialties().forEach(specialtyID -> {
                Optional<Specialty> specialtyOptional = specialtyRepository.findById(specialtyID);

                if(specialtyOptional.isPresent()){
                    Specialty specialty = specialtyOptional.get();
                    specialties.add(specialty);
                }


            });

            Dentist dentist = new Dentist();
            dentist.setUser(dentistDTO.getUser());
            dentist.setName(Security.encrypt(dentistDTO.getName()));
            dentist.setPassword(passwordEncoder.encode(dentistDTO.getPassword()));
            dentist.setIdSchedule(dentistDTO.getIdSchedule());
            dentist.setSpecialties(specialties);
            System.out.println(dentist.getSpecialties());
            dentistRepository.save(dentist);
            return dentistDTO;
    }

    @PostMapping("/update")
    public DentistDTO edit(@RequestBody DentistDTO dentistDTO, @RequestHeader String token) throws Exception {

        String name = jwtUtil.getNameFromToken(token);
        Optional<Admin> adminOptional = adminRepository.findByUser(name);

        if(adminOptional.isPresent()){
            dentistDTO.setPassword(passwordEncoder.encode(dentistDTO.getPassword()));
            List<Specialty> specialties = new ArrayList<Specialty>();

            dentistDTO.getSpecialties().forEach(specialtyID -> {
                Optional<Specialty> specialtyOptional = specialtyRepository.findById(specialtyID);

                if(specialtyOptional.isPresent()){
                    Specialty specialty = specialtyOptional.get();
                    specialties.add(specialty);
                }


            });

            Optional<Dentist> dentistOptional = dentistRepository.findByUser(dentistDTO.getUser());
            if (dentistOptional.isPresent()){
                dentistOptional.get().setUser(dentistDTO.getUser());
                dentistOptional.get().setName(passwordEncoder.encode(dentistDTO.getName()));
                dentistOptional.get().setPassword(Security.encrypt(dentistDTO.getPassword()));
                dentistOptional.get().setIdSchedule(dentistDTO.getIdSchedule());
                dentistOptional.get().setSpecialties(specialties);
                System.out.println(dentistOptional.get().getSpecialties());
                Dentist dentist = dentistOptional.get();

                dentistRepository.save(dentist);
                return dentistDTO;
            }

        }
        return null;
    }



}
