package team15.pos.dto;

/**
 * Created by Schwa on 2017-12-20.
 */

public class Member extends Customer {

    private String memberName;
    private String memberPhoneNumber;
    private int memberPoint;

    public Member(String customerName, String customerPhoneNumber) {
        super(true);
        this.memberName = customerName;
        this.memberPhoneNumber = customerPhoneNumber;
        this.memberPoint =0;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberPhoneNumber() {
        return memberPhoneNumber;
    }

    public void setMemberPhoneNumber(String memberPhoneNumber) {
        this.memberPhoneNumber = memberPhoneNumber;
    }

    public int getMemberPoint() {
        return memberPoint;
    }

    public void setMemberPoint(int memberPoint) {
        this.memberPoint = memberPoint;
    }
}
