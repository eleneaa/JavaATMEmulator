import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OperationLog {
    private List<String> logEntries;

    // Метод для добавления новой записи об операциях пользователя в журнал
    public void addEntryUserOperation(User user, String type, int amount, String exeption) {
        String userOperationEntry = String.format("time: %s; user: %s; type: %s; amount: %d; exeption: %s",
                LocalDateTime.now(), user.username, type, amount, exeption);
        logEntries.add(userOperationEntry);
    }

    // Метод для добавления новой записи о входе/выходе пользователя в журнал
    public void addEntryLoginOperation(User user, boolean login) {
        String loginOperationEntry = String.format("time: %s; user: %s; login: %b",
                LocalDateTime.now(), user.username, login);
        logEntries.add(loginOperationEntry);
    }

    // Метод для добавления новой записи о пополнении кассет в журнал
    public void addEntryCassetteOperation(int denomination, int count) {
        String CassetteOperationEntry = String.format("time: %s; denomination: %d; count: %d",
                LocalDateTime.now(), denomination, count);
        logEntries.add(CassetteOperationEntry);
    }

    // Метод для получения всех записей журнала
    public List<String> getLogEntries() {
        return new ArrayList<>(logEntries);
    }

    // Метод для поиска записей по ключевому слову (например, тип операции)
    public List<String> searchEntries(String keyword) {
        List<String> searchResults = new ArrayList<>();
        for (String entry : logEntries) {
            if (entry.contains(keyword)) {
                searchResults.add(entry);
            }
        }
        return searchResults;
    }
}
