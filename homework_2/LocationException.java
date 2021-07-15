/**
 * Exception used for notifying user of wrong location entry
 * @author
 *  Jamieson Barkume    ID#: 113389269      Recitation: R30
 */
public class LocationException extends Exception {

    /**
     * Default Constructor.
     */
    public LocationException() {}

    /**
     * Exception is thrown with custom error message.
     * @param errorMessage
     *  The message that can be accessed by <code>getMessage()</code>
     */
    public LocationException(String errorMessage) {
        super(errorMessage);
    }
}
