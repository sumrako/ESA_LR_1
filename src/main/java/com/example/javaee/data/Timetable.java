package com.example.javaee.data;

import com.example.javaee.dto.TimetableDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "timetable", schema = "public")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Timetable {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(targetEntity = Performance.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "performance_id")
    private Performance performance;

    @Column(name = "hall")
    private int hall;

    @Column(name = "date")
    private LocalDateTime date;

    public TimetableDto toDto() {
        return TimetableDto.builder()
                .id(this.id)
                .performance(this.performance.toDto())
                .hall(this.hall)
                .date(this.date)
                .build();
    }
}
