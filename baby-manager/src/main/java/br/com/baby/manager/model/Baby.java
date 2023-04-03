package br.com.baby.manager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "baby")
@AllArgsConstructor
@NoArgsConstructor
public class Baby {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    private String id;
    private String name;
    private LocalDateTime birthdate;
    private Double birthWeight;
    private Double birthHeight;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String rg;

    @ElementCollection
    @CollectionTable(name="measurement_list")
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Measurement> measurements = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name="doctor_list")
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Doctor> doctors = new ArrayList<>();
}
