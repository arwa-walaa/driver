package RideDiscount;

import RideDiscount.DiscountDecorator;
import RideDiscount.RideDiscount;
import Users.Client;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package projectssw;
//
///**
// *
// * @author Access
// */
public class PublicHolidayDiscount extends DiscountDecorator {

    public PublicHolidayDiscount(RideDiscount rideDiscount) {
        super(rideDiscount);
    }

    @Override
    public double getCost(Client client) {
        double discountPresentage = 5;
        if ((client.ride.Day.equalsIgnoreCase(PublicHoliday.Friday.toString())) || (client.ride.Day.equalsIgnoreCase(PublicHoliday.Saturday.toString()))) {

            return super.getCost(client) - super.getCost(client) * (double) (discountPresentage / 100);
        }

        return super.getCost(client);
    }
}
