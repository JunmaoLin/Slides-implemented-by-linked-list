/**
 * This class implements a double linked-list data structure.
*/
import java.math.BigDecimal;
public class SlideList{
    private SlideListNode head;
    private SlideListNode tail;
    private SlideListNode cursor;
    private int size;

    /**
     * Default constructor which initializes this object to an empty list of Slides.
     * Post-conditions: This SlideList has been initialized with head, tail, and cursor all set to null.
     */
    public SlideList() {
        this.head = null;
        this.tail = null;
        this.cursor = null;
    }

    /**
     * Returns the total number of Slides in the slideshow.
     * @return
     *  The count of all Slides in the slideshow.
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns the total duration of the slideshow.
     * @return
     *  The sum of all individual Slide durations in the slideshow.
     */
    public double duration(){
        double duration = 0.0;
        SlideListNode temp = head;
        while(temp.getNext() != null){
            duration += temp.getData().getDuration();
            temp = temp.getNext();
        }
        return duration;
    }

    /**
     * Returns the total number of bullet points in the slideshow.
     * @return
     *  The sum of all bullet points of all individual Slides in the slideshow.
     */
    public int numBullets(){
        int numBullets = 0;
        SlideListNode temp = head;
        while(temp.getNext() != null){
            numBullets += cursor.getData().getNumBullets();
            temp = temp.getNext();
        }
        return numBullets;
    }

    /**
     * Gets the reference to the Slide wrapped by the SlideListNode currently referenced by cursor.
     * @return
     *  The reference of the Slide wrapped by the SlideListNode currently referenced by cursor.
     */
    public Slide getCursorSlide(){
        return cursor.getData();
    }

    /**
     * Returns the cursor to the start of the list.
     * Post-conditions: If head is not null, the cursor now references the first SlideListNode in this list.
     * If head is null, the cursor is set to null as well (there are no Slides in this list).
     */
    public void resetCursorToHead(){
        cursor = head;
    }

    /**
     * Moves the cursor to select the next SlideListNode in the list.
     * @throws EndOfListException
     *  Thrown if cursor is at the tail of the list.
     */
    public void cursorForward() throws EndOfListException{
        if(cursor == tail){
            throw new EndOfListException("Cursor is at the tail of the list.");
        }
        cursor = cursor.getNext();
    }

    /**
     * Moves the cursor to select the previous SlideListNode in the list.
     * @throws EndOfListException
     *  Thrown if cursor is at the head of the list.
     */
    public void cursorBackward() throws EndOfListException{
        if(cursor == head){
            throw new EndOfListException("Cursor is at the head of the list.");
        }
        cursor = cursor.getPrev();
    }

    /**
     * Inserts the indicated Slide before the cursor.
     * Preconditions: newSlide is not null.
     * Post-conditions: newSlide has been wrapped in a new SlideListNode object
     * If cursor was previously not null, the newly created SlideListNode has been inserted into the list before the cursor.
     * If cursor was previously null, the newly created SlideListNode has been set as the new head of the list (as well as the tail).
     * The cursor now references the newly created SlideListNode.
     * @param newSlide
     *  The Slide object to be wrapped and inserted into the list before the cursor.
     * @throws IllegalArgumentException
     *  Thrown if newSlide is null.
     */
    public void insertBeforeCursor(Slide newSlide) throws IllegalArgumentException{
        if(newSlide == null){
            throw new IllegalArgumentException("newSlide is null.");
        }
        SlideListNode slideToBeInserted = new SlideListNode(newSlide);
        size++;
        if(cursor == null){
            head = slideToBeInserted;
            cursor = slideToBeInserted;
            tail = slideToBeInserted;
        }
        else if(cursor.getPrev() == null){
            head = slideToBeInserted;
            cursor.setPrev(slideToBeInserted);
            slideToBeInserted.setNext(cursor);
            cursor = cursor.getPrev();
        }
        else{
            slideToBeInserted.setPrev(cursor.getPrev());
            cursor.getPrev().setNext(slideToBeInserted);
            slideToBeInserted.setNext(cursor);
            cursor.setPrev(slideToBeInserted);
            cursor = cursor.getPrev();
        }
    }

