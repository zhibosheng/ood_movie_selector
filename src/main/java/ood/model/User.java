package ood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @JsonView({View.Event.class,View.User.class})
    private long userId;

    @Column(name = "user_name")
    @JsonView({View.Event.class,View.User.class})
    private String userName;

    @Column(name = "password")
    @JsonView({View.Event.class,View.User.class})
    private String password;

    @Column(name = "email")
    @JsonView({View.Event.class,View.User.class})
    private String email;

    @Column(name = "phone")
    @JsonView({View.Event.class,View.User.class})
    private String phone;

    @OneToMany(mappedBy = "moderator", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Group> ownGroups = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "users_groups",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id") })
    @JsonIgnore
    private Set<Group> joinGroups = new HashSet<>();

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
        this.password = password;
        //this.password = DigestUtils.md5Hex(password.trim());
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

    public Set<Group> getOwnGroups() {
        try{
            int size = ownGroups.size();
        }
        catch (Exception e){
            return null;
        }
        return ownGroups;
    }

    public void setOwnGroups(Set<Group> ownGroups) {
        this.ownGroups = ownGroups;
    }

    public Set<Group> getJoinGroups() {
        try{
            int size = joinGroups.size();
        }
        catch (Exception e){
            return null;
        }
        return joinGroups;
    }

    public void setJoinGroups(Set<Group> joinGroups) {
        this.joinGroups = joinGroups;
    }

    @Override
    public String toString() {
        ObjectMapper objectMapper = new ObjectMapper();
        String str = null;
        try {
            str = objectMapper.writeValueAsString(this);
        }
        catch(JsonProcessingException jpe) {
            jpe.printStackTrace();
        }
        return str;
    }

    @Override
    public int hashCode() { return Objects.hash(userId,userName);}

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId;
    }
}
