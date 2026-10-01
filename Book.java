public class Book {
    private int id;
    private String isbn;
    private String title;
    private String author;
    private String category;
    private String publisher;
    private int year;
    private int quantity;
    private int available;

    public Book(int id, String isbn, String title, String author, String category,
                String publisher, int year, int quantity, int available) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.category = category;
        this.publisher = publisher;
        this.year = year;
        this.quantity = quantity;
        this.available = available;
    }

    public int getId() { return id; }
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public int getYear() { return year; }
    public int getQuantity() { return quantity; }
    public int getAvailable() { return available; }

    public void setIsbn(String value) { isbn = value; }
    public void setTitle(String value) { title = value; }
    public void setAuthor(String value) { author = value; }
    public void setCategory(String value) { category = value; }
    public void setPublisher(String value) { publisher = value; }
    public void setYear(int value) { year = value; }
    public void setQuantity(int value) { quantity = value; }
    public void setAvailable(int value) { available = value; }

    public String getPublisher() { return publisher; }

    public boolean isAvailable() {
        return available > 0;
    }

    public String toFile() {
        return id + "|" + clean(isbn) + "|" + clean(title) + "|" + clean(author) +
               "|" + clean(category) + "|" + clean(publisher) + "|" + year + "|" +
               quantity + "|" + available;
    }

    private String clean(String value) {
        return value.replace("|", "/");
    }

    public static Book fromFile(String line) {
        String[] p = line.split("\\|", -1);
        if (p.length != 9) return null;
        try {
            return new Book(
                Integer.parseInt(p[0]), p[1], p[2], p[3], p[4], p[5],
                Integer.parseInt(p[6]), Integer.parseInt(p[7]), Integer.parseInt(p[8])
            );
        } catch (Exception e) {
            return null;
        }
    }

    public void display() {
        System.out.println("Book ID: " + id);
        System.out.println("ISBN: " + isbn);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Category: " + category);
        System.out.println("Publisher: " + publisher);
        System.out.println("Year: " + year);
        System.out.println("Quantity: " + quantity);
        System.out.println("Available: " + available);
    }
}