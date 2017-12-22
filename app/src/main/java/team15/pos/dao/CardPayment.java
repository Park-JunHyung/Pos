package team15.pos.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import team15.pos.dto.Member;

/**
 * Created by JSH on 2017-12-20.
 */

public class CardPayment {

    public void setTypeAndPayment(int getPayment, Context context){


        SharedPreferences preferences;
        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json;
        json = preferences.getString("authMember", "");
        Member oldMember=gson.fromJson(json, new TypeToken<Member>() {}.getType());
        Member newMember=oldMember;
        newMember.setMemberPoint(oldMember.getMemberPoint()+(getPayment/100));
        if(new MemberEdit(context).memberEdit(oldMember,newMember)){
            new PaymentDAO().createPayment(getPayment,"카드",context);
            Toast.makeText(context,String.valueOf(getPayment/100) + "포인트 적립이 완료되었습니다.",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(context,"포인트 적립실패",Toast.LENGTH_SHORT).show();
    }
}
