import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadArchive {
    Path entryPath, exitPath;

    public ReadArchive(Path entryPath, Path exitPath) {
        this.entryPath = entryPath;
        this.exitPath = exitPath;
    }

    public void setEntryPath(Path entryPath) {
        this.entryPath = entryPath;
    }

    public void setExitPath(Path exitPath) {
        this.exitPath = exitPath;
    }

    public ArrayList<String> readArchive(String separator) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new BufferedReader(new FileReader(String.valueOf(entryPath))));
        String message = bufferedReader.readLine();
        bufferedReader.close();
        ArrayList<String> lines = new ArrayList<>();
        if (separator.equals("No separator")) {
            lines.add(message.toLowerCase());
        } else lines.addAll(List.of(message.toLowerCase().split(separator)));
        return lines;
    }

    public boolean saveOutput(String message) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(String.valueOf(exitPath)))) {
            bufferedWriter.write(message);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
