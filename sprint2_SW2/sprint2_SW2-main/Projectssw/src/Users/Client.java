/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import Controller.ClientControler;
import RideDiscount.Ride;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Access
 */
public class Client extends User {

    private static int ClientId = 0;
    int NumClient;
    public Ride ride;
    String eventTime;
    public boolean acceptOffer = false;
    public boolean Susbend = false;
    public ClientControler clientcon = new ClientControler();
    public ArrayList<Driver> ObserverDrivers = new ArrayList<Driver>();
    boolean firstTime = true;
    public int numberofRequestRide = 0;

    public Client() {
        ClientId++;
        NumClient = ClientId;

    }

    public boolean isAcceptOffer() {
        return acceptOffer;
    }

    public void setAcceptOffer(boolean acceptOffer) {
        this.acceptOffer = acceptOffer;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public boolean isSusbend() {
        return Susbend;
    }

    public void setSusbend(boolean Susbend) {
        this.Susbend = Susbend;
    }

    public Ride getRide() {
        return ride;
    }

    public ArrayList<Driver> getObserverDrivers() {
        return ObserverDrivers;
    }

    public int getNumClient() {
        return NumClient;
    }

    public static int getClientId() {
        return ClientId;
    }

    public User Register(User client, Admin admin) {

        System.out.println("please enter Name: ");
        client.userName = input.next();
        System.out.println("please enter password : ");
        client.password = input.next();
        System.out.println("please enter your Birthday : ");
        client.birthDay = input.next();
        System.out.println("please enter email : ");
        client.email = input.next();
        System.out.println("please enter phone : ");
        client.phone = input.next();
        admin.db.addClient((Client) client);
        return client;
    }

    public int Login(Admin admin) {

        System.out.println("Please enter your user name and password ");
        String Username = input.next();
        String Password = input.next();

        int id = 0;
        boolean check = false;
        for (int i = 0; i < admin.db.getClients().size(); i++) {
            if (admin.db.getClients().get(i).getPassword().equals(Password) && admin.db.getClients().get(i).getUserName().equals(Username)) {

                id = i;

                check = true;
                if (admin.db.getClients().get(i).isSusbend()) {
                    check = false;
                }

            }

        }
        if (check == false) {
            System.out.println("your user name or password is wrong or you are susbended now");

        }
        return id;

    }

}
