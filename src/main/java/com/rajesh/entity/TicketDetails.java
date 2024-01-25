package com.rajesh.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDetails {
    private TicketRequest ticketRequest;
    private String userId;
    private String seat;
}
