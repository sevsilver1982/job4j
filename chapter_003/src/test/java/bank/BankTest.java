package bank;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankTest {

    @Test
    public void getUserAccounts() {
        Bank bank = new Bank();
        User user = new User("User1", "4500 000001");
        bank.addUser(user);
        Account acc1 = new Account(1000, "00000000000000000001");
        bank.addAccountToUser("4500 000001", acc1);
        Account acc2 = new Account(2000, "00000000000000000002");
        bank.addAccountToUser("4500 000001", acc2);
        assertEquals(
                Optional.of(
                        List.of(acc1, acc2)
                ),
                bank.getUserAccounts(
                        user.getPassport()
                )
        );
    }

    @Test
    public void deleteAccountFromUser() {
        Bank bank = new Bank();
        User user = new User("User1", "4500 000001");
        bank.addUser(user);
        Account acc1 = new Account(1000, "00000000000000000001");
        bank.addAccountToUser("4500 000001", acc1);
        Account acc2 = new Account(2000, "00000000000000000002");
        bank.addAccountToUser("4500 000001", acc2);
        bank.deleteAccountFromUser("4500 000001", acc2);
        assertEquals(
                Optional.of(
                        List.of(acc1)
                ),
                bank.getUserAccounts(
                        user.getPassport()
                )
        );
    }

    @Test
    public void transferMoney() {
        Bank bank = new Bank();

        User user1 = new User("User1", "4500 000001");
        bank.addUser(user1);
        Account acc1 = new Account(1000, "00000000000000000001");
        bank.addAccountToUser("4500 000001", acc1);

        User user2 = new User("User2", "4500 000002");
        bank.addUser(user2);
        Account acc2 = new Account(2000, "00000000000000000002");
        bank.addAccountToUser("4500 000002", acc2);

        bank.transferMoney("4500 000001", "00000000000000000001", "4500 000002", "00000000000000000002", 500);

        assertEquals(
                500,
                acc1.getValue()
        );
        assertEquals(
                2500,
                acc2.getValue()
        );
    }

}