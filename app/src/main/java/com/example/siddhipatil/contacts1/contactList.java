package com.example.siddhipatil.contacts1;

import android.app.Fragment;
import android.bluetooth.le.AdvertiseData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by siddhipatil on 10/18/17.
 */

public class contactList extends Fragment implements AdapterView.OnItemClickListener
{
    ListView listView;
    ArrayList<toDo> contList;
    contact contact;
    Button addButton;
    Button deleteButton;
    customAdapter customAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.contact_list_layout, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        ArrayList<toDo> contact1= new ArrayList<toDo>();
        ArrayList<toDo> contact2= new ArrayList<toDo>();
        ArrayList<toDo> contact3= new ArrayList<toDo>();
        ArrayList<toDo> contact4= new ArrayList<toDo>();


        toDo user1= new toDo("Olive", 732);
        toDo user2= new toDo("Popeye", 858);
        toDo user3= new toDo("Tom", 123);
        toDo user4= new toDo("Jerry", 456);

        contact1.add(user2);
        contact1.add(user3);
        contact1.add(user4);
        user1.setRelationship(contact1);

        contact2.add(user3);
        contact2.add(user4);
        user2.setRelationship(contact2);
        user3.setRelationship(contact2);
        user4.setRelationship(contact2);


        contList= new ArrayList<toDo>();
        contList.add(user1);
        contList.add(user2);

        listView=(ListView) getActivity().findViewById(R.id.idListView);
        customAdapter= new customAdapter(getActivity(), R.layout.check_list,contList);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(this);

        addButton=(Button) getActivity().findViewById(R.id.idAdd);
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                System.out.println("Please Add a Contact");
                contact.add(contList);

            }

        });

        deleteButton=(Button)getActivity().findViewById(R.id.idDelete);
        deleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                System.out.println("Delete Button");

                for(int i=0;i<contList.size();i++)
                {
                    System.out.println("Delete Button"+contList.get(i).isDelete());
                    if(contList.get(i).isDelete())
                    {
                        System.out.println("Delete Requested");
                        toDo contDelete=contList.get(i);
                        contList.remove(i);
                        contDelete=null;
                    }
                }
                listView.setAdapter(customAdapter);
            }
        });



    }

    public void addContact(toDo selectCont)
    {
        contList.add(selectCont);
        listView.setAdapter(customAdapter);
    }

    public void setContact(contact cont)
    {
        this.contact=cont;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        for(int i=0; i<contList.size();i++)
        {
            contList.get(i).setDelete(false);
        }

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        toDo userContact= customAdapter.getItem(position);
        System.out.println("Contact Selected");
        contact.response(userContact);
    }





}

