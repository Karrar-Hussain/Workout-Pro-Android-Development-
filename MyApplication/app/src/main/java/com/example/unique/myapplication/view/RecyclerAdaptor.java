package com.example.unique.myapplication.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unique.myapplication.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Rezwan on 4/10/2016.
 */
public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerAdaptor.MyViewHolder> {
     private LayoutInflater inflater;
    private static Context sContext;
    public String username;

    List<RowData> data= Collections.emptyList();

    RecyclerAdaptor(Context context,List<RowData> data){
        inflater=LayoutInflater.from(context);
        this.data=data;
        sContext=context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.recycler_rows,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RowData current=data.get(position);
        holder.title.setText(current.title);
        holder.image.setImageResource(current.iconId);
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(sContext,"Clicked",Toast.LENGTH_LONG).show();
            }


        });


    }



    @Override
    public int getItemCount()
    {

        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView title;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.listText);
            image= (ImageView) itemView.findViewById(R.id.listIcon);
            image.setOnClickListener(this);
        }



        @Override
        public void onClick(View v) {
            if(getPosition()==0)
            {
                //update profile
                Intent intent=new Intent(sContext,UpdateProfile.class);
                sContext.startActivity(intent);

            }
            else if(getPosition()==1)
            {
                //Workout schedule
                Intent intent=new Intent(sContext,WorkOut_Schedule.class);
                sContext.startActivity(intent);

            }
            else if(getPosition()==2)
            {
                //Helath Report

            }
            else if(getPosition()==3)
            {
                Intent intent=new Intent(sContext,Change_Password.class);
                sContext.startActivity(intent);
                Toast.makeText(sContext,"Password Changed Request...",Toast.LENGTH_LONG).show();

            }
            else if(getPosition()==4)
            {
                Intent intent=new Intent(sContext,LoginActivity.class);
                sContext.startActivity(intent);
                Toast.makeText(sContext,"Logged Out",Toast.LENGTH_LONG).show();

            }

        }
    }
}
