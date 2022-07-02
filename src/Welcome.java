import org.json.JSONObject;


import java.util.Scanner;

/**
 * the first thing shown in the program when we first run it.
 * logIn or signUp
 */
public class Welcome {
    Scanner scanner = new Scanner(System.in);

    /**
     * if the user select is 1: login case
     * if the user select is 2: signUp case
     * @return a user to the server.
     */
    public JSONObject getInformation() {
        int selected = scanner.nextInt();
        scanner.nextLine();
        System.out.print("UserName: ");
        String userName = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if(selected == 1) {
            JSONObject user = new JSONObject();
            user.put("method", "logIn");
            user.put("userName", userName);
            user.put("password", password);
            return user;
        }
        else if(selected == 2) {
            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("PhoneNumber");
            String phoneNumber = scanner.nextLine();

            JSONObject user = new JSONObject();
            user.put("method", "signUp");
            user.put("userName", userName);
            user.put("password", password);
            user.put("email", email);
            user.put("phoneNumber", phoneNumber);
            return user;
        }
        return null;
    }
    public void menu() {
        System.out.println("1. Login");
        System.out.println("2. SignUp");
    }
}
