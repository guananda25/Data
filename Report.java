public class Report {
    private final BookManager books;
    private final MemberManager members;
    private final IssueManager issues;
    private final ReservationManager reservations;

    public Report(BookManager books, MemberManager members,
                  IssueManager issues, ReservationManager reservations) {
        this.books = books;
        this.members = members;
        this.issues = issues;
        this.reservations = reservations;
    }

    public void dashboard() {
        System.out.println("\n========== LIBRARY DASHBOARD ==========");
        System.out.println("Total Books        : " + books.count());
        System.out.println("Total Members      : " + members.count());
        System.out.println("Issue Records      : " + issues.count());
        System.out.println("Active Loans       : " + issues.activeCount());
        System.out.println("Reservations       : " + reservations.count());
        System.out.println("========================================");
    }

    public void bookReport() {
        System.out.println("\n========== BOOK REPORT ==========");
        books.display();
    }

    public void memberReport() {
        System.out.println("\n========== MEMBER REPORT ==========");
        members.display();
    }

    public void loanReport() {
        System.out.println("\n========== LOAN REPORT ==========");
        issues.displayAll();
    }

    public void activeLoanReport() {
        System.out.println("\n========== ACTIVE LOANS ==========");
        issues.activeLoans();
    }

    public void categoryReport() {
        System.out.println("\n========== CATEGORY REPORT ==========");
        books.categoryReport();
    }

    public void memberHistory() {
        System.out.println("\n========== MEMBER BORROWING HISTORY ==========");
        issues.memberHistory();
    }

    public void bookHistory() {
        System.out.println("\n========== BOOK BORROWING HISTORY ==========");
        issues.bookHistory();
    }
}