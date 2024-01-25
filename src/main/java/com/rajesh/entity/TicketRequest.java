package com.rajesh.entity;

import com.rajesh.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    private String from;
    private String to;
    private UserDto user;
    private double pricePaid;
}
