package com.poppulo.demo.services;

import com.poppulo.demo.models.Ticket;
import com.poppulo.demo.repositories.TicketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Override
    public Ticket create(String series) {
        if (!isSeriesValid(series))
            return null;

        Ticket ticket = new Ticket();
        ticket.setSeries(series);
        ticket.setStatus(-1);
        ticket.setStatusChecked(false);
        return ticketDao.save(ticket);
    }

    @Override
    public List<Ticket> listTickets() {
        return ticketDao.findAll();
    }

    @Override
    public Ticket getTicketById(UUID id) {
        Optional<Ticket> optionalTicket = ticketDao.findById(id);
        return optionalTicket.orElse(null);
    }

    @Override
    @Transactional
    public Ticket changeTicketSeries(UUID id, String series) {
        if (isSeriesValid(series)){
            Ticket one = ticketDao.getOne(id);
            if (one.getStatus() == -1) {
                one.setSeries(series);
                return ticketDao.save(one);
            }
        }

        return null;
    }

    @Override
    @Transactional
    public Ticket getStatus(UUID id) {
        Ticket one = ticketDao.getOne(id);
        if (one.getStatus() == -1) {

            String series = one.getSeries();
            int sum = 0;
            boolean isSeriesOfSameValues = true;
            boolean isSeriesDifferentFromFirstValue = true;
            int firstValue = -1;
            for (int i = 0; i < series.length(); i++) {
                int value = series.charAt(i) - '0';
                sum += value;
                if (firstValue == -1) {
                    firstValue = value;

                } else if (value != firstValue) {
                    isSeriesOfSameValues = false;
                    isSeriesDifferentFromFirstValue = true;
                } else {
                    isSeriesDifferentFromFirstValue = false;
                }
            }

            if (sum == 2){
                one.setStatus(10);
            } else if (isSeriesOfSameValues) {
                one.setStatus(5);
            } else if (isSeriesDifferentFromFirstValue) {
                one.setStatus(1);
            } else {
                one.setStatus(0);
            }

            return ticketDao.save(one);
        }
        return null;
    }

    private boolean isSeriesValid(String series) {
        if (series.length() != 3)
            return false;

        try {
            for (int i = 0; i < series.length(); i++) {
                int value = series.charAt(i) - '0';
                if (value > 3 || value < 0) {
                    return false;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }


}
