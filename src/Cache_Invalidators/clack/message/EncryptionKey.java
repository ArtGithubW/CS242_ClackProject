package Cache_Invalidators.clack.message;

import java.util.Objects;

/**
 * A Message for setting an encryption key
 * This was included in the original spec, but not listed in part 1, added just in case
 */
public class EncryptionKey extends Message
{
    private String key;

    /**
     * Constructs a encryption key message using a username and key
     * @param username the username of the current user
     */
    public EncryptionKey (String username, String key) {
        super (username,MSGTYPE_ENCRYPTION);
        this.key = key;
    }

    /**
     * Return a string array containing the encryption key
     * @return the data of the message
     */
    public String[] getData() {
        return new String[] {key};
    }

    /**
     * Return this message as a string in the form
     * {class=EncryptionKey|
     * timestamp=2024-10-01T08:48:56.887505|
     * username=uname|
     * key=arandomkeystring
     * }
     * @return the string representation of the message
     */
    public String toString() {
        return "{class=EncryptionKey|" +
                super.toString() +
                "|key=" + key +
                "}";
    }

    /**
     * Check if this EncryptionKey has the same content as another EncryptionKey
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

        EncryptionKey that = (EncryptionKey)o;
        return Objects.equals(that.key, this.key)
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

