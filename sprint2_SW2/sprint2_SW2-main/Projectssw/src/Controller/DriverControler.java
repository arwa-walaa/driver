/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Users.Admin;
import Users.Driver;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Access
 */
public class DriverControler {

    Scanner input = new Scanner(System.in);
    boolean arrivedToSource = false;
    boolean arrivedToDes = false;
    boolean avaliable = true;

    protected Admin observerAdmin = Admin.getAdminObj();

    public double avgRate(Driver driver) {
        int i = 0, sum = 0;
        for (i = 0; i < driver.rates.size(); i++) {
            sum += driver.rates.get(i);
        }
        return sum / (double) i;

    }

    public void notifyAdmin(Driver driver) {
        observerAdmin.admincon.updateAfterPrice(driver);
    }

    public void makeOffer(Driver driver) {
        double price;
        for (int i = 0; i < driver.clientsRequsts.size(); i++) {
            if (driver.clientsRequsts.get(i).acceptOffer) {
            } else {
                System.out.println("Enter the current time and  the price  for "
                        + "  #Client User Name " + driver.clientsRequsts.get(i).getUserName()
                        + "  #Source :" + driver.clientsRequsts.get(i).ride.getSource()
                        + "  #Destination :" + driver.clientsRequsts.get(i).ride.getDestnation()
                        + "  #Number of Passenger :" + driver.clientsRequsts.get(i).ride.getNumberOfPassenger());
                driver.eventTime = input.next();
                price = input.nextDouble();
                driver.Offer = price;
                driver.clientsRequsts.get(i).ride.setPrice(price);
                driver.clientsRequsts.get(i).ride.PriceAfterDiscount = (price);
                driver.clientsRequsts.get(i).ride.ridecon.setOfferPrice(price, driver, driver.clientsRequsts.get(i).ride);
                notifyAdmin(driver);

            }
        }

    }

    public void notifyAdminByArrivingtoSorce(Driver driver) {
        observerAdmin.admincon.updateAfterArrivedToSource(driver);
    }

    public void notifyAdminByArrivingtoDest(Driver driver) {
        observerAdmin.admincon.updateAfterArrivedToDest(driver);
    }

    public void checkAvalability(Driver driver) {
        if (driver.drivercon.avaliable) {

        } else {
            notifyAdminByArrivingtoSorce(driver);
        }
        notifyAdminByArrivingtoDest(driver);

    }

}
