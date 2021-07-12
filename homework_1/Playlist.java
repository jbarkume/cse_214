/**
 * This class represents a playlist of songs that has a max of 50 songs held in an array.
 * @author
 *  Name: Jamieson Barkume      ID#: 113389269      Recitation: R30
 */
public class Playlist implements Cloneable{

    /**
     * Max number of songs allowed in a playlist
     */
    private final int MAX_SONGS = 50;
    private SongRecord[] songs;
    private int size = 0;

    /**
     * default constructor which instantiates a playlist with no songRecords in it
     */
    public Playlist() {
        songs = new SongRecord[MAX_SONGS];
    }

    /**
     * Accessor method for songs array
     */
    public SongRecord[] getSongs() {
        return this.songs;
    }

    /**
     * Mutator method for songs array
     */
    public void setSongs(SongRecord[] songs) {
        this.songs = songs;
    }

    /**
     * Makes a clone of this playlist
     * @return
     *  Returns an object of type Object
     */
    public Object clone() {
        Playlist newPlaylist = new Playlist();
        try {
            newPlaylist = (Playlist)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }
        for (int i = 0; i < this.size(); i++) {
            newPlaylist.songs[i] = (SongRecord)this.songs[i].clone();
        }
        return newPlaylist;
    }

    /**
     * Compares two Playlist objects to see if they are equals
     * @param obj
     *  takes in an Object and compares it to given playlist
     * @return
     *  Returns true if each song in each playlist equals each other and is in same order. False if otherwise
     */
    public boolean equals(Object obj) {
        if (obj instanceof Playlist) {
            for (int i = 0; i < this.songs.length; i++) {
                if (!this.songs[i].equals(((Playlist)obj).songs[i]))
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * This method finds how many songs are in the playlist.
     * @return
     * returns size of playlist as integer (0-50);
     */
    public int size() {
        return size;
    }

    /**
     * inserts a song at the given position. Preconditions: playlist size less than 50, and position is within one and size plus one.
     * @param song
     *  takes a songRecord object
     * @param position
     *  takes an integer where the song will be stored
     * @throws IllegalArgumentException
     *  throws exception if position is not within one and size plus one.
     * @throws FullPlaylistException
     *  throws exception if playlist is already full
     */
    public void addSong(SongRecord song, int position) throws FullPlaylistException {
        if (this.size() == MAX_SONGS)
            throw new FullPlaylistException("PlayList is full!");
        if (position < 1 || position > this.size() + 1 || position > MAX_SONGS)
            throw new IllegalArgumentException("Position must be within 1 and " + (this.size()+1));
        for (int i = this.size()-1; i >= position-1; i--) {
            songs[i+1] = songs[i];
        }
        songs[position-1] = song;
        size++;
    }

    /**
     * This method removes a song from the playlist. Preconditions: This PlayList object has been instantiated, and position is within one and size plus one.
     * @param position
     *  position (index+1) of song to be removed
     * @throws IllegalArgumentException
     *  throws exception if position is not between 1 and size of playlist
     */
    public void removeSong(int position) throws IllegalArgumentException{
        if (position < 1 || position > this.size())
            throw new IllegalArgumentException("Position of song to be removed must be within 1 and " + this.size());
        int i = position-1;
        while (this.songs[i] != null && i != MAX_SONGS-1) {
            this.songs[i] = this.songs[i+1];
            i++;
        }
        if (i == MAX_SONGS-1)
            this.songs[i] = null;
        size--;
    }

    /**
     * Gets the SongRecord object at given position. Preconditions: This PlayList object has been instantiated, and position is within one and size plus one.
     * @param position
     *  position of desired song.
     * @return
     *  returns SongRecord object at given position
     * @throws IllegalArgumentException
     *  throws exception if position is not between one and size of playlist.
     */
    public SongRecord getSong(int position) {
        if (position < 1 || position > this.size())
            throw new IllegalArgumentException("Position must be within 1 and " + this.size());
        return this.songs[position-1];
    }

    /**
     * Prints all songs in playlist with position. Preconditions: Preconditions: This PlayList object has been instantiated.
     */
    public void printAllSongs() {
        System.out.printf("%-10s%-20s%-20sLength\n\n", "#", "Title", "Artist");
        for (int i = 0; i < this.size(); i++) {
            System.out.printf("%-10d%s\n", (i+1), songs[i]);
        }
    }

    /**
     * Creates a playlist of songs from a Playlist object all by a specified artist. The Playlist referred to by originalList has been instantiated.
     * @param originalList
     *  The original playlist
     * @param artist
     *  The artist of the song
     * @return
     *  returns a Playlist object with songs only by the specified artist from the original playlist. returns null if original playlist or artist is null.
     * @throws FullPlaylistException
     *  throws exception if more than 50 songs by artist
     */
    public static Playlist getSongsByArtist(Playlist originalList, String artist) throws FullPlaylistException {
        if (originalList == null || artist == null)
            return null;
        Playlist newPlaylist = new Playlist();
        int position = 1;
        for (int i = 1; i <= originalList.size(); i++) {
            if (originalList.getSong(i).getArtist().equals(artist)) {
                newPlaylist.addSong(originalList.getSong(i), position);
                position++;
            }
        }
        return newPlaylist;
    }

    /**
     * Creates tabular format of songs in playlist with their associated position number.
     * @return
     *  returns a string representation of the Playlist object.
     */
    public String toString() {
        String str = String.format("%-10s%-20s%-20sLength\n\n", "#", "Title", "Artist");
        for (int i = 0; i < this.size(); i++) {
            str += String.format("%-10d%s\n", (i+1), songs[i]);
        }
        return str;
    }
}
