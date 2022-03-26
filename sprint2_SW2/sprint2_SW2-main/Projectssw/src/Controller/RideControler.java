/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import RideDiscount.FirstRideDiscount;
import RideDiscount.BirthDayDiscount;
import RideDiscount.PublicHolidayDiscount;
import RideDiscount.TwoPassengersDiscount;
import RideDiscount.Ride;
import RideDiscount.AdminDiscount;
import RideDiscount.RideDiscount;
import Users.Client;
import Users.Driver;

/**
 *
 * @author Access
 */
public class RideControler {

    public void setOfferPrice(Double offer, Driver driver, Ride ride) {
        ride.Offers.add(offer);
        ride.PriceAfterDiscount = offer;
        ride.Price = offer;
        ride.makeOfferDrivers.add(driver);

    }

    public void calcDiscount(Client client) {

        RideDiscount rideTest = new PublicHolidayDiscount(new FirstRideDiscount(new AdminDiscount(new BirthDayDiscount(new TwoPassengersDiscount(client.ride)))));
        System.out.println("The price after discount: " + rideTest.getCost(client));

    }

}
