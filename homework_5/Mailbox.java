import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
* The <code>Mailbox</code> class represents a mailbox program that can add and remove folders, 
* with other functions such as viewing the contents of each folder, deleting emails, and clearing the trash folder.
* Each <code>Mailbox</code> object comes with a predefined inbox folder, trash folder and a list user created folders,
* including the inbox and trash folders.
* @author
*   Jamieson Barkume    ID#: 113389269      Recitation: R30
*/
public class Mailbox implements Serializable {

    private Folder inbox;
    private Folder trash;
    private ArrayList<Folder> folders;
    public static Mailbox mailbox;

    /**
     * Constructs a default <code>Mailbox</code> object.
     */
    public Mailbox() {
        inbox = new Folder("Inbox");
        trash = new Folder("Trash");
        folders = new ArrayList<Folder>();
        folders.add(inbox);
        folders.add(trash);
    }

    /**
     * Adds a folder to the <code>Mailbox</code> object.
     * @param folder
     *  The folder being added.
     */
    public void addFolder(Folder folder) {
        for (Folder fol : folders) {
            if (folder.getName().equals(fol.getName())) {
                System.out.println("Folder name already exists!");
                return;
            }
        }
        folders.add(folder);
    }

    /**
     * Deletes a folder from the <code>Mailbox</code> object. Cannot delete the "Inbox"
     * or "Trash" folders.
     * @param name
     *  The name of the folder.
     */
    public void deleteFolder(String name) {
        if (name.equals("Inbox") || name.equals("Trash")) {
            System.out.println("Cannot delete this folder!");
            return;
        }
        for (Folder fol : folders) {
            if (name.equals(fol.getName())) {
                folders.remove(fol);
                return;
            }
        }
        System.out.println("Folder was not found!");
    }

    /**
     * Gets the data for an <code>Email</code> and adds it to the inbox folder.
     */
    public void composeEmail() {
        Scanner input = new Scanner(System.in);
        Email email = new Email();

        System.out.print("To: ");
        email.setTo(input.nextLine());

        System.out.print("CC: ");
        email.setCc(input.nextLine());

        System.out.print("BCC: ");
        email.setBcc(input.nextLine());

        System.out.print("Subject: ");
        email.setSubject(input.nextLine());

        System.out.println("Body: ");
        email.setBody(input.nextLine());

        inbox.addEmail(email);
    }

    /**
     * Moves the given email to the trash folder.
     * @param email
     *  The given email.
     */
    public void deleteEmail(Email email) {
        trash.addEmail(email);
    }

    /**
     * Clears the trash folder.
     */
    public void clearTrash() {
        while(trash.getEmails().size() != 0) {
            trash.removeEmail(0);
        }
        System.out.println("Trash Cleared!");
    }

    /**
     * Moves the given email to the given folder.
     * @param email
     *  The given email.
     * @param target
     *  The given folder.
     */
    public void moveEmail(Email email, Folder target) {
        Folder folder = getFolder(target.getName());
        if (folder == null)
            folder = inbox;
        folder.addEmail(email);
    }

    /**
     * Gets a certain folder.
     * @param name
     *  The name of the folder.
     * @return
     *  Returns the folder with the matching name.
     *  Returns null if the folder was not found.
     */
    public Folder getFolder(String name) {
        for (Folder fol : folders) {
            if (fol.getName().equals(name))
                return fol;
        }
        System.out.println("Folder was not found!");
        return null;
    }

    /**
     * Returns a string representation of a <code>Mailbox</code> object.
     * Prints the current folders in the mailbox including the "Inbox" and "Trash" folders.
     * @return
     *  Returns a string representing the mailbox
     */
    public String toString() {
        String str = "\nMailbox\n-------\n";
        for (int i = 0; i < folders.size(); i++) {
            str += folders.get(i).getName() + "\n";
        }
        return str;
    }

    /**
     * Prints the starting menu that allows the user pick what to do with the mailbox.
     */
    public void printStartingMenu() {
        System.out.println("\nA >> Add Folder");
        System.out.println("R >> Remove Folder");
        System.out.println("C >> Compose Email");
        System.out.println("F >> View Folder");
        System.out.println("I >> View Inbox");
        System.out.println("T >> View Trash");
        System.out.println("E >> Empty Trash");
        System.out.println("Q >> Quit\n");
    }

    /**
     * Prints the submenu when the user wants to manipulate a certain folder.
     */
    public void printFolderSubmenu() {
        System.out.println("\nM >> Move Email");
        System.out.println("D >> Delete Email");
        System.out.println("V >> View Email Contents");
        System.out.println("SA >> Sort by Subject Ascending");
        System.out.println("SD >> Sort by Subject Descending");
        System.out.println("DA >> Sort by Date Ascending");
        System.out.println("DD >> Sort by Date Descending");
        System.out.println("R >> Return to Main Menu\n");
    }

