package oldVersion;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Challenge01 {
    public static void main(String[] args) {
        Path messagePath = Paths.get("resources/message_01.txt"),
                decryptedFile = Paths.get("resources/solutions/decryotedMessage(C01).txt");
        String decryptedMessage = null;
        try {
            decryptedMessage = readMessage(messagePath);
            writeMessage(decryptedFile, decryptedMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(decryptedMessage);
    }

    private static String readMessage(Path pathMessage) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new BufferedReader(new FileReader(String.valueOf(pathMessage))))) {
            //Leemos el mensaje
            String menssage = bufferedReader.readLine();
            //Separamos las palabras con el separador de espacio en blanco
            String[] words = menssage.toLowerCase().split(" ");
            //Añadimos las palabras a un arrayList y comprobamos que solo añada palabras que no contiene
            ArrayList<String> uniqueWords = new ArrayList<>();
            uniqueWords.add(words[0]);
            for (String word : words) {
                if (!uniqueWords.contains(word)) {
                    uniqueWords.add(word);
                }
            }
            //Pasamos el Set a un Array para poder trabajar con sus indices
            String[] indexUW = uniqueWords.toArray(new String[0]);
            //Contamos cada palabra. El índice en el set es equivalente al número en el array
            int[] wordCounted = new int[indexUW.length];
            for (String word : words) {
                for (int j = 0; j < indexUW.length; j++) {
                    if (word.equals(indexUW[j])) {
                        wordCounted[j]++;
                        //System.out.println(indexUW[j] + " ha salido: " + wordCounted[j] + " veces.");
                    }
                }
            }
            //Finalmente, agregamos al mensaje el conteo por cada palabra
            String finalMessage = "";
            for (int i = 0; i < uniqueWords.size(); i++) {
                finalMessage = finalMessage + indexUW[i] + wordCounted[i];
            }
            return finalMessage;
        }
    }

    private static void writeMessage(Path outputPath, String message) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(String.valueOf(outputPath)))) {
            bufferedWriter.write(message);
        }
    }

}
