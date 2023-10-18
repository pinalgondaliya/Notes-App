package com.kdapps.videoplayer.hdmaxplayer.video.notesapp.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Entity.Notes;
import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Repository.NotesRepository;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    NotesRepository notesRepository;
    public LiveData<List<Notes>> getAllNotes;

    public NotesViewModel(@NonNull Application application) {
        super(application);

        notesRepository = new NotesRepository(application);

        getAllNotes = notesRepository.getallNotes;
    }

    public void insertNotes(Notes notes) {
        notesRepository.insertNotes(notes);
    }

    public void deleteNotes(int id) {
        notesRepository.deleteNotes(id);
    }

    public void updateNotes(Notes notes) {
        notesRepository.updateNotes(notes);
    }
}
