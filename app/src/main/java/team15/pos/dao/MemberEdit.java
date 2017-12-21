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

public class MemberEdit {

    ArrayList<Member> getMemberList;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    public MemberEdit(Context context)
    {
        this.context = context;
    }
    public boolean memberEdit(Member oldMember, Member newMember){
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

        for (int i = 0;i<getMemberList.size();i++)
        {
            Member member = getMemberList.get(i);
            if (member.getMemberName().equals(oldMember.getMemberName())
                    &&member.getMemberPhoneNumber().equals(oldMember.getMemberPhoneNumber())){
                getMemberList.remove(i);
                getMemberList.add(i,newMember);
                json = gson.toJson(getMemberList);
                editor.remove("MemberList").commit();
                editor.putString("MemberList", json);

                editor.commit();
                return true;
            }
        }
        return false;
    }

}
