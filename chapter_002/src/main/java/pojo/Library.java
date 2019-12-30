package pojo;

public class Library {
    public static void main(String[] args) {
        Book cleanCode1 = new Book("Clean code", 700);
        Book cleanCode2 = new Book("Clean code. Tom 2.", 800);
        Book cleanCode3 = new Book("Clean code. Tom 3.", 900);
        Book cleanCode4 = new Book("Clean code. Tom 4.", 1000);

        Book[] books = new Book[4];

        books[0] = cleanCode1;
        books[1] = cleanCode2;
        books[2] = cleanCode3;
        books[3] = cleanCode4;

        for (Book book: books) {
            System.out.println(book.getName() + " - " + book.getListCount());
        }

        System.out.println("\nExchange books 0 and 3.");
        Book book1 = books[0];
        books[0] = books[3];
        books[3] = book1;
        for (Book book: books) {
            System.out.println(book.getName() + " - " + book.getListCount());
        }

        System.out.println("\nShow only this book: Clean code");
        for (Book book: books) {
            if (book.getName().equals("Clean code")) {
                System.out.println(book.getName() + " - " + book.getListCount());
            }
        }
    }
}
