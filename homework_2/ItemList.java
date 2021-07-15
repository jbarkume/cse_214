/**
 * The <code>ItemList</code> class represents a doubly linked list of ItemInfoNodes with references to a head and tail node.
 * @author
 *  Jamieson Barkume    ID#: 113389269      Recitaion: R30
 */
public class ItemList {

    private ItemInfoNode head;
    private ItemInfoNode tail;

    /**
     * Initializes a Doubly-Linked List with <code>head</code>> and <code>tail</code>> values of null
     */
    public ItemList() {
        head = null;
        tail = null;
    }

    /**
     * Accessor of head node.
     * @return
     *  Returns the head node.
     */
    public ItemInfoNode getHead() {
        return this.head;
    }

    /**
     * Accessor of tail node.
     * @return
     *  returns the tail node.
     */
    public ItemInfoNode getTail() {
        return this.tail;
    }

    /**
     * Mutator of head node.
     * @param head
     *  the new head node.
     */
    public void setHead(ItemInfoNode head) {
        this.head = head;
    }

    /**
     * Mutator of tail node.
     * @param tail
     *  the new tail node.
     */
    public void setTail(ItemInfoNode tail) {
        this.tail = tail;
    }

    /**
     * Inserts a new item into the linked list.
     * @param name
     *  The name of the item.
     * @param rfidNum
     *  the tracking # of the item.
     * @param price
     *  the price of the item.
     * @param initPosition
     *  the original location of the item.
     * @throws LocationException
     *  throws exception if <code>rfidNum</code> or <code>initPosition</code> are not valid entries.
     */
    public void insertInfo(String name, String rfidNum, double price, String initPosition) throws LocationException {
        // creates item node.
        ItemInfo item = new ItemInfo(name, price);
        item.setRfidNum(rfidNum);
        item.setOriginalLocation(initPosition);
        ItemInfoNode node = new ItemInfoNode(item);

        // if list is empty (head is null) we set the new node as the head.
        if (head == null) {
            head = node;
            head.setNext(tail);
            return;
        }

        // initializes a pointer that will traverse the list if it is not null.
        ItemInfoNode pointer = head;
        while (pointer != null) {

            // first checks if the node shoudl be inserted here by value of rfidNum
            // being less than or equal to current nodes rfidNum.
            if (Long.parseLong(rfidNum, 16) <= Long.parseLong(pointer.getInfo().getRfidNum(), 16)) {

                // Then checks to see if this happened at the head node, meaning we set a new head.
                if (pointer == head) {
                    head.setPrev(node);
                    node.setNext(head);

                    // if tail is null, then this is our second node we added to the list meaning
                    // the previous head becomes the tail and the node we added becomes the head.
                    if (tail == null)
                        tail = head;
                    head = node;
                    return; // node is inserted and we can break out of the method.
                }

                // If the pointer is not the head, then we have a list that already has a tail and
                // our gets inserted in between.
                node.setNext(pointer);
                pointer.getPrev().setNext(node);
                node.setPrev(pointer.getPrev());
                pointer.setPrev(node);
                return; // node is inserted and we can break out fo the method.
            }
            pointer = pointer.getNext(); // moves on to next node if rfidNum is still greater than every previous nodes value.
        }

        // This will only be executed if we have a head node that has an rfidNum less than our current nodes one,
        // and the head is the only element. We assign the tail the value of the node object, and connect the head and tail.
        if (tail == null) {
            tail = node;
            head.setNext(tail);
            tail.setPrev(head);
        }

        // This code will execute if we have a non-null head and tail, and our nodes rfidNum is greater than tails.
        // We make the new node the new tail and connect it to the list.
        else {
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;

            // The order of complexity will be worst case O(length of list) or O(n) because
            // the loop will run till pointer is null at worst case every time.
        }
    }

    /**
     * Removes every item that was sold. (<code>currentLocation</code> = "out")
     * @throws LocationException
     *  Throws exception if Location is invalid.
     */
    public void removeAllPurchased() throws LocationException {
        ItemInfoNode pointer = head;
        System.out.println("\nItems Removed: \n");

        // prints all the items being removed.
        printByLocation("out");
        while (pointer != null) {
            if (pointer.getInfo().getCurrentLocation().equals("out")) {
                if (pointer == head) {
                    removeHead();
                }
                else if (pointer == tail) {
                    removeTail();
                    return;
                }
                else {

                    // removes node if any in between items are found to be sold
                    pointer.getNext().setPrev(pointer.getPrev());
                    pointer.getPrev().setNext(pointer.getNext());
                }
            }
            pointer = pointer.getNext();
        }

        //Will always be O(length of list) or O(n) because we must loop till the end
        // or rather, finding an instance of "out" will not break the loop.
    }

