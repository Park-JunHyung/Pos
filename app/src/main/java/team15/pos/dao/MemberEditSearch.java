package team15.pos.dao;

import team15.pos.dto.Member;

/**
 * Created by JSH on 2017-12-20.
 */

public class MemberEditSearch {

    public Member search(String phone){
        Member member = new Member("홍길동", "010-1234-5678");
        member.setMemberPoint(1000);
        return member;
    }
}
