/**
 * This is an exception class which indicates that the user attempted to access a SlideListNode that does not exist. 
*/

public class EndOfListException extends Exception{
    public EndOfListException(String errorMessage) {
        super(errorMessage);
    }
}