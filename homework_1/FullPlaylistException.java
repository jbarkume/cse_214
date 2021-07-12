/**
 * this class is a subclass of the Exception class used for when Playlist is full and user tries to add a song.
 * @author
 *  Name: Jamieson Barkume      ID#: 113389269      Recitation: R30
 */
public class FullPlaylistException  extends Exception {

    // default constructor
    public FullPlaylistException() {}

    public FullPlaylistException(String errorMessage) {
        super(errorMessage);
    }
}
