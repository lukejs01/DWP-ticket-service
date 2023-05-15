package org.example.thirdparty.paymentgateway;

public interface TicketPaymentService {

    void makePayment(long accountId, int totalAmountToPay);

}
