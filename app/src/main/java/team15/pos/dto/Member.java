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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        if (memberPoint != member.memberPoint) return false;
        if (memberName != null ? !memberName.equals(member.memberName) : member.memberName != null)
            return false;
        return memberPhoneNumber != null ? memberPhoneNumber.equals(member.memberPhoneNumber) : member.memberPhoneNumber == null;
    }

    @Override
    public int hashCode()
    {
        int result = memberName != null ? memberName.hashCode() : 0;
        result = 31 * result + (memberPhoneNumber != null ? memberPhoneNumber.hashCode() : 0);
        result = 31 * result + memberPoint;
        return result;
    }
}
