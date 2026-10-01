import java.util.*;

public class ReservationManager {
    private final List<Reservation> reservations = new ArrayList<>();
    private final BookManager books;
    private final MemberManager members;

    public ReservationManager(BookManager books, MemberManager members) {
        this.books = books;
        this.members = members;
        load();
    }

    private void load() {
        for (String line : FileManager.read("reservations.txt")) {
            Reservation reservation = Reservation.fromFile(line);
            if (reservation != null) reservations.add(reservation);
        }
    }

    private void save() {
        List<String> lines = new ArrayList<>();
        for (Reservation r : reservations) lines.add(r.toFile());
        FileManager.write("reservations.txt", lines);
    }

    private Reservation find(int id) {
        for (Reservation r : reservations) {
            if (r.getId() == id) return r;
        }
        return null;
    }

    public int count() {
        return reservations.size();
    }

    public void add() {
        int id = InputHelper.positiveInt("Reservation ID: ");
        if (find(id) != null) {
            System.out.println("Reservation ID exists.");
            return;
        }

        int bookId = InputHelper.positiveInt("Book ID: ");
        if (!books.exists(bookId)) {
            System.out.println("Book not found.");
            return;
        }

        int memberId = InputHelper.positiveInt("Member ID: ");
        Member member = members.find(memberId);

        if (member == null || !member.isActive()) {
            System.out.println("Member not found or inactive.");
            return;
        }

        String date = InputHelper.required("Reservation Date: ");

        reservations.add(new Reservation(id, bookId, memberId, date, "Pending"));
        save();
        System.out.println("Reservation created.");
    }

    public void display() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations.");
            return;
        }

        for (Reservation r : reservations) {
            Book book = books.find(r.getBookId());
            Member member = members.find(r.getMemberId());

            System.out.println(r.getId() + " | " +
                (book == null ? "Unknown" : book.getTitle()) +
                " | " +
                (member == null ? "Unknown" : member.getName()) +
                " | " + r.getDate() + " | " + r.getStatus());
        }
    }

    public void updateStatus() {
        Reservation r = find(InputHelper.positiveInt("Reservation ID: "));

        if (r == null) {
            System.out.println("Reservation not found.");
            return;
        }

        r.setStatus(InputHelper.required("New status: "));
        save();
        System.out.println("Reservation updated.");
    }

    public void delete() {
        Reservation r = find(InputHelper.positiveInt("Reservation ID: "));

        if (r == null) {
            System.out.println("Reservation not found.");
            return;
        }

        reservations.remove(r);
        save();
        System.out.println("Reservation deleted.");
    }
}