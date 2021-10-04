package suriyon.cs.ubru.musicrealtimedb;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import suriyon.cs.ubru.musicrealtimedb.adapter.MusicAdapter;
import suriyon.cs.ubru.musicrealtimedb.model.Music;

public class FragmentHome extends Fragment {
    private RecyclerView rcvMusic;
    private MusicAdapter adapter;
    private List<Music> musics;
    private DatabaseReference refMusic;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        refMusic = FirebaseDatabase.getInstance().getReference("music");
        musics = new ArrayList<Music>();
        matchView(view);

        refMusic.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                musics.clear();
                for (DataSnapshot data: snapshot.getChildren()) {
                    Music music = data.getValue(Music.class);

                    musics.add(music);

                }

                adapter = new MusicAdapter(getContext(), musics);
                rcvMusic.setAdapter(adapter);
                rcvMusic.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }

    private void matchView(View view) {
        rcvMusic = view.findViewById(R.id.rcv_music);
    }
}
