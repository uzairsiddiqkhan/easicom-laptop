package com.example.easicom.Models;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.easicom.Fragments.Fragment_calls;
import com.example.easicom.Fragments.Fragment_chat;
import com.example.easicom.Fragments.fragment_status;

public class fragment_adapter extends FragmentPagerAdapter {

    public fragment_adapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public fragment_adapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {


            case 1: return new fragment_status();


            case 2: return new Fragment_calls();
            case 0:


            default: return new Fragment_chat();


        }


    }

    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        String title=null;
        switch (position){
            case 0: title="CHATS";
            break;
            case 1: title="STATUS";
            break;
            case 2: title="CALLS";
            break;
        }
        return title;
    }
}
