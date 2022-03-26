/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Users.Client;
import Users.Driver;
import Users.User;
import java.util.ArrayList;

/**
 *
 * @author Access
 */
public class DB {

    private ArrayList<Client> clients = new ArrayList<Client>();
    private ArrayList<Driver> drivers = new ArrayList<Driver>();
    private ArrayList<Driver> pending_drivers = new ArrayList<Driver>();
    private ArrayList<User> users = new ArrayList<User>();

    public void addUser(User user) {
        users.add(user);
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public ArrayList<Driver> getDrivers() {
        return drivers;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    public void pendingDrivers(Driver driver) {
        pending_drivers.add(driver);
    }

    public ArrayList<Driver> getPending_drivers() {
        return pending_drivers;
    }

    public void displayUsers() {

        for (int i = 0; i < users.size(); i++) {
            System.out.println("user " + (i + 1) + " information ");
            users.get(i).display();
        }

    }

}
