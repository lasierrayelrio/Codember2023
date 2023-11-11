import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Challenge02 {
    public static void main(String[] args) {
        String decryptedMessage = null;
        ReadArchive readArchive = new ReadArchive(Paths.get("resources/message_02.txt"),
                Paths.get("resources/solutions/decryotedMessage(C02).txt"));
        try {
            String message = unificationLines(new ArrayList<>(readArchive.readArchive("No separator")));
            int result = printResult(message);
            System.out.println("El mensaje a codificar es: " + message);
            System.out.printf("El mensaje decodificado es: %01d", result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static private String unificationLines(ArrayList<String> messages) {
        String oneLineMessage = null;
        if (messages.size() > 1) {
            StringBuilder oneLineBuilder = new StringBuilder();
            for (String message : messages) {
                oneLineBuilder.append(message);
            }
            oneLineMessage = oneLineBuilder.toString();
        } else oneLineMessage = messages.get(0);
        return oneLineMessage;
    }

    static private int printResult(String symbolsToConvert) {
        int total = 0;
        StringBuilder global = new StringBuilder("0");
        for (int i = 0; i < symbolsToConvert.length(); i++)
            switch (symbolsToConvert.charAt(i)) {
                case '&' -> global.append(total);
                case '#' -> total++;
                case '@' -> total--;
                case '*' -> total *= total;
            }
        return Integer.parseInt(global.toString());
    }
}