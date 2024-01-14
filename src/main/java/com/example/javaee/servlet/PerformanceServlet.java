package com.example.javaee.servlet;

import com.example.javaee.dto.PerformanceDto;
import com.example.javaee.dto.TimetableDto;
import com.example.javaee.service.PerformanceService;
import com.example.javaee.service.TimetableService;
import com.example.javaee.service.VisitorService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "performanceServlet", value = "/performance")
public class PerformanceServlet extends HttpServlet {
    @Inject
    PerformanceService performanceService;

    @Inject
    TimetableService timetableService;
    @Inject
    VisitorService visitorService;


    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PerformanceDto performanceDto = performanceService.getById(Long.parseLong(request.getParameter("performanceId")));
        List<TimetableDto> timetableDtos = timetableService.getTimetableForPerformance(performanceDto.getName());
        request.setAttribute("timetables", timetableDtos);
        request.setAttribute("performance", performanceDto);
        request.setAttribute("visitor", visitorService.getById(Long.parseLong(request.getParameter("visitorId"))));
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public void destroy() {
    }
}
