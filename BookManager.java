import java.util.*;

public class BookManager {
    private final List<Book> books = new ArrayList<>();

    public BookManager() {
        load();
    }

    private void load() {
        for (String line : FileManager.read("books.txt")) {
            Book book = Book.fromFile(line);
            if (book != null) books.add(book);
        }
    }

    private void save() {
        List<String> lines = new ArrayList<>();
        for (Book book : books) lines.add(book.toFile());
        FileManager.write("books.txt", lines);
    }

    public Book find(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    public boolean exists(int id) {
        return find(id) != null;
    }

    public int count() {
        return books.size();
    }

    public void add() {
        int id = InputHelper.positiveInt("Book ID: ");
        if (exists(id)) {
            System.out.println("Book ID already exists.");
            return;
        }

        String isbn = InputHelper.required("ISBN: ");
        String title = InputHelper.required("Title: ");
        String author = InputHelper.required("Author: ");
        String category = InputHelper.required("Category: ");
        String publisher = InputHelper.required("Publisher: ");
        int year = InputHelper.positiveInt("Publication Year: ");
        int quantity = InputHelper.positiveInt("Quantity: ");

        books.add(new Book(id, isbn, title, author, category, publisher,
                           year, quantity, quantity));
        save();
        System.out.println("Book added successfully.");
    }

    public void display() {
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        for (Book book : books) {
            System.out.println("--------------------------------");
            book.display();
        }
    }

    public void search() {
        int id = InputHelper.positiveInt("Book ID: ");
        Book book = find(id);
        if (book == null) {
            System.out.println("Book not found.");
        } else {
            book.display();
        }
    }

    public void searchByTitle() {
        String key = InputHelper.required("Enter title keyword: ").toLowerCase();
        boolean found = false;

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(key)) {
                System.out.println(book.getId() + " | " + book.getTitle() +
                                   " | " + book.getAuthor() +
                                   " | Available: " + book.getAvailable());
                found = true;
            }
        }

        if (!found) System.out.println("No matching books.");
    }

    public void update() {
        Book book = find(InputHelper.positiveInt("Book ID: "));
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        int oldQuantity = book.getQuantity();

        book.setIsbn(InputHelper.required("ISBN: "));
        book.setTitle(InputHelper.required("Title: "));
        book.setAuthor(InputHelper.required("Author: "));
        book.setCategory(InputHelper.required("Category: "));
        book.setPublisher(InputHelper.required("Publisher: "));
        book.setYear(InputHelper.positiveInt("Publication Year: "));
        int newQuantity = InputHelper.positiveInt("Quantity: ");

        int issued = oldQuantity - book.getAvailable();
        if (newQuantity < issued) {
            System.out.println("Quantity cannot be less than currently issued copies.");
            return;
        }

        book.setQuantity(newQuantity);
        book.setAvailable(newQuantity - issued);
        save();
        System.out.println("Book updated.");
    }

    public void delete() {
        Book book = find(InputHelper.positiveInt("Book ID: "));
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (book.getAvailable() != book.getQuantity()) {
            System.out.println("Cannot delete a book while copies are issued.");
            return;
        }

        books.remove(book);
        save();
        System.out.println("Book deleted.");
    }

    public void changeAvailable(int bookId, int amount) {
        Book book = find(bookId);
        if (book != null) {
            book.setAvailable(book.getAvailable() + amount);
            save();
        }
    }

    public void categoryReport() {
        String category = InputHelper.required("Category: ");
        boolean found = false;

        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                System.out.println(book.getId() + " | " + book.getTitle() +
                                   " | " + book.getAuthor());
                found = true;
            }
        }

        if (!found) System.out.println("No books in this category.");
    }
}