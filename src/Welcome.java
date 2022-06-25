import org.json.JSONObject;


import java.util.Scanner;
public class Welcome {
    Scanner scanner = new Scanner(System.in);

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
