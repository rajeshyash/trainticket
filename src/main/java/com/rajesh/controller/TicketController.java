package com.rajesh.controller;

import com.rajesh.dto.ReceiptDetails;
import com.rajesh.entity.TicketDetails;
import com.rajesh.entity.TicketRequest;
import com.rajesh.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/purchase")
    public ResponseEntity<ReceiptDetails> purchaseTicket(@RequestBody TicketRequest ticketRequest) {
        ReceiptDetails receipt = ticketService.purchaseTicket(ticketRequest);
        return ResponseEntity.ok(receipt);
    }

    @GetMapping("/details")
    public ResponseEntity<List<TicketDetails>> getTicketDetails(@RequestParam(required = false) String userId) {
        List<TicketDetails> details = ticketService.getTicketDetails(userId);
        return ResponseEntity.ok(details);
    }

    @PutMapping("/modify-seat/{userId}")
    public ResponseEntity<String> modifyUserSeat(@PathVariable String userId, @RequestParam String newSeat) {
        String result = ticketService.modifyUserSeat(userId, newSeat);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/remove/{userId}")
    public ResponseEntity<String> removeUser(@PathVariable String userId) {
        String result = ticketService.removeUser(userId);
        return ResponseEntity.ok(result);
    }
}
