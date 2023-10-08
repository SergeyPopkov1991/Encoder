import javax.imageio.IIOException;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        while (true) {
            System.out.println("""
                    Выберите действие , введя его номер.
                    1.Зашифровать текст
                    2.Расшифровать текст
                    3.Подобрать ключ
                    4.Синтаксический анализ
                    5.Закрыть программу""");

            String answer = String.valueOf(Util.readInt());

            switch (answer) {
                case "1" -> new EncryptedDecrypted().encryptedDecrypted(true);
                case "2" -> new EncryptedDecrypted().encryptedDecrypted(false);
                case "3" -> new Bruteforce().bruteforce();
                case "4" -> new Parsing().parse();
                case "5" -> {
                    return;
                }
            }

        }
    }
}
