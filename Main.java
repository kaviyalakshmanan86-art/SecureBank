import model.Account;
import model.Transaction;
import service.StatementService;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        StatementService service = new StatementService();

        // Create accounts
        Account a101 = new Account(101, "Kaviya", 5000);
        Account a103 = new Account(103, "Priya", 2500);
        Account a105 = new Account(105, "Anu", 7500);
        Account a110 = new Account(110, "Divya", 1500);

        // Add accounts
        service.addAccount(a101);
        service.addAccount(a103);
        service.addAccount(a105);
        service.addAccount(a110);

        // Add transactions
        service.addTransaction(
                101,
                LocalDateTime.of(2026, 1, 5, 10, 30),
                new Transaction(
                        LocalDateTime.of(2026, 1, 5, 10, 30),
                        "DEPOSIT",
                        1000,
                        "Salary"
                )
        );

        service.addTransaction(
                101,
                LocalDateTime.of(2026, 1, 10, 12, 15),
                new Transaction(
                        LocalDateTime.of(2026, 1, 10, 12, 15),
                        "WITHDRAW",
                        500,
                        "Shopping"
                )
        );

        service.addTransaction(
                101,
                LocalDateTime.of(2026, 1, 15, 14, 20),
                new Transaction(
                        LocalDateTime.of(2026, 1, 15, 14, 20),
                        "DEPOSIT",
                        2000,
                        "Bonus"
                )
        );

        // 1. Accounts sorted by ID
        System.out.println("=== ACCOUNTS SORTED BY ID ===");
        System.out.println(service.getAccountsById());

        // 2. ceilingKey()
        System.out.println("\n=== CEILING KEY ===");
        System.out.println("ceilingKey(104) = "
                + service.findCeilingAccount(104));

        // 3. floorKey()
        System.out.println("\n=== FLOOR KEY ===");
        System.out.println("floorKey(104) = "
                + service.findFloorAccount(104));

        // 4. subMap()
        System.out.println("\n=== ACCOUNT RANGE ===");
        System.out.println("subMap(100,108) = "
                + service.getAccountRange(100, 108));

        // 5. Accounts sorted by balance
        System.out.println("\n=== ACCOUNTS SORTED BY BALANCE ===");

        service.getAccountsByBalance()
                .forEach((account, id) ->
                        System.out.println(account));

        // 6. Date range statement
        LocalDateTime from =
                LocalDateTime.of(2026, 1, 1, 0, 0);

        LocalDateTime to =
                LocalDateTime.of(2026, 1, 15, 23, 59);

        System.out.println("\n=== STATEMENT: 1 JAN - 15 JAN ===");

        service.getStatement(101, from, to)
                .forEach((time, transaction) ->
                        System.out.println(transaction));
    }
}