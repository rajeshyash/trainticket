package com.rajesh.controller;

import com.rajesh.dto.ReceiptDetails;
import com.rajesh.entity.TicketDetails;
import com.rajesh.entity.TicketRequest;
import com.rajesh.service.TicketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketControllerTest {

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private TicketController ticketController;

    TicketRequest ticketRequest = TicketRequest.builder()
            .from("London")
            .to("France")
            .build();
    ReceiptDetails receiptDetails = ReceiptDetails.builder()
            .from("London")
            .to("France")
            .seat("B1")
            .build();

    TicketDetails ticketDetails = TicketDetails.builder()
            .userId("123")
            .seat("B2")
            .build();

    @Test
    void purchaseTicket() throws Exception {
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(ticketService.purchaseTicket(any(TicketRequest.class))).thenReturn(receiptDetails);
        ResponseEntity<ReceiptDetails> result =  ticketController.purchaseTicket(ticketRequest);
        assertSame(result.getStatusCode(),HttpStatus.OK);
        assertSame(Objects.requireNonNull(result.getBody()).getFrom(),"London");
    }

    @Test
    void getTicketDetails() throws Exception {
        // Arrange
        String userId = "123";
        when(ticketService.getTicketDetails(userId)).thenReturn(Arrays.asList(ticketDetails));
        ResponseEntity<List<TicketDetails>> result =  ticketController.getTicketDetails(userId);
        assertSame(result.getStatusCode(),HttpStatus.OK);
        assertSame(Objects.requireNonNull(result.getBody()).size(),1);
    }

    @Test
    void modifyUserSeat() throws Exception {
        // Arrange
        String userId = "123";
        String newSeat = "A1";
        String result = "Seat modified successfully";

        when(ticketService.modifyUserSeat(userId, newSeat)).thenReturn(result);
        ResponseEntity<String> resultData =  ticketController.modifyUserSeat(userId,newSeat);

        assertSame(resultData.getStatusCode(),HttpStatus.OK);
        assertSame(resultData.getBody(),result);
    }

    @Test
    void removeUser() throws Exception {
        // Arrange
        String userId = "123";
        String result = "User removed successfully";
        when(ticketService.removeUser(userId)).thenReturn(result);
        ResponseEntity<String> resultData = ticketController.removeUser(userId);
        assertSame(resultData.getStatusCode(),HttpStatus.OK);
        assertSame(resultData.getBody(),result);
    }

}

