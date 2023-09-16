import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class Bruteforce {
    private final CaesarCipher caesar = new CaesarCipher();

    @SneakyThrows
    public void bruteforce() {
        Util.writeMessage("Введите путь к файлу для его расшифровки");
        String path = Util.readString();
        Path bruteforce = Util.buildFileName(path, "_bruteforce");
        try (BufferedReader reader = Files.newBufferedReader(Path.of(path));
             BufferedWriter writer = Files.newBufferedWriter(bruteforce)) {
            StringBuilder builder = new StringBuilder();
            while (reader.ready()) {
                String str = reader.readLine();
                builder.append(str).append("\n");
            }
            for (int i = 0; i < caesar.alphabetLength(); i++) {
                String decrypt = caesar.decrypt(builder.toString(), i);
                if (isValidateText(decrypt)) {
                    writer.write(decrypt);
                    Util.writeMessage("Содержимое расшифровано , ключ расшифровки k = " + i);
                    break;
                }
            }
        }

    }

    private boolean isValidateText(String text) {
        boolean isValidate = false;

        String[] split = text.split(" ");
        for (String word : split) {
            if (word.length() > 28) {
                return false;
            }
        }
        if (text.contains(". ") || text.contains(", ") || text.contains("? ") || text.contains("! ")) {
            isValidate = true;
        }

        while (isValidate) {

            Util.writeMessage(text);
            Util.writeMessage("Понятен ли текст? (y/n)");
            String answer = Util.readString();
            if (answer.equalsIgnoreCase("y")) {
                return true;
            } else if (answer.equalsIgnoreCase("n")) {
                isValidate = false;
            } else {
                Util.writeMessage("Выбор только между y/n");
            }
        }
        return false;
    }
}

// собрать все содержимое файла в list <>   параллельно со сбором в builder
//после валидации пробежаться по list и расшифровать еще раз
