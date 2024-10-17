package Cache_Invalidators.clack.cipher;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CaesarCipherTest {
    private static CaesarCipher TestCipherDefault;
    private static CaesarCipher TestCipherCustom;

    // Setting up a default alphabet test and custom alphabet test
    @BeforeEach
    void setUp()
    {
        TestCipherDefault = new CaesarCipher(4);
        TestCipherCustom = new CaesarCipher(4,"ZXCVBNMASDFGJKLPOIUYTREWQ"); // Custom 25 Char alphabet that DOES NOT have 'H'

    }
    // Testing for encrypt function for both constructors
    @Test
    void encryptTest() {

        String actualEncrypted  = TestCipherDefault.encrypt("HELLO World");
        String actualEncryptedCustom  = TestCipherCustom.encrypt("Hello wOrlD");
        String ExpectedEncrypted = "Lipps Asvph".toUpperCase();
        String ExpectedEncryptedCustom = "HXUUT CTZUK".toUpperCase();
        assertEquals(ExpectedEncrypted, actualEncrypted);
        assertEquals(ExpectedEncryptedCustom, actualEncryptedCustom);
    }

    // Testing for decrypt function for both constructors
    @Test
    void decryptTest() {
        String actualDecrypt  = TestCipherDefault.decrypt("Lipps Asvph");
        String actualDecryptCustom  = TestCipherCustom.decrypt("QXJQRDP G KXMXDQ UTDP JQZRDP CRQH HXUUT CTZUK");
        String ExpectedDecrypt = "Hello world".toUpperCase();
        String ExpectedDecryptCustom = "Testing a decent long string with HELLO WORLD".toUpperCase();
        assertEquals(ExpectedDecrypt, actualDecrypt);
        assertEquals(ExpectedDecryptCustom, actualDecryptCustom);
    }

    // Testing getAlphabet function for both constructors
    @Test
    void getAlphabetTestDefault() {
        String actualAlphabet = TestCipherDefault.getAlphabet();
        String actualAlphabetCustom = TestCipherCustom.getAlphabet();
        String expectedAlphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String expectedAlphabetCustom= "ZXCVBNMASDFGJKLPOIUYTREWQ";
        assertEquals(expectedAlphabet, actualAlphabet);
        assertEquals(expectedAlphabetCustom, actualAlphabetCustom);
    }

}