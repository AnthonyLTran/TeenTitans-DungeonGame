import java.util.ArrayList;
import java.util.Objects;

/**
 * The class encapsulating the puzzle features
 */

public class Puzzle {
    private String puzzleID;
    private String puzzleName;
    private ArrayList<String> puzzleDescription;
    private String solvedDescription;
    private String requiredString;
    private ArrayList<String> requiredItem;
    private int noOfAttempts;
    private boolean solved;

    public Puzzle() {
        this.solved = true;
    }

    public String getPuzzleID() {
        return puzzleID;
    }

    public void setPuzzleID(String puzzleID) {
        this.puzzleID = puzzleID;
    }

    public String getPuzzleName() {
        return puzzleName;
    }

    public void setPuzzleName(String puzzleName) {
        this.puzzleName = puzzleName;
    }

    public ArrayList<String> getPuzzleDescription() {
        return puzzleDescription;
    }

    public void setPuzzleDescription(ArrayList<String> puzzleDescription) {
        this.puzzleDescription = puzzleDescription;
    }

    public String getSolvedDescription() {
        return solvedDescription;
    }

    public void setSolvedDescription(String solvedDescription) {
        this.solvedDescription = solvedDescription;
    }

    public String getRequiredString() {
        return requiredString;
    }

    public void setRequiredString(String requiredString) {
        this.requiredString = requiredString;
    }

    public ArrayList<String> getRequiredItem() {
        return requiredItem;
    }

    public void setRequiredItem(ArrayList<String> requiredItem) {
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
                ", puzzleDescription=" + puzzleDescription +
                ", solvedDescription='" + solvedDescription + '\'' +
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
        return puzzleID == puzzle.puzzleID && noOfAttempts == puzzle.noOfAttempts && solved == puzzle.solved && Objects.equals(puzzleName, puzzle.puzzleName) && Objects.equals(puzzleDescription, puzzle.puzzleDescription) && Objects.equals(solvedDescription, puzzle.solvedDescription) && Objects.equals(requiredString, puzzle.requiredString) && Objects.equals(requiredItem, puzzle.requiredItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(puzzleID, puzzleName, puzzleDescription, solvedDescription, requiredString, requiredItem, noOfAttempts, solved);
    }
}
