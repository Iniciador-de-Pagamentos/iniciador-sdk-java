import lombok.Data;

@Data
public class PaymentInitiationPayload {
    private String id;
    private String createdAt;
    private Error error;
    private PaymentInitiationStatus status;
    private String externalId;
    private String endToEndId;
    private String transactionIdentification;
    private String clientId;
    private String customerId;
    private Provider provider;
    private String consentId;
    private String paymentId;
    private String participantId;
    private User user;
    private User businessEntity;
    private String method;
    private String pixKey;
    private String qrCode;
    private double amount;
    private String date;
    private String description;
    private Metadata metadata;
    private String redirectURL;
    private String redirectOnErrorURL;
    private String ibge;
    private BankAccount debtor;
    private BankAccount creditor;
    private double fee;
}
