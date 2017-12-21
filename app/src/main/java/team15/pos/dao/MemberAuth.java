package team15.pos.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import team15.pos.dto.Member;

/**
 * Created by JSH on 2017-12-20.
 */

public class MemberAuth {

    ArrayList<Member> getMemberList;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    public Member memberAuthDone(String phoneNumber,Context context){
        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = preferences.edit();
        Gson gson = new Gson();
        String json;
        json = preferences.getString("MemberList", "");
        getMemberList = gson.fromJson(json, new TypeToken<List<Member>>() {}.getType());
        Member targetMember=null;
        for (Member member:getMemberList){
            if (member.getMemberPhoneNumber().equals(phoneNumber)){
                targetMember=member;
            }
        }

        if (targetMember!=null){
            json = gson.toJson(targetMember);
            editor.remove("authMember").commit();
            editor.putString("authMember", json);
            editor.commit();
        }
        return targetMember;
    }

}
