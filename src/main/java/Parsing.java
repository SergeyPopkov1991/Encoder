import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Parsing {

    private final Map<Character, Integer> mapEncrypted = new HashMap<>();
    private final Map<Character, Integer> mapStatistic = new HashMap<>();
    private final Map<Character, Character> mapDecrypted = new HashMap<>();

    @SneakyThrows
    public void parse()  {
        Util.writeMessage("Введите адрес файла для его расшифрвки");
        String pathEncrypted = Util.readString();

        Util.writeMessage("Введите адрес файла для набора статистики");
        String pathStatistic = Util.readString();
        Path parsing = Util.buildFileName(pathEncrypted, "_parsing");


        fillMapWithValues(mapEncrypted, pathEncrypted);
        fillMapWithValues(mapStatistic, pathStatistic);
        List<Map.Entry<Character, Integer>> listEncrypted = mapToList(mapEncrypted);
        List<Map.Entry<Character, Integer>> listStatistic = mapToList(mapStatistic);

        if (listEncrypted.size() <= listStatistic.size()) {

            for (int i = 0; i < listEncrypted.size(); i++) {
                mapDecrypted.put(listEncrypted.get(i).getKey(), listStatistic.get(i).getKey());
            }
        } else {
            System.out.println("Размер файла статистики должен быть больше");
        }

        try (BufferedReader reader = Files.newBufferedReader(Path.of(pathEncrypted));
             BufferedWriter writer = Files.newBufferedWriter(parsing)) {
            while (reader.ready()) {
                StringBuilder builder = new StringBuilder();
                String string = reader.readLine();
                char[] charArray = string.toCharArray();
                for (char aChar : charArray) {
                    Character decryptedChar = mapDecrypted.get(aChar);
                    builder.append(decryptedChar);
                }
                writer.write(builder.toString());
                writer.newLine();
            }
        }
        System.out.println("Содержимое расшифровано методом синтаксического анализа");
    }

    private List<Map.Entry<Character, Integer>> mapToList(Map<Character, Integer> map) {
        Set<Map.Entry<Character, Integer>> entries = map.entrySet();
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(entries);

        Comparator<Map.Entry<Character, Integer>> comparator = new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        };
        list.sort(comparator);
        return list;
    }


    @SneakyThrows
    private void fillMapWithValues(Map<Character, Integer> map, String path) {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        while (reader.ready()) {
            String readline = reader.readLine();
            builder.append(readline);
        }
        char[] charArray = builder.toString().toCharArray();
        for (char aChar : charArray) {
            if (!map.containsKey(aChar)) {
                map.put(aChar, 1);
            } else {
                map.put(aChar, map.get(aChar) + 1);
            }
            // mapEncrypted.merge(aChar , 1 , (oV , nV) -> oV + nV );
        }
    }
}

// реализовать сортировку list через компаратор от большего к меньшему  по значению