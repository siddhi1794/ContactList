package com.example.siddhipatil.contacts1;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by siddhipatil on 10/18/17.
 */

public class contactProfileToDo extends Activity {

    static toDo saveContact;
    toDo userContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)) {
            Intent intent = new Intent();

            if (saveContact != null) {
                intent.putExtra("fromProfileToDo", true);
                intent.putExtra("userContact", (Serializable) saveContact);
            }

            setResult(RESULT_OK, intent);
            finish();
            return;
        }

        setContentView(R.layout.contact_profile);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        if (userID.equals("fromMain")){
            userContact = (toDo) intent.getSerializableExtra("userContact");
        }
        else if (userID.equals("fromContactDetailsToDo")){
            userContact = (toDo) intent.getSerializableExtra("userContact");
        }
        else if (userID.equals("fromContactProfileToDo")){
            userContact = (toDo) intent.getSerializableExtra("userContact");
        }

        contactProfile cp = (contactProfile) getFragmentManager().findFragmentById(R.id.fragmentContactProfile);

        if (cp != null) {
            cp.newContactData(userContact);
        }





    }

}
