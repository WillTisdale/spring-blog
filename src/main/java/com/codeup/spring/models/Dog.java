package com.codeup.spring.models;

import jdk.jfr.Unsigned;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity(name = "dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) UNSIGNED")
    @Unsigned
    private long id;

    @Column(nullable = false, columnDefinition = "TINYINT(3) UNSIGNED", unique = true)
    private int age;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(columnDefinition = "CHAR(2) DEFAULT 'XX'")
    private String resideState;

    public Dog(){}

    public Dog(long id, int age, String name, String resideState){
        this.id = id;
        this.age = age;
        this.name = name;
        this.resideState = resideState;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResideState() {
        return resideState;
    }

    public void setResideState(String resideState) {
        this.resideState = resideState;
    }
}
