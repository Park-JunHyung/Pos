package team15.pos.dto;

/**
 * Created by Schwa on 2017-12-20.
 */

public class Manager extends Employee {
    private String managerId;
    private String managerName;
    private String managerPassword;

    public Manager(String employeeId, String employeeName, String employeePassword, String managerId, String managerName, String managerPassword) {
        super(employeeId, employeeName, employeePassword);
        this.managerId = managerId;
        this.managerName = managerName;
        this.managerPassword = managerPassword;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }
}
