package com.rajesh.service;

import com.rajesh.dto.ReceiptDetails;
import com.rajesh.entity.TicketDetails;
import com.rajesh.entity.TicketRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicketService {
    private final Map<String, TicketDetails> tickets = new HashMap<>();
    private final Map<String, String> userSeats = new HashMap<>();
    private int seatCounter = 1;
    public ReceiptDetails purchaseTicket(TicketRequest ticketRequest) {
        // Generate receipt and allocate seat
        String userId = UUID.randomUUID().toString();
        String seat = allocateSeat();

        TicketDetails details = new TicketDetails(ticketRequest, userId, seat);
        tickets.put(userId, details);
        userSeats.put(userId, seat);
        System.out.println("User Id : " + tickets.keySet());
        return ReceiptDetails.builder()
                .to(details.getTicketRequest().getTo())
                .from(details.getTicketRequest().getFrom())
                .seat(seat)
                .user(details.getTicketRequest().getUser())
                .pricePaid(details.getTicketRequest().getPricePaid())
                .build();
    }
    private String allocateSeat() {
        // Simple seat allocation logic, alternating between section A and B
        String section = seatCounter % 2 == 0 ? "A" : "B";
        return section + seatCounter++;
    }

    public List<TicketDetails> getTicketDetails(String userId) {
        List<TicketDetails> list = new ArrayList<>();
        if(userId!=null) {
            TicketDetails details = tickets.get(userId);
            if (details == null) {
               list.add(TicketDetails.builder()
                        .userId("User not found : " + userId)
                        .build());
            } else {
                list.add(details);
            }
        }else {
            for (Map.Entry<String, TicketDetails> data : tickets.entrySet()) {
                list.add(data.getValue());
            }
        }
        return list;
    }

    public String modifyUserSeat(String userId, String newSeat) {
        if (userSeats.containsKey(userId)) {
            userSeats.put(userId, newSeat);
            TicketDetails details = tickets.get(userId);
            TicketDetails detailsNew = new TicketDetails(details.getTicketRequest(), userId, newSeat);
            tickets.put(userId, detailsNew);
            return "Seat modified for user: " + userId;
        }
        return "User not found.";
    }

    public String removeUser(String userId) {
        TicketDetails removedTicket = tickets.remove(userId);
        if (removedTicket != null) {
            userSeats.remove(userId);
            return "User removed: " + removedTicket.getTicketRequest().getUser().getFirstName() +" " + removedTicket.getTicketRequest().getUser().getLastName();
        }
        return "User not found.";
    }
}
