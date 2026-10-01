public class Reservation {
    private int id;
    private int bookId;
    private int memberId;
    private String date;
    private String status;

    public Reservation(int id, int bookId, int memberId, String date, String status) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.date = date;
        this.status = status;
    }

    public int getId() { return id; }
    public int getBookId() { return bookId; }
    public int getMemberId() { return memberId; }
    public String getDate() { return date; }
    public String getStatus() { return status; }

    public void setBookId(int value) { bookId = value; }
    public void setMemberId(int value) { memberId = value; }
    public void setDate(String value) { date = value; }
    public void setStatus(String value) { status = value; }

    public String toFile() {
        return id + "|" + bookId + "|" + memberId + "|" +
               date.replace("|", "/") + "|" + status.replace("|", "/");
    }

    public static Reservation fromFile(String line) {
        String[] p = line.split("\\|", -1);
        if (p.length != 5) return null;
        try {
            return new Reservation(Integer.parseInt(p[0]), Integer.parseInt(p[1]),
                                   Integer.parseInt(p[2]), p[3], p[4]);
        } catch (Exception e) {
            return null;
        }
    }
}