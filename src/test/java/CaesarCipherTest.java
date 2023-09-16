import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaesarCipherTest {
    private CaesarCipher caesarCipher = new CaesarCipher();

    @Test
    void encrypt() {
        String encrypt = caesarCipher.encrypt("abc", 3);
        assertEquals("def", encrypt);
    }

    @Test
    void encrypt2() {
        String encrypt = caesarCipher.encrypt("abc", 29);
        assertEquals("def", encrypt);
    }

    @Test
    void encrypt3() {
        String encrypt = caesarCipher.encrypt("zoo", 3);
        assertEquals("crr", encrypt);
    }

    @Test
    void encrypt4() {
        String encrypt = caesarCipher.encrypt("Σ", 3);
        assertEquals("", encrypt);
    }

    @Test
    void decrypt() {
        String encrypt = caesarCipher.decrypt("def", 3);
        assertEquals("abc", encrypt);
    }

    @Test
    void decrypt2() {
        String encrypt = caesarCipher.decrypt("def", 29);
        assertEquals("abc", encrypt);
    }

    @Test
    void decrypt3() {
        String encrypt = caesarCipher.decrypt("crr", 3);
        assertEquals("zoo", encrypt);
    }
}