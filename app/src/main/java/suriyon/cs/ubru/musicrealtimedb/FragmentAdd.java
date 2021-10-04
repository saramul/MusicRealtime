package suriyon.cs.ubru.musicrealtimedb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import suriyon.cs.ubru.musicrealtimedb.model.Music;

public class FragmentAdd extends Fragment {
    private TextInputEditText edtSong, edtArtist, edtGenre;
    private MaterialButton btnAdd;
    private DatabaseReference refMusic;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        refMusic = FirebaseDatabase.getInstance().getReference("music");
        matchView(view);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String song = edtSong.getText().toString();
                String artist = edtArtist.getText().toString();
                String genre = edtGenre.getText().toString();

                if(song.isEmpty() || artist.isEmpty() || genre.isEmpty()) {
                    Toast.makeText(getContext(), "please full fill data!", Toast.LENGTH_SHORT).show();
                    edtSong.requestFocus();
                }else{
                    String id = refMusic.push().getKey();
                    Music music = new Music(song, artist, genre);

                    refMusic.child(id).setValue(music);

                    clearData();
                }
            }
        });
        return view;
    }

    private void clearData() {
        edtArtist.setText("");
        edtGenre.setText("");
        edtSong.setText("");
        edtSong.requestFocus();
    }

    private void matchView(View view) {
        edtSong = view.findViewById(R.id.tiedt_song_name);
        edtArtist = view.findViewById(R.id.tiedt_artist_name);
        edtGenre = view.findViewById(R.id.tiedt_genre_name);
        btnAdd = view.findViewById(R.id.btn_add);
    }
}
