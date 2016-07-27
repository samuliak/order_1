package com.project.samuliak.psychogram.Activity.main.menu.common_items;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.project.samuliak.psychogram.API.PsychogolistAPI;
import com.project.samuliak.psychogram.Model.Client;
import com.project.samuliak.psychogram.Model.Psychogolist;
import com.project.samuliak.psychogram.Model.Tab;
import com.project.samuliak.psychogram.R;
import com.project.samuliak.psychogram.Util.Constants;
import com.project.samuliak.psychogram.Util.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommunicationActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private PsychogolistAPI service;
    private ProgressDialog progressDialog;

    private Psychogolist doctor;
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initTab();
    }

    private void initTab() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(PsychogolistAPI.class);
        Call<List<Tab>> listCall = null;
        mViewPager = (ViewPager) findViewById(R.id.container);
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        Bundle data = getIntent().getExtras();
        if (data != null){
            doctor = data.getParcelable(Psychogolist.class.getCanonicalName());
            client = data.getParcelable(Client.class.getCanonicalName());
            if (doctor != null)
                listCall = service.getAllTabsByDoctor(doctor.getLogin());
            else if (client != null)
                listCall = service.getAllTabsByClient(client.getLogin());
        }
        progressDialog = new ProgressDialog(this);
        Utils.initProgressDialog(progressDialog, this);

        assert listCall != null;
        listCall.enqueue(new Callback<List<Tab>>() {
            @Override
            public void onResponse(Call<List<Tab>> call, Response<List<Tab>> response) {
                if (response.isSuccessful()){
                    mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),
                            response.body());
                    mViewPager.setAdapter(mSectionsPagerAdapter);
                    assert tabLayout != null;
                    tabLayout.setupWithViewPager(mViewPager);
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Tab>> call, Throwable t) {
                Toast.makeText(getBaseContext(), R.string.connecting_error, Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_communication, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(Tab tab) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putParcelable(Tab.class.getCanonicalName(), tab);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_communication, container, false);
            RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv_message);
            Tab tab = getArguments().getParcelable(Tab.class.getCanonicalName());

            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private List<Tab> tabs;

        public SectionsPagerAdapter(FragmentManager fm, List<Tab> body) {
            super(fm);
            this.tabs = body;
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(tabs.get(position));
        }

        @Override
        public int getCount() {
            return tabs.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position).getClient();
        }
    }
}
