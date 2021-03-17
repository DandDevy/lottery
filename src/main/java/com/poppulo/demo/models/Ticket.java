package com.poppulo.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * A Ticket represents a ticket for the lottery.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    /**
     * ticketId is a UUID for identifying tickets.
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID ticketId;

    /**
     * The ticket's series must be of integers between 0 and 2. It must from 3 values long.
     */
    @Length(min = 3, max = 3, message = "Ticket series must from 3 values long")
    @Column(nullable = false)
    private String series;

    /**
     * The tickets result.
     */
    @Column(nullable = false)
    private int status;

    public Ticket(UUID ticketId) {
        this.ticketId = ticketId;
    }
}
