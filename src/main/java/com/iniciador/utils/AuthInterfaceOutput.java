package com.iniciador.utils;

public class AuthInterfaceOutput {
    private String accessToken;
    private String interfaceURL;
    private String paymentId;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getInterfaceURL() {
        return interfaceURL;
    }

    public void setInterfaceURL(String interfaceURL) {
        this.interfaceURL = interfaceURL;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}