package your_team_name.clack.message;

import java.util.Objects;

/*
    This class is fully implemented, to help you get started.
*/

/**
 * This class represents a command to the server, asking for
 * a list of all active users of the server.
 *
 * @author D. Tuinstra, adapted from work by Soumyabrata Dey.
 */
public class ListUsersMessage extends Message
{
    /**
     * Constructs a ListUsersMessage with a given username
     * and the msgType set to MSGTYPE_LISTUSERS
     *
     * @param username the user sending this message.
     */
    public ListUsersMessage(String username) {
        super(username, MSGTYPE_LISTUSERS);
    }

    /**
     * Return this objects data in a String array. For objects
     * with no data, return an empty array.
     *
     * @return object data, in a String array.
     */
    @Override
    public String[] getData()
    {
        return new String[0];
    }

    /**
     * Equality comparison. Returns true iff the other object is of
     * the same class and all fields (including those inherited from
     * superclasses) are equal.
     *
     * @param o the object to test for equality.
     * @return whether o is of the same class as this, and all fields
     * are equal.
     */
    @Override
    public boolean equals(Object o)
    {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        ListUsersMessage that = (ListUsersMessage) o;
        return Objects.equals(this.getTimestamp(), that.getTimestamp())
                && Objects.equals(this.getUsername(), that.getUsername());
    }

    /**
     * Return this object's hash. In Message objects, this is simply the
     * hash of the string returned by this.toString().
     *
     * @return hash of this object.
     */
    @Override
    public int hashCode()
    {
        return this.toString().hashCode();
    }

    /**
     * Constructs a string representation of this object:
     *   "{class=ListUsersMessage|" + super.toString() + "}"
     *
     * @return this object's string representation.
     */
    @Override
    public String toString()
    {
        return "{class=ListUsersMessage|" + super.toString() + "}";
    }
}
