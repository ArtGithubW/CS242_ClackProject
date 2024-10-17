package Cache_Invalidators.clack.message;

import java.util.Objects;

/**
 * A Message for enabling or disabling encryption
 * This was included in the original spec, but not listed in part 1, added just in case
 */
public class EncryptionOption extends Message
{
    private boolean enable;

    /**
     * Constructs a encryption option message using a username and option
     * @param username the username of the current user
     * @param enable should encryption be enabled or disabled
     */
    public EncryptionOption(String username, boolean enable) {
        super (username,MSGTYPE_ENCRYPTION);
        this.enable = enable;
    }

    /**
     * Return a string array containing the encryption state
     * @return the data of the message
     */
    public String[] getData() {
        return new String[] {String.valueOf(enable)};
    }

    /**
     * Return this message as a string in the form
     * {class=EncryptionOption|
     * timestamp=2024-10-01T08:48:56.887505|
     * username=uname|
     * enable=True
     * }
     * @return the string representation of the message
     */
    public String toString() {
        return "{class=EncryptionOption|" +
                super.toString() +
                "|enable=" + enable +
                "}";
    }

    /**
     * Check if this EncryptionOption has the same content as another EncryptionOption
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

        EncryptionOption that = (EncryptionOption)o;
        return Objects.equals(that.enable, this.enable)
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

