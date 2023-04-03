package br.com.baby.manager.service;
import br.com.baby.manager.model.Doctor;
import br.com.baby.manager.repository.DoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Optional<Doctor> save(Doctor doctor) {
        Doctor savedDoctor = doctorRepository.save(doctor);
        return Optional.of(savedDoctor);
    }

    public List<Doctor> findAll() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    public Optional<Doctor> findById(String id) {
        return doctorRepository.findById(id);
    }

    public Optional<Doctor> update(Doctor doctor, String id) {
        Optional<Doctor> foundDoctor = doctorRepository.findById(id);

        if(foundDoctor.isPresent()) {
            if(doctor.getName() != null && !doctor.getName().isEmpty()) {
                foundDoctor.get().setName(doctor.getName());
            }


            if(doctor.getPhone() != null && !doctor.getPhone().isEmpty()) {
                foundDoctor.get().setPhone(doctor.getPhone());
            }

            if(doctor.getSpecialty() != null && !doctor.getSpecialty().isEmpty()) {
                foundDoctor.get().setSpecialty(doctor.getSpecialty());
            }

            doctorRepository.save(foundDoctor.get());


        }

        return foundDoctor;
    }

    public Optional<Doctor> delete(String id) {
        Optional<Doctor> foundDoctor = doctorRepository.findById(id);

        foundDoctor.ifPresent(doctor -> doctorRepository.delete(doctor));

        return foundDoctor;
    }
}
