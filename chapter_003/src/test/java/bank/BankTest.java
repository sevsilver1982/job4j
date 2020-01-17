package bank;

import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

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

        assertThat(
                bank.getUserAccounts(user.getPassport()),
                is(Optional.of(List.of(acc1, acc2)))
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

        assertThat(
                bank.getUserAccounts(user.getPassport()),
                is(Optional.of(List.of(acc1)))
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

        assertThat(acc1.getValue(), is(500D));
        assertThat(acc2.getValue(), is(2500D));
    }

}