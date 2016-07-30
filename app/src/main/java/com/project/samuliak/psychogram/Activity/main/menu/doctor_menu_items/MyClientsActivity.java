package com.project.samuliak.psychogram.Activity.main.menu.doctor_menu_items;

import android.app.ProgressDialog;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.project.samuliak.psychogram.API.PsychogolistAPI;
import com.project.samuliak.psychogram.Adapter.MyClientsAdapter;
import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.Model.Psychogolist;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyClientsActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static Psychogolist doctor;
    private static PsychogolistAPI service;
    private static ProgressDialog progressDialog;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_clients);
        initUI();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }

    private void initUI() {
        service = Utils.getRetrofit().create(PsychogolistAPI.class);
        progressDialog = new ProgressDialog(this);
        Utils.initProgressDialog(progressDialog, this);
        doctor = getIntent().getExtras().getParcelable(Psychogolist.class.getCanonicalName());
    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(boolean b) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putBoolean("boolean", b);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_my_clients, container, false);
            final RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_clients);
            if (getArguments().getBoolean("boolean")){
                Call<List<Client>> listCurrentCall = service.getClientsByDoctorLogin(doctor.getLogin());
                Utils.initProgressDialog(progressDialog, getContext());
                listCurrentCall.enqueue(new Callback<List<Client>>() {
                    @Override
                    public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                        if (response.isSuccessful()){
                            MyClientsAdapter adapter = new MyClientsAdapter(getContext(), response.body(),
                                    false);
                            rv.setAdapter(adapter);
                            rv.setLayoutManager(new LinearLayoutManager(getContext()));
                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<Client>> call, Throwable t) {
                        Toast.makeText(getContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                });
            }else{
                Call<List<Client>> listMayCall = service.getPotencialClients(doctor.getLogin());
                Utils.initProgressDialog(progressDialog, getContext());
                listMayCall.enqueue(new Callback<List<Client>>() {
                    @Override
                    public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                        if (response.isSuccessful()){
                            MyClientsAdapter adapter = new MyClientsAdapter(getContext(), response.body(),
                                    true);
                            rv.setAdapter(adapter);
                            rv.setLayoutManager(new LinearLayoutManager(getContext()));
                        }
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<List<Client>> call, Throwable t) {
                        Toast.makeText(getContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                });
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
                    return "Клиенты";
                case 1:
                    return "Заявки";
            }
            return null;
        }
    }
}
