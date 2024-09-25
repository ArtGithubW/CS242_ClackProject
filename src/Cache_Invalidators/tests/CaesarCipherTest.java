import Cache_Invalidators.clack.Cipher.CaesarCipher;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CaesarCipherTest {
    @org.junit.jupiter.api.Test
    void encryptTestDefault() {
        CaesarCipher TestCipher = new CaesarCipher(4);
        String actualEncrypted  = TestCipher.encrypt("HELLO World");
        String expectedEncrypted = "Lipps Asvph".toUpperCase();
        assertEquals(expectedEncrypted, actualEncrypted);
    }
    @org.junit.jupiter.api.Test
    void encryptTestCustom() {
        CaesarCipher TestCipher = new CaesarCipher(4,"ZXCVBNMASDFGHJKLPOIUYTREWQ");
        String actualEncrypted  = TestCipher.encrypt("HELLO World");
        String expectedEncrypted = "PXUUT CTZUJ".toUpperCase();
        assertEquals(expectedEncrypted, actualEncrypted);
    }
    @org.junit.jupiter.api.Test
    void decryptTestDefault() {
        CaesarCipher TestCipher = new CaesarCipher(4);
        String actualEncrypted  = TestCipher.decrypt("LIPPS ASVPH");
        String expectedEncrypted = "HELLO World".toUpperCase();
        assertEquals(expectedEncrypted, actualEncrypted);
    }
    @org.junit.jupiter.api.Test
    void decryptTestCustom() {
        CaesarCipher TestCipher = new CaesarCipher(4,"ZXCVBNMASDFGHJKLPOIUYTREWQ");
        String actualEncrypted  = TestCipher.decrypt("PXUUT CTZUJ");
        String expectedEncrypted = "HELLO World".toUpperCase();
        assertEquals(expectedEncrypted, actualEncrypted);
    }
    @org.junit.jupiter.api.Test
    void getAlphabetTestDefault() {
        CaesarCipher TestCipher = new CaesarCipher(4);
        String actualAlphabet = TestCipher.getAlphabet();
        String expectedAlphabet= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        assertEquals(expectedAlphabet, actualAlphabet);
    }
    @org.junit.jupiter.api.Test
    void getAlphabetTestCustom() {
        CaesarCipher TestCipher = new CaesarCipher(4,"ZXCVBNMASDFGHJKLPOIUYTREWQ");
        String actualAlphabet = TestCipher.getAlphabet();
        String expectedAlphabet= "ZXCVBNMASDFGHJKLPOIUYTREWQ";
        assertEquals(expectedAlphabet, actualAlphabet);
    }
}