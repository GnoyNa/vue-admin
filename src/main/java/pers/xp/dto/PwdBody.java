package pers.xp.dto;

import pers.xp.util.EncodeUtil;

public class PwdBody {
    private String username;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "PwdBody{" +
                "username='" + username + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        try {
            this.oldPassword = EncodeUtil.sha1Encode(oldPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        try {
            this.newPassword = EncodeUtil.sha1Encode(newPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        try {
            this.confirmPassword = EncodeUtil.sha1Encode(confirmPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
