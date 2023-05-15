package org.example.ticketbooking;

import org.example.exception.InvalidPurchaseException;
import org.example.persistence.PersistData;
import org.example.user.User;
import java.util.Arrays;
import java.util.List;

public class TicketServiceImpl implements TicketService {


    public TicketServiceImpl() {
    }

    PersistData persistData = new PersistData();
    List<User> users = persistData.createUsers();

    /**
     * @should throw error if account id is invalid
     * @should throw error if purchasing too many tickets
     * @should throw error if purchasing without adult ticket
     * @should throw error if purchasing with insufficient funds
     */
    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        User user = getUser(accountId);
        int costOfTickets = getCostOfTickets(ticketTypeRequests);
        int numOfSeats = getNumOfSeats(ticketTypeRequests);

        boolean verified = ticketPurchaseChecker(user, costOfTickets);

        if (verified){
            // TicketPaymentService makePayment( accountId, costOfTickets ) ... - pass cost of tickets for payment
            // SeatBookingService reserveSeats( accountId, numOfSeats ) ... - pass number of seats
        }
    }


    private boolean ticketPurchaseChecker(User user, int costOfTickets) {
        if (costOfTickets == -1) {
            throw new RuntimeException("Attempting to purchase too many tickets");
        }
        if (costOfTickets == -2) {
            throw new RuntimeException("Attempting to purchase without an adult ticket");
        }
        if (costOfTickets > user.getBalance()) {
            throw new InvalidPurchaseException("Insufficient funds for purchase");
        }

        return true;
    }

    private User getUser(Long accountId) {
        try {
            if (accountId < 1){
                throw new InvalidPurchaseException("Account Id is invalid");
            } else {
                return persistData.findUser(users, accountId);
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private int getCostOfTickets(TicketTypeRequest... ticketTypeRequests) {
        int costOfTickets = 0;
        int numOfTotalTickets = 0;
        boolean isNotMissingAdult = false;

        // transformed ticketTypeRequests to List for easier manipulation
        List<TicketTypeRequest> ticketType;
        ticketType = Arrays.stream(ticketTypeRequests).toList();

        for (TicketTypeRequest ticket : ticketType) {
            int numOfTickets = ticket.getNumOfTickets();
            numOfTotalTickets += numOfTickets;
            
            // business rules - checking if there are adult tickets being purchased
            if (ticket.getType() == TicketTypeRequest.Type.ADULT){
                isNotMissingAdult = true;
            }

            // calculations for total cost of tickets
            switch (ticket.getType()) {
                case ADULT -> costOfTickets += (numOfTickets * 20);
                case CHILD -> costOfTickets += (numOfTickets * 10);
            }
        }

        // negative number indicates that one or more of the business rules has been broken
        if (numOfTotalTickets > 20 ) {
            return -1;
        }if (!isNotMissingAdult) {
            return -2;
        }
        return costOfTickets;
    }

    private int getNumOfSeats(TicketTypeRequest... ticketTypeRequests) {
        int numOfSeats = 0;
        List<TicketTypeRequest> ticketType;
        ticketType = Arrays.stream(ticketTypeRequests).toList();

        for (TicketTypeRequest ticket: ticketType){
            switch (ticket.getType()) {
                case ADULT, CHILD -> numOfSeats += ticket.getNumOfTickets();
            }
        }

        return numOfSeats;
    }

}
