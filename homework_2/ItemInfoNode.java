/**
 * The <code>ItemInfoNode</code> represents a node in a doubly Linked List data structure, which contains a reference to an <code>ItemInfo</code> object
 * as well as two other <code>ItemInfoNode</code> objects.
 * @author
 *  Jamieson Barkume    ID#: 113389269      Recitation: R30
 */
public class ItemInfoNode {

    // Each node will have a link to both a previous node and the next node, allowing the cursor to go back and forth / up and down the list.

    private ItemInfo info; // the info of the current item node
    private ItemInfoNode next; // the next item node
    private ItemInfoNode prev; // the previous item node

    /**
     * Initializes a single node with data and no links to any other nodes yet.
     * @param info
     *  The data of the <code>ItemInfoNode</code> Object.
     */
    public ItemInfoNode(ItemInfo info) {
        this.info = info;
        this.next = null;
        this.prev = null;
    }

    /**
     * Accessor for <code>ItemInfo</code> object contained in the node
     * @return
     *  Returns the info about the node.
     */
    public ItemInfo getInfo() {
        return this.info;
    }

    /**
     * Accessor for next <code>ItemInfoNode</code> object contained in link
     * @return
     *  Returns the link node.
     */
    public ItemInfoNode getNext() {
        return this.next;
    }

    /**
     * Accessor for previous <code>ItemInfoNode</code> object contained in previous link
     * @return
     *  Returns the previous node.
     */
    public ItemInfoNode getPrev() {
        return this.prev;
    }

    /**
     * Mutator for <code>info</code>
     * @param info
     *  The new item that will be referenced in the node
     */
    public void setInfo(ItemInfo info) {
        this.info = info;
    }

    /**
     * Mutator for the <code>next</code> node
     * @param node
     *  The new node that will be the next link of the current node
     */
    public void setNext(ItemInfoNode node) {
        this.next = node;
    }

    /**
     * Mutator for the <code>prev</code> node
     * @param node
     *  The new node that will have a link the current node
     */
    public void setPrev(ItemInfoNode node) {
        this.prev = node;
    }
}
