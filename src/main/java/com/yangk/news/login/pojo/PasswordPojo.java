package com.yangk.news.login.pojo;

/**
 *      传递密码修改的数据
 */

public class PasswordPojo {
    private String username;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
    public String getOldPassword() {
        return oldPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    public String getNewPassword() {
        return newPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public String toString() {
        return "PasswordUp{" +
                "username='" + username + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
