/**
 * This class creates an instance of the SlideList class and provides an interface for a user to manipulate the list by 
 * adding, removing, and editing slides.
*/

import java.util.*;
public class PresentationManager {
    public static Scanner scan = new Scanner(System.in);
    /**
     * The main method runs a menu driven application which first creates an empty SlideList and then prompts the user for a menu command selecting the operation. 
     * @param args
     */
    public static void main(String[] args) {
        SlideList slideList = new SlideList();
        System.out.println("Welcome to PresentationManager!");
        printMenu();
        String userInput = scan.nextLine().trim().toUpperCase();
        System.out.println();
        while(!userInput.equals("Q")){
            switch (userInput){
                case "F":
                    try {
                        slideList.cursorForward();
                    } catch (EndOfListException e) {
                        System.out.println("End of list cannot move forward");
                        break;
                    }
                    System.out.println();
                    System.out.println("The cursor moved forward to slide \"" + slideList.getCursorSlide().getTitle() + "\".");
                    break;
                case "B":
                    try {
                        slideList.cursorBackward();
                    } catch (EndOfListException e) {
                        System.out.println("End of list cannot move backward");
                        break;
                    }
                    System.out.println("The cursor moved backward to slide \"" + slideList.getCursorSlide().getTitle() + "\".");
                    break;
                case "D":
                    try {
                        slideList.getCursorSlide();
                    } catch (Exception e) {
                        System.out.println("empty slideshow");
                        break;
                    }
                    System.out.println("==============================================");
                    System.out.println("  " + slideList.getCursorSlide().getTitle());
                    System.out.println("==============================================");
                    for(int i  = 1 ; i <= slideList.getCursorSlide().getNumBullets(); i++){
                        System.out.println(" " + i + ". " + slideList.getCursorSlide().getBullet(i));
                    }
                    System.out.println("=============================================");
                    break;
                case "E":
                    try {
                        slideList.getCursorSlide();
                    } catch (Exception e) {
                        System.out.println("empty slideshow");
                        break;
                    }
                    System.out.print("Edit title, duration, or bullets? (t/d/b) ");
                    String edit = scan.nextLine().trim().toLowerCase();
                    if(!(edit.equals("t") || edit.equals("d") || edit.equals("b"))){
                        System.out.println("Invalid option");
                        break;
                    }
                    if(edit.equals("t")){
                        System.out.println("Enter the new title: ");
                        String title = scan.nextLine();
                        try {
                            slideList.getCursorSlide().setTitle(title);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid title");
                        }
                    }
                    else if(edit.equals("d")){
                        System.out.println("Enter the new duration: ");
                        double duration = scan.nextDouble();
                        scan.nextLine();
                        try {
                            slideList.getCursorSlide().setDuration(duration);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Invalid duration");
                        }
                    }
                    else if(edit.equals("b")){
                        System.out.print("Bullet index: ");
                        int bulletIndex = scan.nextInt();
                        if(bulletIndex < 1 || bulletIndex > slideList.getCursorSlide().getBulletSize()){
                            System.out.println("Invalid index");
                            break;
                        }
                        scan.nextLine();
                        System.out.print("Delete or edit? (d/e) ");
                        String deleteOrEdit = scan.nextLine().trim().toLowerCase();
                        if(!(deleteOrEdit.equalsIgnoreCase("d") || deleteOrEdit.equalsIgnoreCase("e"))){
                            System.out.println("Invalid option");
                            break;
                        }
                        if(deleteOrEdit.equals("d")){
                            try {
                                slideList.getCursorSlide().setBullet(null, bulletIndex);
                                System.out.println("\nBullet " + bulletIndex + " has been deleted.");
                            } catch (IllegalArgumentException e) {
                                System.out.println("Invalid index");
                            }
                        }
                        else if(deleteOrEdit.equals("e")) {
                            try {
                                System.out.print("Bullet " + bulletIndex + ": ");
                                String bullet = scan.nextLine();
                                slideList.getCursorSlide().setBullet(bullet, bulletIndex);
                            } catch (Exception e) {
                                System.out.println("Invalid index");
                            }
                        }
                    }
                    break;
                case "P":
                    slideList.printSlideshow();
                    break;
                case "A":
                    Slide newSlide = createSlide();
                    try {
                        slideList.appendToTail(newSlide);
                    } catch (Exception e) {
                        System.out.println("Invalid duration");
                        break;
                    }
                    System.out.println("\nSlide \"" + newSlide.getTitle() + "\" added to presentation.");
                    break;
                case "I":
                    Slide newSlideForInsertion = createSlide();
                    try {
                        slideList.insertBeforeCursor(newSlideForInsertion);
                    } catch (Exception e) {
                        System.out.println("Invalid duration");
                        break;
                    }
                    System.out.println("\nSlide \"" + newSlideForInsertion.getTitle() + "\" added to presentation.");
                    break;
                case "R":
                    try {
                       String title = slideList.removeCursor().getTitle();
                       System.out.println("Slide \"" + title + "\" has been removed.");
                    } catch (EndOfListException e) {
                        System.out.println("Empty slideshow");
                    }
                    break;
                case "H":
                    try {
                        slideList.getCursorSlide();
                    } catch (Exception e) {
                        System.out.println("empty slideshow");
                        break;
                    }
                    slideList.resetCursorToHead();
                    System.out.println("cursor has been reset to the head.");
                    break;
                default:
                    System.out.println("Invalid option.");
                    System.out.println("Try again.");
                    break;
            }
            printMenu();
            userInput = scan.nextLine().trim().toUpperCase();
            System.out.println();

        }
        System.out.println("Program terminating normally...");
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("Please select an option:");
        System.out.println("F) Move cursor forward");
        System.out.println("B) Move cursor backward");
        System.out.println("D) Display cursor slide");
        System.out.println("E) Edit cursor slide");
        System.out.println("P) Print presentation summary");
        System.out.println("A) Append new slide to tail");
        System.out.println("I) Insert new slide before cursor");
        System.out.println("R) Remove slide at cursor");
        System.out.println("H) Reset cursor to head");
        System.out.println("Q) Quit");
        System.out.println();
        System.out.print("Select a menu option: ");
    }

    public static Slide createSlide(){
        System.out.print("Enter the slide title: ");
        String title = scan.nextLine();
        System.out.print("Enter the slide duration: ");
        double duration = scan.nextDouble();
        if(duration <= 0){
            return null;
        }
        Slide newSlide = new Slide(title, duration);
        scan.nextLine();
        System.out.print("Bullet 1: ");
        String bullet = scan.nextLine();
        newSlide.setBullet(bullet, 1);
        for(int i = 2; i < 6; i++){
            System.out.print("Add another bullet point? (y/n) ");
            String yesOrNo = scan.nextLine().trim().toLowerCase();
            if(!(yesOrNo.equalsIgnoreCase("y") || yesOrNo.equalsIgnoreCase("n"))){
                System.out.println("Invalid option");
                return null;
            }
            if(yesOrNo.equals("n")){
                break;
            }
            System.out.print("Bullet " + i + ": ");
            String newBullet = scan.nextLine();
            newSlide.setBullet(newBullet, i);
            if(i == 5){
                System.out.println("No more bullets allowed. Slide is full.");
            }
        }
        return newSlide;
    }
}