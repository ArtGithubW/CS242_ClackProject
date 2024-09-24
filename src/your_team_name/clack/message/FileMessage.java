package your_team_name.clack.message;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

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
        //TODO: parse the filename portion of fileSaveAsPath, and assign
        // it to this.fileSaveAsName. NOTE: There are Java library routines
        // for manipulating file paths and names. Don't re-invent!
        this.fileSaveAsName = null;
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
        return filePath;
    }

    /**
     * Get the path where the file is to be written.
     *
     * @return the path where the file is to be written.
     */
    public String getFileSaveAsName() {
        //TODO: Implement this. Return something other than null.
        return null;
    }

    /**
     * Set the path where the file-to-read is located. This does not
     * cause the file to be read -- that must be done with readFile().
     *
     * @param filePath new file name to associate with this message.
     */
    public void setFilePath(String filePath)
    {
        //TODO: Implement this.
    }

    /**
     * Set the name for the file to be written. This does not
     * cause the file to be written -- that must be done with
     * writeFile(). It is an IllegalArgument exception if the
     * fileSaveAsName contains path components.
     *
     * @throws IllegalArgumentException if fileSaveAsName contains path components
     */
    public void setFileSaveAsName(String fileSaveAsName)
    {
        //TODO: implement this. Remember that Java has libraries
        //  for manipulating file paths and names.
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
        //TODO: Implement this. Return an array as described in the
        //  JavaDoc, not null.
        return null;
    }

    /**
     * Read contents of file 'fileName' into this message's fileContents.
     *
     * @throws IOException if the file named by this.filename does
     * not exist or cannot be opened for reading.
     */
    /* Since Java 11, there's an easy way to do this. It even handles
     * closing the files when done, whether normally or by Exception
     * (so we don't need to use try-with-resources). See
       https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
     */
    public void readFile() throws IOException
    {
        //TODO: Implement this.
    }

    /**
     * Write this message's fileContents to the local Clack directory.
     *
     * @throws FileNotFoundException if file cannot be found or created,
     * or opened for writing.
     */
    public void writeFile() throws FileNotFoundException
    {
        // TODO: Implement this. Use try-with-resources to ensure
        //   output file is closed.
        // HOLD OFF FOR NOW ON IMPLEMENTING THIS. There is a design
        //   issue that we'll discuss in class.
    }

    @Override
    public String toString()
    {
        //TODO: Implement this. Should be similar to TextMessage class,
        //  but include filePath, fileSaveAsName, and fileContents.
        return null;
    }

    @Override
    public boolean equals(Object o)
    {
        //TODO: Implement this.
    }

    @Override
    public int hashCode()
    {
        //TODO: Implement this.
    }

}