import java.io.IOException;

/**
 * Main class that simulates an ATM system by extending the OptionMenu class.
 */
public class ATM extends OptionMenu {

    public static void main(String[] args) throws IOException {
        // Create an instance of OptionMenu to begin ATM operations
        OptionMenu optionMenu = new OptionMenu();

        // Call the getLogin method to start the login process
        optionMenu.getLogin();
    }
}