    /**
    * Handles what to do when the user selects to view either
    * the inbox, the trash, or any other folder. Will stay in the folder
    * submenu until the user selects "R". 
    *@param folder
    * The folder being viewed.
    */
    public void viewFolder(Folder folder) {
        Scanner input = new Scanner(System.in);
        Email email;
        String name;
        String answer = "";

        if (folder == null)
            return;

        // Will stay in the selected folder until "R" is selected.
        while (!answer.equals("R")) {
            if (folder.getEmails().size() == 0) {
                System.out.println("\n" + folder.getName() + " is empty.");
                answer = "R";
            }
            else { // Print contents of folder and get users option.
                System.out.println("\n" + folder);
                printFolderSubmenu();
                System.out.print("\nEnter Option: ");
                answer = input.nextLine().toUpperCase();
            }

            // submenu switch case.
            switch (answer) {
                case "M": // Move email.
                    System.out.print("\nEnter Index of Email: ");
                    email = folder.removeEmail(input.nextInt()-1);
                    input.nextLine();
                    System.out.println(this);
                    System.out.print("\nEnter Folder to move \"" + email.getSubject() + "\" into: ");
                    name = input.nextLine();
                    Folder target = getFolder(name);
                    if (target == null)
                        break;
                    moveEmail(email, target);
                    System.out.println("\n\"" + email.getSubject() + "\" moved to \"" + target.getName() + "\"");
                    break;
                case "D": // Delete email.
                    System.out.print("\nEnter Index of Email: ");
                    email = folder.removeEmail(input.nextInt()-1);
                    input.nextLine();
                    moveEmail(email, trash);
                    System.out.println("\n\"" + email.getSubject() + "\" moved to trash");
                    break;
                case "V": // View contents of certain email.
                    System.out.print("\nEnter Index of Email: ");
                    System.out.println(folder.getEmails().get(input.nextInt()-1));
                    input.nextLine();
                    System.out.print("\n\nPress Enter to Continue...");
                    input.nextLine();
                    break;
                case "SA":
                    folder.sortBySubjectAscending();
                    break;
                case "SD":
                    folder.sortBySubjectDescending();
                    break;
                case "DA":
                    folder.sortByDateAscending();
                    break;
                case "DD":
                    folder.sortByDateDescending();
                    break;
                default:
                    if (!answer.equals("R"))
                        System.out.println("\nInvalid Choice!");
            }
        }
    }

    // Main method.
    public static void main(String[] args) {
        Mailbox mainMailbox;
        
        // read in the object from the "mailbox.obj" file if it exists,
        // and assign its value to the mainMailbox object.
        try {
            FileInputStream   file = new FileInputStream("mailbox.obj");
            ObjectInputStream fin  = new ObjectInputStream(file);
            mainMailbox = (Mailbox) fin.readObject();
            file.close();
        } catch (Exception e) {
            mainMailbox = new Mailbox();
        }

        String answer = "";
        Scanner input = new Scanner(System.in);
        Folder folder;
        Email email;

        while(!answer.equals("Q")) {
            System.out.println(mainMailbox);
            mainMailbox.printStartingMenu();

            System.out.print("Enter Option: ");
            answer = input.nextLine().trim().toUpperCase();
            switch(answer) {
                case "A": // Add folder.
                   System.out.print("\nEnter Folder Name: ");
                   String name = input.nextLine();
                   folder = new Folder(name);
                   mainMailbox.addFolder(folder);
                   break;
                case "R": // Remove folder.
                    System.out.print("\nEnter Folder Name: ");
                    name = input.nextLine();
                    mainMailbox.deleteFolder(name);
                    break;
                case "C": // Create email.
                    mainMailbox.composeEmail();
                    break;
                case "F": // View folder.
                    System.out.print("\nEnter Folder Name: ");
                    name = input.nextLine();
                    folder = mainMailbox.getFolder(name);
                    mainMailbox.viewFolder(folder);
                    break;

                // View inbox.
                case "I":
                    mainMailbox.viewFolder(mainMailbox.inbox);
                    break;
                case "T": // View trash.
                    mainMailbox.viewFolder(mainMailbox.trash);
                    break;
                case "E": // Clear Trash
                    mainMailbox.clearTrash();
                    break;
                default:
                    if (!answer.equals("Q"))
                        System.out.println("\nInvalid Choice!");
            }
        }



        // Write the mainMailbox object to the "mailbox.obj" file.
        try {
            FileOutputStream file = new FileOutputStream("mailbox.obj");
            ObjectOutputStream fout = new ObjectOutputStream(file);
            fout.writeObject(mainMailbox);
            fout.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
