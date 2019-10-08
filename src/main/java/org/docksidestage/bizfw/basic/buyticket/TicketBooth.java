/*
 * Copyright 2019-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.docksidestage.bizfw.basic.buyticket;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jflute
 */
public class TicketBooth {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final int MAX_QUANTITY = 10;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private Map<TicketType, Integer> availabilities;
    private Integer salesProceeds;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public TicketBooth() {
        availabilities = new HashMap<>();
        for (TicketType type : TicketType.values()) {
            availabilities.put(type, MAX_QUANTITY);
        }
    }

    // ===================================================================================
    //                                                                          Buy Ticket
    //                                                                          ==========
    public Ticket buyOneDayPassport(int handedMoney) {
        TicketBuyResult ticketBuyResult = buyPassport(TicketType.ONE_DAY, handedMoney);

        return ticketBuyResult.getTicket();
    }

    public TicketBuyResult buyTwoDayPassport(int handedMoney) {
        return buyPassport(TicketType.TWO_DAY, handedMoney);
    }

    public TicketBuyResult buyFourDayPassport(int handedMoney) {
        return buyPassport(TicketType.FOUR_DAY, handedMoney);
    }

    private TicketBuyResult buyPassport(TicketType type, int handedMoney) {
        Ticket ticket = generateTicket(type);
        validatePurchase(ticket, handedMoney);

        int availability = availabilities.get(type);
        availabilities.put(type, --availability);

        int displayPrice = ticket.getDisplayPrice();
        incrementSalesProceeds(displayPrice);

        return new TicketBuyResult(ticket, handedMoney - displayPrice);
    }

    private Ticket generateTicket(TicketType type) {
        if (type == TicketType.ONE_DAY) {
            return new OneDayTicket();
        }

        return new MultipleDayTicket(type);
    }

    private void validatePurchase(Ticket ticket, int handedMoney) {
        int availability = availabilities.get(ticket.getType());

        if (availability <= 0) {
            throw new TicketSoldOutException("Sold out");
        }

        if (handedMoney < ticket.getDisplayPrice()) {
            throw new TicketShortMoneyException("Short money: " + handedMoney);
        }
    }
    
    private void incrementSalesProceeds(int price) {
        if (salesProceeds != null) {
            salesProceeds = salesProceeds + price;
        } else {
            salesProceeds = price;
        }
    }

    public static class TicketSoldOutException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketSoldOutException(String msg) {
            super(msg);
        }
    }

    public static class TicketShortMoneyException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public TicketShortMoneyException(String msg) {
            super(msg);
        }
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public int getOneDayQuantity() {
        return availabilities.get(TicketType.ONE_DAY);
    }

    public int getTwoDayQuantity() {
        return availabilities.get(TicketType.TWO_DAY);
    }

    public int getFourDayQuantity() {
        return availabilities.get(TicketType.FOUR_DAY);
    }

    public Integer getSalesProceeds() {
        return salesProceeds;
    }
}
