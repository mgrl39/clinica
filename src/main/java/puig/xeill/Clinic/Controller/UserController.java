package puig.xeill.Clinic.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;
import puig.xeill.Clinic.Model.DTO.DentistDTO;
import puig.xeill.Clinic.Model.Persons.Admin;
import puig.xeill.Clinic.Model.Persons.Dentist;
import puig.xeill.Clinic.Repository.AdminRepository;
import puig.xeill.Clinic.Repository.DentistRepository;
import puig.xeill.Clinic.Repository.SpecialtyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    @GetMapping("")
    public Page<Object> get(@RequestParam int page, @RequestParam int size) {
        List<Dentist> dentistList = dentistRepository.findAll();
        List<DentistDTO> dentistDTOList = new ArrayList<>();

        dentistList.forEach(dentist -> {
            DentistDTO dentistDTO = new DentistDTO();
            dentistDTO.setUser(dentist.getUser());
            dentistDTO.setName(dentist.getName());
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

        List<Object> combinedList = new ArrayList<>();
        System.out.println(adminList.toString());
        System.out.println(dentistDTOList.toString());

        combinedList.addAll(dentistDTOList);
        combinedList.addAll(adminList);

        Pageable pageable = PageRequest.of(page, size);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), combinedList.size());
        List<Object> filterList = combinedList.subList(start, end);

        Page<Object> pagedResult = new PageImpl<>(filterList, pageable, combinedList.size());


        return pagedResult;
    }





}
