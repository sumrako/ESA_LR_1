package com.example.javaee.dto;

import com.example.javaee.data.Timetable;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class TimetableDto {
    private Long id;
    private PerformanceDto performance;
    private int hall;
    private LocalDateTime date;

    public Timetable toEntity(){
        return new Timetable(this.id, performance.toEntity(), hall, date);
    }
}
