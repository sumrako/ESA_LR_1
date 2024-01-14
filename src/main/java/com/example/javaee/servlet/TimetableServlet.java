package com.example.javaee.servlet;

import java.io.*;
import java.util.List;

import com.example.javaee.dto.SeatDto;
import com.example.javaee.dto.TimetableDto;
import com.example.javaee.service.PerformanceService;
import com.example.javaee.service.TicketService;
import com.example.javaee.service.TimetableService;
import com.example.javaee.service.VisitorService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "timetableServlet", value = "/timetable")
public class TimetableServlet extends HttpServlet {
    @Inject
    TimetableService timetableService;
    @Inject
    VisitorService visitorService;
    @Inject
    PerformanceService performanceService;
    @Inject
    TicketService ticketService;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        TimetableDto timetableDto = timetableService.getById(Long.parseLong(request.getParameter("timetableId")));
        List<SeatDto> seatDtos = ticketService.getFreeSeats(timetableDto);
        request.setAttribute("seats", seatDtos);
        request.setAttribute("timetable", timetableDto);
        request.setAttribute("performance", performanceService.getById(Long.parseLong(request.getParameter("performanceId"))));
        request.setAttribute("visitor", visitorService.getById(Long.parseLong(request.getParameter("visitorId"))));
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }

    public void destroy() {
    }
}