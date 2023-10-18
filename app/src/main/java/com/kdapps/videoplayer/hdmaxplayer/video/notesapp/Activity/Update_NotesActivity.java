package com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Entity.Notes;
import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.R;
import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.ViewModels.NotesViewModel;
import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.databinding.ActivityUpdateNotesBinding;

import java.util.Date;

public class Update_NotesActivity extends AppCompatActivity {
    ActivityUpdateNotesBinding binding;
    private String priority = "1";
    String stitle, ssubtitle, spriority, snotes;
    NotesViewModel notesViewModel;
    private int sid;
    TextView delete_no,delete_yes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        stitle = getIntent().getStringExtra("title");
        ssubtitle = getIntent().getStringExtra("subtitle");
        spriority = getIntent().getStringExtra("priority");
        snotes = getIntent().getStringExtra("notes");
        sid = getIntent().getIntExtra("id",0);

        binding.notesuptitle.setText(stitle);
        binding.notesupsubtitle.setText(ssubtitle);
        binding.upnotesdata.setText(snotes);
        priority = spriority;

        if (spriority.equals("1")) {
            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
        }else if (spriority.equals("2")){
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
        }else if (spriority.equals("3")){
            binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
        }

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

        binding.updateNotesBtn.setOnClickListener(view -> {
            String title = binding.notesuptitle.getText().toString();
            String subtitle = binding.notesupsubtitle.getText().toString();
            String notes = binding.upnotesdata.getText().toString();

            UpdateNotes(title,subtitle,notes);
        });
    }


    private void UpdateNotes(String title, String subtitle, String notes) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d,yyyy",date.getTime());
        Notes updateNotes = new Notes();
        updateNotes.notesTitle = title;
        updateNotes.id = sid;
        updateNotes.notesSubTitle = subtitle;
        updateNotes.notes = notes;
        updateNotes.notesDate = sequence.toString();
        updateNotes.notesPriority = priority;
        notesViewModel.updateNotes(updateNotes);

        finish();
        Toast.makeText(this, "Notes Created Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete){
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Update_NotesActivity.this);
            View view = LayoutInflater.from(Update_NotesActivity.this).inflate(R.layout.delete_layout_bottomsheer,(LinearLayout) findViewById(R.id.bottomsheet));
            bottomSheetDialog.setContentView(view);


            delete_no = view.findViewById(R.id.delete_no);
            delete_yes = view.findViewById(R.id.delete_yes);

            delete_yes.setOnClickListener(v ->{
                notesViewModel.deleteNotes(sid);
                finish();
            });

            delete_no.setOnClickListener(v ->{
                bottomSheetDialog.dismiss();
            });
            bottomSheetDialog.show();
        }
        return true;

    }
}