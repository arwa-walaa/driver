/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import RideDiscount.Ride;
import Users.Admin;
import Users.Client;
import Users.Driver;
import java.util.Scanner;

/**
 *
 * @author Access
 */
public class ClientControler {

    Scanner input = new Scanner(System.in);
    public Admin observerAdmin = Admin.getAdminObj();

    public void addRate(Driver driver, int rate) {
        driver.addRate(rate);
    }

    public void checkAvgRate(Driver driver) {
        System.out.println("Driver " + driver.getUserName() + " rate: " + driver.drivercon.avgRate(driver));
    }

    public Client request(Ride r, Admin admin, Client client) {
        client.ride = r;
        System.out.println("Enter Your Source and Destination: ");
        client.ride.Source = input.next();
        client.ride.Destnation = input.next();
        System.out.println("Enter Number of Passengers: ");
        client.ride.numberOfPassenger = input.nextInt();
        System.out.println("Enter the Ride Day ");
        client.ride.Day = input.next();
        System.out.println("Enter the Ride Date ");
        client.ride.Date = input.next();
        SubcribeDriver(admin, client);
        notifyDrivers(client);
        client.numberofRequestRide++;

        return client;
    }

    public void SubcribeDriver(Admin admin, Client client) {
        for (int i = 0; i < admin.db.getDrivers().size(); i++) {
            for (int j = 0; j < admin.db.getDrivers().get(i).getFavArea().size(); j++) {
                if (admin.db.getDrivers().get(i).getFavArea().get(j).equalsIgnoreCase(client.ride.getSource())
                        && (admin.db.getDrivers().get(i).drivercon.avaliable)) {
                    client.ObserverDrivers.add(admin.db.getDrivers().get(i));
                }
            }
        }

    }

    public void notifyDrivers(Client client) {
        for (int i = 0; i < client.ObserverDrivers.size(); i++) {
            client.ObserverDrivers.get(i).updateRequstClient(client);

        }

    }

    public void DisplayRideInfo(Client client) {
        String choose;
        System.out.println("Source : " + client.ride.getSource());
        System.out.println("Destination : " + client.ride.getDestnation());
        for (int i = 0; i < client.ride.getMakeOfferDrivers().size(); i++) {

            System.out.println("Driver Name : " + client.ride.getMakeOfferDrivers().get(i).getUserName()
                    + " , His Offer = " + client.ride.getOffers().get(i));
            System.out.println("Do you want to accept this offer (y/n)");
            choose = input.next();
            if (choose.equalsIgnoreCase("y")) {

                client.ride.setPrice(client.ride.getOffers().get(i));
                client.ride.PriceAfterDiscount = (double) client.ride.getOffers().get(i);
                client.ride.ridecon.calcDiscount(client);
                System.out.println("Enter The Time(Accedpt offer)  ");
                client.setEventTime(input.next());

                client.ride.setDriverName(client.ride.getMakeOfferDrivers().get(i).getUserName());
                client.acceptOffer = true;
                client.ride.getMakeOfferDrivers().get(i).drivercon.arrivedToSource = true;
                client.ride.getMakeOfferDrivers().get(i).drivercon.arrivedToDes = true;
                client.ride.getMakeOfferDrivers().get(i).drivercon.avaliable = false;
                client.ride.getMakeOfferDrivers().get(i).drivercon.checkAvalability(client.ride.getMakeOfferDrivers().get(i));
                System.out.println("Enter The Time(Driver arrived )  ");
                client.ride.getMakeOfferDrivers().get(i).setEventTime(input.next());
                notifyAdmin(client);

            } else if ((choose.equalsIgnoreCase("n"))) {
                client.acceptOffer = false;
                continue;

            }
        }

    }

    public void notifyAdmin(Client client) {
        observerAdmin.admincon.updateAfterAcceptPrice(client);
    }

}
