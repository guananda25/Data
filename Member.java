public class Member {
    private int id;
    private String name;
    private String type;
    private String phone;
    private String email;
    private String address;
    private String joinDate;
    private boolean active;

    public Member(int id, String name, String type, String phone, String email,
                  String address, String joinDate, boolean active) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.joinDate = joinDate;
        this.active = active;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public boolean isActive() { return active; }

    public void setName(String value) { name = value; }
    public void setType(String value) { type = value; }
    public void setPhone(String value) { phone = value; }
    public void setEmail(String value) { email = value; }
    public void setAddress(String value) { address = value; }
    public void setJoinDate(String value) { joinDate = value; }
    public void setActive(boolean value) { active = value; }

    private String clean(String value) {
        return value.replace("|", "/");
    }

    public String toFile() {
        return id + "|" + clean(name) + "|" + clean(type) + "|" + clean(phone) +
               "|" + clean(email) + "|" + clean(address) + "|" + clean(joinDate) + "|" + active;
    }

    public static Member fromFile(String line) {
        String[] p = line.split("\\|", -1);
        if (p.length != 8) return null;
        try {
            return new Member(Integer.parseInt(p[0]), p[1], p[2], p[3], p[4],
                              p[5], p[6], Boolean.parseBoolean(p[7]));
        } catch (Exception e) {
            return null;
        }
    }

    public void display() {
        System.out.println("Member ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
        System.out.println("Join Date: " + joinDate);
        System.out.println("Status: " + (active ? "Active" : "Inactive"));
    }
}