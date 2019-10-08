package org.docksidestage.bizfw.basic.buyticket;

public class MultipleDayTicket implements Ticket {

    private int daysLeft;
    private int displayedPrice;
    private boolean inPark;
    private TicketType type;

    MultipleDayTicket(TicketType type) {
        this.type = type;
        this.daysLeft = calculateDaysLeft(type);
        this.displayedPrice = calculatePrice(type);
    }

    @Override
    public void doInPark() {
        if (daysLeft <= 0) {
            inPark = false;
            throw new IllegalStateException("Already used up all tickets: displayedPrice=" + displayedPrice);
        }

        --daysLeft;
        inPark = true;
    }

    @Override
    public boolean isAlreadyIn() {
        return inPark;
    }

    @Override
    public int getDisplayPrice() {
        return displayedPrice;
    }

    @Override
    public TicketType getType() {
        return type;
    }

    private int calculateDaysLeft(TicketType type) {
        switch (type) {
            case TWO_DAY: return 2;
            case FOUR_DAY: return 4;
            default: throw new IllegalArgumentException("Ticket type not supported: " + type);
        }
    }

    private int calculatePrice(TicketType type) {
        switch (type) {
            case TWO_DAY: return 13200;
            case FOUR_DAY: return 22400;
            default: throw new IllegalArgumentException("Ticket type not supported: " + type);
        }
    }
}
