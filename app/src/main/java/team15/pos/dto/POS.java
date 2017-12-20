package team15.pos.dto;

import java.util.ArrayList;

/**
 * Created by Schwa on 2017-12-20.
 */

public class POS {
    private ArrayList<Member> memberList;
    private ArrayList<Product> productList;
    private ArrayList<Employee> employeeList;
    private ArrayList<Payment> paymentList;
    private int balance;

    public POS(ArrayList<Member> memberList, ArrayList<Product> productList, ArrayList<Employee> employeeList, ArrayList<Payment> paymentList, int balance) {
        this.memberList = memberList;
        this.productList = productList;
        this.employeeList = employeeList;
        this.paymentList = paymentList;
        this.balance = balance;
    }

    public ArrayList<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(ArrayList<Member> memberList) {
        this.memberList = memberList;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public ArrayList<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(ArrayList<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
