public class Main {
    private static BookManager books;
    private static MemberManager members;
    private static IssueManager issues;
    private static ReservationManager reservations;
    private static Report reports;

    public static void main(String[] args) {
        FileManager.initialize();

        books = new BookManager();
        members = new MemberManager();
        issues = new IssueManager(books, members);
        reservations = new ReservationManager(books, members);
        reports = new Report(books, members, issues, reservations);

        mainMenu();
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("\n========================================");
            System.out.println("       LIBRARY MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Book Management");
            System.out.println("2. Member Management");
            System.out.println("3. Issue / Return Management");
            System.out.println("4. Reservation Management");
            System.out.println("5. Reports");
            System.out.println("6. Dashboard");
            System.out.println("0. Exit");
            System.out.println("========================================");

            int choice = InputHelper.readInt("Choose: ");

            switch (choice) {
                case 1 -> bookMenu();
                case 2 -> memberMenu();
                case 3 -> issueMenu();
                case 4 -> reservationMenu();
                case 5 -> reportMenu();
                case 6 -> reports.dashboard();
                case 0 -> {
                    System.out.println("Thank you for using the Library Management System.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void bookMenu() {
        while (true) {
            System.out.println("\n--- BOOK MANAGEMENT ---");
            System.out.println("1. Add Book");
            System.out.println("2. Display Books");
            System.out.println("3. Search by ID");
            System.out.println("4. Search by Title");
            System.out.println("5. Update Book");
            System.out.println("6. Delete Book");
            System.out.println("7. Search by Category");
            System.out.println("0. Back");

            int choice = InputHelper.readInt("Choose: ");

            switch (choice) {
                case 1 -> books.add();
                case 2 -> books.display();
                case 3 -> books.search();
                case 4 -> books.searchByTitle();
                case 5 -> books.update();
                case 6 -> books.delete();
                case 7 -> books.categoryReport();
                case 0 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void memberMenu() {
        while (true) {
            System.out.println("\n--- MEMBER MANAGEMENT ---");
            System.out.println("1. Add Member");
            System.out.println("2. Display Members");
            System.out.println("3. Search by ID");
            System.out.println("4. Search by Name");
            System.out.println("5. Update Member");
            System.out.println("6. Deactivate Member");
            System.out.println("7. Activate Member");
            System.out.println("0. Back");

            int choice = InputHelper.readInt("Choose: ");

            switch (choice) {
                case 1 -> members.add();
                case 2 -> members.display();
                case 3 -> members.search();
                case 4 -> members.searchByName();
                case 5 -> members.update();
                case 6 -> members.deactivate();
                case 7 -> members.activate();
                case 0 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void issueMenu() {
        while (true) {
            System.out.println("\n--- ISSUE / RETURN MANAGEMENT ---");
            System.out.println("1. Issue Book");
            System.out.println("2. Return Book");
            System.out.println("3. Display All Issue Records");
            System.out.println("4. Display Active Loans");
            System.out.println("5. Member Borrowing History");
            System.out.println("6. Book Borrowing History");
            System.out.println("0. Back");

            int choice = InputHelper.readInt("Choose: ");

            switch (choice) {
                case 1 -> issues.issueBook();
                case 2 -> issues.returnBook();
                case 3 -> issues.displayAll();
                case 4 -> issues.activeLoans();
                case 5 -> issues.memberHistory();
                case 6 -> issues.bookHistory();
                case 0 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void reservationMenu() {
        while (true) {
            System.out.println("\n--- RESERVATION MANAGEMENT ---");
            System.out.println("1. Create Reservation");
            System.out.println("2. Display Reservations");
            System.out.println("3. Update Reservation Status");
            System.out.println("4. Delete Reservation");
            System.out.println("0. Back");

            int choice = InputHelper.readInt("Choose: ");

            switch (choice) {
                case 1 -> reservations.add();
                case 2 -> reservations.display();
                case 3 -> reservations.updateStatus();
                case 4 -> reservations.delete();
                case 0 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void reportMenu() {
        while (true) {
            System.out.println("\n--- REPORTS ---");
            System.out.println("1. Book Report");
            System.out.println("2. Member Report");
            System.out.println("3. Loan Report");
            System.out.println("4. Active Loan Report");
            System.out.println("5. Category Report");
            System.out.println("6. Member History");
            System.out.println("7. Book History");
            System.out.println("0. Back");

            int choice = InputHelper.readInt("Choose: ");

            switch (choice) {
                case 1 -> reports.bookReport();
                case 2 -> reports.memberReport();
                case 3 -> reports.loanReport();
                case 4 -> reports.activeLoanReport();
                case 5 -> reports.categoryReport();
                case 6 -> reports.memberHistory();
                case 7 -> reports.bookHistory();
                case 0 -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}