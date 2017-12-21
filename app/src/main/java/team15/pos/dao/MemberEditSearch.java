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

public class MemberEditSearch {

    ArrayList<Member> getMemberList;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    public MemberEditSearch(Context context)
    {
        this.context = context;
    }

    public ArrayList<Member> search(String phone){

        ArrayList<Member> members = new ArrayList<>();
        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = preferences.edit();
        Gson gson = new Gson();
        String json;
        json = preferences.getString("MemberList", "");
        getMemberList = gson.fromJson(json, new TypeToken<List<Member>>() {}.getType());
        if (null == getMemberList)
        {
            getMemberList = new ArrayList<>();
        }

        for (Member member : getMemberList)
        {
            if (member.getMemberPhoneNumber().contains(phone)){
                members.add(member);
            }
        }

        return members;
    }


}
