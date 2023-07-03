import lombok.Data;

@Data
public class PaymentStatusPayload {
    private String id;
    private String date;
    private String consentId;
    private String createdAt;
    private String updatedAt;
    private String transactionIdentification;
    private String endToEndId;
    private double amount;
    private String status;
    private Error error;
    private String redirectConsentURL;
    private String externalId;
}
