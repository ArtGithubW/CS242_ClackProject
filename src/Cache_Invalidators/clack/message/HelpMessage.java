package Cache_Invalidators.clack.message;

import java.util.Objects;

/**
 * A Message for showing help text to the user
 * This was not included in the original spec, but it was in the JUnit tests
 */
public class HelpMessage extends Message
{
    private String help;
    public static String HELP = """
            <required> [optional]
            KEYWORD parameter
            
            LOGOUT - Disconnect from the server and close the client
            LIST USERS - List all users connected to the server
            SEND FILE <file1> [AS file2] - Send a file to all other users
            ENCRYPTION KEY <key> - Set encryption parameters
            ENCRYPTION <ON|OFF> - Enable or disable encryption
            HELP - Display this help message
            """;

    /**
     * Constructs a help message using the default text
     * @param username the username of the current user
     */
    public HelpMessage (String username) {
        super (username,MSGTYPE_HELP);
        this.help = HELP;
    }

    /**
     * Constructs a help message using the default text plus some extra text
     * @param username the username of the current user
     * @param extendedHelp additional help text to display before the main text
     */
    public HelpMessage(String username, String extendedHelp)
    {
        super (username,MSGTYPE_HELP);
        this.help = extendedHelp + "\n" + HELP;
    }

    /**
     * Return a string array containing the help text as it's first element
     * @return the data of the message
     */
    public String[] getData() {
        return new String[] {help};
    }

    /**
     * Return this message as a string in the form
     * {class=HelpMessage|
     * timestamp=2024-10-01T08:48:56.887505|
     * username=uname|
     * help=helptext
     * }
     * @return the string representation of the message
     */
    public String toString() {
        return "{class=HelpMessage|" +
                super.toString() +
                "|help=" + help +
                "}";
    }

    /**
     * Check if this HelpMessage has the same content as another HelpMessage
     * @param o the object to test for equality.
     * @return a boolean value representing the equality between this object and another
     */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        //this operation checks if both objects are the same class instance
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        HelpMessage that = (HelpMessage)o;
        return Objects.equals(that.help, this.help)
                && Objects.equals(that.getUsername(), this.getUsername())
                && Objects.equals(that.getTimestamp(), this.getTimestamp());
    }

    /**
     * Return this object's hash. In Message objects, this is simply the
     * hash of the string returned by this.toString().
     * @return hashCode of the object
     */
    @Override
    public int hashCode()
    {
        return this.toString().hashCode();
    }
}
