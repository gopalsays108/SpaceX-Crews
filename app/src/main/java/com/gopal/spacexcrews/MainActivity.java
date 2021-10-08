package com.gopal.spacexcrews;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.gopal.spacexcrews.adapters.CrewRecyclerAdapter;
import com.gopal.spacexcrews.modal.CrewsModal;
import com.gopal.spacexcrews.modal.ReponseModal;
import com.gopal.spacexcrews.network.Api;
import com.gopal.spacexcrews.repository.CrewsRepository;
import com.gopal.spacexcrews.viewmodal.CrewsViewModal;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.internal.EverythingIsNonNull;

public class MainActivity extends AppCompatActivity {

    private CrewsViewModal crewsViewModal;
    private RecyclerView crewListRv;
    private static final String url = "https://api.spacexdata.com/";
    private CrewRecyclerAdapter crewRecyclerAdapter;
    private List<CrewsModal> crewList;
    private CrewsRepository crewsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        crewListRv = findViewById( R.id.crew_list_rv );
        crewListRv.setHasFixedSize( true );
        crewListRv.setLayoutManager( new LinearLayoutManager( this ) );
        crewListRv.setItemAnimator( new DefaultItemAnimator() );
        crewList = new ArrayList<>();
        crewsRepository = new CrewsRepository( getApplication() );
        crewsViewModal = new ViewModelProvider( this )
                .get( CrewsViewModal.class );
        crewRecyclerAdapter = new CrewRecyclerAdapter( this, crewList, crewsViewModal );

        crewsViewModal.getAllCrews().observe( this, new Observer<List<CrewsModal>>() {
            @Override
            public void onChanged(List<CrewsModal> crewsModals) {
                crewRecyclerAdapter.getAllActor( crewsModals );
                crewListRv.setAdapter( crewRecyclerAdapter );
            }
        } );
        fetchDataFromApi();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            fetchDataFromApi();
        } else if (item.getItemId() == R.id.deleteAll) {
            crewsViewModal.deleteALl();
            Toast.makeText( getApplicationContext(), "Delete all", Toast.LENGTH_SHORT ).show();
        }
        return super.onOptionsItemSelected( item );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.refreshmenu, menu );
        return true;
    }

    private void fetchDataFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( url )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

        Api api = retrofit.create( Api.class );
        Call<ReponseModal> allCrews = api.getAllCrews();

        allCrews.enqueue( new Callback<ReponseModal>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<ReponseModal> call, Response<ReponseModal> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i( "TAG", "onResponse Data:  " + response.body() );
                    crewsRepository.insertCrew( response.body().getDocs() );
                } else {
                    Log.i( "TAG", "onResponse Data:  " + response.errorBody() );

                }
            }

            @Override
            public void onFailure(Call<ReponseModal> call, Throwable t) {
                Log.i( "TAG", "onFailure: " + t.getLocalizedMessage() + " " + call.toString() );
            }
        } );
    }
}