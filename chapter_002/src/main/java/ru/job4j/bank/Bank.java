package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private Map<User, List<Account>> users = new HashMap<>();;

    /*public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addUser(new User("User1", "4500 000001"));
        bank.addAccountToUser("4500 000001", new Account(1000,"00000000000000000001"));
        bank.addAccountToUser("4500 000001", new Account(2000,"00000000000000000002"));
        bank.addUser(new User("User2", "4500 000002"));
        bank.addAccountToUser("4500 000002", new Account(1000,"00000000000000000003"));
        bank.addAccountToUser("4500 000002", new Account(2000,"00000000000000000004"));
        bank.addUser(new User("User3", "4500 000003"));
        bank.addUser(new User("User4", "4500 000004"));
        bank.addUser(new User("User5", "4500 000005"));

        List<Account> accounts = bank.getUserAccounts("4500 000002");
        boolean qwe = bank.transferMoney(
                "4500 000001",
                "00000000000000000002",
                "4500 000001",
                "00000000000000000001",
                50);
        System.out.println(qwe);
    }*/

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
        for (Map.Entry<User, List<Account>> item : this.users.entrySet()) {
            if (item.getKey().getPassport().equals(passport)) {
                List<Account> accounts = item.getValue();
                if (!accounts.contains(account)) {
                    accounts.add(account);
                    break;
                }
            }
        }
    }

    /**
     * Удалить один счёт пользователя.
     * @param passport
     * @param account
     */
    public void deleteAccountFromUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> item : this.users.entrySet()) {
            if (item.getKey().getPassport().equals(passport)) {
                item.getValue().remove(account);
                break;
            }
        }
    }

    /**
     * Получить список счетов для пользователя.
     * @param passport
     * @return
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> accounts = new ArrayList<>();
        for (Map.Entry<User, List<Account>> item : this.users.entrySet()) {
            if (item.getKey().getPassport().equals(passport)) {
                return new ArrayList<>(item.getValue());
            }
        }
        return accounts;
    }

    /**
     * Перечисление денег с одного счёта на другой.
     * Если счёт не найден или не хватает денег на счёте srcAccount (с которого переводят) вернёт false.
     * @param srcPassport
     * @param srcRequisite
     * @param destPassport
     * @param dstRequisite
     * @param amount
     * @return
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        Account srcAccount = null;
        Account dstAccount = null;
        for (Map.Entry<User, List<Account>> item : this.users.entrySet()) {
            if (item.getKey().getPassport().equals(srcPassport) || item.getKey().getPassport().equals(destPassport)) {
                for (Account account : item.getValue()) {
                    if (account.getRequisites().equals(srcRequisite)) {
                        srcAccount = account;
                    }
                    if (account.getRequisites().equals(dstRequisite)) {
                        dstAccount = account;
                    }
                }
            }
            if (srcAccount != null && dstAccount != null) {
                break;
            }
        }
        if (srcAccount == null || dstAccount == null || srcAccount.getValue() < amount) {
            return false;
        }
        srcAccount.setValue(srcAccount.getValue() - amount);
        dstAccount.setValue(dstAccount.getValue() + amount);
        return true;
    }

}