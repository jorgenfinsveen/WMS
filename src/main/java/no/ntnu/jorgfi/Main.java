package no.ntnu.jorgfi;

/** 
 * Represents the starting point of the 
 * application. The public static void
 * method is located here, which will
 * be responsible for initializing the
 * user interface.
 * 
 * @since       2022-11-03
 * @version     2022-11-04
 * @author      Candidate 10034
*/
public class Main {
    
    /**
     * Initialize the application.
     * 
     * Will call the launch() method of the static
     * class UserInterface, which is responsible
     * for the communication between the user and
     * the business model of this application.
     * 
     * The UserInterface class is static, which means
     * that there is no support for running multiple
     * instances of this application simultaneously.
     */
    public static void main(String[] args) {
        UserInterface.launch();
    }
}
