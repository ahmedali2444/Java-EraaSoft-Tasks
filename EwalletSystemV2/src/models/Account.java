package models;

public class Account {
    private int accountId;
    private String email;
    private String password;
    private Double balance = 0.0;
    private String phoneNumber;
    private int age;
    private Boolean isAdmin = false;
    private Boolean isActive = true;

    public Account(){}

    public Account(String email , String password){
        this.email = email;
        this.password = password;
    }
    public Account(String email , String password , String phoneNumber , int age) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        if (balance == null) {
            return 0.0;
        }

        return balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setBalance(Double balance) {
        if (balance == null) {
            this.balance = 0.0;
            return;
        }

        this.balance = balance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getIsAdmin() {
        if (isAdmin == null) {
            return false;
        }

        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        if (isAdmin == null) {
            this.isAdmin = false;
            return;
        }

        this.isAdmin = isAdmin;
    }

    public Boolean getIsActive() {
        if (isActive == null) {
            return true;
        }

        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        if (isActive == null) {
            this.isActive = true;
            return;
        }

        this.isActive = isActive;
    }

}
