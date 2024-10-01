package Cache_Invalidators.clack.message;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
class FileMessageTest {

    @org.junit.jupiter.api.Test
    void getFileSaveAsName() {
        FileMessage fileMessage = new FileMessage("Art","./tests/Cache_Invalidators/clack/message/name.txt","./tests/Cache_Invalidators/clack/message/output.txt");
        String actualFileName = fileMessage.getFileSaveAsName();
        String expectedFileName = "output.txt";
        assertEquals(expectedFileName, actualFileName);
    }

    @org.junit.jupiter.api.Test
    void getData() {
        FileMessage fileMessage = new FileMessage("Art","./tests/Cache_Invalidators/clack/message/name.txt","./tests/Cache_Invalidators/clack/message/output.txt");
        String[] actualData = new String[]{fileMessage.getData()[0],fileMessage.getData()[1],fileMessage.getData()[2],};
        String[] expectedData = new String[]{"./tests/Cache_Invalidators/clack/message/name.txt","output.txt",null};
        assertEquals(expectedData[0], actualData[0]);
        assertEquals(expectedData[1], actualData[1]);
        assertEquals(expectedData[2], actualData[2]);
    }

    @org.junit.jupiter.api.Test
    void readFile() throws IOException {
        FileMessage fileMessage = new FileMessage("Art","./tests/Cache_Invalidators/clack/message/name.txt","./tests/Cache_Invalidators/clack/message/output.txt");
        fileMessage.readFile();
        String actualFileContents = fileMessage.getData()[2];
        String expectedFileContents = "lol";
        assertEquals(actualFileContents, expectedFileContents);
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        FileMessage fileMessage = new FileMessage("Art","./tests/Cache_Invalidators/clack/message/name.txt","./tests/Cache_Invalidators/clack/message/output.txt");
        LocalDateTime Timestamp = fileMessage.getTimestamp();
        String expectedString = "{class=FileMessage|timestamp="+Timestamp+"|username=Art|filePath=./tests/Cache_Invalidators/clack/message/name.txt|fileSaveAsName=output.txt|fileContents=null}";
        assertEquals(expectedString, fileMessage.toString());
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        FileMessage fileMessage = new FileMessage("Art","./tests/Cache_Invalidators/clack/message/name.txt","./tests/Cache_Invalidators/clack/message/output.txt");
        FileMessage fileMessage2 = new FileMessage("Art","./tests/Cache_Invalidators/clack/message/name.txt","./tests/Cache_Invalidators/clack/message/output.txt");
        assertTrue(fileMessage.equals(fileMessage2));
    }

}