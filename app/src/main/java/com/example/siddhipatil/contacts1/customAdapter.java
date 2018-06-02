package com.example.siddhipatil.contacts1;

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

public class customAdapter extends ArrayAdapter<toDo> {
    TextView actorList;
    CheckBox checkBox;

    public customAdapter(Context context, int resource, ArrayList<toDo> chkList)
    {
        super(context,R.layout.check_list, chkList);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater= LayoutInflater.from(getContext());
        View view= inflater.inflate(R.layout.check_list,parent,false);

        actorList=(TextView) view.findViewById(R.id.idChkList);
        checkBox=(CheckBox)view.findViewById(R.id.idCheckBox);

        checkBox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getItem(position).setDelete(!getItem(position).isDelete());

                System.out.println("CheckBox Clicked!"+getItem(position).getName()+" with Pos "+getItem(position).isDelete());
            }
        });

        String name = getItem(position).getName();
        actorList.setText(name);

        return view;
    }


}
