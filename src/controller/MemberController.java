package controller;

import model.Member;
import repository.MemberRepository;
import java.util.Date;

public class MemberController {
    private MemberRepository memberRepository;

    public MemberController() {
        this.memberRepository = new MemberRepository();
    }

    // Business Logic: Validate member details before registration
    public void registerNewMember(String firstName, String lastName, String phone) {
        // Validation: First name and Last name must not be empty
        if (firstName == null || firstName.trim().isEmpty()) {
            System.out.println("⚠️ Validation Error: First name cannot be empty!");
            return;
        }
        if (lastName == null || lastName.trim().isEmpty()) {
            System.out.println("⚠️ Validation Error: Last name cannot be empty!");
            return;
        }
        // Validation: Phone number must have a valid length (e.g., at least 10 digits)
        if (phone == null || phone.trim().length() < 10) {
            System.out.println("⚠️ Validation Error: Invalid phone number! Must be at least 10 digits.");
            return;
        }

        // If validation passes, create a Member object with the current registration date
        Member member = new Member(0, firstName, lastName, phone, new Date());

        // Pass to repository to save in PostgreSQL
        memberRepository.addMember(member);
    }
}