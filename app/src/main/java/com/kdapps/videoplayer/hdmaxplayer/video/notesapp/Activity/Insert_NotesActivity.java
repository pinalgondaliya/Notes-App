package com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Activity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Entity.Notes;
import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.R;
import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.ViewModels.NotesViewModel;
import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.databinding.ActivityInsertNotesBinding;

import java.util.Date;

public class Insert_NotesActivity extends AppCompatActivity {
    ActivityInsertNotesBinding binding;
    String title,subtitle,notes;
    NotesViewModel notesViewModel;
    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        binding.doneNotesbtn.setOnClickListener(view -> {
            title = binding.notestitle.getText().toString();
            subtitle = binding.notessubtitle.getText().toString();
            notes = binding.notesdata.getText().toString();

            createNotes(title,subtitle,notes);
        });

        binding.greenPriority.setOnClickListener(view -> {
            priority = "1";
            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellowPriority.setImageResource(0);//remove image of done
            binding.redPriority.setImageResource(0);//remove image of done
        });

        binding.yellowPriority.setOnClickListener(view -> {
            priority = "2";
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redPriority.setImageResource(0);//remove image of done
            binding.greenPriority.setImageResource(0);//remove image of done
        });

        binding.redPriority.setOnClickListener(view -> {
            priority = "3";
            binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellowPriority.setImageResource(0);//remove image of done
            binding.greenPriority.setImageResource(0);//remove image of done
        });

    }

    private void createNotes(String title, String subtitle, String notes) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy",date.getTime());
        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubTitle = subtitle;
        notes1.notes = notes;
        notes1.notesDate = sequence.toString();
        notes1.notesPriority = priority;
        notesViewModel.insertNotes(notes1);

        finish();
        Toast.makeText(this, "Notes Created Successfully", Toast.LENGTH_SHORT).show();
    }
}