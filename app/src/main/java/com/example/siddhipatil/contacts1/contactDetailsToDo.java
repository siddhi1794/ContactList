package com.example.siddhipatil.contacts1;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by siddhipatil on 10/18/17.
 */

public class contactDetailsToDo extends Activity implements contactDetails.detailsContact {
    FragmentManager fragmentManager;
    ArrayList<toDo> chkList;
    contactDetails contactDetailsNewFragment;

    public static String userName;
    public static int userCont;
    public static ArrayList<toDo> userRel;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if((getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE))
        {
            Intent intent= new Intent();
            intent.putExtra("fromDetailsToDo", true);
            intent.putExtra("relationshipFromContactDetails", (Serializable)userRel);
            intent.putExtra("contactNameFromContactDetails", userName);
            intent.putExtra("contactFromContactDetails",userCont);
            setResult(RESULT_OK, intent);
            finish();
            return;

        }
        setContentView(R.layout.contact_details);

        Intent intent=getIntent();
        chkList=(ArrayList<toDo>)intent.getSerializableExtra("chkList");
        fragmentManager=getFragmentManager();

        contactDetailsNewFragment=(contactDetails)fragmentManager.findFragmentById(R.id.fragmentContactDetails);
        boolean responseScreen= intent.getBooleanExtra("responseScreen", false);

        if(responseScreen){
            String currUserName = intent.getStringExtra("receivedName");
            int currUserContact = intent.getIntExtra("receivedNumber", 0);
            contactDetailsNewFragment.setTextView(currUserName, currUserContact);
        }
        contactDetailsNewFragment.setRelationship(chkList);
        contactDetailsNewFragment.setContactDetailsCont(this);
        contactDetailsNewFragment.showRelList();
    }

    @Override
    public void addNewContact(toDo selectCont)
    {
        Intent sendIntent= new Intent();
        sendIntent.putExtra("Intent Contact",selectCont);
        setResult(RESULT_OK, sendIntent);
        finish();
    }


}



