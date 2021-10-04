package suriyon.cs.ubru.musicrealtimedb.model;

public class Music {
    private String song;
    private String artist;
    private String genre;

    @Override
    public String toString() {
        return this.song;
    }

    public Music() {
    }

    public Music(String song, String artist, String genre) {
        this.song = song;
        this.artist = artist;
        this.genre = genre;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
