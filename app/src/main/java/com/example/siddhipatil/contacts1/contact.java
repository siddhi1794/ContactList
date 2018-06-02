package com.example.siddhipatil.contacts1;

import java.util.ArrayList;

/**
 * Created by siddhipatil on 10/18/17.
 */

public interface contact {

    public void add(ArrayList<toDo> chkList);
    public void response(toDo selectCont);
    public void addDetails(ArrayList<toDo> chkList, String name, int contact);
    public void response2(toDo selectCont);

}
