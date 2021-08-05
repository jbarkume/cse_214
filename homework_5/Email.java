import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * The <code>Email</code> class represents an email that will store important information in a usual email,
 * such as who it is going to, who is copied, the subject, the body, and when it was sent.
 * @author
 *  Jamieson Barkume    ID# 113389269       Recitation:R30
 */
public class Email implements Serializable {

    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String body;
    private GregorianCalendar timestamp;

    /**
     * Constructs a default <code>Email</code> object.
     */
    public Email() {
        timestamp = new GregorianCalendar();
    }

    /**
     * Constructs an <code>Email</code> object with the values given for the <code>to</code>
     * field and the <code>subject</code> field.
     * @param to
     *  Who the email is being sent to.
     * @param subject
     *  The subject of the email.
     */
    public Email(String to, String subject) {
        this.to = to;
        this.subject = subject;
        timestamp = new GregorianCalendar();
    }

    /**
     * Returns who the email is being sent to.
     * @return
     *  Returns the value of <code>to</code>.
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets who the email is being sent to.
     * @param to
     *  The email of the recipient.
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Returns who the email is being copied to.
     * @return
     *  Returns the value of <code>cc</code>.
     */
    public String getCc() {
        return cc;
    }

    /**
     * Sets who the email is being copied to.
     * @param cc
     *  The email of the copied recipient.
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * Returns who the email is being bcc'd to.
     * @return
     *  Returns the value of <code>bcc</code>.
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * Sets who the email is being bcc'd to.
     * @param bcc
     *  The email of the bcc'd recipient.
     */
    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    /**
     * Returns the subjet of the email.
     * @return
     *  Returns the value of <code>subject</code>.
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the email.
     * @param subject
     *  The subject of the email.
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Returns the body of the email.
     * @return
     *  Returns the value of <code>body</code>.
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the bodu of the email.
     * @param body
     *  The body of the email.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Returns the time that the email was sent.
     * @return
     *  Returns the value of <code>timestamp</code>.
     */
    public GregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the email.
     * @param timestamp
     *  The time the email was sent.
     */
    public void setTimestamp(GregorianCalendar timestamp) {
        this.timestamp = timestamp;
    }

    public String toString() {
        return "\nTo: '" + to + '\'' +
                "\nCC: '" + cc + '\'' +
                "\nBCC: '" + bcc + '\'' +
                "\nSubject: '" + subject + '\'' +
                "\nBody: '" + body + '\'' +
                "\nDate: " + timestamp.getTime();
    }
}
