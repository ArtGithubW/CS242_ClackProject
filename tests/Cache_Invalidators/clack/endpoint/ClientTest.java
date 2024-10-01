//TODO: FIX TIMESTAMP BUGS! TESTS WILL FAIL EVEN IF NOTHING IS WRONG

package Cache_Invalidators.clack.endpoint;

import Cache_Invalidators.clack.message.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
class ClientTest {
    private static Client TestClient;
    private static final ByteArrayOutputStream BytesOut = new ByteArrayOutputStream();
    private static final LocalDateTime Timestamp = null;

    @BeforeEach
    void setUp()
    {
        TestClient = new Client("demo", "clack.example.com", 42);

        // Simulated user input code from stackoverflow
        // https://stackoverflow.com/questions/2169330/java-junit-capture-the-standard-input-output-for-use-in-a-unit-test
        System.setOut(new PrintStream(BytesOut));
    }

    /**
     * Test the REPL by starting the client then logging out
     * This also tests readUserInput and printMessage
     */
    @Test
    void startTest()
    {
        // Simulated user input/output code from stackoverflow
        // https://stackoverflow.com/questions/2169330/java-junit-capture-the-standard-input-output-for-use-in-a-unit-test
        final String userString = "LOGOUT\n"; // simulated user input
        byte[] userBytes = userString.getBytes();
        final ByteArrayInputStream testIn = new ByteArrayInputStream(userBytes);
        System.setIn(testIn);

        TestClient.start();

        final String ExpectedOutput = "clack> {class=LogoutMessage|timestamp="+Timestamp+"|username=demo}\n";
        final String ActualOutput = BytesOut.toString();
        assertEquals(ActualOutput.replace("\r\n", "\n"), ExpectedOutput.replace("\r\n", "\n"));
    }

    /**
     * Tests most of the commands, as well as a regular message
     * @throws IOException if the required test file does not exist
     */
    @Test
    void readUserInputTest() throws IOException
    {
        final String[] inputs = {
                "LIST USERS\n",                                                                     // List users test
                "SEND FILE ./tests/Cache_Invalidators/clack/endpoint/send.txt\n",                   // Send a file
                "SEND FILE ./tests/Cache_Invalidators/clack/endpoint/send.txt AS rename.txt \n",    // Send a file and rename
                "hello\n",                                                                          // Send a regular message
                "LOGOUT\n"                                                                          // Test logout
        };

        FileMessage fileMessage1 = new FileMessage("demo", "./tests/Cache_Invalidators/clack/endpoint/send.txt");
        fileMessage1.readFile();

        FileMessage fileMessage2 = new FileMessage("demo", "./tests/Cache_Invalidators/clack/endpoint/send.txt", "rename.txt");
        fileMessage2.readFile();

        final Message[] results = {
                new ListUsersMessage("demo"),
                fileMessage1,
                fileMessage2,
                new TextMessage("demo", "hello"),
                new LogoutMessage("demo")
        };

        Message result;
        for (int i = 0; i < inputs.length; i++) {
            System.setIn(new ByteArrayInputStream(inputs[i].getBytes()));
            result = TestClient.readUserInput();
            assertEquals(results[i].toString().replace("\r\n", "\n"), result.toString().replace("\r\n", "\n"));
        }
    }

    /**
     * Verify the username getter
     */
    @Test
    void getUsernameTest()
    {
        assertEquals("demo", TestClient.getUsername());
    }

    /**
     * Verify the server name getter
     */
    @Test
    void getServerNameTest()
    {
        assertEquals("clack.example.com", TestClient.getServerName());
    }

    /**
     * Test the toString() method
     * This relies heavily on start() as this is basically the startTest() with extra steps
     * @precondition startTest() passes
     */
    @Test
    void testToStringTest()
    {
        final String userString = "LOGOUT\n"; // simulated user input
        byte[] userBytes = userString.getBytes();
        final ByteArrayInputStream testIn = new ByteArrayInputStream(userBytes);
        System.setIn(testIn);

        TestClient.start();

        final String ActualOutput = TestClient.toString();
        // Apologies for bad string literal, where were you when sanity was kill?
        final String ExpectedOutput = "{class=Client|username=demo|serverName=clack.example.com|serverPort=42|messageToSend={{class=LogoutMessage|timestamp="+Timestamp+"|username=demo}}|messageReceived={{class=LogoutMessage|timestamp="+Timestamp+"|username=demo}}}";

        assertEquals(ActualOutput, ExpectedOutput);
    }
}