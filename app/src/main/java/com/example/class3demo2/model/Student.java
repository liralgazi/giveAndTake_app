package com.example.class3demo2.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.HashMap;
import java.util.Map;

@Entity
public class Student {
    @PrimaryKey
    @NonNull
    public String id="";
    public String name="";
    public String avatarUrl="";
    public Boolean cb=false;

    public Student(){
    }
    public Student( String id,String name, String avatarUrl, Boolean cb) {
        this.name = name;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.cb = cb;
    }

    static final String NAME = "name";
    static final String ID = "id";
    static final String AVATAR = "avatar";
    static final String CB = "cb";
    static final String COLLECTION = "students";

    public static Student fromJson(Map<String,Object> json){
        String id = (String)json.get(ID);
        String name = (String)json.get(NAME);
        String avatar = (String)json.get(AVATAR);
        Boolean cb = (Boolean) json.get(CB);
        Student st = new Student(id,name,avatar,cb);
        return st;
    }

    public Map<String,Object> toJson(){
        Map<String, Object> json = new HashMap<>();
        json.put(ID, getId());
        json.put(NAME, getName());
        json.put(AVATAR, getAvatarUrl());
        json.put(CB, getCb());
        return json;
    }




    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setCb(Boolean cb) {
        this.cb = cb;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Boolean getCb() {
        return cb;
    }
}
