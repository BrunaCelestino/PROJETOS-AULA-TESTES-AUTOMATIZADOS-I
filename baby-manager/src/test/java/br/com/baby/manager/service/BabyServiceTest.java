package br.com.baby.manager.service;

import br.com.baby.manager.exception.InvalidInformationException;
import br.com.baby.manager.model.Baby;
import br.com.baby.manager.model.Doctor;
import br.com.baby.manager.model.Measurement;
import br.com.baby.manager.repository.BabyRepository;
import br.com.baby.manager.repository.DoctorRepository;
import br.com.baby.manager.repository.MeasurementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BabyServiceTest {


    @Mock
    private BabyRepository babyRepository;

    @Mock
    private MeasurementRepository measurementRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private BabyService babyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void save() throws InvalidInformationException {

       Baby baby = new Baby();
       baby.setName("Noah");
       baby.setId("1");
       baby.setCpf("593.642.000-04");
       baby.setBirthdate(LocalDateTime.now());
       baby.setBirthWeight(4.390);
       baby.setBirthHeight(0.53);
       baby.setRg("10.159.629-7");

        when(babyRepository.save(Mockito.any(Baby.class))).thenReturn(baby);




        var savedBaby = babyService.save(baby);


        assertTrue(savedBaby.isPresent());
        assertEquals("10.159.629-7", savedBaby.get().getRg());
    }


    @Test
    void saveInvalidBaby() {

        Baby baby = new Baby();
        baby.setName("Noah");
        baby.setId("1");
        baby.setCpf("XXX.XXX.XXX-XX");
        baby.setBirthdate(LocalDateTime.now());
        baby.setBirthWeight(4.390);
        baby.setBirthHeight(0.53);
        baby.setRg("10.159.629-7");

        assertThrows(InvalidInformationException.class, () -> babyService.save(baby));
    }

    @Test
    void findAll() {

        List<Baby> babies = List.of(
                new Baby("1", "Noah", LocalDateTime.now(), 4.390, 0.53, "593.642.000-04", "10.159.629-7",
                        new ArrayList<>(), new ArrayList<>()),
                new Baby("2", "Liam", LocalDateTime.now(), 3.200, 0.45, "321.654.987-12", "11.111.111-1", new ArrayList<>(), new ArrayList<>())
        );
        when(babyRepository.findAll()).thenReturn(babies);

        var result = babyService.findAll();


        assertEquals(2, result.size());
    }

    @Test
    void findById() {

        String id = "123";
        Baby baby = new Baby();
        baby.setId(id);

        when(babyRepository.findById(id)).thenReturn(Optional.of(baby));


        Optional<Baby> result = babyService.findById(id);


        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());



    }

    @Test
    void update() throws InvalidInformationException {

        String id = "123";
        Baby baby = new Baby();
        baby.setId(id);
        baby.setName("Alice");
        baby.setBirthdate(LocalDateTime.of(2021, 3, 10, 0, 0, 0));
        baby.setBirthWeight(3.5);
        baby.setBirthHeight(0.50);
        baby.setCpf("593.642.000-04");
        baby.setRg("1234567");

        Baby updatedBaby = new Baby();
        updatedBaby.setId(id);
        updatedBaby.setName("Alice Updated");
        updatedBaby.setBirthdate(LocalDateTime.of(2021, 3, 11, 0, 0, 0));
        updatedBaby.setBirthWeight(4.0);
        updatedBaby.setBirthHeight(0.49);
        updatedBaby.setRg("7654321");

        when(babyRepository.findById(id)).thenReturn(Optional.of(baby));
        when(babyRepository.save(baby)).thenReturn(baby);


        Optional<Baby> result = babyService.update(updatedBaby, id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals(updatedBaby.getName(), result.get().getName());
        assertEquals(updatedBaby.getBirthdate(), result.get().getBirthdate());
        assertEquals(updatedBaby.getBirthWeight(), result.get().getBirthWeight());
        assertEquals(updatedBaby.getBirthHeight(), result.get().getBirthHeight());
        assertEquals(updatedBaby.getRg(), result.get().getRg());



    }


    @Test
    public void testDeleteBabyWithValidId() {
        String id = "1";
        Baby baby = new Baby();
        baby.setId(id);

        when(babyService.findById(id)).thenReturn(Optional.of(baby));

        Optional<Baby> deletedBaby = babyService.delete(id);


        assertTrue(deletedBaby.isPresent());
        assertEquals(baby, deletedBaby.get());
    }


    @Test
    public void testDeleteBabyWithInvalidId() {
        String id = "1";

        when(babyRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Baby> deletedBaby = babyService.delete(id);

        assertTrue(deletedBaby.isEmpty());
    }

    @Test
    void addMeasurements() {
        Baby baby = new Baby();
        baby.setName("Noah");
        baby.setId("1");
        baby.setCpf("593.642.000-04");
        baby.setBirthdate(LocalDateTime.now());
        baby.setBirthWeight(4.390);
        baby.setBirthHeight(0.53);
        baby.setRg("10.159.629-7");

        Measurement measurement = new Measurement();
        measurement.setId("1");
        measurement.setHeight(50.0);
        measurement.setWeight(20.0);
        measurement.setHeadCircumference(30.0);

        when(babyRepository.findById("1")).thenReturn(Optional.of(baby));
        when(measurementRepository.findById("1")).thenReturn(Optional.of(measurement));

        Optional<Baby> result = babyService.addMeasurements("1", "1");

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getMeasurements().size());

    }

    @Test
    void addMeasurements_MeasurementNotFound() {
        Baby baby = new Baby();
        baby.setName("Noah");
        baby.setId("1");
        baby.setCpf("593.642.000-04");
        baby.setBirthdate(LocalDateTime.now());
        baby.setBirthWeight(4.390);
        baby.setBirthHeight(0.53);
        baby.setRg("10.159.629-7");

        when(babyRepository.findById("1")).thenReturn(Optional.empty());


        Optional<Baby> result = babyService.addMeasurements("1", "1");

        assertTrue(result.isEmpty());
        assertEquals(0, baby.getMeasurements().size());
    }

    @Test
    void addDoctor() {
        Baby baby = new Baby();
        baby.setName("Noah");
        baby.setId("1");
        baby.setCpf("593.642.000-04");
        baby.setBirthdate(LocalDateTime.now());
        baby.setBirthWeight(4.390);
        baby.setBirthHeight(0.53);
        baby.setRg("10.159.629-7");

        Doctor doctor = new Doctor();
        doctor.setId("1");
        doctor.setSpecialty("Pediatrics");
        doctor.setPhone("098-765-4321");
        doctor.setName("Michael");

        when(babyRepository.findById("1")).thenReturn(Optional.of(baby));
        when(doctorRepository.findById("1")).thenReturn(Optional.of(doctor));

        Optional<Baby> result = babyService.addDoctor("1", "1");

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getDoctors().size());
    }

    @Test
    void addDoctor_DoctorNotFound() {

        Baby baby = new Baby();
        baby.setName("Baby");
        babyRepository.save(baby);


        Optional<Baby> updatedBaby = babyService.addDoctor(baby.getId(), "1");


        assertFalse(updatedBaby.isPresent());

    }
}