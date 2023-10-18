package com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Entity.Notes;

import java.util.List;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM notes")
    LiveData<List<Notes>> getAllNotes();
//    List<Notes> getAllNotes();

    @Insert
    void insertNotes(Notes... notes);

    @Query("DELETE FROM notes WHERE id=:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes notes);
}
