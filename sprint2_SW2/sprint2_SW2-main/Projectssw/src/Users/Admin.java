package Users;

import Controller.AdminControler;
import Controller.Event;
import DB.DB;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Access
 */
public class Admin {

    Scanner input = new Scanner(System.in);
    boolean suspended = true;
    public AdminControler admincon = new AdminControler();
    private static Admin adminObj = null;
    public DB db = new DB();
    private ArrayList<Event> Events = new ArrayList<Event>();

    //Admin info
    private String userName, password;

    //applay sigleton pattern
    private Admin() {

    }

    public static Admin getAdminObj() {
        if (adminObj == null) {
            adminObj = new Admin();
        }
        return adminObj;
    }

    public void SuspendDriver(Driver driver, Boolean b) {
        driver.setSusbend(b);
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

    public void Register(Admin admin) {
        System.out.println("please enter Name: ");
        admin.setUserName(input.next());
        System.out.println("please enter password : ");
        admin.setPassword(input.next());

    }

    public boolean login(Admin admin) {
        System.out.println("please enter Name: ");
        admin.setUserName(input.next());
        System.out.println("please enter password : ");
        admin.setPassword(input.next());

        if (admin.getUserName().equals(this.getUserName()) && admin.getPassword().equals(this.getPassword())) {
            System.out.println("loged in sucessfuly ");
            return true;
        } else {
            System.out.println("oops! you username or password is wrong");
            return false;
        }
    }

    public ArrayList<Event> getEvents() {
        return Events;
    }

    public void setEvents(ArrayList<Event> Events) {
        this.Events = Events;
    }

    public void addEvent(Event event) {
        Events.add(event);
    }

}
