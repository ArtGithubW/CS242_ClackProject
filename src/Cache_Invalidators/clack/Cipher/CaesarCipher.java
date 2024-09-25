package Cache_Invalidators.clack.Cipher;
import java.util.regex.Pattern;

public class CaesarCipher {
    public static final String DEFAULT_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private int key;
    private String alphabet;

    /** Constructor for the CaesarCipher class that takes in custom Alphabet string
     *
     * @param key shift parameter
     * @param alphabet Custom 26 char uppercase alphabet
     */
    public CaesarCipher(int key, String alphabet) {
        this.key = key;
        this.alphabet = alphabet.toUpperCase();

        // Throws IllegalArgumentException if provided alphabet is not of length 26
        if(alphabet.length()!=26){
            throw new IllegalArgumentException("Alphabet length must equal to 26");
        }
        // Throws IllegalArgumentException if alphabet contains any character that is not uppercase alphabet
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
     *
     * @param clearText normal string to be encrypted
     * @return encrypted text based on the provided/default alphabet and key
     */
    public String encrypt(String clearText) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < clearText.length(); i++) {
            char ch = clearText.toUpperCase().charAt(i);
            // if ch is a letter then shift ch to appropriate shifted letter, else append the blank space
            if (ch != ' ') {
                ch = this.alphabet.charAt((this.alphabet.indexOf(ch)+this.key) % 26);
                result.append(ch);
            }
            else{
                result.append(ch);
            }
        }
        return result.toString();
    }

    /** Method that decrypts a provided string using Caesar Cipher
     *
     * @param cipherText encrypted string to be decrypted
     * @return decrypted text based on the provided/default alphabet and key
     */
    public String decrypt(String cipherText) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < cipherText.length(); i++) {
            char ch = cipherText.toUpperCase().charAt(i);
            // if ch is a letter then shift ch back to the appropriate letter
            if (ch != ' ') {
                int shiftedIndex = (this.alphabet.indexOf(ch) - this.key) % 26;
                if (shiftedIndex < 0) {
                    shiftedIndex += 26;  // Ensure positive index by adding 26
                }
                ch = this.alphabet.charAt(shiftedIndex);
                result.append(ch);
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
