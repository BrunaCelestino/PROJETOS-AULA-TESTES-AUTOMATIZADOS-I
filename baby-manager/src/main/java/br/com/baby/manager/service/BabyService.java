package br.com.baby.manager.service;

import br.com.baby.manager.exception.InvalidInformationException;
import br.com.baby.manager.model.Baby;
import br.com.baby.manager.model.Doctor;
import br.com.baby.manager.model.Measurement;
import br.com.baby.manager.repository.BabyRepository;
import br.com.baby.manager.repository.DoctorRepository;
import br.com.baby.manager.repository.MeasurementRepository;
import br.com.baby.manager.utility.CpfValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;



@Slf4j
@Service
@Transactional
public class BabyService {

    @Autowired
    private BabyRepository babyRepository;

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public Optional<Baby> save(Baby baby) throws InvalidInformationException {
        if(!CpfValidation.validateCPF(baby.getCpf())) {
            throw new InvalidInformationException("CPF is invalid.");
        }
        Baby savedBaby = babyRepository.save(baby);
       return Optional.of(savedBaby);
    }

    public List<Baby> findAll() {
        return (List<Baby>) babyRepository.findAll();
    }

    public Optional<Baby> findById(String id) {
        return babyRepository.findById(id);
    }

    public Optional<Baby> update(Baby baby, String id) throws InvalidInformationException {
        Optional<Baby> foundBaby = babyRepository.findById(id);

        if(foundBaby.isPresent()) {
            if(baby.getName() != null && !baby.getName().isEmpty()) {
                foundBaby.get().setName(baby.getName());
            }

            if(baby.getBirthdate() != null) {
                foundBaby.get().setBirthdate(baby.getBirthdate());
            }

            if(baby.getBirthWeight() != null) {
                foundBaby.get().setBirthWeight(baby.getBirthWeight());
            }

            if(baby.getBirthHeight() != null) {
                foundBaby.get().setBirthHeight(baby.getBirthHeight());
            }

            if(baby.getCpf() != null && !baby.getCpf().isEmpty()) {
                if(!CpfValidation.validateCPF(baby.getCpf())) {
                 throw new InvalidInformationException("CPF is invalid.");
                }
                foundBaby.get().setCpf(baby.getCpf());
            }

            if(baby.getRg() != null && !baby.getRg().isEmpty()) {
                foundBaby.get().setRg(baby.getRg());
            }

            babyRepository.save(foundBaby.get());


        }

        return foundBaby;
    }

    public Optional<Baby> delete(String id) {
        Optional<Baby> foundBaby = babyRepository.findById(id);

        foundBaby.ifPresent(baby -> babyRepository.delete(baby));

        return foundBaby;
    }

    public Optional<Baby> addMeasurements(String id, String measurement) {
        Optional<Baby> foundBaby = babyRepository.findById(id);
        Optional<Measurement> foundMeasurement = measurementRepository.findById(measurement);

        if(foundBaby.isPresent() && foundMeasurement.isPresent()) {
            foundBaby.get().getMeasurements().add(foundMeasurement.get());
        }

        return foundBaby;
    }

    public Optional<Baby> addDoctor(String id, String doctor) {
        Optional<Baby> foundBaby = babyRepository.findById(id);
        Optional<Doctor> foundDoctor = doctorRepository.findById(doctor);

        if(foundBaby.isPresent() && foundDoctor.isPresent()) {
            foundBaby.get().getDoctors().add(foundDoctor.get());
        }

        return foundBaby;
    }
}
