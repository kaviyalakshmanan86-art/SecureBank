package service;

import model.Account;
import model.Transaction;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class StatementService {

    // TreeMap keeps accounts sorted by ID
    private final TreeMap<Integer, Account> accounts = new TreeMap<>();

    // Transaction history for each account
    private final Map<Integer, TreeMap<LocalDateTime, Transaction>> history =
            new HashMap<>();

    // Add account
    public void addAccount(Account account) {
        accounts.put(account.getId(), account);
        history.putIfAbsent(account.getId(), new TreeMap<>());
    }

    // Add transaction
    public void addTransaction(int accountId,
                               LocalDateTime time,
                               Transaction transaction) {

        history.computeIfAbsent(accountId, k -> new TreeMap<>())
               .put(time, transaction);
    }

    // Accounts sorted by ID
    public NavigableMap<Integer, Account> getAccountsById() {
        return accounts;
    }

    // Separate Comparator for balance order
    private final Comparator<Account> balanceComparator =
            Comparator.comparingDouble(Account::getBalance)
                      .thenComparingInt(Account::getId);

    // Accounts sorted by balance
    public NavigableMap<Account, Integer> getAccountsByBalance() {

        TreeMap<Account, Integer> result =
                new TreeMap<>(balanceComparator);

        for (Account account : accounts.values()) {
            result.put(account, account.getId());
        }

        return result;
    }

    // Transactions between two dates
    public NavigableMap<LocalDateTime, Transaction> getStatement(
            int accountId,
            LocalDateTime from,
            LocalDateTime to) {

        TreeMap<LocalDateTime, Transaction> transactions =
                history.get(accountId);

        if (transactions == null) {
            return new TreeMap<>();
        }

        // No filtering loop - directly uses subMap()
        return transactions.subMap(from, true, to, true);
    }

    // First key >= given key
    public Integer findCeilingAccount(int id) {
        return accounts.ceilingKey(id);
    }

    // Last key <= given key
    public Integer findFloorAccount(int id) {
        return accounts.floorKey(id);
    }

    // Keys below the given key
    public NavigableMap<Integer, Account> getHeadAccounts(int id) {
        return accounts.headMap(id, false);
    }

    // Keys from the given key
    public NavigableMap<Integer, Account> getTailAccounts(int id) {
        return accounts.tailMap(id, true);
    }

    // Range: from inclusive, to exclusive
    public NavigableMap<Integer, Account> getAccountRange(
            int from,
            int to) {

        return accounts.subMap(from, true, to, false);
    }
}