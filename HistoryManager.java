import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    private List<CalculationHistory> history = new ArrayList<>();

    public void addHistory(String query, String result) {
        if (history.size() >= 50) 
            history.remove(0);
            
        history.add(new CalculationHistory(query, result, ""));
        saveHistoryToFile();
    }

    public void saveHistoryToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./JavaCalcGUI/history.txt"))) {
            for (CalculationHistory entry : history) {
                writer.write(entry.toString());
                writer.newLine();
            }
        } catch (IOException e) {
        }
    }

    public void loadHistoryFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("./JavaCalcGUI/history.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.replace("{", "").replace("}", "").split(", \"");
                String query = parts[0].split(":")[1].replace("\"", "").trim();
                String result = parts[1].split(":")[1].replace("\"", "").trim();
                String dateTime = parts[2].substring(parts[2].indexOf(":") + 1).replace("\"", "").trim();
                history.add(new CalculationHistory(query, result, dateTime));
            }
        } catch (IOException e) {
        }
    }        

    public List<CalculationHistory> getHistory() {
        return history;
    }
}
