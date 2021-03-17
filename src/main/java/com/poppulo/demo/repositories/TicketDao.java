package com.poppulo.demo.repositories;

import com.poppulo.demo.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketDao extends JpaRepository<Ticket, UUID> {
}
