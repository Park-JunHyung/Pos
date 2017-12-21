package team15.pos.dao;

import java.util.ArrayList;

import team15.pos.dto.Member;

/**
 * Created by JSH on 2017-12-20.
 */

public class MemberDelete
{

    private ArrayList<Member> members;

    public MemberDelete()
    {
        members = new ArrayList<Member>();
        Member member = new Member("홍길동", "010-1234-5678");
        member.setMemberPoint(1000);
        members.add(member);
    }

    public Member search(String phone)
    {
        for (Member m:members)
        {
            if (m.getMemberPhoneNumber().equals(phone)){
                return m;
            }
        }
        return null;
    }

    public boolean delete(ArrayList<Member> members)
    {
        return true;
    }
}
