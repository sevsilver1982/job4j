package bank;

import java.util.*;

public class Bank {
    private Map<User, List<Account>> users = new HashMap<>();

    /*public static void main(String[] args) {
        Bank bank = new Bank();
        bank.addUser(new User("User1", "4500 000001"));
        bank.addAccountToUser("4500 000001", new Account(1000, "00000000000000000001"));
        Account acc = new Account(2000, "00000000000000000002");
        bank.addAccountToUser("4500 000001", acc);
        bank.deleteAccountFromUser("4500 000001", acc);

        bank.addUser(new User("User2", "4500 000002"));
        bank.addAccountToUser("4500 000002", new Account(0, "00000000000000000003"));
        bank.addAccountToUser("4500 000002", new Account(2000, "00000000000000000004"));
        Optional<List<Account>> accounts = bank.getUserAccounts("4500 000002");

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
    }*/

    private Optional<User> getUserByPassport(String passport) {
        return this.users.keySet()
                .stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst();
    }

    private List<Account> getAccountList(User user) {
        return this.users.get(user);
    }

    private Optional<Account> getAccount(User user, String req) {
        return getAccountList(user).stream()
                .filter(account -> account.getRequisites().equals(req))
                .findFirst();
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
        Optional<User> user = getUserByPassport(passport);
        if (user.isPresent()) {
            List<Account> accounts = getAccountList(user.get());
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Удалить один счёт пользователя.
     * @param passport
     * @param account
     */
    public void deleteAccountFromUser(String passport, Account account) {
        Optional<User> user = getUserByPassport(passport);
        user.ifPresent(value -> getAccountList(value).remove(account));
    }

    /**
     * Получить список счетов для пользователя.
     * @param passport
     * @return
     */
    public Optional<List<Account>> getUserAccounts(String passport) {
        Optional<User> user = getUserByPassport(passport);
        return this.users.entrySet().stream()
                .filter(entry -> entry.getKey().getPassport().equals(passport))
                .map(Map.Entry::getValue)
                .findFirst();
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
        Optional<User> userS = getUserByPassport(srcPassport);
        Optional<User> userT = getUserByPassport(dstPassport);
        if (userS.isPresent() && userT.isPresent()) {
            Optional<Account> srcAccount = getAccount(userS.get(), srcRequisite);
            Optional<Account> dstAccount = getAccount(userT.get(), dstRequisite);
            if (srcAccount.isPresent() && dstAccount.isPresent()) {
                return srcAccount.get().transfer(dstAccount.get(), amount);
            }
        }
        return false;
    }

}