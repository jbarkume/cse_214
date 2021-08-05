import java.util.Comparator;

/**
 * The <code>DateComparator</code> class compares the dates of two <code>Email</code> objects.
 * @author
 *  Jamieson Barkume    ID#: 113389269      Recitation: R30
 */
public class DateComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        Email email1 = (Email)o1;
        Email email2 = (Email)o2;
        if (email1.getTimestamp().compareTo(email2.getTimestamp()) < 0)
            return -1; // return -1 if 1st email is older than 2nd email.
        else if (email1.getTimestamp().compareTo(email2.getTimestamp()) > 0)
            return 1; // return 1 if 1st email is newer than 2nd email.
        else
            return 0;
    }

    public Comparator reversed() {
        return Comparator.super.reversed();
    }
}
