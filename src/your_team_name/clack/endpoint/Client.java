package your_team_name.clack.endpoint;

import your_team_name.clack.message.Message;

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
    private final String saveDirectory;
    private Message messageToSend;
    private Message messageReceived;

    //TODO: JavaDoc
    public Client(String username, String serverName, int serverPort) {
        this.username = username;
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    //TODO: JavaDoc
    public Client(String username, String serverName) {
        this(username, serverName, DEFAULT_SERVER_PORT);
    }

    //TODO: JavaDoc
    public Client(String username, int serverPort) {
        this(username, DEFAULT_SERVER_NAME, serverPort);
    }

    //TODO: JavaDoc
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
        //TODO: Implement this.
    }

    /**
     * Parse the line of user input and create the appropriate
     * message.
     * @return an object of the appropriate Message subclass.
     */
    public Message readUserInput() {
        //TODO: implement this.
        return null;
    }

    /**
     * Print the current messageReceived object to System.out.
     * What is printed is the result of calling toString()
     * on the messageReceived object.
     */
    public void printMessage() {
        //TODO: implement this.
    }

    //TODO: JavaDoc
    public String getUsername() {
        //TODO: implement this (return something other than null)
        return null;
    }

    //TODO: JavaDoc
    public String getServerName() {
        //TODO: implement this (return something other than null)
        return null;
    }

    //TODO: JavaDoc
    public String toString() {
        return "{class=Client|"
                + "|DEFAULT_SERVER_NAME=" + DEFAULT_SERVER_NAME
                + "|DEFAULT_SERVER_PORT=" + DEFAULT_SERVER_PORT
                + "|username=" + this.username
                + "|serverName=" + this.serverName
                + "|serverPort=" + this.serverPort
                + "|messageToSend={" + this.messageToSent.toString() + "}"
                + "|messageReceived={" + this.messageReceived.toString() + "}"
                + "}";
    }
}
