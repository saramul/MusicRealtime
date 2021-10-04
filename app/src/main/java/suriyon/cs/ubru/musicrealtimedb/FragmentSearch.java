package suriyon.cs.ubru.musicrealtimedb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import suriyon.cs.ubru.musicrealtimedb.adapter.MusicAdapter;
import suriyon.cs.ubru.musicrealtimedb.model.Music;

public class FragmentSearch extends Fragment {
    private RecyclerView rcvSearch;
    private EditText edtSearch;
    private MaterialButton btnSearch;
    private DatabaseReference refMusic;
    private MusicAdapter adapter;
    private List<Music> musics;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        refMusic = FirebaseDatabase.getInstance().getReference("music");
        musics = new ArrayList<Music>();

        matchView(view);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = edtSearch.getText().toString();
                Query query = refMusic.orderByChild("artist").startAt(search).endAt(search+"~");

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        musics.clear();
                        for(DataSnapshot data: snapshot.getChildren()){
                            Music music = data.getValue(Music.class);

                            musics.add(music);
                        }
                        adapter = new MusicAdapter(getContext(), musics);
                        rcvSearch.setAdapter(adapter);
                        rcvSearch.setLayoutManager(new LinearLayoutManager(getContext()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                edtSearch.setText("");
            }
        });

        return view;
    }

    private void matchView(View view) {
        rcvSearch = view.findViewById(R.id.rcv_search);
        edtSearch = view.findViewById(R.id.edt_search);
        btnSearch = view.findViewById(R.id.btn_search);
    }
}
