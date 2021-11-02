package FreeSpace;


import MiniTextGame.Item;

import java.util.Objects;

/**
 * The class encapsulating the puzzle features
 */
public class Puzzle {
    private int puzzleID;
    private String puzzleName;
    private String puzzleDescription;
    private String requiredString;
    private Item requiredItem;
    private int noOfAttempts;
    private boolean solved;

    public Puzzle() {
        this.solved=true;
    }

    public int getPuzzleID() {
        return puzzleID;
    }

    public void setPuzzleID(int puzzleID) {
        this.puzzleID = puzzleID;
    }

    public String getPuzzleName() {
        return puzzleName;
    }

    public void setPuzzleName(String puzzleName) {
        this.puzzleName = puzzleName;
    }

    public String getPuzzleDescription() {
        return puzzleDescription;
    }

    public void setPuzzleDescription(String puzzleDescription) {
        this.puzzleDescription = puzzleDescription;
    }

    public String getRequiredString() {
        return requiredString;
    }

    public void setRequiredString(String requiredString) {
        this.requiredString = requiredString;
    }

    public Item getRequiredItem() {
        return requiredItem;
    }

    public void setRequiredItem(Item requiredItem) {
        this.requiredItem = requiredItem;
    }

    public int getNoOfAttempts() {
        return noOfAttempts;
    }

    public void setNoOfAttempts(int noOfAttempts) {
        this.noOfAttempts = noOfAttempts;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "puzzleID=" + puzzleID +
                ", puzzleName='" + puzzleName + '\'' +
                ", puzzleDescription='" + puzzleDescription + '\'' +
                ", requiredString='" + requiredString + '\'' +
                ", requiredItem=" + requiredItem +
                ", noOfAttempts=" + noOfAttempts +
                ", solved=" + solved +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Puzzle puzzle = (Puzzle) o;
        return puzzleID == puzzle.puzzleID && noOfAttempts == puzzle.noOfAttempts && solved == puzzle.solved && Objects.equals(puzzleName, puzzle.puzzleName)
                && Objects.equals(puzzleDescription, puzzle.puzzleDescription) && Objects.equals(requiredString, puzzle.requiredString) && Objects.equals(requiredItem, puzzle.requiredItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(puzzleID, puzzleName, puzzleDescription,requiredString, requiredItem, noOfAttempts, solved);
    }
}