package com.example.javaee.service.impl;

import com.example.javaee.dao.PerformanceDAO;
import com.example.javaee.data.Performance;
import com.example.javaee.dto.PerformanceDto;
import com.example.javaee.service.PerformanceService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class PerformanceServiceImpl implements PerformanceService {
    @Inject
    PerformanceDAO performanceDAO;

    @Override
    public void save(PerformanceDto performanceDto) {
        performanceDAO.save(performanceDto.toEntity());
    }

    @Override
    public void update(PerformanceDto performanceDto, Long id) {
        Performance performance = performanceDto.toEntity();
        performance.setId(id);
        performanceDAO.update(performance);
    }
    @Override
    public void delete(Long id) {
        performanceDAO.deleteById(id);
    }
    @Override
    public PerformanceDto getById(Long id) {
        return performanceDAO.getById(id).toDto();
    }
    @Override
    public List<PerformanceDto> getAll() {
        return performanceDAO.getAll().stream().map(Performance::toDto).collect(Collectors.toList());
    }
}
