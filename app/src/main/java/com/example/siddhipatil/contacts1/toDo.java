package com.example.siddhipatil.contacts1;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by siddhipatil on 10/18/17.
 */

public class toDo implements Serializable {

    private String name;
    private int contacts;
    private ArrayList<toDo> relationship;
    private boolean checkList;
    private boolean delete;

    public toDo(String name, int contacts, ArrayList<toDo> relationship) {
        this.name = name;
        this.contacts = contacts;
        this.relationship = relationship;
    }

    public toDo(String name, int contacts)
    {
        this.name=name;
        this.contacts=contacts;
    }

    public toDo()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContact() {
        return contacts;
    }

    public void setContact(int contacts) {
        this.contacts = contacts;
    }

    public ArrayList<toDo> getRelationship() {
        return relationship;
    }

    public void setRelationship(ArrayList<toDo> relationship) {
        this.relationship = relationship;
    }

    public boolean isCheckList() {
        return checkList;
    }

    public void setCheckList(boolean checkList) {
        this.checkList = checkList;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
