import java.text.SimpleDateFormat;
import java.util.Date;

public class CalculationHistory {
    private String query;
    private String result;
    private String dateTime;

    public CalculationHistory(String query, String result, String dateTime) {
        this.query = query;
        this.result = result;
        this.dateTime = dateTime.isEmpty() ? getCurrentDateTime() : dateTime;
    }

    private String getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy - hh:mm:ss a");
        return formatter.format(new Date());
    }

    @Override
    public String toString() {
        return String.format("{\"query\":\"%s\", \"result\":\"%s\", \"dateTime\":\"%s\"}", query, result, dateTime);
    }

    // Getters
    public String getQuery() {
        return query;
    }

    public String getResult() {
        return result;
    }

    public String getDateTime() {
        return dateTime;
    }
}
