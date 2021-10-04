package suriyon.cs.ubru.musicrealtimedb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import suriyon.cs.ubru.musicrealtimedb.R;
import suriyon.cs.ubru.musicrealtimedb.model.Music;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {
    private Context context;
    private List<Music> musics;

    public MusicAdapter(Context context, List<Music> musics) {
        this.context = context;
        this.musics = musics;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.music_item, parent, false);

        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        Music music = musics.get(position);

        holder.tvSong.setText(music.getSong());
        holder.tvArtist.setText(music.getArtist());
        holder.tvGenre.setText(music.getGenre());
    }

    @Override
    public int getItemCount() {
        return musics.size();
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder {
        TextView tvSong, tvArtist, tvGenre;
        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSong = itemView.findViewById(R.id.tv_song_name);
            tvArtist = itemView.findViewById(R.id.tv_artist_name);
            tvGenre = itemView.findViewById(R.id.tv_genre_name);
        }
    }
}
