import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class Util {
    private static final BufferedReader CONSOLE = new BufferedReader(new InputStreamReader(System.in));

    private Util() {

    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() {
        String str;
        try {
            str = CONSOLE.readLine();
        } catch (IOException e) {
            writeMessage("Произошла ошибка при попытке ввода текста , попробуйте еще раз");
            str = readString();
        }
        return str;
    }

    public static int readInt() {
        int number;
        try {
            number = Integer.parseInt(readString());
        } catch (NumberFormatException e) {
            writeMessage("Произошла ошибка ввода числа , попробуйте еще раз");
            number = readInt();
        }
        return number;
    }

    public static Path buildFileName (String path , String suffix) {
        Path fullPath = Path.of(path);
        Path parent = fullPath.getParent();
        String fileName = fullPath.getFileName().toString();
        String newFileName;
        if (fileName.contains(".")) {
            int index = fileName.lastIndexOf(".");
            newFileName = fileName.substring(0 , index) + suffix + fileName.substring(index);

        } else {
            newFileName = fileName + suffix;
        }
        return parent.resolve(Path.of(newFileName));
    }

}



