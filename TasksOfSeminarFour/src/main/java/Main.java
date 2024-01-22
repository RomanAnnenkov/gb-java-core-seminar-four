import exceptions.WrongLoginException;
import exceptions.WrongPasswordException;

public class Main {
    public static void main(String[] args) {
        System.out.println("seminar four");

        try {
            System.out.println(authorize("login","password","passwordf"));
        } catch (WrongLoginException | WrongPasswordException e) {
            System.out.println(e.getMessage());
        }


    }

    public static boolean authorize(String login, String password, String confirmPassword) throws WrongLoginException, WrongPasswordException {
        if (login.length() > 20) {
            throw new WrongLoginException();
        }
        if (password.length() > 20) {
            throw new WrongPasswordException("password length is more than 20");
        }
        if (!password.equals(confirmPassword)) {
            throw new WrongPasswordException("Password does not match confirmPassword");
        }

        return false;
    }
}
