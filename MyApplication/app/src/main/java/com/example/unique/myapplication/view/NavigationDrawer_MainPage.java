package com.example.unique.myapplication.view;


import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unique.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawer_MainPage extends Fragment {

    private RecyclerView recyclerView;

    private RecyclerAdaptor adaptor;
    //public String val1,val2,val3,val4,val5;

    public static final String PrefFileName="testpref";   //Name of the file where preferneces are stored..

    public static final String keyUserLearnedDrawer="user_learned_drawer";

    private ActionBarDrawerToggle actionbarobj;
    private DrawerLayout drawerobj;

    private boolean userLearnedDrawer;  //It tells whether the user is aare of drawer existance...
    private boolean fromSavedInstance;  //It tells whether the user created the drawer for the first time or it pop up due to change in direction.

    //private View containerView;

    public NavigationDrawer_MainPage() {
        // Required empty public constructor
    }


    //public NavigationDrawer_MainPage(String v1,String v2,String v3,String v4,String v5) {
        // Required empty public constructor
        //val1=v1;
        //val2=v2;
        //val3=v3;
        //val4=v4;
        //val5=v5;
    //}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //userLearnedDrawer=Boolean.valueOf(readFromPrefernces(getActivity(),keyUserLearnedDrawer,"false"));
        if(savedInstanceState!=null)
        {
            fromSavedInstance=true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout=inflater.inflate(R.layout.fragment_navigation_drawer__main_page, container, false);

        recyclerView= (RecyclerView) layout.findViewById(R.id.drawerList);

        adaptor=new RecyclerAdaptor(getActivity(),getData());
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public List<RowData> getData()
    {
        List<RowData> data=new ArrayList();
        int[] icon={R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
        String[] title={"Update Profile","WorkOut Schedule","Health Report","Change Password","Log Out"};
        for(int i=0;i<5;i++)
        {
            RowData obj=new RowData();
            obj.iconId=icon[i%icon.length];
            obj.title=title[i%title.length];
            data.add(obj);
        }
        return data;
    }


    public void setUp(DrawerLayout drawerlayout,Toolbar toolbar)
    {
        //containerView=getActivity().findViewById(fragment_id);

        drawerobj=drawerlayout;
        actionbarobj=new ActionBarDrawerToggle(getActivity(),drawerlayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView)
            {
                super.onDrawerOpened(drawerView);
                //if(!userLearnedDrawer)
                //{
                  //  userLearnedDrawer=true;
                   // saveToPrefernces(getActivity(),keyUserLearnedDrawer,userLearnedDrawer+"");

                //}
                //getActivity().invalidateOptionsMenu();  //ERRoR
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                //getActivity().invalidateOptionsMenu();
            }
        };
        if(!userLearnedDrawer && !fromSavedInstance)
        {
          //  drawerlayout.openDrawer(containerView);
        }
        drawerobj.setDrawerListener(actionbarobj);
        drawerobj.post(new Runnable() {
            @Override
            public void run() {
             actionbarobj.syncState();
            }
        });

    }

    //public static void saveToPrefernces(Context context,String preferenceName,String preferenceValue)
    //{
      //  SharedPreferences obj=context.getSharedPreferences(PrefFileName,Context.MODE_PRIVATE);
       // SharedPreferences.Editor editor=obj.edit();
        //editor.putString(preferenceName,preferenceValue);
        //editor.apply();
    //}

    //public static String readFromPrefernces(Context context,String preferenceName,String defaultValue)
    //{
      //  SharedPreferences obj=context.getSharedPreferences(PrefFileName,Context.MODE_PRIVATE);
       // return obj.getString(preferenceName,defaultValue);
    //}
}
