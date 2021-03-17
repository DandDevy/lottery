package com.poppulo.demo;

import com.poppulo.demo.models.Ticket;
import com.poppulo.demo.repositories.TicketDao;
import com.poppulo.demo.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class AppDev implements CommandLineRunner {
    @Autowired
    private TicketDao ticketDao;

    @Autowired
    TicketService service;

    @Override
    public void run(String... args) throws Exception {
        Ticket t = new Ticket();
        t.setSeries("012");
        t.setStatus(-1);
        t.setStatusChecked(false);
        ticketDao.save(t);
        Ticket t2 = new Ticket();
        t2.setSeries("000");
        t.setStatus(-1);
        t.setStatusChecked(false);
        ticketDao.save(t2);

        Ticket ticket = service.create("111");
        System.out.println("\n\nTICKET: " + ticket + "\n\n");
        ticket = service.create("11d");
        System.out.println("\n\nTICKET: " + ticket + "\n\n");

    }
}
