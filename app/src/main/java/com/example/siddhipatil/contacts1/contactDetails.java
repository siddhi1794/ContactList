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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by siddhipatil on 10/18/17.
 */

public class contactDetails extends Fragment implements Serializable {
    EditText editName;
    EditText editContact;
    ListView addRelationship;

    String enterName=" ";
    int enterContact=0;
    ArrayList<toDo> addRelList;
    ArrayList<toDo> addRelCont;
    addRelDetails addRelDetails;
    toDo contSel;
    contact contact;
    Button addPerson;
    detailsContact detailsContact;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
       View view= inflater.inflate(R.layout.contact_details_layout,container,false);
        editName=(EditText) view.findViewById(R.id.idName1);
        editContact=(EditText) view.findViewById(R.id.idPhNum1);
        addRelationship=(ListView) view.findViewById(R.id.idListView2);
        addPerson=(Button) view.findViewById(R.id.idAddPerson);

        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE && getActivity().getClass().getSimpleName().toString().equals("MainActivity") )
        {
            System.out.println("557755"+getActivity().getClass().getSimpleName().toString());
            showRelList();

        }
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        addPerson.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String newContact= editContact.getText().toString();
                String name= editName.getText().toString();

                if(name.equals(""))
                {
                    Toast.makeText(getActivity(),"Please Enter the Name!",Toast.LENGTH_SHORT).show();
                }
                else if(newContact.equals(""))
                {
                    Toast.makeText(getActivity(),"Please Enter the Contact Number!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int contNum= Integer.parseInt(newContact);

                    contSel= new toDo(name,contNum);
                    addRelCont= new ArrayList<>();
                    for(int i=0;i<addRelList.size();i++)
                    {
                        if(addRelList.get(i).isCheckList())
                        {
                            addRelCont.add(addRelList.get(i));
                            addRelList.get(i).getRelationship().add(contSel);
                        }
                    }

                    contSel.setRelationship(addRelCont);

                    if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT) {
                        detailsContact.addNewContact(contSel);
                    }
                    else if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE)
                    {
                        contact.response2(contSel);
                        editName.setText("");
                        editContact.setText("");
                        showRelList();
                    }
                }
            }
        });
    }

    public void setTextView(String name, int contact)
    {
        System.out.println("successContactDetails"+name);
        enterName= name;
        enterContact= contact;
        System.out.println("successContactDetails"+enterName);

    }

    public void setRelationship(ArrayList<toDo> chkList)
    {
        contactDetailsToDo dc= (contactDetailsToDo) getActivity();
        dc.userRel=chkList;

        addRelList= new ArrayList<>();
        for(int i=0; i<chkList.size();i++)
        {
            addRelList.add(chkList.get(i));
        }

    }

    public void showRelList()
    {
        editName.setText(enterName);
        editContact.setText(Integer.toString(enterContact));

        if(addRelList!=null)
        {
            addRelDetails= new addRelDetails(getActivity(), R.layout.check_list, addRelList);
            addRelationship.setAdapter(addRelDetails);
            setOnClickListenerRelDet();
        }

    } 


    public void setContactDetailsCont(detailsContact dc)
    {
        this.detailsContact=dc;
    }

    public void setLandscapeContact(contact lc)
    {
        this.contact=lc;
    }

    public void setOnClickListenerRelDet()
    {
        addRelationship.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             if(getResources().getConfiguration().orientation==Configuration.ORIENTATION_PORTRAIT)
             {
                 Intent intent= new Intent(getActivity(), contactProfileToDo.class);
                 intent.putExtra("userContact",(Serializable) addRelList.get(position));
                 intent.putExtra("userID","fromContactDetailsToDo");
                 startActivity(intent);
             }
             else
             {
                 for(int i=0; i<addRelList.size();i++)
                 {
                     addRelList.get(i).setCheckList(false);
                 }
                 contact.response(addRelList.get(position));
             }
            }
        });

    }


    @Override
    public void onDestroy()
    {
        if(getActivity().getClass().getSimpleName().toString().equals("contactDetailsToDo"))
        {
            contactDetailsToDo dc= (contactDetailsToDo) getActivity();
            dc.userName=editName.getText().toString();
            dc.userCont=Integer.parseInt(editContact.getText().toString());
            dc.userRel=addRelList;
        }

        if(getActivity().getClass().getSimpleName().toString().equals("MainActivity"))
        {
            MainActivity dc= (MainActivity) getActivity();
            dc.userNameMain=editName.getText().toString();
            dc.userPhoneNum=Integer.parseInt(editContact.getText().toString());
            dc.userRelationList=addRelList;
        }

        if(addRelList!=null)
        {
            for(int i=0; i<addRelList.size();i++)
            {
                addRelList.get(i).setCheckList(false);
            }
        }
        super.onDestroy();
    }

    public interface detailsContact
    {
        public void addNewContact(toDo selectCont);

    }

}

