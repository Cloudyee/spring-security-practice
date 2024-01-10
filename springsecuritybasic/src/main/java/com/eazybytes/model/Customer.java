package com.eazybytes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Customer {
    @Id //이 열은 기본키이다.
    // generator = "native" 은 native타입의 생성기(@GenericGenerator)를 따라야함을 뜻함
    // stategy는 native로 설정되어,  새로운 시퀀수 수를 생성하는 것을 데이터 베이스에 맡긴다.
    // TODO : 관련 개념과 적용법에 대해 알아볼 것
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    private String email;
    private String pwd;
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
