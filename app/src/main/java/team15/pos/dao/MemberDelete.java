package team15.pos.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import team15.pos.CustomerDeleteActivity;
import team15.pos.dto.Member;

/**
 * Created by JSH on 2017-12-20.
 */

public class MemberDelete
{
    ArrayList<Member> getMemberList;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    public MemberDelete(Context context)
    {
        this.context = context;
    }

    public Member search(String phone)
    {
        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = preferences.edit();
        Gson gson = new Gson();
        String json;
        json = preferences.getString("MemberList", "");
        getMemberList = gson.fromJson(json, new TypeToken<List<Member>>()
        {
        }.getType());
        if (null == getMemberList)
        {
            getMemberList = new ArrayList<>();
        }

        for (Member member : getMemberList)
        {
            if (member.getMemberPhoneNumber().equals(phone)){
                return member;
            }
        }
        return null;
    }

    public boolean delete()
    {
        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = preferences.edit();
        Gson gson = new Gson();
        String json;
        json = preferences.getString("Temp", "");
        ArrayList<Member> del = gson.fromJson(json, new TypeToken<List<Member>>() {}.getType());
        if (null == del)
        {
            del = new ArrayList<>();
        }


        json = preferences.getString("MemberList", "");
        getMemberList = gson.fromJson(json, new TypeToken<List<Member>>() {}.getType());
        if (null == getMemberList)
        {
            getMemberList = new ArrayList<>();
        }

        getMemberList.removeAll(del);

        json = gson.toJson(getMemberList);
        editor.remove("MemberList").commit();
        editor.putString("MemberList", json);

        editor.commit();
        return true;
    }
}
