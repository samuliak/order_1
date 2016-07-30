package com.project.samuliak.psychogram.Activity.main.menu.doctor_menu_items;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.samuliak.psychogram.API.PsychogolistAPI;
import com.project.samuliak.psychogram.Adapter.FriendsAdapter;
import com.project.samuliak.psychogram.Model.Psychogolist;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static Psychogolist doctor;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        doctor = getIntent().getExtras().getParcelable(Psychogolist.class.getCanonicalName());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        assert mViewPager != null;
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        assert tabLayout != null;
        tabLayout.setupWithViewPager(mViewPager);



    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {}

        public static PlaceholderFragment newInstance(boolean b) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putBoolean("friend", b);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_friend, container, false);
            final RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_friends);
            if (doctor != null){
                PsychogolistAPI service = Utils.getRetrofit().create(PsychogolistAPI.class);
                if(getArguments().getBoolean("friend")) {
                    Call<List<Psychogolist>> call = service.getAllFriendsByLogin(doctor.getLogin());
                    final ProgressDialog progressDialog = new ProgressDialog(rootView.getContext());
                    Utils.initProgressDialog(progressDialog, rootView.getContext());
                    call.enqueue(new Callback<List<Psychogolist>>() {
                        @Override
                        public void onResponse(Call<List<Psychogolist>> call, Response<List<Psychogolist>> response) {
                            if (response.isSuccessful()) {
                                FriendsAdapter adapter = new FriendsAdapter(getContext(), response.body(),
                                        true, doctor);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                            }
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<List<Psychogolist>> call, Throwable t) {
                            Toast.makeText(getContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    });
                } else {
                    Call<List<Psychogolist>> call = service.getAllFriendsRequestByLogin(doctor.getLogin());
                    final ProgressDialog progressDialog = new ProgressDialog(rootView.getContext());
                    Utils.initProgressDialog(progressDialog, rootView.getContext());
                    call.enqueue(new Callback<List<Psychogolist>>() {
                        @Override
                        public void onResponse(Call<List<Psychogolist>> call, Response<List<Psychogolist>> response) {
                            if (response.isSuccessful()) {
                                FriendsAdapter adapter = new FriendsAdapter(getContext(), response.body(),
                                        false, doctor);
                                rv.setAdapter(adapter);
                                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                            }
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<List<Psychogolist>> call, Throwable t) {
                            Toast.makeText(getContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    });
                }
            }
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return PlaceholderFragment.newInstance(true);
            else
                return PlaceholderFragment.newInstance(false);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.friends);
                case 1:
                    return getString(R.string.request);
            }
            return null;
        }
    }
}
