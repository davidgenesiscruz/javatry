package org.docksidestage.bizfw.basic.buyticket;

public class OneDayTicket implements Ticket {

    private static final int DISPLAY_PRICE = 7400;
    private boolean inPark;

    @Override
    public void doInPark() {
        if (inPark) {
            throw new IllegalStateException("Already in park by this ticket: displayedPrice=" + DISPLAY_PRICE);
        }
        inPark = true;
    }

    @Override
    public int getDisplayPrice() {
        return DISPLAY_PRICE;
    }

    @Override
    public boolean isAlreadyIn() {
        return inPark;
    }

    @Override
    public TicketType getType() {
        return TicketType.ONE_DAY;
    }
}
