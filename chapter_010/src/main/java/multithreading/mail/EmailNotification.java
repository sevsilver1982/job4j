package multithreading.mail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    void emailTo(User user) {
        pool.submit(() ->
            send(
                    String.format("Notification %s to email %s.", user.getUserName(), user.getEmail()),
                    String.format("Add a new event to %s", user.getUserName()),
                    user.getEmail()
            )
        );
    }

    public void send(String subject, String body, String email) {
        System.out.println(
                String.format("subject=%s; body=%s; email=%s", subject, body, email)
        );
    }

    void close() {
        pool.shutdown();
    }

    public static void main(String[] args) {
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.emailTo(new User("user1", "user1@mail.ru"));
        emailNotification.emailTo(new User("user2", "user2@mail.ru"));
        emailNotification.emailTo(new User("user3", "user3@mail.ru"));
        emailNotification.emailTo(new User("user4", "user4@mail.ru"));
        emailNotification.emailTo(new User("user5", "user5@mail.ru"));
        emailNotification.close();
    }

}