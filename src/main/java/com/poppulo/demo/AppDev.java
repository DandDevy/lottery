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

    @Override
    public void run(String... args) throws Exception {
        Ticket t = new Ticket();
        t.setSeries("012");
        t.setStatus(-1);
        ticketDao.save(t);
    }
}
