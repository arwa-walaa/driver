package RideDiscount;
//
///**

import Users.Client;

// *
// * @author Access
// */
public class BirthDayDiscount extends DiscountDecorator {

    public BirthDayDiscount(RideDiscount rideDiscount) {
        super(rideDiscount);
    }

    @Override
    public double getCost(Client client) {
        double discountPresentage = 10;
        if (client.birthDay.equalsIgnoreCase(client.ride.Date)) {
            return super.getCost(client) - super.getCost(client) * (double) (discountPresentage / 100);
        }
        return super.getCost(client);
    }
}
