package Cache_Invalidators.clack.cipher;
import java.util.regex.Pattern;

/**
 * Implements a caesar cipher as a form of encryption for clack messages
 */
public class CaesarCipher {
    /**
     * The default alphabet to use for the cipher if none is provided
     */
    public static final String DEFAULT_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int key;
    private String alphabet;

    /** Constructor for the CaesarCipher class that takes in custom Alphabet string
     *  All encrypted and decrypted outputs are Uppercased.
     * @param key shift parameter(0 is accepted)
     * @param alphabet Custom user given alphabet.(Empty is Accepted) Can only contain uppercase letters A-Z of the latin alphabet, in any order without duplicates.
     *
     * @throws IllegalArgumentException if alphabet contains duplicates
     * @throws IllegalArgumentException if alphabet has characters that are NOT within the standard english alphabet(ඞ)
     */
    public CaesarCipher(int key, String alphabet) {
        this.key = key;
        this.alphabet = alphabet.toUpperCase();

        // Throws IllegalArgumentException if a non english alphabet character
        if(!(Pattern.matches("[A-Z]+",this.alphabet))){
            throw new IllegalArgumentException("Alphabet contains invalid characters");
        }
        // Throws IllegalArgumentException if the alphabet contains duplicate chars
        if(this.alphabet.length() != this.alphabet.chars().distinct().count()){
            throw new IllegalArgumentException("Alphabet contains duplicate characters");
        }
    }

    /** Default constructor for the CaesarCipher that uses the default alphabet
     *
     * @param key shift parameter
     */
    public CaesarCipher(int key) {
        this(key,DEFAULT_ALPHABET);
    }

    /** Method that encrypts a provided string using Caesar Cipher
     * Symbols not found in the defined alphabet will be returned unmodified.
     * @param clearText normal string to be encrypted
     * @return encrypted text based on the provided/default alphabet and key
     */
    public String encrypt(String clearText) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < clearText.length(); i++) {
            char ch = clearText.toUpperCase().charAt(i);
            // if ch is a character then append encrypted ch, blank if blank, ch itself if ch is not in given alphabet
            if (ch != ' ') {
                int index = this.alphabet.indexOf(ch);
                //If the ch does not exist in the Alphabet then we append it unchanged
                if (index != -1) {
                    ch = this.alphabet.charAt((index + this.key) % this.alphabet.length());
                    result.append(ch);
                } else {
                    result.append(ch);
                }
            }
            else{
                result.append(ch);
            }
        }
        return result.toString();
    }

    /** Method that decrypts a provided string using Caesar Cipher
     * Symbols not found in the defined alphabet will be returned unmodified.
     * @param cipherText encrypted string to be decrypted
     * @return decrypted text based on the provided/default alphabet and key
     */
    public String decrypt(String cipherText) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < cipherText.length(); i++) {
            char ch = cipherText.toUpperCase().charAt(i);
            // if ch is a letter that exist in alphabet then shift ch back to the appropriate letter
            if (ch != ' ') {
                int index = this.alphabet.indexOf(ch);
                //If the ch does not exist in the Alphabet then we append ch to result
                if (index != -1) {
                    int shiftedIndex = (this.alphabet.indexOf(ch) - this.key) % this.alphabet.length();
                    if (shiftedIndex < 0) {
                        shiftedIndex += this.alphabet.length();  // Ensure positive index by adding 26
                    }
                    ch = this.alphabet.charAt(shiftedIndex);
                    result.append(ch);
                }
                else{
                    result.append(ch);
                    }
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    /** Getter method to obtain the alphabet used by the cipher
     *
     * @return The object's initialized alphabet
     */
    public String getAlphabet(){
        return this.alphabet;
    }
}
