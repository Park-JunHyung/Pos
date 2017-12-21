package team15.pos.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import team15.pos.dto.Employee;
import team15.pos.dto.Manager;
import team15.pos.dto.Member;

/**
 * Created by JSH on 2017-12-20.
 */

public class EmployeeAuth
{

    private Context context;

    ArrayList<Employee> employeeArrayList;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ArrayList<Manager> managerArrayList;
    public EmployeeAuth(Context context)
    {
        this.context = context;
    }

    public boolean employeeAuth(String id, String password)
    {
        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = preferences.edit();
        Gson gson = new Gson();
        String json;
        json = preferences.getString("EmployeeList", "");
        employeeArrayList = gson.fromJson(json, new TypeToken<List<Employee>>()
        {
        }.getType());
        if (null == employeeArrayList)
        {
            employeeArrayList = new ArrayList<>();
            employeeArrayList.add(new Employee("test", "test", "test"));
        }

        for (Employee employee : employeeArrayList)
        {
            if (employee.getEmployeeId().equals(id) && employee.getEmployeePassword().equals(password))
            {
                return true;
            }
        }
        return false;
    }

    public boolean managerAuth(String id, String password)
    {
        preferences = context.getSharedPreferences("data", Context.MODE_PRIVATE);
        editor = preferences.edit();
        Gson gson = new Gson();
        String json;
        json = preferences.getString("ManagerList", "");
        managerArrayList = gson.fromJson(json, new TypeToken<List<Manager>>()
        {
        }.getType());
        if (null == managerArrayList)
        {
            managerArrayList = new ArrayList<>();
            managerArrayList.add(new Manager("test", "test", "test",
                    "root", "test", "test"));

        }

        for (Manager manager : managerArrayList)
        {
            if (manager.getManagerId().equals(id) && manager.getManagerPassword().equals(password))
            {
                return true;
            }
        }
        return false;
    }

}
