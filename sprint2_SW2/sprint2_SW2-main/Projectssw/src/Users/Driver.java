/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import Controller.DriverControler;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Access
 */
public class Driver extends User {

    private static int DriverId = 0;
    int numDriver;
    public double Offer;
    public String eventTime;
    public String drivingLicense, nationalID;
    public DriverControler drivercon = new DriverControler();
    protected boolean veryfied = false;
    protected boolean Susbend = false;
    public ArrayList<Client> clientsRequsts = new ArrayList<Client>();
    public ArrayList<Integer> rates = new ArrayList<Integer>();
    protected ArrayList<String> favArea = new ArrayList<String>();

    public Driver() {
        DriverId++;
        numDriver = DriverId;

    }

    public Driver(String drivingLicense, String nationalID, String userName, int id, String password, String email, String phone) {
        super(userName, id, password, email, phone);
        this.drivingLicense = drivingLicense;
        this.nationalID = nationalID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Scanner getInput() {
        return input;
    }

    public void setInput(Scanner input) {
        this.input = input;
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

    public double getOffer() {
        return Offer;
    }

    public ArrayList<Client> getClientsRequsts() {
        return clientsRequsts;
    }

    public int getNumDriver() {
        return numDriver;
    }

    public static int getDriverId() {
        return DriverId;

    }

    public void addFavArea(String favarea) {

        favArea.add(favarea);
    }

    public ArrayList<String> getFavArea() {
        return favArea;
    }

    public String getDrivingLicense() {
        return drivingLicense;
    }

    public String getNationalID() {
        return nationalID;
    }

    public boolean isVeryfied() {
        return veryfied;
    }

    public void setVeryfied(boolean veryfied) {
        this.veryfied = veryfied;
    }

    public void addRate(int rate) {
        rates.add(rate);

    }

    public ArrayList<Integer> getRates() {
        return rates;
    }

    public void updateRequstClient(Client c) {
        clientsRequsts.add(c);

    }

    public User Register(User driver, Admin admin) {
        System.out.println("please enter Name: ");
        driver.userName = input.next();
        System.out.println("please enter password : ");
        driver.password = input.next();
        System.out.println("please enter email : ");
        driver.email = input.next();
        System.out.println("please enter phone : ");
        driver.phone = input.next();
        System.out.println("please enter License : ");
        driver.drivingLicense = input.next();
        System.out.println("please enter nationalID : ");
        driver.nationalID = input.next();
        admin.db.pendingDrivers((Driver) driver);
        return driver;

    }

    public int Login(Admin admin) {

        System.out.println("Please enter your user name and password");
        String Username = input.next();
        String Password = input.next();
        boolean check = false;
        int id = -1;

        for (int i = 0; i < admin.db.getDrivers().size(); i++) {

            if (admin.db.getDrivers().get(i).getPassword().equals(Password) && admin.db.getDrivers().get(i).getUserName().equals(Username)) {
                check = true;

                id = i;

                if (admin.db.getDrivers().get(i).isSusbend()) {
                    check = false;

                }
            }
        }

        if (check == false) {
            System.out.println("your user name or password is wrong or you are suspended or Admin Did not verify you yet! ");

        }
        return id;
    }

}
