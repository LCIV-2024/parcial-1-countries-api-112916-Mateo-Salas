package ar.edu.utn.frc.tup.lciii.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "country")
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    private String name;
    private long population;
    private double area;
    private String code;
    private String region;
//    private List<String> borders;
//    private Map<String, String> languages;
}
