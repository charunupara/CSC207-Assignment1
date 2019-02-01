
import java.io.PrintWriter; 
import java.io.BufferedReader; 
import java.io.InputStreamReader; 



public class VigenereCipher {

    /*
Purpose:
--to change the alphabetic characters in phrase according to the 
   integer values of the of characters in keyInput
Preconditions:
--phrase and keyInput must have zero spaces
--phrase and keyInput must contain only lowercase alphabetic characters
Postconditions:
--all characters in phrase will be changed by number of places in the alphabet specified by keyInput
--if keyInput.length == 0, then phrase is not changed
--if phrase is an empty string, returns empty string
Parameter:
--phrase, a string
--keyInput, a string
Produces:
--a string that is a modified version of phrase that has been altered by keyInput
     */
    public static String encrypt(String phrase, String keyInput){
        
        //create a character array from the passed string
   
        char[] str = phrase.toCharArray(); 
        int stringSize = str.length;
        // convert the key string into array of characters
        char[] key = keyInput.toCharArray();
        int keySize = key.length;
        int m = 0;
        
        //PrintWriter pen = new PrintWriter(System.out, true);
        
        // if key is an empty string, do not encrypt and return 
        if (keySize == 0) {
            return phrase;
        }
        // else create a loop to encrypt each character by the key
        else {
            for(int i = 0; i < stringSize; i++) {
                // encrypting
                int character = (int) str[i]; 
                // if the index is divisible by keySize, reset m = 0
                if (i != 0){
                    if ((i % keySize == 0) && (i >= keySize)) {
                            m = 0;
                        }
                }
                // else encrypt like normal
                character = character - 97;
                int keyNum = key[m] - 97;
                character = ((character + keyNum) % 26); 
                character = character + 97;
                keyNum = keyNum + 97;
                // put the characyer in to the array
                str[i] = (char) character;

                m = m + 1;
            }
        }
        phrase = new String (str);
        return phrase;
    }

    /*
Purpose:
--to decode phrase by subtracting integer values of phrase by those of key Input
Preconditions:
--phrase and keyInput must have zero spaces
--phrase and keyInput must contain only lowercase alphabetic characters
Postconditions:
--all characters in phrase will be changed by number of places in the alphabet specified by keyInput
--if keyInput.length == 0, then phrase is not changed
--if phrase is an empty string, returns empty string
Parameter:
--phrase, a string
--keyInput, a string
Produces:
--a string that is a modified version of phrase that has been altered by keyInput
   (reverses encryption to obtain original message)
     */
    
    public static String  decrypt(String phrase, String keyInput){
     
        //create a character array from the passed string
   
        char[] str = phrase.toCharArray(); 
        int stringSize = str.length;
   
        // convert the key string into array of characters
        char[] key = keyInput.toCharArray();
        int keySize = key.length;
        int m = 0; 
        
        // if key is an empty string, do not decrypt and return 
        if (keySize == 0) {
            return phrase;
        }
        // else create a loop to decrypt each character by the key
        else {
            for(int i = 0; i < stringSize; i++) {
                // decrypting
                int character = (int) str[i]; 
                // if index i of the phrase is divisible by Keysize, reset m  = 0
                if (i != 0){
                    if ((i % keySize == 0) && (i >= keySize))
                        {
                            m = 0;
                        }
                }
                // else decrypt like normal
                character = character - key[m];
                if (character < 0){
                    character = character + 'z' - 'a' + 1;
                }
                character = character + 97;
                str[i] = (char) character;
                m = m + 1;
            }
        }
        phrase = new String(str);
        return phrase;

    }

      /*
Preconditions:
--args[0] must be either "encode" or "decode"; otherwise prints error message
Parameter:
--command, either "encode" or "decode"
--phrase, a string
--keyInput, a string
Produces:
--printed message with phrase either encoded or decoded by a Vigenere Cipher
     */
    public static void main(String[] args) throws Exception{

        BufferedReader eyes = new BufferedReader(new InputStreamReader( System.in));
        PrintWriter pen = new PrintWriter(System.out, true); 

        // if the command is encode, then call encrypt function
        if (args[0].equals("encode")){
        
            pen.println(encrypt(args[1], args[2]));
        }
        // else if the first string is decode, call decrypt 
        else if (args[0].equals("decode")){
        
            pen.println(decrypt(args[1], args[2]));
        }
        else {
            pen.println("Valid options are \"encode\" or \"decode\"");
            pen.flush();
        }
    }
}

