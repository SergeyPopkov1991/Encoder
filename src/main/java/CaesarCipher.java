public class CaesarCipher {
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            ".,\":!? +-*/\\@#$%^&(){}[];'|`~=_©«»—" + "0123456789";

    public String encrypt(String message, int key) {
        StringBuilder result = new StringBuilder();
        for (char character : message.toCharArray()) {
            int index = alphabet.indexOf(character);
            if (index >= 0) {
                int newIndex = (index + key) % alphabet.length();
                char charAt = 0;
                if (newIndex < 0) {
                    charAt = alphabet.charAt(newIndex + alphabet.length());
                } else {
                    charAt = alphabet.charAt(newIndex);
                }
                result.append(charAt);
            }
        }
        return result.toString();
    }

    public String decrypt(String message, int key) {
        return encrypt(message, key * (-1));
    }

    public int alphabetLength() {
        return alphabet.length();
    }
}


