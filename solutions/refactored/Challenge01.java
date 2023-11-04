package refactored;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class Challenge01 {
    public static void main(String[] args) {
        Path messagePath = Paths.get("resources/01/message_01.txt");
        Path decryptedFile = Paths.get("resources/01/decryptedMessage.txt");
        try {
            String decryptedMessage = readMessage(messagePath);
            writeMessage(decryptedFile, decryptedMessage);
            System.out.println(decryptedMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readMessage(Path pathMessage) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathMessage.toFile()))) {
            String line;
            StringBuilder messageBuilder = new StringBuilder();

            LinkedHashSet<String> uniqueWords = new LinkedHashSet<>();
            HashMap<String, Integer> wordCount = new HashMap<>();

            while ((line = bufferedReader.readLine()) != null) {
                messageBuilder.append(line);
                String[] words = line.toLowerCase().split(" ");
                for (String word : words) {
                    uniqueWords.add(word);
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                }
            }

            StringBuilder finalMessage = new StringBuilder();
            for (String word : uniqueWords) {
                finalMessage.append(word).append(wordCount.get(word));
            }

            return finalMessage.toString();
        }
    }

    private static void writeMessage(Path outputPath, String message) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputPath.toFile()))) {
            bufferedWriter.write(message);
        }
    }
}

