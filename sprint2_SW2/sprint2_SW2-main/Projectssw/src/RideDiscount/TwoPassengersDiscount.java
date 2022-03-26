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
public class TwoPassengersDiscount extends DiscountDecorator {

    public TwoPassengersDiscount(RideDiscount rideDiscount) {
        super(rideDiscount);
    }

    @Override
    public double getCost(Client client) {
        double discountPresentage = 5;
        if (client.ride.numberOfPassenger == 2) {
            return super.getCost(client) - super.getCost(client) * (double) (discountPresentage / 100);
        }
        return super.getCost(client);
    }
}
