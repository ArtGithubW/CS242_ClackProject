package Cache_Invalidators.clack.message;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;

import static java.time.Duration.between;
import static org.junit.jupiter.api.Assertions.*;

class FileMessageTest {

    // Initialized in setUp().
    FileMessage msg2;
    FileMessage msg3;

    final String USERNAME = "the user";
    final String LOCAL_FILENAME = "clack_part1_local.txt";

    // For readFile and writeFile tests. Initialized in createTestFile().
    /** Path to subdir of system's temp dir. Created at run time. */
    static Path TEMP_DIR_PATH;
    /** String version of TEMP_DIR_PATH. Created at run time. */
    static String TEMP_DIR_STR;
    /** Filename and extension only, no path components. */
    static final String TEST_FILE_NAME = "clack_part1_test.txt";
    /** Full path to test file, in TEMP_DIR_PATH. Created at run time. */
    static Path TEST_FILE_PATH;
    /** String version of TEMP_FILE_PATH. Created at run time. */
    static String TEST_FILE_STR;

    /** File name for "SEND FILE ... AS ..." test. */
    static String TEST_FILE_AS_NAME = "clack_part1_test_send_as.txt";
    /** Full path to above test file, in TEMP_DIR_PATH. Created at run time. */
    static Path TEST_FILE_AS_PATH;
    /** String version of TEST_FILE_AS_PATH. Created at run time. */
    static String TEST_FILE_AS_STR;

    /** Contents stored in temp file, at run time. */
    static String FILE_CONTENTS =
            "This is a test file\nwith two lines.";

    @BeforeAll
    static void createTestFile() throws IOException {
        // Create paths and their string representations.
        TEMP_DIR_PATH = Files.createTempDirectory("clack-");
        TEMP_DIR_STR = TEMP_DIR_PATH.toString();

        TEST_FILE_PATH = Path.of(TEMP_DIR_STR, TEST_FILE_NAME);
        TEST_FILE_STR = TEST_FILE_PATH.toString();

        TEST_FILE_AS_PATH = Path.of(TEMP_DIR_STR, TEST_FILE_AS_NAME);
        TEST_FILE_AS_STR = TEST_FILE_AS_PATH.toString();

        System.out.println("Test file path          : " + TEST_FILE_STR);
        System.out.println("Test 'send file as' path: " + TEST_FILE_AS_STR);

        // Write the actual temp/test file
        try (PrintWriter out = new PrintWriter(TEST_FILE_STR)) {
            out.print(FILE_CONTENTS);
        }
    }

    @AfterAll
    static void deleteTempFile() throws IOException {
            Files.delete(TEST_FILE_PATH);
            Files.delete(TEST_FILE_AS_PATH);
            Files.delete(TEMP_DIR_PATH);
    }

    @BeforeEach
    void setUp() {
        // 2-param constructor
        msg2 = new FileMessage(USERNAME, TEST_FILE_STR);
        // 3-param constructor
        msg3 = new FileMessage(USERNAME, TEST_FILE_STR, TEST_FILE_AS_STR);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getMsgType() {
        assertEquals(Message.MSGTYPE_FILE, msg2.getMsgType());
        assertEquals(Message.MSGTYPE_FILE, msg3.getMsgType());
    }

    @Test
    void getTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = between(msg2.getTimestamp(), now);
        long timeDiff = Math.abs(duration.toSeconds());
        assertTrue(timeDiff <= 1);

        now = LocalDateTime.now();
        duration = between(msg3.getTimestamp(), now);
        timeDiff = Math.abs(duration.toSeconds());
        assertTrue(timeDiff <= 1);
    }

    @Test
    void getUsername() {
        assertEquals(USERNAME, msg2.getUsername());
        assertEquals(USERNAME, msg3.getUsername());
    }

    @Test
    void testHashCode() {
        assertNotEquals(msg2.hashCode(), msg3.hashCode());
    }

    @Test
    void testToString() {
        String str2 = "{class=FileMessage|"
                + "timestamp=" + msg2.getTimestamp()
                + "|username=" + USERNAME
                + "|filePath=" + TEST_FILE_STR
                + "|fileSaveAsName=" + TEST_FILE_NAME
                + "|fileContents=" + ""
                + "}";

        assertEquals(str2, msg2.toString());

        String str3 = "{class=FileMessage|"
                + "timestamp=" + msg3.getTimestamp()
                + "|username=" + USERNAME
                + "|filePath=" + TEST_FILE_AS_STR
                + "|fileSaveAsName=" + TEST_FILE_AS_NAME
                + "|fileContents=" + ""
                + "}";
    }

    @Test
    void getFilePath() {
        assertEquals(TEST_FILE_STR, msg2.getFilePath());
        assertEquals(TEST_FILE_STR, msg3.getFilePath());
    }

    @Test
    void setFilePath() {
        msg2.setFilePath("/etc/hostname");
        assertEquals("/etc/hostname", msg2.getFilePath());

        msg3.setFilePath("/etc/hostname");
        assertEquals("/etc/hostname", msg3.getFilePath());
    }

    @Test
    void getFileSaveAsName() {
        assertEquals(TEST_FILE_NAME, msg2.getFileSaveAsName());
        assertEquals(TEST_FILE_AS_NAME, msg3.getFileSaveAsName());
    }

    @Test
    void setFileSaveAsName() {
        msg2.setFileSaveAsName("hostname");
        assertEquals("hostname", msg2.getFileSaveAsName());

        msg3.setFileSaveAsName("hostname");
        assertEquals("hostname", msg3.getFileSaveAsName());
    }

    @Test
    void getData() {
        String[] data2 = new String[] {
                TEST_FILE_STR,
                TEST_FILE_NAME,
                ""};
        assertArrayEquals(data2, msg2.getData());

        String[] data3 = new String[] {
                TEST_FILE_STR,
                TEST_FILE_AS_NAME,
                ""};
        assertArrayEquals(data3, msg3.getData());
    }

    @Test
    void readFile() throws IOException {
        // Read temp file.
        msg2.readFile();
        assertEquals(FILE_CONTENTS, msg2.getFileContents());

        msg3.readFile();
        assertEquals(FILE_CONTENTS, msg3.getFileContents());
    }

    @Test
    void writeFile() throws IOException {
        msg2.readFile();
        msg2.writeFile();
        // Is what we just wrote the same as fileContents?
        String whatWeWrote = Files.readString(Path.of(TEST_FILE_STR));
        assertEquals(FILE_CONTENTS, whatWeWrote);

        // Read temp file, to put something into fileContents field.
        msg3.readFile();
        msg3.writeFile();
        whatWeWrote = Files.readString(Path.of(TEST_FILE_AS_STR));
        assertEquals(FILE_CONTENTS, whatWeWrote);
    }

    @Test
    void testEquals() {
        assertTrue(msg2.equals(msg2));
        assertFalse(msg2.equals(null));
        assertFalse(msg2.equals(msg3));
        assertFalse(msg2.equals(new FileMessage(USERNAME, "/some/other/file")));
        assertFalse(msg2.equals(new FileMessage("other user", TEST_FILE_STR)));

        assertTrue(msg3.equals(msg3));
        assertFalse(msg3.equals(null));
        assertFalse(msg3.equals(msg2));
        assertFalse(msg3.equals(new FileMessage("other user",
                TEST_FILE_STR, TEST_FILE_AS_STR)));
        assertFalse(msg3.equals(new FileMessage(USERNAME,
                "some/other/file", TEST_FILE_AS_STR)));
        assertFalse(msg3.equals(new FileMessage(USERNAME,
                TEST_FILE_STR, "/some/other/name")));
    }
}