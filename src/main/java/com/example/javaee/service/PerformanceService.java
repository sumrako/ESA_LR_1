package com.example.javaee.service;

import com.example.javaee.dto.PerformanceDto;

import java.util.List;

public interface PerformanceService {
    void save(PerformanceDto performanceDto);
    void update(PerformanceDto performanceDto, Long id);
    void delete(Long id);
    PerformanceDto getById(Long id);
    List<PerformanceDto> getAll();
}
