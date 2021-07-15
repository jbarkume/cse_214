
/**
 * The <code>ItemInfo</code> class has the information of a specific item being sold/bought with an associated name, price, tracking number, and locations.
 * @author
 *  Jamieson Barkume    ID#: 113389269      Recitation: R30
 */
public class ItemInfo implements Cloneable {

    private String name;
    private double price;

    /**
     * RFID tracking number (Hexadecimal). See mutator for requirements.
     */
    private String rfidNum;
    private String originalLocation; // Orignal shelf location of product
    private String currentLocation; // Current shelf location of product

    /**
     * Default Constructor
     */
    public ItemInfo() {}

    /**
     * Constructor that sets a <code>name</code> and <code>price</code> value to the item.
     * @param name
     *  The set value of the items <code>name</code>
     * @param price
     *  The set value of the items <code>price</code>
     */
    public ItemInfo(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Accessor for <code>name</code>
     * @return
     *  returns the name of the item.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Accessor for <code>price</code>
     * @return
     *  returns the price of the item.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Accessor for <code>rfidNum</code>
     * @return
     *  returns the RFID tracking# of the item.
     */
    public String getRfidNum() {
        return this.rfidNum;
    }

    /**
     * Accessor for <code>originalLocation</code>
     * @return
     *  returns the original location of the item.
     */
    public String getOriginalLocation() {
        return this.originalLocation;
    }

    /**
     * Accessor for <code>currentLocation</code>
     * @return
     *  returns the current location of the item.
     */
    public String getCurrentLocation() {
        return this.currentLocation;
    }

    /**
     * Mutator for Item's <code>name</code>
     * @param name
     *  the new value for <code>name</code>
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Mutator for price. Takes in a double value
     * @param price
     *  the new value for <code>price</code> as a double
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Mutator for price. Takes in a String value.
     * @param price
     *  the new value for <code>price</code> as a string
     */
    public void setPrice(String price) {
        this.price = Double.parseDouble(price);
    }

    /**
     * Mutator for RFID tracking#.
     * @param rfidNum
     *  The new value of RFID tracking#.
     * @throws LocationException
     *  Throws exception if <code>rfidNum</code> is not a hexadecimal number of 9 digits. (Each digit must be 0-9 or A-F)
     */
    public void setRfidNum(String rfidNum) throws LocationException {
        if (rfidNum.length() != 9)
            throw new LocationException("RFID# Must be 9 digits");
        try {
            Long.parseLong(rfidNum,16);
        } catch (NumberFormatException e) {
            throw new LocationException("RFID# Must be hexadecimal");
        }
        this.rfidNum = rfidNum;
    }

    /**
     * Mutator for the Original Location shelf number
     * @param originalLocation
     *  The new value for <code>originalLocation</code>.
     * @throws LocationException
     *  throws Exception if <code>originalLocation</code>> is not a string that starts with s and has a 5-digit number follow ("s10034", "s35449")
     */
    public void setOriginalLocation(String originalLocation) throws LocationException {
        if (originalLocation.charAt(0) != 's')
            throw new LocationException("First letter must be 's' for for original location");
        try {
            Integer.parseInt(originalLocation.substring(1,originalLocation.length()));
        } catch (Exception e) {
            throw new LocationException("a 5-digit integer must follow " + originalLocation.charAt(0) + " for original location");
        }
        if (originalLocation.length() != 6)
            throw new LocationException("Length of original location number must be 6");
        this.originalLocation = originalLocation;
        this.currentLocation = originalLocation;
    }

    /**
     * Mutator for the Current Location.
     * @param currentLocation
     *  The new value for <code>currentLocation</code>.
     *  @throws LocationException
     *   Throws exception if <code>currentLocation</code> is not either a shelf number like original location, "out" (meaning it was sold), or a cart number
     *    where the first letter is a "c" followed by a 3-digit number ("c153", "c761").
     */
    public void setCurrentLocation(String currentLocation) throws LocationException {
        if (currentLocation.toLowerCase().equals("out")) {
            this.currentLocation = currentLocation.toLowerCase();
            return;
        }
        else if (currentLocation.charAt(0) == 's') {
            if (currentLocation.length() != 6)
                throw new LocationException("5 digits must follow 's' for shelf location");
        }
        else if (currentLocation.charAt(0) == 'c') {
            if (currentLocation.length() != 4)
                throw new LocationException("3 digits must follow 'c' for cart location");
        }
        else
            throw new LocationException("First letter must start with either 's' or 'c' or be 'out' for location");
        try {
            Integer.parseInt(currentLocation.substring(1,currentLocation.length()));
        } catch (Exception e) {
            throw new LocationException("an integer must follow " + currentLocation.charAt(0) + " for location");
        }
        this.currentLocation = currentLocation;
    }

    /**
     * Tests if two <code>ItemInfo</code> objects are equal.
     * @param obj
     *  the object that is being compared.
     * @return
     *  returns true if <code>name</code>, <code>price</code>, <code>rfidNum</code>, <code>originalLocation</code>, and <code>currentLocation</code> are the same.
     *  returns false if otherwise.
     */
    public boolean equals(Object obj) {
        if (obj instanceof ItemInfo) {
            boolean test1 = this.name.equals(((ItemInfo)obj).name);
            boolean test2 = this.price == ((ItemInfo)obj).price;
            boolean test3 = this.rfidNum.equals(((ItemInfo)obj).rfidNum);
            boolean test4 = this.originalLocation.equals(((ItemInfo)obj).originalLocation);
            boolean test5 = this.currentLocation.equals(((ItemInfo)obj).currentLocation);
            return test1 && test2 && test3 && test4 && test5;
        }
        return false;
    }

    /**
     * Makes a copy of this <code>ItemInfo</code> object
     * @return
     *  returns a copy of this object.
     */
    public Object clone() {
        ItemInfo newItem = new ItemInfo();
        try {
            newItem = (ItemInfo)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }
        return newItem;
    }
}
