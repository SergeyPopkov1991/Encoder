import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class EncryptedDecrypted {

    @SneakyThrows
    public void encryptedDecrypted(boolean flag)  {

        System.out.println(flag ? "Введите путь к файлу для его зашифровки" : "Введите путь к файлу для его расшифровки");

        String path = Util.readString();
        System.out.println("Введите ключ шифрования");
        int key = Integer.parseInt(Util.readString());
        Path result = Util.buildFileName(path, flag ? "_encrypted" : "_decrypted");


        CaesarCipher caesar = new CaesarCipher();
        try (BufferedReader reader = new BufferedReader(new FileReader(path));
             BufferedWriter writer = Files.newBufferedWriter(result)) {
            while (reader.ready()) {
                String readLine = reader.readLine();
                String str = flag ? caesar.encrypt(readLine, key) : caesar.decrypt(readLine, key);
                writer.write(str);
                writer.newLine();

            }

        }
    }
}



/*заменить сканеры (ввод и вывод строк и чисел ) на соответствующие методы класса утил
* 10 , 11 , 12 строки переписать через тернарный оператор    */

// не вижу новый файл после расшифровки!
