package com.poppulo.demo.services;

import com.poppulo.demo.models.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TicketServiceTest {

    @Autowired
    private TicketService service;
    @Test
    void createSuccess() {
        Ticket ticket = service.create("111");
        assertEquals("111",ticket.getSeries());
    }

    @Test
    void createFailNan() {
        Ticket ticket = service.create("1d1");
        assertNull(ticket);
    }

    @Test
    void createFailTooSmall() {
        Ticket ticket = service.create("11");
        assertNull(ticket);
    }

    @Test
    void createFailTooBig() {
        Ticket ticket = service.create("0011");
        assertNull(ticket);
    }

    @Test
    void listSuccess() {
        for (int i = 0; i < 3; i++) {
            service.create("010");
        }

        List<Ticket> tickets = service.listTickets();

        assertTrue(tickets.size() > 2);
    }

    @Test
    void getByIdSuccess(){
        Ticket ticket = service.create("111");
        UUID ticketId = ticket.getTicketId();

        Ticket ticketById = service.getTicketById(ticketId);
        assertEquals(ticket, ticketById);
    }

    @Test
    void getByIdFail(){
        UUID uuid = UUID.randomUUID();
        Ticket ticketById = service.getTicketById(uuid);
        assertNull(ticketById);
    }

    @Test
    void changeTicketSeries(){
        String pastSeries = "121";
        String newSeries = "122";

        Ticket ticket = service.create(pastSeries);
        Ticket changedTicket = service.changeTicketSeries(ticket.getTicketId(), newSeries);

        assertEquals(newSeries, changedTicket.getSeries());
    }

    @Test
    void getStatusSeriesWithSameValues() {
        Ticket ticket = service.create("111");
        ticket = service.getStatus(ticket.getTicketId());
        assertEquals(5, ticket.getStatus());
    }

    @Test
    void getStatusSeriesWithSumIs2() {
        Ticket ticket = service.create("110");
        ticket = service.getStatus(ticket.getTicketId());
        assertEquals(10, ticket.getStatus());
    }

    @Test
    void getStatusSeriesWithFirstValueDifferent() {
        Ticket ticket = service.create("022");
        ticket = service.getStatus(ticket.getTicketId());
        assertEquals(1, ticket.getStatus());
    }

    @Test
    void getStatusSeriesWith0Status() {
        Ticket ticket = service.create("212");
        ticket = service.getStatus(ticket.getTicketId());
        assertEquals(0, ticket.getStatus());
    }

    @Test
    void changeSeriesAfterStatusCheckFail() {
        Ticket ticket = service.create("212");
        ticket = service.getStatus(ticket.getTicketId());
        Ticket changeTicket = service.changeTicketSeries(ticket.getTicketId(), "110");
        assertNull(changeTicket);
    }
}
