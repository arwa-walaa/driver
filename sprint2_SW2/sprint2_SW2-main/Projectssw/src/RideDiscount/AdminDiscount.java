package RideDiscount;

import Controller.AdminControler;

import RideDiscount.DiscountDecorator;
import RideDiscount.RideDiscount;
import Users.Client;
///**
// *
// * @author Access
// */

public class AdminDiscount extends DiscountDecorator {

    public AdminDiscount(RideDiscount rideDiscount) {
        super(rideDiscount);
    }

    @Override
    public double getCost(Client client) {
        AdminControler admin = client.clientcon.observerAdmin.admincon;
        double discountPresentage = 10;
        for (int i = 0; i < admin.DiscountAreas.size(); i++) {
            if (client.ride.getDestnation().equalsIgnoreCase(admin.DiscountAreas.get(i))) {
                return super.getCost(client) - super.getCost(client) * (double) (discountPresentage / 100);
            }
        }
        return super.getCost(client);

    }
}
