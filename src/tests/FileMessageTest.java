import The_Snails_Conglomerate.clack.message.FileMessage;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
class FileMessageTest {

    @org.junit.jupiter.api.Test
    void getFileSaveAsName() {
        FileMessage fileMessage = new FileMessage("Art","C:\\Users\\Admin\\IdeaProjects\\CS242_SnailsConglomerate\\clack-part1\\tests\\name.txt","C:\\Users\\Admin\\IdeaProjects\\CS242_SnailsConglomerate\\clack-part1\\tests\\output.txt");
        String actualFileName = fileMessage.getFileSaveAsName();
        String expectedFileName = "output.txt";
        assertEquals(expectedFileName, actualFileName);
    }

    @org.junit.jupiter.api.Test
    void getData() {
        FileMessage fileMessage = new FileMessage("Art","C:\\Users\\Admin\\IdeaProjects\\CS242_SnailsConglomerate\\clack-part1\\tests\\name.txt","C:\\Users\\Admin\\IdeaProjects\\CS242_SnailsConglomerate\\clack-part1\\tests\\output.txt");
        String[] actualData = new String[]{fileMessage.getData()[0],fileMessage.getData()[1],fileMessage.getData()[2],};
        String[] expectedData = new String[]{"C:\\Users\\Admin\\IdeaProjects\\CS242_SnailsConglomerate\\clack-part1\\tests\\name.txt","output.txt",null};
        assertEquals(expectedData[0], actualData[0]);
        assertEquals(expectedData[1], actualData[1]);
        assertEquals(expectedData[2], actualData[2]);
    }

    @org.junit.jupiter.api.Test
    void readFile() throws IOException {
        FileMessage fileMessage = new FileMessage("Art","C:\\Users\\Admin\\IdeaProjects\\CS242_SnailsConglomerate\\clack-part1\\tests\\name.txt","C:\\Users\\Admin\\IdeaProjects\\CS242_SnailsConglomerate\\clack-part1\\tests\\output.txt");
        fileMessage.readFile();
        String actualFileContents = fileMessage.getData()[2];
        String expectedFileContents = "lol";
        assertEquals(actualFileContents, expectedFileContents);
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        FileMessage fileMessage = new FileMessage("Art","C:\\Users\\Admin\\IdeaProjects\\CS242_SnailsConglomerate\\clack-part1\\tests\\name.txt","C:\\Users\\Admin\\IdeaProjects\\CS242_SnailsConglomerate\\clack-part1\\tests\\output.txt");
        String expectedString = "{class=FileMessage|timestamp=2024-09-24|username=Art|filePath=C:\\Users\\Admin\\IdeaProjects\\CS242_SnailsConglomerate\\clack-part1\\tests\\name.txt|fileSaveAsName=output.txt|fileContents=null}";
        assertEquals(expectedString, fileMessage.toString());
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        FileMessage fileMessage = new FileMessage("Art","C:\\Users\\Admin\\IdeaProjects\\CS242_SnailsConglomerate\\clack-part1\\tests\\name.txt","C:\\Users\\Admin\\IdeaProjects\\CS242_SnailsConglomerate\\clack-part1\\tests\\output.txt");
        FileMessage fileMessage2 = new FileMessage("Art","C:\\Users\\Admin\\IdeaProjects\\CS242_SnailsConglomerate\\clack-part1\\tests\\name.txt","C:\\Users\\Admin\\IdeaProjects\\CS242_SnailsConglomerate\\clack-part1\\tests\\output.txt");
        assertEquals(true,fileMessage.equals(fileMessage2));
    }

}