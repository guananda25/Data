import java.util.*;

public class IssueManager {
    private final List<Issue> issues = new ArrayList<>();
    private final BookManager books;
    private final MemberManager members;

    public IssueManager(BookManager books, MemberManager members) {
        this.books = books;
        this.members = members;
        load();
    }

    private void load() {
        for (String line : FileManager.read("issues.txt")) {
            Issue issue = Issue.fromFile(line);
            if (issue != null) issues.add(issue);
        }
    }

    private void save() {
        List<String> lines = new ArrayList<>();
        for (Issue issue : issues) lines.add(issue.toFile());
        FileManager.write("issues.txt", lines);
    }

    private Issue find(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) return issue;
        }
        return null;
    }

    public int count() {
        return issues.size();
    }

    public int activeCount() {
        int count = 0;
        for (Issue issue : issues) if (issue.isActive()) count++;
        return count;
    }

    public void issueBook() {
        int id = InputHelper.positiveInt("Issue ID: ");
        if (find(id) != null) {
            System.out.println("Issue ID already exists.");
            return;
        }

        int bookId = InputHelper.positiveInt("Book ID: ");
        Book book = books.find(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        if (!book.isAvailable()) {
            System.out.println("No copy is currently available.");
            return;
        }

        int memberId = InputHelper.positiveInt("Member ID: ");
        Member member = members.find(memberId);
        if (member == null || !member.isActive()) {
            System.out.println("Member not found or inactive.");
            return;
        }

        String issueDate = InputHelper.required("Issue Date: ");
        String dueDate = InputHelper.required("Due Date: ");

        Issue issue = new Issue(id, bookId, memberId, issueDate,
                                dueDate, "-", 0.0, "Issued");
        issues.add(issue);
        books.changeAvailable(bookId, -1);
        save();

        System.out.println("Book issued successfully.");
    }

    public void returnBook() {
        int id = InputHelper.positiveInt("Issue ID: ");
        Issue issue = find(id);

        if (issue == null) {
            System.out.println("Issue record not found.");
            return;
        }

        if (!issue.isActive()) {
            System.out.println("This book has already been returned.");
            return;
        }

        String returnDate = InputHelper.required("Return Date: ");
        double fine = InputHelper.readDouble("Fine amount: ");

        if (fine < 0) {
            System.out.println("Fine cannot be negative.");
            return;
        }

        issue.setReturnDate(returnDate);
        issue.setFine(fine);
        issue.setStatus("Returned");

        books.changeAvailable(issue.getBookId(), 1);
        save();

        System.out.println("Book returned successfully.");
        System.out.println("Fine: " + fine);
    }

    public void displayAll() {
        if (issues.isEmpty()) {
            System.out.println("No issue records.");
            return;
        }

        for (Issue issue : issues) {
            Book book = books.find(issue.getBookId());
            Member member = members.find(issue.getMemberId());

            System.out.println(
                issue.getId() + " | Book: " +
                (book == null ? "Unknown" : book.getTitle()) +
                " | Member: " +
                (member == null ? "Unknown" : member.getName()) +
                " | Issue: " + issue.getIssueDate() +
                " | Due: " + issue.getDueDate() +
                " | Return: " + issue.getReturnDate() +
                " | Fine: " + issue.getFine() +
                " | " + issue.getStatus()
            );
        }
    }

    public void activeLoans() {
        boolean found = false;

        for (Issue issue : issues) {
            if (issue.isActive()) {
                Book book = books.find(issue.getBookId());
                Member member = members.find(issue.getMemberId());

                System.out.println(issue.getId() + " | " +
                    (book == null ? "Unknown" : book.getTitle()) +
                    " | " +
                    (member == null ? "Unknown" : member.getName()) +
                    " | Due: " + issue.getDueDate());
                found = true;
            }
        }

        if (!found) System.out.println("No active loans.");
    }

    public void memberHistory() {
        int memberId = InputHelper.positiveInt("Member ID: ");
        boolean found = false;

        for (Issue issue : issues) {
            if (issue.getMemberId() == memberId) {
                Book book = books.find(issue.getBookId());
                System.out.println(issue.getId() + " | " +
                    (book == null ? "Unknown" : book.getTitle()) +
                    " | " + issue.getIssueDate() +
                    " | " + issue.getStatus() +
                    " | Fine: " + issue.getFine());
                found = true;
            }
        }

        if (!found) System.out.println("No borrowing history.");
    }

    public void bookHistory() {
        int bookId = InputHelper.positiveInt("Book ID: ");
        boolean found = false;

        for (Issue issue : issues) {
            if (issue.getBookId() == bookId) {
                Member member = members.find(issue.getMemberId());
                System.out.println(issue.getId() + " | " +
                    (member == null ? "Unknown" : member.getName()) +
                    " | " + issue.getIssueDate() +
                    " | " + issue.getStatus());
                found = true;
            }
        }

        if (!found) System.out.println("No issue history.");
    }
}