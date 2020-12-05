package io.github.darryring.util.user;

//import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;

import java.util.Date;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author darryring
 * @since 2020-09-21 16:36
 */

public class CurrentUser {//extends SerializableSerializer {
    private static final long serialVersionUID = -8842406321307180300L;
    private static final int SSO_ROLE_ADMIN_TYPE = 32768;
    private static final int SSO_ROLE_SUPPER_TYPE = 16384;
    private Long id;
    private String code;
    private String account;
    private String mobile;
    private String name;
    private String nickname;
    private String qq;
    private String wechat;
    private String language;
    private String gender;
    private String avatar;
    private String email;
    private Date birthday;
    private int type;
    private Integer status;
    private String identifier;
    private String token;
    private int authStatus;
    Set<String> permissions;
    Set<String> groups;

    public CurrentUser() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getQq() {
        return this.qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return this.wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isAdmin() {
        return (this.type & '耀') == 32768;
    }

    public boolean isSupper() {
        return (this.type & 16384) == 16384;
    }

    public Set<String> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Set<String> getGroups() {
        return this.groups;
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getAuthStatus() {
        return this.authStatus;
    }

    public void setAuthStatus(int authStatus) {
        this.authStatus = authStatus;
    }
}
