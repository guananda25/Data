import java.util.*;

public class MemberManager {
    private final List<Member> members = new ArrayList<>();

    public MemberManager() {
        load();
    }

    private void load() {
        for (String line : FileManager.read("members.txt")) {
            Member member = Member.fromFile(line);
            if (member != null) members.add(member);
        }
    }

    private void save() {
        List<String> lines = new ArrayList<>();
        for (Member member : members) lines.add(member.toFile());
        FileManager.write("members.txt", lines);
    }

    public Member find(int id) {
        for (Member member : members) {
            if (member.getId() == id) return member;
        }
        return null;
    }

    public boolean exists(int id) {
        return find(id) != null;
    }

    public int count() {
        return members.size();
    }

    public void add() {
        int id = InputHelper.positiveInt("Member ID: ");
        if (exists(id)) {
            System.out.println("Member ID already exists.");
            return;
        }

        String name = InputHelper.required("Name: ");
        String type = InputHelper.required("Type (Student/Teacher/Other): ");
        String phone = InputHelper.required("Phone: ");
        String email = InputHelper.required("Email: ");
        String address = InputHelper.required("Address: ");
        String joinDate = InputHelper.required("Join Date: ");

        members.add(new Member(id, name, type, phone, email, address,
                               joinDate, true));
        save();
        System.out.println("Member added.");
    }

    public void display() {
        if (members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }

        for (Member member : members) {
            System.out.println("--------------------------------");
            member.display();
        }
    }

    public void search() {
        Member member = find(InputHelper.positiveInt("Member ID: "));
        if (member == null) System.out.println("Member not found.");
        else member.display();
    }

    public void searchByName() {
        String key = InputHelper.required("Name keyword: ").toLowerCase();
        boolean found = false;

        for (Member member : members) {
            if (member.getName().toLowerCase().contains(key)) {
                System.out.println(member.getId() + " | " + member.getName() +
                                   " | " + member.getType() +
                                   " | " + (member.isActive() ? "Active" : "Inactive"));
                found = true;
            }
        }

        if (!found) System.out.println("No matching members.");
    }

    public void update() {
        Member member = find(InputHelper.positiveInt("Member ID: "));
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        member.setName(InputHelper.required("Name: "));
        member.setType(InputHelper.required("Type: "));
        member.setPhone(InputHelper.required("Phone: "));
        member.setEmail(InputHelper.required("Email: "));
        member.setAddress(InputHelper.required("Address: "));
        member.setJoinDate(InputHelper.required("Join Date: "));
        save();
        System.out.println("Member updated.");
    }

    public void deactivate() {
        Member member = find(InputHelper.positiveInt("Member ID: "));
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        member.setActive(false);
        save();
        System.out.println("Member deactivated.");
    }

    public void activate() {
        Member member = find(InputHelper.positiveInt("Member ID: "));
        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        member.setActive(true);
        save();
        System.out.println("Member activated.");
    }
}