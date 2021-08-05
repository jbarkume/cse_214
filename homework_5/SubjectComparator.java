import java.util.Comparator;

/**
 * The <code>SubjectComparator</code> class compares the subjects of two <code>Email</code> objects.
 * @author
 *  Jamieson Barkume    ID#: 113389269      Recitation: R30
 */
public class SubjectComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        Email email1 = (Email)o1;
        Email email2 = (Email)o2;
        if (email1.getSubject().compareTo(email2.getSubject()) < 0)
            return -1; // return -1 if 1st email is older than 2nd email.
        else if(email1.getSubject().compareTo(email2.getSubject()) > 0)
            return 1; // return 1 if 2nd email is newer than 2nd email.
        else
            return 0;
    }

    public Comparator reversed() {
        return Comparator.super.reversed();
    }
}
