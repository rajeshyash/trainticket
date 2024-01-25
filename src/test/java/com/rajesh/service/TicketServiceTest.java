package com.rajesh.service;

import com.rajesh.dto.ReceiptDetails;
import com.rajesh.dto.UserDto;
import com.rajesh.entity.TicketDetails;
import com.rajesh.entity.TicketRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {
    @InjectMocks
    private TicketService ticketService;
    TicketRequest ticketRequest = TicketRequest.builder()
            .from("London")
            .to("France")
            .user(UserDto.builder().firstName("Demo").lastName("Demo").build())
            .pricePaid(20.0)
            .build();
    @Test
    void purchaseTicket() {
        ReceiptDetails receiptDetails = ticketService.purchaseTicket(ticketRequest);
        assertSame(receiptDetails.getFrom(), "London");
        assertSame(receiptDetails.getTo(), "France");
    }

    @Test
    void getTicketDetails() {
        List<TicketDetails> ticketDetails= ticketService.getTicketDetails("123");
        assertSame(ticketDetails.size(), 1);
    }
    @Test
    void getTicketDetailsNotUserIdNull() {
        List<TicketDetails> ticketDetails= ticketService.getTicketDetails(null);
        assertSame(ticketDetails.size(), 0);
    }

    @Test
    void modifyUserSeat() {
        String string = ticketService.modifyUserSeat("123", "A1");
        System.out.println(string);
    }

    @Test
    void removeUser() {
        String string = ticketService.removeUser("123");
        System.out.println(string);
    }
}