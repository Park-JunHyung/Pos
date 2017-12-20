package team15.pos.dto;

/**
 * Created by Schwa on 2017-12-20.
 */

public class Customer {
    private boolean isSignUp;

    public Customer() {
        this.isSignUp = false;
    }
    public Customer(boolean isSignUp) {
        this.isSignUp = isSignUp;
    }

    public boolean isSignUp() {
        return isSignUp;
    }

    public void setSignUp(boolean signUp) {
        isSignUp = signUp;
    }
}
