package ru.job4j.bank;

import java.util.*;

public class Bank {
    private Map<User, List<Account>> users = new HashMap<>();

    public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addUser(new User("User1", "4500 000001"));
        bank.addAccountToUser("4500 000001", new Account(1000,"00000000000000000001"));
        Account acc = new Account(2000,"00000000000000000002");
        bank.addAccountToUser("4500 000001", acc);
        bank.deleteAccountFromUser("4500 000001", acc);

        bank.addUser(new User("User2", "4500 000002"));
        bank.addAccountToUser("4500 000002", new Account(0,"00000000000000000003"));
        bank.addAccountToUser("4500 000002", new Account(2000,"00000000000000000004"));
        List<Account> accounts = bank.getUserAccounts("4500 0000020");

        bank.addUser(new User("User3", "4500 000003"));
        bank.addUser(new User("User4", "4500 000004"));
        bank.addUser(new User("User5", "4500 000005"));

        boolean qwe = bank.transferMoney(
                "4500 000001",
                "00000000000000000001",
                "4500 000002",
                "00000000000000000003",
                50);
        System.out.println(qwe);
    }

    public User getUserByPassport(String passport) {
        for (Map.Entry<User, List<Account>> item : this.users.entrySet()) {
            if (item.getKey().getPassport().equals(passport)) {
                return item.getKey();
            }
        }
        return null;
    }

    public List<Account> getAccountList(User user) {
        for (Map.Entry<User, List<Account>> item : this.users.entrySet()) {
            if (item.getKey().equals(user)) {
                return item.getValue();
            }
        }
        return null;
    }

    public Account getAccount(User user, String req) {
        List<Account> accounts = getAccountList(user);
        if (accounts != null) {
            for (Account account : accounts) {
                if (account.getRequisites().equals(req)) {
                    return account;
                }
            }
        }
        return null;
    }

    /**
     * Добавление пользователя.
     * @param user
     */
    public void addUser(User user) {
        this.users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Удаление пользователя.
     * @param user
     */
    public void deleteUser(User user) {
        this.users.remove(user);
    }

    /**
     * Добавить счёт пользователю.
     * @param passport
     * @param account
     */
    public void addAccountToUser(String passport, Account account) {
        List<Account> accounts = getAccountList(getUserByPassport(passport));
        if (!accounts.contains(account)) {
            accounts.add(account);
        }
    }

    /**
     * Удалить один счёт пользователя.
     * @param passport
     * @param account
     */
    public void deleteAccountFromUser(String passport, Account account) {
        getAccountList(getUserByPassport(passport)).remove(account);
    }

    /**
     * Получить список счетов для пользователя.
     * @param passport
     * @return
     */
    public List<Account> getUserAccounts(String passport) {
        User user = getUserByPassport(passport);
        if (user != null) {
            List<Account> accounts = getAccountList(user);
            if (accounts != null) {
                return new ArrayList<>(accounts);
            }
        }
        return null;
    }

    /**
     * Перечисление денег с одного счёта на другой.
     * Если счёт не найден или не хватает денег на счёте srcAccount (с которого переводят) вернёт false.
     * @param srcPassport
     * @param srcRequisite
     * @param dstPassport
     * @param dstRequisite
     * @param amount
     * @return
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String dstPassport, String dstRequisite, double amount) {
        Account srcAccount = getAccount(getUserByPassport(srcPassport), srcRequisite);
        Account dstAccount = getAccount(getUserByPassport(dstPassport), dstRequisite);
        if (srcAccount == null || dstAccount == null) {
            return false;
        }
        return srcAccount.transfer(dstAccount, amount);
    }

}