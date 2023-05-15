package org.example.ticketbooking;


import org.example.persistence.PersistData;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicketServiceImplTest {

    TicketServiceImpl ticketService = new TicketServiceImpl();


    /**
     * @verifies throw error if account id is invalid
     * @see TicketServiceImpl#purchaseTickets(Long, TicketTypeRequest...)
     */
    @Test
    public void purchaseTickets_shouldThrowErrorIfAccountIdIsInvalid() throws Exception {


        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                ticketService.purchaseTickets(-1L, new TicketTypeRequest(TicketTypeRequest.Type.ADULT,1)));
        assertTrue(exception.getMessage().contentEquals("Account Id is invalid"));

    }

    /**
     * @verifies throw error if purchasing too many tickets
     * @see TicketServiceImpl#purchaseTickets(Long, TicketTypeRequest...)
     */
    @Test
    public void purchaseTickets_shouldThrowErrorIfPurchasingTooManyTickets() throws Exception {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                ticketService.purchaseTickets(1L, new TicketTypeRequest(TicketTypeRequest.Type.ADULT,21)));
        assertTrue(exception.getMessage().contentEquals("Attempting to purchase too many tickets"));
    }

    /**
     * @verifies throw error if purchasing without adult ticket
     * @see TicketServiceImpl#purchaseTickets(Long, TicketTypeRequest...)
     */
    @Test
    public void purchaseTickets_shouldThrowErrorIfPurchasingWithoutAdultTicket() throws Exception {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                ticketService.purchaseTickets(1L, new TicketTypeRequest(TicketTypeRequest.Type.CHILD,1)));
        assertTrue(exception.getMessage().contentEquals("Attempting to purchase without an adult ticket"));
    }

    /**
     * @verifies throw error if purchasing with insufficient funds
     * @see TicketServiceImpl#purchaseTickets(Long, TicketTypeRequest...)
     */
    @Test
    public void purchaseTickets_shouldThrowErrorIfPurchasingWithInsufficientFunds() throws Exception {
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                ticketService.purchaseTickets(2L, new TicketTypeRequest(TicketTypeRequest.Type.ADULT,20)));
        assertTrue(exception.getMessage().contentEquals("Insufficient funds for purchase"));
    }
}
