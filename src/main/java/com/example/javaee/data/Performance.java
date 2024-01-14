package com.example.javaee.data;

import com.example.javaee.dto.PerformanceDto;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "performances", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Performance {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "genre")
    private String genre;

    @Column(name = "country")
    private String country;

    @Column(name = "director")
    private String director;

    public PerformanceDto toDto() {
        return PerformanceDto.builder()
                .id(this.id)
                .name(this.name)
                .country(this.country)
                .director(this.director)
                .genre(this.genre)
                .build();
    }
}
