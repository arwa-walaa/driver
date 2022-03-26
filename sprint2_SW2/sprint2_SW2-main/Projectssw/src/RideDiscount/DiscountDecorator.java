/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RideDiscount;

import Users.Client;

/**
 *
 * @author Access
 */
public abstract class DiscountDecorator implements RideDiscount {

    private RideDiscount rideDiscount;

    public DiscountDecorator(RideDiscount rideDiscount) {
        this.rideDiscount = rideDiscount;
    }

    @Override
    public double getCost(Client client) {
        return this.rideDiscount.getCost(client);
    }

}
