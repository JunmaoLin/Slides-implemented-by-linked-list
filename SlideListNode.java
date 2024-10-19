/**
 * This class wraps a Slide object to allow it to be inserted into a doubly linked-list data structure.
*/

public class SlideListNode{
    private Slide data;
    private SlideListNode next;
    private SlideListNode prev;

    /**
     * Default constructor.
     * @param initData
     *  The data to be wrapped by this SlideListNode.
     * Preconditions: initData is not null.
     * Post-conditions: This SlideListNode has been initialized to wrap the indicated Slide, and prev and next have been set to null.
     * @throws IllegalArgumentException
     *  Thrown if initData is null.
     */
    public SlideListNode(Slide initData) throws IllegalArgumentException{
        if(initData == null){
            throw new IllegalArgumentException("initData is null.");
        }
        this.data = initData;
        this.next = null;
        this.prev = null;
    }

    /**
     * Gets the reference to the data member variable of the list node.
     * @return
     *  The next SlideListNode.
     */
    public Slide getData(){
        return this.data;
    }

    /**
     * Updates the data member variable with a reference to a new Slide.
     * @param newData
     *  Reference to a new Slide object to update the data member variable. 
     * Preconditions: newData is not null.
     * @throws IllegalArgumentException
     *  Thrown if newData is null.
     */
    public void setData(Slide newData) throws IllegalArgumentException{
        if(newData == null){
            throw new IllegalArgumentException("newData is null.");
        }
        this.data = newData;
    }

    /**
     * Gets the reference to the next member variable of the list node.
     * @return
     *  The reference of the next member variable.
     */
    public SlideListNode getNext(){
        return this.next;
    }

    /**
     * Updates the next member variable with a new SlideListNode reference.
     * @param newNext
     *  Reference to a new SlideListNode object to update the next member variable.
     */
    public void setNext(SlideListNode newNext){
        this.next = newNext;
    }

    /**
     * Gets the reference to the prev member variable of the list node.
     * @return
     *  The reference of the prev member variable.
     */
    public SlideListNode getPrev(){
        return this.prev;
    }

    /**
     * Updates the prev member variable with a new SlideListNode reference.
     * @param newPrev
     *  Reference to a new SlideListNode object to update the prev member variable.
     */
    public void setPrev(SlideListNode newPrev){
        this.prev = newPrev;
    }
}