package model;

import java.util.Date;

public class Member {
    private int memberId;
    private String firstName;
    private String lastName;
    private String phone;
    private Date joinDate;

    public Member(int memberId, String firstName, String lastName, String phone, Date joinDate) {
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.joinDate = joinDate;
    }

    // Getters and Setters
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Date getJoinDate() { return joinDate; }
    public void setJoinDate(Date joinDate) { this.joinDate = joinDate; }
}