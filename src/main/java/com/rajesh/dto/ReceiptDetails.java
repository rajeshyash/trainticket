package com.rajesh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.BindParam;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptDetails {
    private String from;
    private String to;
    private String seat;
    private UserDto user;
    private double pricePaid;
}
