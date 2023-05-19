package com.iniciador.dtos;

import lombok.Data;

@Data
public class AuthInterfaceOutput {
    private String accessToken;
    private String interfaceURL;
    private String paymentId;
}
