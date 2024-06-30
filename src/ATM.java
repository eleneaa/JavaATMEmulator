import Exeption.DailyLimitExceededException;
import Exeption.InsufficientFundsException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.util.stream.IntStream;

class ATM {
    private int count10Cassets;
    private int count50Cassets;
    private int count100Cassets;
    private Cassette cassette = new Cassette(count10Cassets, count50Cassets, count100Cassets);
    private OperationLog operationLog = new OperationLog();
    private List<User> users = new ArrayList<>();
    private User currentUser;

    public ATM(int count10Cassets, int count50Cassets, int count100Cassets) {
        this.count10Cassets = count10Cassets;
        this.count50Cassets = count50Cassets;
        this.count100Cassets = count100Cassets;
    }

    public void deposit(int amount) throws IllegalArgumentException {
        if (amount <= 0 || amount % 10 != 0) {
            throw new IllegalArgumentException("Invalid denomination of the banknote when replenishing.");
        }
        int remainingAmount = amount;
        int[] denominations = {100, 50, 10};
        for (int denomination : denominations) {
            int count = remainingAmount / denomination;
            if (count > 0) {
                remainingAmount -= count * denomination;
                cassette.refillCassette(denomination, count);
                operationLog.addEntryCassetteOperation(denomination, count);
            }
        }
    }

    public void withdraw(int amount, User user) throws IllegalArgumentException, InsufficientFundsException, DailyLimitExceededException {
        if (amount <= 0 || amount % 10 != 0) {
            operationLog.addEntryUserOperation(user, "withdraw", amount, "Invalid amount.");
            throw new IllegalArgumentException("Invalid amount.");
        }

        if (currentUser == null || !currentUser.equals(user)) {
            operationLog.addEntryUserOperation(user, "withdraw", amount, "The user is not authenticated.");
            throw new IllegalStateException("The user is not authenticated.");
        }

        if (amount > user.dailyLimit) {
            operationLog.addEntryUserOperation(user, "withdraw", amount, "Exceeding the daily withdrawal limit.");
            throw new DailyLimitExceededException("Exceeding the daily withdrawal limit.");
        }

        int balance = getBalance();
        if (amount > balance) {
            operationLog.addEntryUserOperation(user, "withdraw", amount,
                    "Request for withdrawal of an amount exceeding the current ATM balance.");
            throw new InsufficientFundsException("Request for withdrawal of an amount exceeding the current ATM balance.");
        }

        int[] denominations = {100, 50, 10};
        int remainingAmount = amount;
        Map<Integer, Integer> withdrawMap = new HashMap<>();

        for (int denomination : denominations) {
            int count = remainingAmount / denomination;
            if (count > 0) {
                withdrawMap.put(denomination, count);
                remainingAmount -= count * denomination;
                cassette.removeFromCassette(denomination, count);
                if (remainingAmount == 0) {
                    operationLog.addEntryUserOperation(user, "withdraw", amount, null);
                    break;
                }
            }
        }

        if (remainingAmount != 0) {
            operationLog.addEntryUserOperation(user, "withdraw", amount,
                    "A request to withdraw an amount that cannot be issued with existing banknotes.");
            throw new InsufficientFundsException("A request to withdraw an amount that cannot be issued with existing banknotes.");
        }

        user.dailyLimit -= amount;
    }

    public int getBalance() {
        return IntStream.of(cassette.getCasseteStatys()).sum();
    }
}

