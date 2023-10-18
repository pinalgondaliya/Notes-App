package com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Repository;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Dao.NotesDao;
import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Databse.NotesDatabase;
import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Entity.Notes;

import java.util.List;

public class NotesRepository {

    public NotesDao notesDao;
    public LiveData<List<Notes>> getallNotes;

    public NotesRepository(Application application) {
        NotesDatabase notesDatabase = NotesDatabase.getDatabaseinstance(application);
        notesDao = notesDatabase.notesDao();
        getallNotes = notesDao.getAllNotes();
    }


    public void insertNotes(Notes notes) {
        notesDao.insertNotes(notes);
    }

    public void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }

    public void updateNotes(Notes notes){
        notesDao.updateNotes(notes);
    }
}
