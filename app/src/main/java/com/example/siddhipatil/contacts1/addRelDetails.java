package com.example.siddhipatil.contacts1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by siddhipatil on 10/18/17.
 */

public class addRelDetails extends ArrayAdapter<toDo>{

    TextView actorList;
    CheckBox checkBox;


    public addRelDetails(Context context, int resource, ArrayList<toDo> chkList) {
        super(context, R.layout.check_list, chkList);
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent)
    {
        LayoutInflater inflater= LayoutInflater.from(getContext());
        final View view= inflater.inflate(R.layout.check_list,parent,false);

        actorList=(TextView) view.findViewById(R.id.idChkList);
        checkBox=(CheckBox) view.findViewById(R.id.idCheckBox);

        checkBox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int ogPos= position;
                toDo toDoTask= getItem(position);

                if(!getItem(position).isCheckList()) {
                    getItem(position).setCheckList(true);
                    System.out.println("... 1 ");
                }
                else{
                    getItem(position).setCheckList(false);
                    System.out.println("... 2 ");
                }


            }
        });

        String name= getItem(position).getName();
        actorList.setText(name);
        return  view;


    }


}
