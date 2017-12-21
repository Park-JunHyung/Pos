package team15.pos.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import team15.pos.dto.Member;
import team15.pos.dto.Product;

/**
 * Created by JSH on 2017-12-20.
 */

public class MemberSignUp
{

    ArrayList<Member> getMemberList;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    public MemberSignUp(Context context)
    {
        this.context = context;
    }

    public boolean checkDuplicationFunc(String name, String phone)
    {

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
            if (member.getMemberName().equals(name)&&member.getMemberPhoneNumber().equals(phone)){
                return false;
            }
        }

        return true;
    }

    public boolean addMember(String name, String phone)
    {
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
        getMemberList.add(new Member(name, phone));
        json = gson.toJson(getMemberList);
        editor.remove("MemberList").commit();
        editor.putString("MemberList", json);

        editor.commit();

        return true;
    }

}
