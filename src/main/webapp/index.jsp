<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.javaee.data.Ticket" %>
<%@ page import="com.example.javaee.dto.*" %>
<!DOCTYPE html>
<html>
<head>
  <title>Theater "Matrechka"</title>
</head>
<body style="background: darkseagreen">
<h1><%= "Welcome to the web-site of theater \"Matreshka \"!" %></h1>
<br/>
<hr>
<%
  VisitorDto visitor = (VisitorDto) request.getAttribute("visitor");
  PerformanceDto performance = (PerformanceDto) request.getAttribute("performance");
  TimetableDto timetable = (TimetableDto) request.getAttribute("timetable");
  List<PerformanceDto> performanceDtos = (List<PerformanceDto>) request.getAttribute("performancesList");
  List<TimetableDto> timetableDtos = (List<TimetableDto>) request.getAttribute("timetables");
  List<SeatDto> seatDtos = (List<SeatDto>) request.getAttribute("seats");
  List<TicketDto> myTicket = (List<TicketDto>) request.getAttribute("myTickets");
  if(visitor == null) {
    out.println("<p>" + "<h2>Enter your name please and mark if you have any benefits</h2>" + "</p>");
    out.println("<form action=\"visitor\" method=\"post\">\n" +
            "  <label for=\"name\" style=\"margin: 5%\">Name:</label>\n" +
            "  <input type=\"text\" id=\"name\" name=\"name\" required>\n" +
            "  <br>\n" +
            "  <label for=\"benefits\" style=\"margin: 5%\">Benefits:</label>\n" +
            "  <input type=\"checkbox\" id=\"benefits\" name=\"benefits\">\n" +
            "  <br>\n" +
            "  <input type=\"submit\" value=\"further...\" style=\"margin-left: 20%; font-family: Arial,serif\">\n" +
            "</form>");
  }
  else {
    out.println("<p>" + "Register by " + visitor.getName() + (visitor.getBenefits() ? " with" :
            " without") + " benefits" + "</p>");
    if(performanceDtos != null && !performanceDtos.isEmpty()) {
      out.println("<p>" + "<h3>Select the performance you want</h3>" + "</p>");
      for (PerformanceDto performanceDto : performanceDtos) {
        out.println(
                "<a href=\"performance?performanceId=" + performanceDto.getId() + "&visitorId=" + visitor.getId() + "\">" + performanceDto.getName() +"</a>"
        );
      }
    }
    if (performance != null) {
      out.println("<p>" + "Performance: " + performance.getName() + "</p>");
      if (timetableDtos != null && !timetableDtos.isEmpty()) {
        out.println("<p>" + "<h3>Select time you want</h3>" + "</p>");
        for (TimetableDto timetableDto : timetableDtos) {
          out.println(
                  "<a href=\"timetable?performanceId=" + performance.getId() + "&visitorId=" + visitor.getId() + "&timetableId=" + timetableDto.getId() + "\">" + timetableDto.getDate() +"</a>"
          );
        }
      }
      else if (timetable == null) {
        out.println("<p>" + "<h3>There are no any seances</h3>" + "</p>");
      }
    }
    if (performance != null && timetable != null) {
      out.println("<p>" + "Timetable: " + timetable.getDate() + "</p>");
      if (seatDtos != null && !seatDtos.isEmpty()) {
        out.println("<table>\n");
        for (SeatDto seatDto : seatDtos) {
          out.println(
                  "<tr>\n" +
                  "<td>" + "Seat: " + seatDto.getSeat()
                          + "   Row: " + seatDto.getRow() + "</td>\n" +
                  "<td><a href=\"ticket?performanceId=" + performance.getId() + "&visitorId=" + visitor.getId()
                          + "&timetableId=" + timetable.getId() + "&seat=" + seatDto.getSeat()
                          + "&row=" + seatDto.getRow() + "\">" + "buy" +"</a></td>\n"
                  + "</tr>\n"
          );
        }
        out.println("</table>");
      }
      else {
        out.println("<p>" + "<h3>There are not free seats</h3>" + "</p>");
      }
    }
    out.println("<br>\n");
    out.println("<a href=\"my-ticket" + "?visitorId=" + visitor.getId()
            + ((performance != null) ? ("&performanceId=" + performance.getId()) : "")
            + (timetable != null ? ("&timetableId=" + timetable.getId()) : "") + "\">My tickets</a>");
    if (myTicket != null){
      if (! myTicket.isEmpty()) {
        out.println("<table>\n");
        for (TicketDto ticketDto : myTicket) {
          out.println(
                  "<tr>\n" +
                          "<td>" + "Performance: " + ticketDto.getTimetable().getPerformance().getName() + "</td>\n" +
                          "<td>" + "   Date: " + ticketDto.getTimetable().getDate() + "</td>\n" +
                          "<td>" + "   Cost: " + ticketDto.getCost() + "</td>\n" +
                          "<td>" +  "   Seat: " + ticketDto.getSeat()
                          + " Row: " + ticketDto.getRow() +  "</td>\n"
                          + "<td><form action=\"" +
                          "my-ticket" +
                          "\" method=\"post\">\n" +
                          "  <input type=\"hidden\" id=\"ticketToDelete\" name=\"ticketToDelete\" value=\""+ ticketDto.getId() +"\">\n" +
                          "  <input type=\"hidden\" id=\"ticketToDelete\" name=\"visitorId\" value=\""+ visitor.getId() +"\">\n" +
                          ((performance != null) ? (
                                  "  <input type=\"hidden\" id=\"ticketToDelete\" name=\"performanceId\" value=\""+ performance.getId() +"\">\n"
                          ) : "") +
                          (timetable != null ? (
                                  "  <input type=\"hidden\" id=\"ticketToDelete\" name=\"timetableId\" value=\""+ timetable.getId() +"\">\n"
                          ) : "") +
                          "    <input type=\"submit\" value=\"delete\"/>\n" +
                          "</form></td>"
                          + "</tr>\n"
          );
        }
        out.println("</table>");
      }
      else {
        out.println("<p>" + "<h3>There are not no tickets. Buy any new!</h3>" + "</p>");
      }
    }
  }
%>





</body>
</html>