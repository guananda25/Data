public class Issue {
    private int id;
    private int bookId;
    private int memberId;
    private String issueDate;
    private String dueDate;
    private String returnDate;
    private double fine;
    private String status;

    public Issue(int id, int bookId, int memberId, String issueDate,
                 String dueDate, String returnDate, double fine, String status) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.fine = fine;
        this.status = status;
    }

    public int getId() { return id; }
    public int getBookId() { return bookId; }
    public int getMemberId() { return memberId; }
    public String getIssueDate() { return issueDate; }
    public String getDueDate() { return dueDate; }
    public String getReturnDate() { return returnDate; }
    public double getFine() { return fine; }
    public String getStatus() { return status; }

    public void setBookId(int value) { bookId = value; }
    public void setMemberId(int value) { memberId = value; }
    public void setIssueDate(String value) { issueDate = value; }
    public void setDueDate(String value) { dueDate = value; }
    public void setReturnDate(String value) { returnDate = value; }
    public void setFine(double value) { fine = value; }
    public void setStatus(String value) { status = value; }

    public boolean isActive() {
        return status.equalsIgnoreCase("Issued");
    }

    private String clean(String value) {
        return value.replace("|", "/");
    }

    public String toFile() {
        return id + "|" + bookId + "|" + memberId + "|" + clean(issueDate) + "|" +
               clean(dueDate) + "|" + clean(returnDate) + "|" + fine + "|" + clean(status);
    }

    public static Issue fromFile(String line) {
        String[] p = line.split("\\|", -1);
        if (p.length != 8) return null;
        try {
            return new Issue(Integer.parseInt(p[0]), Integer.parseInt(p[1]),
                Integer.parseInt(p[2]), p[3], p[4], p[5],
                Double.parseDouble(p[6]), p[7]);
        } catch (Exception e) {
            return null;
        }
    }
}