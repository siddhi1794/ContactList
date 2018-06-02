package com.example.siddhipatil.contacts1;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;



import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends Activity implements contact {

    contactList contactListFragment;
    contactDetails contactDetailsFragment;
    contactProfile contactProfileFragment;

    FragmentManager fragmentManager;

    static String userNameMain;
    static int userPhoneNum;
    static toDo userProfile;
    static ArrayList<toDo> userRelationList;

    private static final int reqCode = 555;
    private static final int resCode = 777;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            String value = savedInstanceState.getString("savedInstance");
            if (value != null && value.equals("saveProfile")) {
                System.out.println("Restored!");
                toDo value2 = (toDo) savedInstanceState.getSerializable("showProfile");
                response(value2);
            }

        }
        fragmentManager = getFragmentManager();
        contactListFragment = (contactList) fragmentManager.findFragmentById(R.id.fragment);
        contactListFragment.setContact(this);
    }

    @Override
    public void response(toDo userContact) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //landscape
            userProfile = userContact;
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            contactProfileFragment = new contactProfile();
            contactProfileFragment.setProfile(userContact);
            fragmentTransaction.replace(R.id.landScape, contactProfileFragment, "fragment_profile");
            fragmentTransaction.commit();
        } else {
            //portrait
            Intent intent = new Intent(this, contactProfileToDo.class);
            intent.putExtra("userContact", (Serializable) userContact);
            intent.putExtra("userID", "fromMain");
            startActivityForResult(intent, resCode);
        }

    }


    @Override
    public void add(ArrayList<toDo> chkList) {
        //toDo
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            contactDetailsFragment = new contactDetails();
            contactDetailsFragment.setRelationship(chkList);
            contactDetailsFragment.setLandscapeContact(this);
            fragmentTransaction.replace(R.id.landScape, contactDetailsFragment, "fragment_details");
            fragmentTransaction.commit();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(this, contactDetailsToDo.class);
            intent.putExtra("chkList", (Serializable) chkList);
            startActivityForResult(intent, reqCode);
        }

    }

    @Override
    public void addDetails(ArrayList<toDo> chkList, String name, int contact) {

        System.out.println("successMainAct" + name);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            contactDetailsFragment = new contactDetails();
            contactDetailsFragment.setRelationship(chkList);
            contactDetailsFragment.setTextView(name, contact);
            contactDetailsFragment.setLandscapeContact(this);
            fragmentTransaction.replace(R.id.landScape, contactDetailsFragment, "fragment_details");
            fragmentTransaction.commit();
        }
        else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            Intent intent = new Intent(this, contactDetailsToDo.class);
            System.out.println(" value2 not null" + userNameMain);
            intent.putExtra("chkList", (Serializable) chkList);
            intent.putExtra("receivedName", userNameMain);
            intent.putExtra("receivedNumber", userPhoneNum);
            intent.putExtra("responseScreen", true);
            startActivityForResult(intent, reqCode);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if( requestCode == resCode){
            if(intent != null){
                boolean success = intent.getBooleanExtra("fromProfileToDo", false);
                if(success){
                    toDo chkProfile = (toDo) intent.getSerializableExtra("userContact");
                    if(chkProfile != null){
                        response(chkProfile);
                    }
                }
            }
        }

        if (requestCode == reqCode) {
            if (intent != null) {
                if(intent.getBooleanExtra("fromDetailsToDo", false)){

                    ArrayList<toDo> chkUserList = (ArrayList<toDo>) intent.getSerializableExtra("relationFromDetails");

                    String actorName = intent.getStringExtra("nameFromDetails");
                    int actorContact = intent.getIntExtra("contactFromDetails", 0);

                    userRelationList = chkUserList;
                    if(chkUserList!= null){
                        System.out.println("successMainAct" + actorName );
                        addDetails(chkUserList, actorName, actorContact);
                    }
                    else {
                        System.out.println("successMainAct with null object");
                    }
                }else{
                    toDo userContact = (toDo) intent.getSerializableExtra("Intent Contact");
                    if(userContact != null){
                        contactListFragment.addContact(userContact);
                    }
                }
            }
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            contactProfile contactProfile = (contactProfile) getFragmentManager().findFragmentByTag("fragment_profile");
            if (contactProfile != null && contactProfile.isVisible()) {
                String saved = "saveProfile";
                outState.putString("savedInstance", saved);
                outState.putSerializable("showProfile", (Serializable) userProfile);
                System.out.println("onSaveInstanceState profile");
            }
            contactDetails contactDetails = (contactDetails) getFragmentManager().findFragmentByTag("fragment_details");
            if (contactDetails != null && contactDetails.isVisible()) {
                String saved = "saveDetails";
                outState.putString("savedInstance", saved);
                outState.putSerializable("showDetails", (Serializable) userRelationList);
                System.out.println("onSaveInstanceState details");
            }
        }

    }

    @Override
    public void response2(toDo selectCont) {
        System.out.println("response2" + selectCont.getRelationship().size());
        contactListFragment.addContact(selectCont);

    }


}


