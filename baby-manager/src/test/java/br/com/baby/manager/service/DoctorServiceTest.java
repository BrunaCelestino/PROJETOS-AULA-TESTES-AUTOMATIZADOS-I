package br.com.baby.manager.service;

import br.com.baby.manager.model.Doctor;
import br.com.baby.manager.repository.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Doctor doctor = new Doctor("1", "Dr. John Doe", "+1 123-456-7890", "Pediatrics");

        when(doctorRepository.save(any(Doctor.class))).thenAnswer((Answer<Doctor>) invocation -> invocation.getArgument(0));


        Optional<Doctor> savedDoctor = doctorService.save(doctor);


        assertTrue(savedDoctor.isPresent());
        assertEquals(doctor, savedDoctor.get());
    }

    @Test
    void findAll() {
        List<Doctor> doctors = Arrays.asList(
                new Doctor("1", "Dr. John Doe", "123-456-7890", "Pediatrics"),
                new Doctor("2", "Dr. Jane Smith", "098-765-4321", "Dermatology")
        );

        when(doctorRepository.findAll()).thenReturn(doctors);

        List<Doctor> foundDoctors = doctorService.findAll();


        assertFalse(foundDoctors.isEmpty());
        assertEquals(doctors, foundDoctors);
    }

    @Test
    void findById() {
        Doctor doctor = new Doctor("1", "Dr. John Doe", "123-456-7890", "Pediatrics");

        when(doctorRepository.findById(doctor.getId())).thenReturn(Optional.of(doctor));


        Optional<Doctor> foundDoctor = doctorService.findById(doctor.getId());

        assertTrue(foundDoctor.isPresent());
        assertEquals(doctor, foundDoctor.get());
    }

    @Test
    void update() {
        String id = "1";
        Doctor doctor = new Doctor();
        doctor.setName("Dr. John");
        doctor.setPhone("1234567890");
        doctor.setSpecialty("Pediatrics");

        Optional<Doctor> foundDoctor = Optional.of(new Doctor());
        foundDoctor.get().setId(id);
        foundDoctor.get().setName("Dr. Smith");
        foundDoctor.get().setPhone("0987654321");
        foundDoctor.get().setSpecialty("Neurology");

        when(doctorRepository.findById(id)).thenReturn(foundDoctor);

        Optional<Doctor> updatedDoctor = doctorService.update(doctor, id);
        assertTrue(updatedDoctor.isPresent());
        assertEquals(id, updatedDoctor.get().getId());
        assertEquals(doctor.getName(), updatedDoctor.get().getName());
        assertEquals(doctor.getPhone(), updatedDoctor.get().getPhone());
        assertEquals(doctor.getSpecialty(), updatedDoctor.get().getSpecialty());

    }

    @Test
    public void testUpdateNotFound() {
        String id = "1";
        Doctor doctor = new Doctor();
        doctor.setName("Dr. John");
        doctor.setPhone("1234567890");
        doctor.setSpecialty("Pediatrics");

        Optional<Doctor> foundDoctor = Optional.empty();

        when(doctorRepository.findById(id)).thenReturn(foundDoctor);

        Optional<Doctor> updatedDoctor = doctorService.update(doctor, id);


        assertFalse(updatedDoctor.isPresent());

    }

    @Test
    void delete() {
        Doctor doctorToDelete = new Doctor();
        doctorToDelete.setId("123");
        doctorToDelete.setName("John Doe");
        doctorToDelete.setPhone("555-1234");
        doctorToDelete.setSpecialty("Pediatrics");


        when(doctorRepository.findById("123")).thenReturn(Optional.of(doctorToDelete));


        Optional<Doctor> deletedDoctor = doctorService.delete("123");

        assertTrue(deletedDoctor.isPresent());
        assertEquals(doctorToDelete, deletedDoctor.get());

    }

    @Test
    public void testDeleteNotFoundDoctor() {
        when(doctorRepository.findById("123")).thenReturn(Optional.empty());

        Optional<Doctor> deletedDoctor = doctorService.delete("123");

        assertFalse(deletedDoctor.isPresent());

    }
}