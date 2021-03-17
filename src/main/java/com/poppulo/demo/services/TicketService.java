package com.poppulo.demo.services;

import com.poppulo.demo.models.Ticket;

import java.util.List;
import java.util.UUID;

/**
 * TicketService is the interface for using the Ticket service.
 */
public interface TicketService {
    /**
     * Creates a new Ticket.
     * @param series
     * @return Ticket
     */
    Ticket create(String series);

    /**
     * List and return all tickets.
     * @return List<Ticket>
     */
    List<Ticket> listTickets();

    /**
     * Gets a ticket by its id.
     * @param id
     * @return Ticket
     */
    Ticket getTicketById(UUID id);

    /**
     * Change the series on a ticket by ticket id.
     * @param id
     * @param series
     * @return Ticket
     */
    Ticket changeTicketSeries(UUID id, String series);

    /**
     * Gets the status of a ticket. Once a status has been checked the series can no longer be changed.
     * @param id
     * @return Ticket
     */
    Ticket getStatus(UUID id);
}
