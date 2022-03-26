/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.Controller.admin;
import RideDiscount.Ride;
import Controller.Event;

import Users.Admin;
import Users.Client;
import Users.Driver;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Access
 */
public class AdminControler {

    Scanner input = new Scanner(System.in);
    public ArrayList<String> DiscountAreas = new ArrayList<String>();

    public void addDiscountAreas(String discountAreas) {

        DiscountAreas.add(discountAreas);
    }

    public ArrayList<String> getDiscountAreas() {
        return DiscountAreas;
    }

    void verifyDrivers(Admin admin) {
        String verify;
        System.out.println("if you want to verify (Y) or (N) if did not want to verify ");
        for (int i = 0; i < admin.db.getPending_drivers().size(); i++) {
            System.out.print("Do you want to verify " + admin.db.getPending_drivers().get(i).getUserName() + " / " + admin.db.getPending_drivers().get(i).getDrivingLicense() + " : ");
            verify = input.next();
            if (verify.equalsIgnoreCase("Y")) {
                admin.db.getPending_drivers().get(i).setVeryfied(true);
                admin.db.addDriver(admin.db.getPending_drivers().get(i));
                admin.db.addUser(admin.db.getPending_drivers().get(i));
                admin.db.getPending_drivers().remove(i);
                i--;

            } else if (verify.equalsIgnoreCase("N")) {
                admin.db.getPending_drivers().get(i).setVeryfied(false);

            } else {
                System.out.println("plz enter y or n only");
                continue;
            }
        }

    }

    void SusbendDriver(Admin admin) {
        String Suspend;
        System.out.println("if you want to Suspend (Y) or (N) if did not want to Suspend ");
        for (int i = 0; i < admin.db.getDrivers().size(); i++) {
            System.out.print("Do you want to Suspend " + admin.db.getDrivers().get(i).getUserName() + " / " + admin.db.getDrivers().get(i).getNumDriver() + " : ");
            Suspend = input.next();
            if (Suspend.equalsIgnoreCase("Y")) {
                admin.db.getDrivers().get(i).setSusbend(true);

            } else if (Suspend.equalsIgnoreCase("N")) {
                admin.db.getDrivers().get(i).setSusbend(false);

            } else {
                System.out.println("plz enter y or n only");
                continue;
            }
        }

    }

    void SusbendClient(Admin admin) {
        String Suspend;
        System.out.println("if you want to Suspend (Y) or (N) if did not want to Suspend ");
        for (int i = 0; i < admin.db.getClients().size(); i++) {
            System.out.print("Do you want to Suspend " + i + 1 + "." + admin.db.getClients().get(i).getUserName() + " / " + admin.db.getClients().get(i).getNumClient() + " : ");
            Suspend = input.next();
            if (Suspend.equalsIgnoreCase("Y")) {
                admin.db.getClients().get(i).setSusbend(true);
                System.out.println("Susbend= " + admin.db.getClients().get(i).isSusbend());

            } else if (Suspend.equalsIgnoreCase("N")) {
                admin.db.getClients().get(i).setSusbend(false);

            } else {
                System.out.println("plz enter y or n only");
                continue;
            }
        }

    }

    void updateAfterPrice(Driver driver) {
        Event event = new Event();
        for (int i = 0; i < admin.db.getDrivers().size(); i++) {
            if (admin.db.getDrivers().get(i).getPassword().equals(driver.getPassword())) {
                event.setDriverName(admin.db.getDrivers().get(i).getUserName());
                event.setEventName("Driver Put A Price");
                event.setEventTime(admin.db.getDrivers().get(i).getEventTime());
                event.setPrice(admin.db.getDrivers().get(i).Offer);
                for (int j = 0; j < driver.clientsRequsts.size(); j++) {
                    if (admin.db.getDrivers().get(i).clientsRequsts.get(j).acceptOffer) {
                        event.setClientrName(admin.db.getDrivers().get(i).clientsRequsts.get(j).getUserName());
                    }
                }
            }
        }
        admin.addEvent(event);
    }

    void updateAfterAcceptPrice(Client client) {
        Event event = new Event();
        for (int i = 0; i < admin.db.getClients().size(); i++) {
            if (admin.db.getClients().get(i).getPassword().equals(client.getPassword())) {
                event.setDriverName(admin.db.getClients().get(i).ride.getDriverName());
                event.setEventName("Client Accept Driver Offer");
                event.setEventTime(admin.db.getClients().get(i).getEventTime());
                event.setPrice(admin.db.getClients().get(i).ride.getPriceAfterDiscount());
                event.setClientrName(admin.db.getClients().get(i).getUserName());
            }
        }
        admin.addEvent(event);
    }

    void updateAfterArrivedToSource(Driver driver) {
        Event event = new Event();
        for (int i = 0; i < admin.db.getDrivers().size(); i++) {
            if (admin.db.getDrivers().get(i).getPassword().equals(driver.getPassword())) {
                event.setDriverName(admin.db.getDrivers().get(i).getUserName());
                event.setEventName("Driver arrived to user location ");
                event.setEventTime(admin.db.getDrivers().get(i).getEventTime());
                event.setPrice(admin.db.getDrivers().get(i).Offer);
                for (int j = 0; j < driver.clientsRequsts.size(); j++) {
                    if (admin.db.getDrivers().get(i).clientsRequsts.get(j).acceptOffer) {
                        event.setClientrName(driver.clientsRequsts.get(j).getUserName());
                    }
                }

            }
        }
        admin.addEvent(event);

    }

    void updateAfterArrivedToDest(Driver driver) {
        Event event = new Event();

        for (int i = 0; i < admin.db.getDrivers().size(); i++) {
            if (admin.db.getDrivers().get(i).getPassword().equals(driver.getPassword())) {
                event.setDriverName(admin.db.getDrivers().get(i).getUserName());
                event.setEventName("Driver arrived to user destenation ");
                Integer time = Integer.parseInt(admin.db.getDrivers().get(i).getEventTime()) + 2;
                event.setEventTime(time.toString());
                event.setPrice(admin.db.getDrivers().get(i).Offer);
                for (int j = 0; j < driver.clientsRequsts.size(); j++) {
                    if (admin.db.getDrivers().get(i).clientsRequsts.get(j).acceptOffer) {
                        event.setClientrName(driver.clientsRequsts.get(j).getUserName());
                    }
                }
            }
        }
        admin.addEvent(event);
    }

    void displayEvents() {
        for (int i = 0; i < admin.getEvents().size(); i++) {
            System.out.println(admin.getEvents().get(i).getEventName() + " --> in time " + admin.getEvents().get(i).getEventTime()
                    + " Driver : " + admin.getEvents().get(i).getDriverName()
                    + " and the price is  " + admin.getEvents().get(i).getPrice()
                    + " for Client :  " + admin.getEvents().get(i).getClientrName());
        }
    }

}
