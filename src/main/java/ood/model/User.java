package ood.model;

import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "moderator", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Group> ownGroups;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "users_groups",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id") })
    private List<Group> joinGroups;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(@org.jetbrains.annotations.NotNull String password) {
        this.password = DigestUtils.md5Hex(password.trim());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Group> getOwnGroups() {
        try{
            int size = ownGroups.size();
        }
        catch (Exception e){
            return null;
        }
        return ownGroups;
    }

    public void setOwnGroups(List<Group> ownGroups) {
        this.ownGroups = ownGroups;
    }

    public List<Group> getJoinGroups() {
        try{
            int size = joinGroups.size();
        }
        catch (Exception e){
            return null;
        }
        return joinGroups;
    }

    public void setJoinGroups(List<Group> joinGroups) {
        this.joinGroups = joinGroups;
    }


}
