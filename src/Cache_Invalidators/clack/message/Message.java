package Cache_Invalidators.clack.message;

import java.time.LocalDateTime;

/**
 * Abstract base class for Clack messages.
 *
 * @author D. Tuinstra, adapted from work by Soumyabrata Dey.
 */
public abstract class Message
{
    /**
     * Constant representing an encryption message type.
     */
    public static int MSGTYPE_ENCRYPTION = 0;

    /**
     * Constant representing a file transfer message type
     */
    public static int MSGTYPE_FILE = 10;

    /**
     * Constant representing a list users message type
     */
    public static int MSGTYPE_LISTUSERS = 20;

    /**
     * Constant representing a user logout message type
     */
    public static int MSGTYPE_LOGOUT = 30;

    /**
     * Constant representing a user text message type
     */
    public static int MSGTYPE_TEXT = 40;

    /**
     * Constant representing a help message type
     */
    public static int MSGTYPE_HELP = 50;

    private final int msgType;
    private final LocalDateTime timestamp;
    private final String username;

    /**
     * Constructs a Message object with a given username.
     *
     * <b><i>NOTE that the access level is "protected".</i></b>
     * This means that only subclasses of this class can call this
     * constructor. This is (in part) because only a concrete
     * subclass will know what argument to give for the msgType
     * parameter.
     *
     * @param username name of user creating this message.
     * @param msgType the class of message that this object is
     */
    protected Message(String username, int msgType)
    {
        this.timestamp = LocalDateTime.now();
        this.username = username;
        this.msgType = msgType;
    }

    /**
     * Get the message's msgType. This allows the possessor
     * of a Message object to know the concrete subclass the
     * object belongs to. This in turn allows the possessor
     * to cast the Message object to the appropriate subclass.
     * The constructor of each concrete Message subclass
     * should set this to the proper value for the subclass.
     *
     * @return the msgType for the object's concrete subclass.
     */
    public int getMsgType()
    {
        return this.msgType;
    }

    /**
     * Gets the message's timestamp (as a LocalDateTime).
     *
     * @return the message's timestamp (as a LocalDateTime).
     */
    public LocalDateTime getTimestamp()
    {
        return this.timestamp;
    }

    /**
     * Gets the message's username.
     *
     * @return the message's username.
     */
    public String getUsername()
    {
        return this.username;
    }

    /**
     * Return this object's data in a String array. For objects
     * with no data, return an empty array.
     * This is an abstract method; each concrete subclass must
     * override this with a subclass-appropriate implementation.
     *
     * @return object data, in a String array.
     */
    public abstract String[] getData();

    /**
     * Equality comparison. Returns true iff the other object is of
     * the same class and all fields (including those inherited from
     * superclasses) are equal.
     *
     * This is an abstract method; each concrete subclass must
     * override this with a subclass-appropriate implementation.
     *
     * @param o the object to test for equality.
     * @return whether o is of the same class as this, and all fields
     * are equal.
     */
    public abstract boolean equals(Object o);

    /**
     * Return this object's hash. In Message objects, this is simply the
     * hash of the string returned by this.toString().
     *
     * This is an abstract method; each concrete subclass must
     * override this with a subclass-appropriate implementation.

     * @return hash of this object.
     */
    public int hashCode() {
        return this.toString().hashCode();
    }

    /**
     * Return a string representation of this object (note no spaces):
     * <br>
     *   <code>"timestamp=<i>the_timestamp</i>|username=<i>the_username</i>"</code>
     * <br>
     * No class name or enclosing brackets are included in the returned
     * string, to allow subclasses to use this method in their
     * toString() implementations.
     * <br><br>
     * @return String showing fields and field contents
     */
    public String toString()
    {
        return "timestamp=" + this.timestamp + "|username=" + this.username;
    }
}