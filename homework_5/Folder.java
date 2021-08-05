import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * The <code>Folder</code> class represents an email folder and has the ability
 * to sort the emails in certain ways (i.e. date ascending).
 * @author
 *  Jamieson Barkume       ID#: 13389269        Recitation:R30
 */
public class Folder implements Serializable {

    private ArrayList<Email> emails;
    private String name;
    private String currentSortingMethod = "dateD";
    private String[] sortingMethods = {"dateA", "dateD", "subjectA", "subjectD"};

    /**
    * Constructs a default <code>Folder</code> object with a given name.
    * Initializes the emails <code>ArrayList</code> to a new list.
    * @param name
    *  The name of the folder.
    */
    public Folder(String name) {
        this.name = name;
        emails = new ArrayList<Email>();
    }

    /**
     * Returns the list of emails in this folder.
     *
     * @return The list of emails.
     */
    public ArrayList<Email> getEmails() {
        return emails;
    }

    /**
     * Sets the emails for the folder.
     *
     * @param emails The list of emails being added to the folder.
     */
    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }

    /**
     * Returns the name of the folder.
     *
     * @return Returns <code>name</code>.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the folder.
     *
     * @param name The new name of the folder.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the current sorting method of the folder.
     *
     * @return Returns the sorting method.
     */
    public String getCurrentSortingMethod() {
        return currentSortingMethod;
    }

    /**
     * Sets the current sorting method of the folder.
     *
     * @param currentSortingMethod The new sorting method.
     * @throws IllegalArgumentException Throws exception if the given sorting method is not valid.
     *                                  The current sorting method must either be "dateA", "dateB",
     *                                  "subjectA", or "subjectD", "A and D" meaning either ascending or descending.
     */
    public void setCurrentSortingMethod(String currentSortingMethod) {
        for (String str : sortingMethods) {
            if (currentSortingMethod.equals(str)) {
                this.currentSortingMethod = currentSortingMethod;
                return;
            }
        }
        throw new IllegalArgumentException("Must be in the format" +
                " \"(category)(letter)\" where letter can be either " +
                "'A' for ascending or 'D' for descending");
    }

    /**
     * Adds an email to the folder according to the current sorting method.
     *
     * @param email The email being added.
     */
    public void addEmail(Email email) {
        emails.add(email);
        switch (currentSortingMethod) {
            case "subjectA":
                sortByDateAscending();
            case "subjectD":
                sortByDateDescending();
            case "dateA":
                sortByDateAscending();
            case "dateD":
                sortByDateDescending();
        }
    }

    /**
     * Removes an email from the folder by index.
     *
     * @param index The index of the email being removed.
     * @return Returns the <code>Email</code> object removed from the folder.
     */
    public Email removeEmail(int index) {
        return emails.remove(index);
    }

    /**
     * Sorts the emails alphabetically by subject in ascending order.
     */
    public void sortBySubjectAscending() {
        currentSortingMethod = "subjectA";
        Collections.sort(emails, new SubjectComparator().reversed());
    }

    /**
     * Sorts the emails alphabetically by subject in descending order.
     */
    public void sortBySubjectDescending() {
        currentSortingMethod = "subjectD";
        Collections.sort(emails, new SubjectComparator());
    }

    /**
     * Sorts the emails alphabetically by date in ascending order.
     */
    public void sortByDateAscending() {
        currentSortingMethod = "dateA";
        Collections.sort(emails, new DateComparator().reversed());
    }

    /**
     * Sorts the emails alphabetically by date in descending order.
     */
    public void sortByDateDescending() {
        currentSortingMethod = "dateD";
        Collections.sort(emails, new SubjectComparator());
    }

    public String toString() {
        String str = String.format("%s\n\n%-8s | %-30s | %-15s\n-------------" +
                "----------------------------------------\n", name, "Index", "Time", "Subject");
        for (int i = 0; i < emails.size(); i++) {
            str += String.format("%-8d | %-30tc | %-15s\n", i+1, emails.get(i).getTimestamp().getTime(), emails.get(i).getSubject());
        }
        return str;
    }
}
