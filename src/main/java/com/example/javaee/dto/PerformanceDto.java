package com.example.javaee.dto;

import com.example.javaee.data.Performance;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class PerformanceDto {
    private Long id;
    private String name;
    private String genre;
    private String country;
    private String director;

    public Performance toEntity() {
        return new Performance(this.id, this.name, this.genre, this.country, this.director);
    }
}
