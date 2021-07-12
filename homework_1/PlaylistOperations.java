import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is used as a user menu that allows the user to create a playlist and perform different functions such as add or remove songs, etc.
 * @author
 *  Name: Jamieson Barkume      ID#: 113389269      Recitation: R30
 */
public class PlaylistOperations {

    private static final String[] options = {"A","B", "G","S","Q","P","R"};

    /**
     * prints starting menu
     */
    private static void printMenu() {
        System.out.printf("%-25sA: <Title> <Artist> <Minutes> <Seconds> <Position>\n", "Add Song");
        System.out.printf("%-25sG: <Position>\n", "Get Song");
        System.out.printf("%-25sR: <Position>\n", "Remove Song");
        System.out.printf("%-25sP:\n", "Print All Songs");
        System.out.printf("%-25sB: <Artist>\n", "Print Songs By Artist");
        System.out.printf("%-25sS: \n", "Size");
        System.out.printf("%-25sQ: \n", "Quit");
    }

    /**
     * gets the answer of what the user would like to do
     * @return
     *  returns a String of a single letter
     */
    private static String getAnswer() {
        Scanner input = new Scanner(System.in);
        printMenu();
        System.out.print("Please Choose An Option: ");
        String answer = input.next().toUpperCase();
        boolean flag = false;
        for (String i : options){
            if (answer.equals(i)) {
                flag = true;
                break;
            }
        }
        if (flag)
            return answer;
        else
            System.out.println("\n\nPlease choose a CORRECT letter\n\n");
            return getAnswer();
    }

    /**
     * This method handles adding a song to the users playlist. Expects integers for minute and second values
     * @return
     *  Returns a SongRecord Object
     * @throws IllegalArgumentException
     *  throws exception if minutes < 0, seconds not between 0 and 59
     */
    private static SongRecord caseA() {
        Scanner input = new Scanner(System.in);
        SongRecord song = new SongRecord();
        try {
            System.out.print("Enter Title: ");
            String title = input.nextLine();
            song.setTitle(title);
            System.out.print("Enter Artist: ");
            String artist = input.nextLine();
            song.setArtist(artist);
            System.out.print("Enter Minutes: ");
            int min = input.nextInt();
            song.setLengthInMinutes(min);
            System.out.print("Enter Seconds: ");
            int sec = input.nextInt();
            song.setLengthInSeconds(sec);
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException();
        }
        return song;
    }

    /**
     * Finds desired position from user. Expects integer for position value.
     * @return
     *  returns the position.
     * @throws IllegalArgumentException
     *  throws exception if position is not within 1 and current num of songs + 1.
     */
    private static int getPosition() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.print("Enter Position: ");
            return input.nextInt();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("Position Must be an Integer!!!");
        }
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Playlist userPlaylist = new Playlist();
        while (true) {
            String answer = getAnswer();
            if (answer.equals("Q"))
                    break;
            switch (answer) {
                case "A" -> {
                    try {
                        SongRecord song = caseA();
                        userPlaylist.addSong(song, getPosition());
                    } catch (IllegalArgumentException e) {
                        System.out.println("\n\n" + e.getMessage());
                    } catch (FullPlaylistException e) {
                        System.out.println("\n\nPlaylist is already full!");
                    }
                }
                case "G" -> System.out.println("\n\n" + userPlaylist.getSong(getPosition()));
                case "R" -> userPlaylist.removeSong(getPosition());
                case "P" -> System.out.println("\n\n" + userPlaylist);
                case "B" -> {
                    try {
                        System.out.print("Enter Artist: ");
                        Playlist newPlaylist = Playlist.getSongsByArtist(userPlaylist, input.nextLine());
                        System.out.println(newPlaylist);
                    } catch (FullPlaylistException e) {
                        System.out.println("\n\nPlaylist is already full!");
                    }
                }
                case "S" -> System.out.println("\n\nThere are " + userPlaylist.size() + " songs in this playlist");
            }
            System.out.println("\n\n");
        }
    }
}
