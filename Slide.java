/**
 * This class contains the title, the array of bullet points, and the duration of the slide in minutes.
*/

public class Slide{

    public static final int MAX_BULLETS = 5;
    private String title;
    private String[] bullets;
    private double duration; // in minutes

    /**
     * Default constructor
     * 
     * preconditions: This object has been initialized to an empty Slide (title and all bullets are null, duration = 0.0)
     */
    public Slide(){
        this.title = null;
        this.bullets = new String[MAX_BULLETS];
        this.duration = 0.0;
    }

    /**
     * This is an constructor
     * @param title
     *  title of the slide
     * @param duration
     *  duration of the slide in minutes
     */
    public Slide(String title, double duration){
        setTitle(title);
        setDuration(duration);
        this.bullets = new String[MAX_BULLETS];
    }


    /**
     * Public getter method for the title member variable.
     * @return
     *  The title of the Slide.
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * Public setter method for the title member variable.
     * @param newTitle
     *  The new title of this slide. This parameter should not be null
     * Preconditions: newTitle is not null.
     * @throws IllegalArgumentException
     *  Thrown if newTitle is null.
     */
    public void setTitle(String newTitle) throws IllegalArgumentException{
        if(newTitle == null){
            throw new IllegalArgumentException("Title cannot be null.");
        }
        this.title = newTitle;
    }

    /**
     * Public getter method for the duration member variable.
     * @return
     *  The duration of the Slide.
     */
    public double getDuration(){
        return this.duration;
    }

    /**
     * Public setter method for the duration member variable.
     * @param newDuration
     *  The new duration of this slide. This parameter should be greater than 0.
     * Preconditions: newDuration is greater than 0.
     * @throws IllegalArgumentException
     *  Thrown if newDuration is less than or equal to 0.
     */
    public void setDuration(double newDuration) throws IllegalArgumentException{
        if(newDuration <= 0){
            throw new IllegalArgumentException("Duration is less than or equal to 0.");
        }
        this.duration = newDuration;
    }

    /**
     * Gets the total number of bullet point in the Slide.
     * @return
     * The number of bullet points in the Slide.
     */
    public int getNumBullets(){
        int count = 0;
        for(int i = 0; i < MAX_BULLETS; i++){
            if(this.bullets[i] != null){
                count++;
            }
        }
        return count;
    }

    /**
     * This method returns the maximum number of bullet points
     * @return
     *  The maximum amount of bullet points
     */
    public int getBulletSize(){
        return this.bullets.length;
    }

    /**
     * Gets the bullet point at index i.
     * @param i
     *  The index to retrieve from the array. This value must be between 1 and MAX_BULLETS, inclusive.
     * Preconditions: 1 ≤ i ≤ MAX_BULLETS.
     * @return
     *  The String representing the bullet point at the given index (may be null, meaning there is no bullet point at this index).
     * @throws IllegalArgumentException
     *  Thrown if i is not in the valid range.
     */
    public String getBullet(int i) throws IllegalArgumentException{
        if(!(1 <= i && i <= MAX_BULLETS)){
            throw new IllegalArgumentException("i is not in the valid range.");
        }
        return this.bullets[i-1];// -1 for the array index
    }

    /**
     * Sets bullet as the i'th bullet point for bullets. If bullet is null, this method erases the bullet point at index i and pushes all bullet points greater 
     * than i back one index (i.e. leaves no “holes” in the bullets array).
     * @param bullet
     *  The String to place as the ith bullet point in bullets. This parameter may be null, indicating that the bullet at index i is to be erased from the slide.
     * @param i
     *  The index to place bullet in the array. This value must be between 1 and MAX_BULLETS, inclusive.
     * Preconditions: 1 ≤ i ≤ MAX_BULLETS.
     * Post-conditions: The bullet point at index i has been updated to the String bullet.
     *  There are no holes in the bullets array. All bullet points occupy the lowest possible indices of the array.
     * @throws IllegalArgumentException
     *  Thrown if i is not in the valid range.
     */
    public void setBullet(String bullet, int i) throws IllegalArgumentException{
        if(!(1 <= i && i <= MAX_BULLETS)){
            throw new IllegalArgumentException("i is not in the valid range.");
        }
        int position = i-1;
        if(bullet == null){
            bullets[position] = null;
            for(int j = position; j < bullets.length-1; j++){
                bullets[j] = bullets[j+1];
            }
            bullets[bullets.length-1] = null;
        }
        else{
            bullets[position] = bullet;
        }
    }
}