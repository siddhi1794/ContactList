package com.example.siddhipatil.contacts1;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by siddhipatil on 10/18/17.
 */

public class contactProfile extends Fragment {

    TextView user;
    TextView userPhone;
    ListView contRelList;
    String actorName;
    int actorContact;
    ArrayList<toDo> userContactList;
    ArrayList<String> showRelationList;
    ArrayAdapter<String> addRelDetails;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view= inflater.inflate(R.layout.contact_profile_layout,container,false);
        user=(TextView) view.findViewById(R.id.idNameEditProfile);
        userPhone=(TextView) view.findViewById(R.id.idPhNumEditProfile);
        contRelList=(ListView)view.findViewById(R.id.idListViewProfile);

        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE && getActivity().getClass().getSimpleName().toString().equals("MainActivity"))
        {
            System.out.println("AppActivity Crash!"+getActivity().getClass().getSimpleName().toString());
            viewProfile();
        }
        return view;
    }

    public void setProfile(toDo userContact)
    {
        actorName=userContact.getName();
        actorContact=userContact.getContact();
        userContactList=userContact.getRelationship();

    }

    private void viewProfile() {
        showRelationList= new ArrayList<>();
        if(userContactList!=null)
        {
            for(int i=0;i<userContactList.size();i++)
            {
                showRelationList.add(userContactList.get(i).getName());
            }
        }
        addRelDetails= new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,showRelationList);
        contRelList.setAdapter(addRelDetails);
        setOnClickListenerRelDel();
        user.setText(actorName);
        userPhone.setText(String.valueOf(actorContact));

    }

    public void newContactData(toDo userContact)
    {
        if (getActivity().getClass().getSimpleName().toString().equals("contactProfileToDo")) {
            contactProfileToDo cptd = (contactProfileToDo)getActivity();
            cptd.saveContact=userContact;
        }
        showRelationList= new ArrayList<>();
        userContactList=userContact.getRelationship();

        for (int i = 0; i < userContactList.size(); i++) {
            showRelationList.add(userContactList.get(i).getName());
        }

        addRelDetails = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, showRelationList);
        contRelList.setAdapter(addRelDetails);
        setOnClickListenerRelDel();

        actorName = userContact.getName();
        actorContact = userContact.getContact();
        user.setText(actorName);
        userPhone.setText(String.valueOf(actorContact));

    }

    private void setOnClickListenerRelDel() {
        contRelList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT)
                {
                    Intent intent = new Intent(getActivity(), contactProfileToDo.class);
                    intent.putExtra("userContact", (Serializable) userContactList.get(position));
                    intent.putExtra("userID", "fromContactProfileToDo");
                    startActivity(intent);
                }
                else
                {
                    toDo newUser = userContactList.get(position);
                    setProfile(newUser);
                    viewProfile();
                }

            }
        });
    }





}
