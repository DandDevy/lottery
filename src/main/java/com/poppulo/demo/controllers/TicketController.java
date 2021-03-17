package com.poppulo.demo.controllers;

import com.poppulo.demo.models.Ticket;
import com.poppulo.demo.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TicketController {
    @Autowired
    TicketService service;

    @PostMapping("/ticket")
    public ResponseEntity<Ticket> create(@RequestBody Ticket newTicket) {
        Ticket ticket = service.create(newTicket.getSeries());
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @GetMapping("/ticket")
    public ResponseEntity<List<Ticket>> list() {
        List<Ticket> tickets = service.listTickets();
        if (tickets == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<Ticket> get(@PathVariable(name = "ticketId") String ticketIdString) {
        UUID ticketId = UUID.fromString(ticketIdString);
        Ticket ticketById = service.getTicketById(ticketId);
        if (ticketById == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticketById, HttpStatus.OK);
    }

    @PutMapping("/ticket/{ticketId}")
    public ResponseEntity<Ticket> edit(@PathVariable(name = "ticketId") String ticketIdString, @RequestBody Ticket ticketEdit){
        UUID ticketId = UUID.fromString(ticketIdString);
        Ticket ticket = service.changeTicketSeries(ticketId, ticketEdit.getSeries());
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @PutMapping("/status/{ticketId}")
    public ResponseEntity<Ticket> getStatus(@PathVariable(name = "ticketId") String ticketIdString){
        UUID ticketId = UUID.fromString(ticketIdString);
        Ticket ticket = service.getStatus(ticketId);
        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

}