    /**
     * Moves the item to a new location.
     * @param rfidNum
     *  The tracking # of the desired item.
     * @param source
     *  The current location of the desired item
     * @param dest
     *  the new location where the item will be moved
     * @returns
     *  returns true if item with valid RFID # and source is found and moved to right location, false if otherwise.
     * @throws LocationException
     *  Throws exception if <code>rfidNum</code>, <code>source</code>, or <code>dest</code> are invalid entries, or if the item is not found,
     *  or if <code>source</code> is passed a value of 'out'.
     */
    public boolean moveItem(String rfidNum, String source, String dest) throws LocationException {

        // Initializes new ItemInfo objet to see if rfidNum, source, and dest are valid entries.
        ItemInfo newItem = new ItemInfo();
        newItem.setRfidNum(rfidNum);
        if (source.toLowerCase().equals("out"))
            throw new LocationException("Source cannot be 'out'");
        newItem.setCurrentLocation(source);

        // traverses through the list until a match is found. We then set the items current location to dest
        // and return true.
        ItemInfoNode pointer = head;
        while(pointer != null) {
            ItemInfo item = pointer.getInfo();
            if (item.getRfidNum().equals(newItem.getRfidNum()) && item.getCurrentLocation().equals(newItem.getCurrentLocation())) {
                item.setCurrentLocation(dest);
                return true;
            }
            pointer = pointer.getNext(); // next node in the list.
        }
        // if item is not found, false is returned.
        return false;
    }

    /**
     * Prints a table of all the items in stock sorted by RFID tracking #.
     */
    public void printAll() {
        System.out.printf("\n%-20s%-20s%-20s%-20s%-20s\n\n", "Name", "Price", "RFID", "Original Location", "Current Location");
        ItemInfoNode pointer = head;
        if (pointer == null) {
            System.out.println("Stock Empty!");
            return;
        }
        while (pointer != null) {
            ItemInfo item = pointer.getInfo();
            System.out.printf("%-20s%-20.2f%-20s%-20s%-20s\n\n", item.getName(), item.getPrice(), item.getRfidNum(),
                    item.getOriginalLocation(), item.getCurrentLocation());
            pointer = pointer.getNext();
        }
    }

    /**
     * Prints a table of items in the same current location
     * @param location
     *  the location of items that will be printed
     * @throws LocationException
     *  Throws exception if location is not a valid entry.
     */
    public void printByLocation(String location) throws LocationException {
        ItemInfo item = new ItemInfo();
        item.setCurrentLocation(location);
        ItemList listByLocation = new ItemList();
        ItemInfoNode pointer = this.head;
        while (pointer != null) {
            ItemInfo newItem = pointer.getInfo();
            if (newItem.getCurrentLocation().equals(location)) {
                listByLocation.insertInfo(newItem.getName(), newItem.getRfidNum(), newItem.getPrice(), newItem.getOriginalLocation());
                listByLocation.moveItem(newItem.getRfidNum(), newItem.getOriginalLocation(), location);
            }
            pointer = pointer.getNext();
        }
        listByLocation.printAll();
    }

    /**
     * Returns every item that is in a different shelf back to its original location. Does not apply to "out" or cart locations.
     * @throws LocationException
     *  Throws exception if item is not valid entry
     */
    public void cleanStore() throws LocationException {

        // Creates a new list that stores every item moved back to its original location.
        ItemList movedBack = new ItemList();

        // traverses list to see if anything is not in orignal location excluding carts and "out".
        // If so we move it back to its original location
        ItemInfoNode pointer = this.head;
        while (pointer != null) {
            ItemInfo item = pointer.getInfo();
            if (item.getCurrentLocation().equals("out") || item.getCurrentLocation().charAt(0) == 'c') {
                pointer = pointer.getNext();
                continue; // ignores the car or "out" cases.
            }
            else if (!item.getCurrentLocation().equals(item.getOriginalLocation())) {

                // adds the item with its current location to the new list
                movedBack.insertInfo(item.getName(), item.getRfidNum(), item.getPrice(), item.getOriginalLocation());
                movedBack.moveItem(item.getRfidNum(), item.getOriginalLocation(), item.getCurrentLocation());

                // moves the item in our original list back to its original location.
                this.moveItem(item.getRfidNum(), item.getCurrentLocation(), item.getOriginalLocation());
            }
            pointer = pointer.getNext(); // next node in list.
        }
        System.out.println("\nThese Items Were moved back to their original locations: \n");
        movedBack.printAll(); // Prints which items were moved back.
    }

    /**
     * Finds the total price in given cart number and changes <code>currentLocation</code> of each <code>ItemInfo</code> object
     * to "out".
     * @param cart
     *  The cart number with the items inside.
     * @return
     *  Returns the total price of the given cart.
     * @throws LocationException
     *  Throws exception if cart number is either a correct cart number or does not exist. (Nothing is in the cart).
     */
    public double checkOut(String cart) throws LocationException {
        double total = 0;
        System.out.println("\n\nItems in Cart are:\n");
        printByLocation(cart);

        // pointer traverses list and if the current location is equal to the provided cart number we:
        // 1. Add its price to total
        // 2. move its current location to "out"
        ItemInfoNode pointer = head;
        while (pointer != null) {
            ItemInfo item = pointer.getInfo();
            if (item.getCurrentLocation().equals(cart)) {
                total += item.getPrice();
                moveItem(item.getRfidNum(), item.getCurrentLocation(), "out");
            }
            pointer = pointer.getNext(); // pointer iterates to next node.
        }
        if (total == 0) // No item has a current location of the given cart number.
            throw new LocationException("Cart Not Found");
        return total;
    }

    /**
     * Removes the head of the list.
     */
    public void removeHead() {
        if (head != null)
            head = head.getNext();
        else if (head == null)
            tail = null;
    }

    /**
     * Removes the tail of the list
     */
    public void removeTail() {
        if (tail != null) {
            tail = tail.getPrev();
            tail.setNext(null);
        }
        else if (tail == null) // In otherwords, head is the tail
            head = null;
    }
}
