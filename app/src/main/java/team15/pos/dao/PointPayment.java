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

public class PointPayment {

    public void setTypeAndPayment(int getPayment, Context context){


        SharedPreferences preferences;
        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json;
        json = preferences.getString("authMember", "");
        Member oldMember=gson.fromJson(json, new TypeToken<Member>() {}.getType());
        Member newMember=oldMember;
        newMember.setMemberPoint(oldMember.getMemberPoint()-getPayment);
        if (oldMember.getMemberPoint()<=10000){
            Toast.makeText(context,"10000포인트 이상부터 사용가능합니다.",Toast.LENGTH_SHORT).show();
        }else {
            if(new MemberEdit(context).memberEdit(oldMember,newMember)){
                new PaymentDAO().createPayment(getPayment,"포인트",context);
                Toast.makeText(context,"포인트 결제가 완료되었습니다.",Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(context,"포인트 결제실패",Toast.LENGTH_SHORT).show();
        }
    }
}
