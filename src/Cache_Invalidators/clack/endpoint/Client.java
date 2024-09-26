package Cache_Invalidators.clack.endpoint;

import Cache_Invalidators.clack.message.*;

import java.util.Scanner;

public class Client
{
    /**
     * Default port for connecting to server. This should be
     * a port listed as "unassigned" in
     * <a href="https://www.iana.org/assignments/service-names-port-numbers/service-names-port-numbers.txt">IANA</a>.
     */
    public static final int DEFAULT_SERVER_PORT = 0 ; //TODO: choose an unassigned port (NOT 0!!)

    /**
     *  The server to connect to if one is not explicitly given.
     */
    public static final String DEFAULT_SERVER_NAME = "localhost";

    private final String username;
    private final String serverName;
    private final int serverPort;
    //private final String saveDirectory;
    private Message messageToSend;
    private Message messageReceived;

    /**
     * Create a client instance using a username, server name, and server port
     * @param username a unique string representing a user
     * @param serverName a server address to exchange messages with
     * @param serverPort the port over which to exchange messages with the server
     */
    public Client(String username, String serverName, int serverPort) {
        this.username = username;
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    /**
     * Create a client instance using a username and server name, assuming default port
     * @param username a unique string representing a user
     * @param serverName a server address to exchange messages with
     */
    public Client(String username, String serverName) {
        this(username, serverName, DEFAULT_SERVER_PORT);
    }

    /**
     * Create a client instance using a username and port, assuming default server name
     * @param username a unique string representing a user
     * @param serverPort a server address to exchange messages with
     */
    public Client(String username, int serverPort) {
        this(username, DEFAULT_SERVER_NAME, serverPort);
    }

    /**
     * Create a client instance, using a username, assuming a server name and port
     * @param username a unique string representing a user
     */
    public Client(String username) {
        this(username, DEFAULT_SERVER_NAME, DEFAULT_SERVER_PORT);
    }

    /**
     * The client's REPL loop. Prompt for input, build
     * message from it, send message and receive/process
     * the reply, print info for user; repeat until
     * user enters "LOGOUT".
     */
    public void start() {
        do
        {
            // Print prompt
            System.out.print("clack> ");

            // Generate the current outgoing message
            this.messageToSend = readUserInput();

            // "Send" the message (for now this just sends outgoing back to incoming)
            this.messageReceived = this.messageToSend;

            // Print the incoming message
            printMessage();

            // If file message, save the file message
            if (this.messageReceived.getMsgType() == Message.MSGTYPE_FILE) {
                try {
                    ((FileMessage) this.messageReceived).writeFile();
                }
                catch (Exception e) {
                    System.out.println("File error " + e.getMessage());
                }
            }
        } while (this.messageToSend.getMsgType() != Message.MSGTYPE_LOGOUT); // While not logout message

    }

    /**
     * Parse the line of user input and create the appropriate
     * message.
     * @return an object of the appropriate Message subclass.
     */
    public Message readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine(); // Get a line of input

        // Switching logic - either run a command or just send a message
        if (in.toUpperCase().startsWith("LOGOUT"))
        {
            // send a LogoutMessage
            return new LogoutMessage(this.username);
        }
        else if (in.toUpperCase().startsWith("LIST USERS"))
        {
            // send a ListUsersMessage
            return new ListUsersMessage(this.username);
        }
        else if (in.toUpperCase().startsWith("SEND FILE"))
        {
            FileMessage file;
            // remove extra spaces and split into args. praise be to stackoverflow for the regex
            String[] args = in.trim().replaceAll(" +"," ").split(" ");
            // SEND FILE file1(arg2)
            if (args.length == 3)
            {
                 file = new FileMessage(this.username,args[2]);
            }
            // SEND FILE file1(arg2) AS(arg3) file2(arg4)
            else if (args.length == 5 && args[3].equalsIgnoreCase("AS"))
            {
                file = new FileMessage(this.username,args[2],args[4]);
            }
            else {
                System.out.println("SEND FILE <file1> [AS file2] - Send a file to all other users");
                return readUserInput();
            }

            try {
                file.readFile();
                return file;
            }
            catch (Exception e) {
                System.out.println("File error " + e.getMessage());
                return readUserInput();
            }
        }
        else if (in.toUpperCase().startsWith("ENCRYPTION"))
        {
            //TODO: Implement encryption
            System.out.println("Encryption not implemented yet D:");
            return readUserInput();
        }
        else if (in.toUpperCase().startsWith("HELP"))
        {
            // Show a help message
            System.out.println("<required> [optional]\nKEYWORD parameter\n");
            System.out.println("LOGOUT - Disconnect from the server and close the client");
            System.out.println("LIST USERS - List all users connected to the server");
            System.out.println("SEND FILE <file1> [AS file2] - Send a file to all other users");
            System.out.println("ENCRYPTION KEY <key> - Set encryption parameters");
            System.out.println("ENCRYPTION <ON|OFF> - Enable or disable encryption");
            System.out.println("HELP - Display this help message");

            // call again because we have to return a message
            return readUserInput();
        }
        else if (in.isEmpty())
        {
            // call again because we have to return a message
            return readUserInput();
        }
        else {
            return new TextMessage(this.username, in);
        }
    }

    /**
     * Print the current messageReceived object to System.out.
     * What is printed is the result of calling toString()
     * on the messageReceived object.
     */
    public void printMessage() {
        System.out.println(this.messageReceived);
    }

    /**
     * Return the username of the current user
     * @return a unique string representing the user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Return the currently connected server
     * @return the name of the currently connected server
     */
    public String getServerName() {
        return this.serverName;
    }

    /**
     * Return the client object as a string
     * @return a string containing username, current server name and port, incoming and outgoing message
     */
    public String toString() {
        return "{class=Client|"
                + "|username=" + this.username
                + "|serverName=" + this.serverName
                + "|serverPort=" + this.serverPort
                + "|messageToSend={" + this.messageToSend.toString() + "}"
                + "|messageReceived={" + this.messageReceived.toString() + "}"
                + "}";
    }
}
