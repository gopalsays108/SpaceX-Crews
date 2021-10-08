package com.gopal.spacexcrews.viewmodal;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gopal.spacexcrews.modal.CrewsModal;
import com.gopal.spacexcrews.repository.CrewsRepository;

import java.util.List;

public class CrewsViewModal extends AndroidViewModel {

    private CrewsRepository crewsRepository;
    private LiveData<List<CrewsModal>> getAllCrews;
    private String id = "";


    public CrewsViewModal(@NonNull Application application, String id) {
        super( application );
        this.id = id;
    }

    public CrewsViewModal(@NonNull Application application) {
        super( application );
        crewsRepository = new CrewsRepository( application );
        getAllCrews = crewsRepository.getCrews();
    }

    public void insertCrew(List<CrewsModal> modals) {
        crewsRepository.insertCrew( modals );
    }

    public void deleteUserById(String id) {
        crewsRepository.deleteUserById( id );
    }
    public void deleteALl() {
        crewsRepository.deleteALl( );
    }

    public LiveData<List<CrewsModal>> getAllCrews() {
        return getAllCrews;
    }
}
