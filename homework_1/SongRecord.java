/**
 * This class represents a song which has an associated title, artist, and length in min:sec
 * @author
 *  Name: Jamieson Barkume      ID#: 113389269      Recitation: R30
 */
public class SongRecord implements Cloneable {

    private String title;
    private String artist;
    private int lengthInMinutes;
    private int lengthInSeconds;

    /**
     * default constructor for the SongRecord class
     */
    public SongRecord() {}

    /**
     * Accessor method for title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Mutator method for title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Accessor method for artist
     */
    public String getArtist() {
        return this.artist;
    }

    /**
     * Mutator method for artist
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Accessor method for length in minutes
     */
    public int getLengthInMinutes() {
        return this.lengthInMinutes;
    }

    /**
     * mutator method for lengthInMinutes.
     * @param lengthInMinutes
     *  integer value >= 0
     * @throws IllegalArgumentException
     *  throws exception if value for lengthInMinutes is negative.
     */
    public void setLengthInMinutes(int lengthInMinutes) {
        if (lengthInMinutes < 0)
            throw new IllegalArgumentException("Minutes must be a positive integer");
        else
            this.lengthInMinutes = lengthInMinutes;
    }

    /**
     * Accessor method for length in seconds
     */
    public int getLengthInSeconds() {
        return this.lengthInSeconds;
    }

    /**
     * mutator method for lengthInSeconds.
     * @param lengthInSeconds
     *  integer value within 0 and 59
     * @throws IllegalArgumentException
     *  throws exception if value for lengthInSeconds is less than zero or greater than 59.
     */
    public void setLengthInSeconds(int lengthInSeconds) {
        if (lengthInSeconds > 59 || lengthInSeconds < 0)
            throw new IllegalArgumentException("Seconds must be between 0 and 59");
        else
            this.lengthInSeconds = lengthInSeconds;
    }

    /**
     * String representation of a SongRecord object
     * @return
     *  Returns a string of neatly formatted information about the SongRecord object
     */
    public String toString() {
        return String.format("%-20s%-20s%d:%d",title, artist, lengthInMinutes, lengthInSeconds);
    }

    /**
     * tests if two song records are equal to each other
     * @param obj
     *  comparable object
     * @return
     *  Returns true if records have same title, artist, and length. False if otherwise.
     */
    public boolean equals(Object obj) {
        if (obj instanceof SongRecord) {
            boolean test1 = ((SongRecord)obj).title.equals(this.title);
            boolean test2 = ((SongRecord)obj).artist.equals(this.artist);
            boolean test3 = ((SongRecord)obj).lengthInMinutes == this.lengthInMinutes;
            boolean test4 = ((SongRecord)obj).lengthInSeconds == this.lengthInSeconds;
            return test1 && test2 && test3 && test4;
        }
        return false;
    }

    /**
     * Makes a clone of this SongRecord object
     * @return
     *  returns a songRecord object
     */
    public Object clone() {
        SongRecord song;
        try {
            song = (SongRecord)super.clone(); // Every field in SongRecord is immutable so super.clone() is all that is needed.
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }
        return song;
    }
}
