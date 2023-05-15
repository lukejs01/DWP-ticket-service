package org.example.ticketbooking;

public class TicketTypeRequest {

    private int numOfTickets;
    private Type type;

    public TicketTypeRequest(Type type, int numOfTickets) {
        this.type = type;
        this.numOfTickets = numOfTickets;
    }

    public int getNumOfTickets() {
        return numOfTickets;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        INFANT, CHILD, ADULT
    }
}
