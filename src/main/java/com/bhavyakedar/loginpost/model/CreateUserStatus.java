package com.bhavyakedar.loginpost.model;

public class CreateUserStatus {
    private String emailerror;
    private String usernameError;
    private String usercreatedmsg;

    public CreateUserStatus(String emailerror, String usernameerror, String usercreatedmsg) {
        this.usercreatedmsg = usercreatedmsg;
        this.emailerror = emailerror;
        this.usernameError = usernameerror;
    }

    public String getUsercreatedmsg() {
        return usercreatedmsg;
    }

    public void setUsercreatedmsg(String usercreatedmsg) {
        this.usercreatedmsg = usercreatedmsg;
    }

    public String getEmailerror() {
        return emailerror;
    }

    public void setEmailerror(String emailerror) {
        this.emailerror = emailerror;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameerror) {
        this.usernameError = usernameerror;
    }
}
