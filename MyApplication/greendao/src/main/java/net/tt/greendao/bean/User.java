package net.tt.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by admin on 2017/4/17.
 */
@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String age;
    private String score;
    @Transient
    private int tempUsageCount;
    @Generated(hash = 1883942139)
    public User(Long id, String name, String age, String score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.score = score;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getScore() {
        return this.score;
    }
    public void setScore(String score) {
        this.score = score;
    }
}
