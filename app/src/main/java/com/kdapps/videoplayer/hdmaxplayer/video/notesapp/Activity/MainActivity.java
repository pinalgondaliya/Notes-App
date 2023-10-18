package com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Adapter.NotesAdpater;
import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Entity.Notes;
import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.R;
import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.ViewModels.NotesViewModel;
import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.databinding.ActivityInsertNotesBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newnotesbtn;
    NotesViewModel notesViewModel;
    RecyclerView notesrecyclerview;
    NotesAdpater  notesAdpater;
    TextView no_filter,hightolow,lowtohigh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newnotesbtn = findViewById(R.id.newnotesbtn);
        notesrecyclerview = findViewById(R.id.notesrecyclerview);

        no_filter = findViewById(R.id.no_filter);
        hightolow = findViewById(R.id.hightolow);
        lowtohigh = findViewById(R.id.lowtohigh);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        newnotesbtn.bringToFront();
        newnotesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,Insert_NotesActivity.class));
            }
        });

        notesViewModel.getAllNotes.observe(this,notes -> {
            if (notes != null) {
                notesrecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
                notesAdpater = new NotesAdpater(MainActivity.this, notes);
                notesrecyclerview.setAdapter(notesAdpater);
            }
        });

        no_filter.setOnClickListener(view -> {
            hightolow.setBackgroundResource(0);
            lowtohigh.setBackgroundResource(0);
            no_filter.setBackgroundResource(R.drawable.green_shape);
        });

        lowtohigh.setOnClickListener(view -> {
            no_filter.setBackgroundResource(0);
            hightolow.setBackgroundResource(0);
            lowtohigh.setBackgroundResource(R.drawable.green_shape);
        });

        hightolow.setOnClickListener(view -> {
            no_filter.setBackgroundResource(0);
            lowtohigh.setBackgroundResource(0);
            hightolow.setBackgroundResource(R.drawable.green_shape);
        });




        // Set up the RecyclerView adapter (you may need to initialize it if not already done)
//        notesAdpater = new NotesAdpater(MainActivity.this, new ArrayList<>());
//        notesrecyclerview.setLayoutManager(new GridLayoutManager(this, 2));
//        notesrecyclerview.setAdapter(notesAdpater);
//
//        notesViewModel.getAllNotes.observe(this, new androidx.lifecycle.Observer<List<Notes>>() {
//            @Override
//            public void onChanged(List<Notes> notes) {
//                if (notes != null) {
//                    // Update the RecyclerView with new data
//                    notesAdpater.setNotesList(notes);
//                }
//            }
//        });
    }


}