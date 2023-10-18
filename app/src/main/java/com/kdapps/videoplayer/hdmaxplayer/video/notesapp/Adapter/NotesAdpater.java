package com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Activity.MainActivity;
import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Activity.Update_NotesActivity;
import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.Entity.Notes;
import com.kdapps.videoplayer.hdmaxplayer.video.notesapp.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdpater extends RecyclerView.Adapter<NotesAdpater.notesViewHolder> {
    Context mainActivity;
    List<Notes> notes = new ArrayList<>();

    public NotesAdpater(Context mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
    }

    public void setNotesList(List<Notes> notes) {
        this.notes = notes;
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    @NonNull
    @Override
    public notesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes, parent, false);
        return new notesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull notesViewHolder holder, int position) {
        Notes noteslist = notes.get(position);
        holder.title.setText(noteslist.notesTitle);
        holder.subtitle.setText(noteslist.notesSubTitle);
        holder.notesdate.setText(noteslist.notesDate);

        if (noteslist.notesPriority.equals("1")) {
            holder.priority.setBackgroundResource(R.drawable.green_shape);
        } else if (noteslist.notesPriority.equals("2")) {
            holder.priority.setBackgroundResource(R.drawable.yellow_shape);
        } else if (noteslist.notesPriority.equals("3")) {
            holder.priority.setBackgroundResource(R.drawable.red_shape);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, Update_NotesActivity.class);
                intent.putExtra("id",noteslist.id);
                intent.putExtra("title",noteslist.notesTitle);
                intent.putExtra("subtitle",noteslist.notesSubTitle);
                intent.putExtra("priority",noteslist.notesPriority);
                intent.putExtra("notes",noteslist.notes);
                mainActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class notesViewHolder extends RecyclerView.ViewHolder {
        TextView title, subtitle, notesdate;
        View priority;

        public notesViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notestitle);
            subtitle = itemView.findViewById(R.id.notessubtitle);
            notesdate = itemView.findViewById(R.id.notesDate);
            priority = itemView.findViewById(R.id.notesPriority);
        }
    }
}
