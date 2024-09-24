package Cache_Invalidators.clack.message;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.PrintWriter;
import java.io.File;
import java.util.Objects;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.String;

/**
 * This class represents messages containing the name and
 * contents of a text file.
 *
 * @author D. Tuinstra, adapted from work by Soumyabrata Dey.
 */
public class FileMessage extends Message
{

    private String filePath;
    private String fileSaveAsName;
    private String fileContents;

    /**
     * Constructs a FileMessage object with a given username
     * and file paths. It does not read in the file contents --
     * that must be done with readFile(). The fileSaveAsPath is
     * not used in its entirety: only the filename portion of
     * is kept and used when saving the message's file contents.
     *
     * @param username name of user for this message.
     * @param filePath where to find the file to read.
     * @param fileSaveAsPath the filename portion of this is used when saving the file.
     */
    public FileMessage(String username, String filePath, String fileSaveAsPath)
    {
        super(username, MSGTYPE_FILE);
        this.filePath = filePath;
        // Creates path object from fileSaveAsPath string and getFileName then turn it into a string
        Path f = Path.of(fileSaveAsPath);
        this.fileSaveAsName = f.getFileName().toString();
        // This really should be null when object is created.
        this.fileContents = null;
    }

    /**
     * Constructs a FileMessage object with a given username,
     * and a given filePath to give both the reading and saving
     * location of the file. It does not read in the file contents --
     * that must be done with readFile().
     *
     * @param username name of user for this message.
     * @param filePath where to find the file to read, and where to write it.
     */
    public FileMessage(String username, String filePath)
    {
        this(username, filePath, filePath);
    }

    /**
     * Get the path, on the local file system, of the file to read.
     *
     * @return the path to the file to read.
     */
    public String getFilePath()
    {
        return this.filePath;
    }

    /**
     * Get the path where the file is to be written.
     *
     * @return the path where the file is to be written.
     */
    public String getFileSaveAsName() {
        return this.fileSaveAsName;
    }

    /**
     * Set the path where the file-to-read is located. This does not
     * cause the file to be read -- that must be done with readFile().
     *
     * @param filePath new file name to associate with this message.
     */
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    /**
     * Set the name for the file to be written. This does not
     * cause the file to be written -- that must be done with
     * writeFile(). It is an IllegalArgument exception if the
     * fileSaveAsName contains path components.
     * @param fileSaveAsName filename
     * @throws IllegalArgumentException if fileSaveAsName contains path components
     */
    public void setFileSaveAsName(String fileSaveAsName)
    {
        Path f = Path.of(fileSaveAsName);
        // If getParent returns null then fileSaveAsName is file name, else it is a path
        if(f.getParent() != null){
            throw new IllegalArgumentException("fileSaveAsName contains path components");
        }
        this.fileSaveAsName = fileSaveAsName;
    }

    /**
     * Returns a three-element array of String. The first element is
     * the current filePath value, the second is the current
     * fileSaveAsName value, and the third is the current
     * fileContents value. The method does <b><em>not</em></b> read
     * the file named by filename -- that must be done with readFile().
     *
     * @return the current values of filePath, fileSaveAsName, and fileContents.
     */
    @Override
    public String[] getData()
    {
        //TODO: No Null handling for fileContents
        return new String[]{this.filePath, this.fileSaveAsName, this.fileContents};
    }

    /**
     * Read contents of file 'filePath' into this message's fileContents.
     *
     * @throws IOException if the file named by this.filePath does
     * not exist or cannot be opened for reading.
     */
    /* Since Java 11, there's an easy way to do this. It even handles
     * closing the files when done, whether normally or by Exception
     * (so we don't need to use try-with-resources). See
       https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
     */

    //! fileName is supposed to be this.filePath according to professor
    public void readFile() throws IOException
    {
        //readString() will throw IOException if read_file_path DNE
        Path read_file_path = Path.of(this.filePath);
        this.fileContents = Files.readString(read_file_path);
    }

    /**
     * Write this message's fileContents to the local Clack directory.
     *
     * @throws FileNotFoundException if file cannot be found or created,
     * or opened for writing.
     */
    public void writeFile() throws FileNotFoundException
    {
        int lastIndex = this.filePath.lastIndexOf("\\");
        // Extracting the substring up to that last index where "\\" was found
        // The expected path should be ""C:\\Users\\Admin\\IdeaProjects\\" from this operation
        String directoryPath = this.filePath.substring(0, lastIndex+1);

        // File will throw FileNotFoundException if the file can't be created/opened for writing
        File file = new File(directoryPath + this.fileSaveAsName);
        try (PrintWriter writer = new PrintWriter(file)){
            writer.write(this.fileContents);
        }
    }

    /**
     * Constructs a string representation of this object:
     *   "{class=FileMessage|" + super.toString() <br>
     *                  + "|filePath=" + this.getData()[0] <br>
     *                 + "|fileSaveAsName=" + this.getData()[1] <br>
     *                 + "|fileContents=" + this.getData()[2]" + }"
     *
     * @return this object's string representation.
     */
    @Override
    public String toString()
    {
        return "{class=FileMessage|"
                + super.toString()
                + "|filePath=" + this.getData()[0]
                + "|fileSaveAsName=" + this.getData()[1]
                + "|fileContents=" + this.getData()[2]
                + '}';
    }

    /**
     * Equality comparison. Returns true if the other object is of
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
        //this operation checks if both objects are the same class instance
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        //comparing Object o's values, note that Objects.equals() and Object.equals() are different
        FileMessage that = (FileMessage) o;
        return     Objects.equals(this.getData()[0], that.getData()[0])
                && Objects.equals(this.getData()[1], that.getData()[1])
                && Objects.equals(this.getData()[2], that.getData()[2]);
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