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
import Users.User;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Access
 */
public class Controller {

    static Admin admin = Admin.getAdminObj();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {

            System.out.println("1-register ");
            System.out.println("2-login");
            System.out.println("3-exit");
            int outerchoice = input.nextInt();
            if (outerchoice == 1) {

                System.out.println("register as :  ");
                System.out.println("1.Client");
                System.out.println("2.Driver");
                System.out.println("3.Admin");

                int choice = input.nextInt();
                if (choice == 1) {
                    User client = new Client();
                    client.Register(client, admin);
                } else if (choice == 2) {
                    User driver = new Driver();
                    driver.Register(driver, admin);
                } else if (choice == 3) {

                    admin.Register(admin);
                } else {
                    continue;
                }

            } else if (outerchoice == 2) {

                System.out.println("login as :  ");
                System.out.println("1.Client");
                System.out.println("2.Driver");
                System.out.println("3.Admin");

                int choice = input.nextInt();

                if (choice == 1) {
                    User client = new Client();
                    int id = client.Login(admin);

                    if (admin.db.getClients().get(id).isSusbend()) {
                        break;
                    }
                    while (true) {
                        System.out.println("1.ADD RATE For Driver");
                        System.out.println("2.Request Ride");
                        System.out.println("3.Display All Offers");
                        System.out.println("4.Back to main manu");
                        choice = input.nextInt();
                        if (choice == 1) {
                            System.out.println("Plz enter the drive name and its rate");
                            String dName = input.next();
                            int rate = input.nextInt();
                            int indexDriver = 0;
                            for (int i = 0; i < admin.db.getDrivers().size(); i++) {
                                if (admin.db.getDrivers().get(i).getUserName().equals(dName)) {
                                    admin.db.getClients().get(id).clientcon.addRate(admin.db.getDrivers().get(i), rate);

                                    indexDriver = i;
                                    break;

                                }

                            }
                            admin.db.getClients().get(id).clientcon.checkAvgRate(admin.db.getDrivers().get(indexDriver));

                        } else if (choice == 2) {
                            Ride ride = new Ride(admin.db.getClients().get(id));
                            admin.db.getClients().get(id).clientcon.request(ride, admin, admin.db.getClients().get(id));

                        } else if (choice == 3) {
                            admin.db.getClients().get(id).clientcon.DisplayRideInfo(admin.db.getClients().get(id));

                        } else if (choice == 4) {
                            break;
                        }
                    }
                } else if (choice == 2) {
                    User driver = new Driver();
                    int id = driver.Login(admin);
                    System.out.println("Size of drivers-->" + admin.db.getDrivers().size());

                    if (id == -1) {
                        continue;
                    }
                    System.out.println("Driver name" + admin.db.getDrivers().get(id).getUserName());

                    if (admin.db.getDrivers().get(id).isVeryfied()) {
                        if (admin.db.getDrivers().get(id).isSusbend()) {
                            break;
                        }
                        while (true) {
                            System.out.println("1.Add Favourite Area");
                            System.out.println("2.List Your Favourite Areas");
                            System.out.println("3.List Requstes From fav Area And Set Thier Offers");
                            System.out.println("4.List Users Rates");
                            System.out.println("5.Back to main manu");
                            choice = input.nextInt();
                            if (choice == 1) {
                                String fav;
                                String choicen;
                                System.out.println("Plz enter your favourite area");
                                fav = input.next();
                                admin.db.getDrivers().get(id).addFavArea(fav);

                                for (int i = 0; i < 5; i++) {

                                    System.out.println("do you want add more? y/n");
                                    choicen = input.next();
                                    if (choicen.equalsIgnoreCase("y")) {
                                        fav = input.next();
                                        admin.db.getDrivers().get(id).addFavArea(fav);
                                    } else if ((choicen.equalsIgnoreCase("N"))) {

                                        break;

                                    }

                                }

                            } else if (choice == 2) {
                                System.out.println("Driver name " + admin.db.getDrivers().get(id).getUserName());
                                System.out.println("Your Favourite Areas : " + admin.db.getDrivers().get(id).getFavArea());

                            } else if (choice == 3) {

                                admin.db.getDrivers().get(id).drivercon.makeOffer(admin.db.getDrivers().get(id));

                            } else if (choice == 4) {
                                System.out.println("Your Rates : " + admin.db.getDrivers().get(id).getRates());

                            } else if (choice == 5) {
                                break;

                            }

                        }
                    }

                } else if (choice == 3) {
                    if (admin.login(admin)) {
                        while (true) {
                            System.out.println("1.display pending drivers ");
                            System.out.println("2.verify drivers ");
                            System.out.println("3.display drivers ");
                            System.out.println("4.Susbend Driver");
                            System.out.println("5.Susbend Client");
                            System.out.println("6.display Events ");
                            System.out.println("7.add Discount areas");
                            System.out.println("8.Back to main manu ");

                            choice = input.nextInt();
                            if (choice == 1) {
                                for (int i = 0; i < admin.db.getPending_drivers().size(); i++) {
                                    System.out.println(admin.db.getPending_drivers().get(i).getUserName());

                                }
                            } else if (choice == 2) {
                                admin.admincon.verifyDrivers(admin);

                            } else if (choice == 3) {
                                for (int i = 0; i < admin.db.getDrivers().size(); i++) {
                                    System.out.println(admin.db.getDrivers().get(i).getUserName());
                                }
                            } else if (choice == 4) {
                                admin.admincon.SusbendDriver(admin);
                            } else if (choice == 5) {
                                admin.admincon.SusbendClient(admin);

                            } else if (choice == 6) {
                                admin.admincon.displayEvents();

                            } else if (choice == 7) {

                                String fav;
                                String choicen;
                                System.out.println("Plz enter your dicount areas");
                                fav = input.next();
                                admin.admincon.addDiscountAreas(fav);

                                for (int i = 0; i < 5; i++) {

                                    System.out.println("do you want add more? y/n");
                                    choicen = input.next();
                                    if (choicen.equalsIgnoreCase("y")) {
                                        fav = input.next();
                                        admin.admincon.addDiscountAreas(fav);
                                    } else if ((choicen.equalsIgnoreCase("N"))) {

                                        break;

                                    }

                                }

                            } else if (choice == 8) {
                                break;
                            }
                        }
                    } else {
                        continue;
                    }
                }

            } else if (outerchoice == 3) {

                break;
            }
        }

    }

}