    /**
     * Inserts the indicated Slide after the tail of the list.
     * Preconditions: newSlide is not null.
     * Post-conditions: newSlide has been wrapped in a new SlideListNode object
     * If tail was previously not null, the newly created SlideListNode has been inserted into the list after the tail.
     * If tail was previously null, the newly created SlideListNode has been set as the new head of the list (as well as the tail).
     * The tail now references the newly created SlideListNode.
     * @param newSlide
     *  The Slide object to be wrapped and inserted into the list after the tail of the list.
     * @throws IllegalArgumentException
     *  Thrown if newSlide is null.
     */
    public void appendToTail(Slide newSlide) throws IllegalArgumentException{
        if(newSlide == null){
            throw new IllegalArgumentException("newSlide is null.");
        }
        size++;
        SlideListNode slideToBeInserted = new SlideListNode(newSlide);
        if(tail == null){
            head = slideToBeInserted;
            tail = slideToBeInserted;
            cursor = slideToBeInserted;
        }
        else {
            slideToBeInserted.setPrev(tail);
            tail.setNext(slideToBeInserted);
            tail = slideToBeInserted;
        }
    }

    /**
     * Removes the SlideListNode referenced by cursor and returns the Slide inside.
     * Preconditions: cursor is not null.
     * Post-conditions: The SlideListNode referenced by cursor has been removed from the list.
     * All other SlideListNodes in the list exist in the same order as before.
     * The cursor now references the previous SlideListNode (or the head, if the cursor previously referenced the head of the list).
     * @return
     *  The reference to the Slide contained within the SlideListNode which was just removed from the list.
     * @throws EndOfListException
     *  Thrown if cursor is null.
     */
    public Slide removeCursor() throws EndOfListException {
        if(cursor == null){
            throw new EndOfListException("cursor is null.");
        }
        size--;
        Slide removedSlide = cursor.getData();
        if(head.equals(tail) && cursor.equals(head)){ // only one item in the list
            head = null;
            tail = null;
            cursor = null;
        }
        else if(cursor.equals(head)){ // if cursor is the head of the list
            cursor = cursor.getNext();
            cursor.setPrev(null);
            head = cursor;
        }
        else if(cursor.equals(tail)){ // if cursor is the tail of the list
            cursor = cursor.getPrev();
            cursor.setNext(null);
            tail = cursor;
        }
        else{
            cursor = cursor.getPrev();
            cursor.setNext(cursor.getNext().getNext());
            cursor.getNext().setPrev(cursor);

            //cursor.getPrev().setNext(cursor.getNext());
            //cursor.getNext().setPrev(cursor.getPrev());
        }
        return removedSlide;
    }

    public void printSlideshow(){
        System.out.println("Slideshow Summary:");
        System.out.println("==============================================");
        System.out.println("  Slide    Title         Duration   Bullets");
        System.out.println("----------------------------------------------");
        SlideListNode temp = head;
        int slideNumber = 0;
        //double totalDuration = 0;
        int totalBullets = 0;
        BigDecimal totalDuration = new BigDecimal(0.0);
        while(temp != null){
            slideNumber++;
            String slideInfo = "";
            if(temp.equals(cursor)){
                slideInfo += "-> ";
            }
            else {
                slideInfo += "   ";
            }
            String title = temp.getData().getTitle();
            double duration = temp.getData().getDuration();
            //totalDuration += duration;
            totalDuration = totalDuration.add(new BigDecimal(duration));
            int bullets = temp.getData().getNumBullets();
            totalBullets += bullets;
            slideInfo += String.format("%-8s%-14s%-11s%-10s", slideNumber, title, duration, bullets);
            System.out.println(slideInfo);
            temp = temp.getNext();
        }
        
        System.out.println("==============================================");
        System.out.println("Total: " + slideNumber + " slide(s), " + totalDuration.doubleValue() + " minute(s), " + totalBullets + " bullet(s) ");
        System.out.println("==============================================");
    }
    
}