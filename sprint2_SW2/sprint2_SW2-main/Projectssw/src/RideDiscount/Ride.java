/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RideDiscount;

import Controller.RideControler;
import Users.Client;
import Users.Driver;
import java.util.ArrayList;

/**
 *
 * @author Access
 */
public class Ride implements RideDiscount {

    public String Source, Destnation, driverName;
    public Client client;
    public double Price;
    public double PriceAfterDiscount;
    public int numberOfPassenger = 0;
    public String Day, Date;
    public RideControler ridecon = new RideControler();
    public ArrayList<Double> Offers = new ArrayList<Double>();
    public ArrayList<Driver> makeOfferDrivers = new ArrayList<Driver>();

    public double getPriceAfterDiscount() {
        return PriceAfterDiscount;
    }

    public int getNumberOfPassenger() {
        return numberOfPassenger;
    }

    public void setNumberOfPassenger(int numberOfPassenger) {
        this.numberOfPassenger = numberOfPassenger;
    }

    public void setPriceAfterDiscount(double PriceAfterDiscount) {
        this.PriceAfterDiscount = PriceAfterDiscount;
    }

    public Ride() {
    }

    public Ride(Client client) {
        this.client = client;
    }

    public Ride(String Source, String Destnation, double Price) {
        this.Source = Source;
        this.Destnation = Destnation;
        this.Price = Price;
    }

    public ArrayList<Driver> getMakeOfferDrivers() {
        return makeOfferDrivers;
    }

    public String getSource() {
        return Source;
    }

    public String getDestnation() {
        return Destnation;
    }

    public double getPrice() {
        return Price;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }

    public void setDestnation(String Destnation) {
        this.Destnation = Destnation;
    }

    public void setPrice(double Price) {
        this.Price = Price;
        this.PriceAfterDiscount = Price;
    }

    public ArrayList<Double> getOffers() {
        return Offers;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public double CalcDiscount() {
        return this.Price;
    }

    @Override
    public double getCost(Client client) {
        return PriceAfterDiscount;
    }

}
