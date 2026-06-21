package controller;

import model.Member;
import repository.MemberRepository;
import java.util.List;

public class MemberController {
    private MemberRepository memberRepository;

    public MemberController() {
        this.memberRepository = new MemberRepository();
    }

    public void registerNewMember(String firstName, String lastName, String phoneNumber) {
        if (firstName == null || firstName.trim().isEmpty()) {
            System.out.println("⚠️ Validation Error: First name cannot be empty!");
            return;
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            System.out.println("⚠️ Validation Error: Last name cannot be empty!");
            return;
        }
        Member member = new Member(0, firstName, lastName, phoneNumber);
        memberRepository.addMember(member);
    }

    // NEW METHOD: List all library members
    public void listAllMembers() {
        List<Member> members = memberRepository.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("📭 No members found in the library database.");
        } else {
            System.out.println("\n👤 --- REGISTERED MEMBERS LIST ---");
            for (Member m : members) {
                System.out.println(" -> ID: " + m.getMemberId() + " | Name: " + m.getFirstName() + " " + m.getLastName() + " | Phone: " + m.getPhoneNumber());
            }
        }
    }
}