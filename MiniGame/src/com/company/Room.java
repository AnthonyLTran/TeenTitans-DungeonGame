package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * The class encapsulating room features
 */
public class Room {
    private int RoomID;
    private String RoomName;
    private ArrayList<String> roomDescription;
    private HashMap<String,Integer> navTable;
    private boolean visited;


    private ArrayList<Item> roomInventory;
    private Puzzle puzzle;
    private Monster monster;

    public Room() {

        roomDescription=new ArrayList<>();
        navTable=new HashMap<String,Integer>();
        roomInventory=new ArrayList<>();
        puzzle=new Puzzle();
        puzzle.setSolved(true);
        this.monster=new Monster();
        this.monster.setDefeated(true);


    }

    public int getRoomID() {
        return RoomID;
    }

    public void setRoomID(int roomID) {
        RoomID = roomID;
    }
    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String roomName) {
        RoomName = roomName;
    }

    public ArrayList<String> getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(ArrayList<String> roomDescription) {
        this.roomDescription = roomDescription;
    }

    public HashMap<String, Integer> getNavTable() {
        return navTable;
    }

    public void setNavTable(HashMap<String, Integer> navTable) {
        this.navTable = navTable;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public ArrayList<Item> getRoomInventory() {
        return roomInventory;
    }

    public void setRoomInventory(ArrayList<Item> roomInventory) {
        this.roomInventory = roomInventory;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    @Override
    public String toString() {
        return "Room{" +
                "RoomID=" + RoomID +
                "RoomName=" + RoomName +
                ",\nroomDescription='" + roomDescription + '\'' +
                "\n, navTable=" + navTable +
                "\n, visited=" + visited +
                "\n room inventory = "+roomInventory+
                "\n room puzzle = "+puzzle+
                "\n room monster = "+monster+
                "}\n\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return RoomID == room.RoomID && visited == room.visited && Objects.equals(RoomName, room.RoomName) && Objects.equals(roomDescription, room.roomDescription) && Objects.equals(navTable, room.navTable) && Objects.equals(roomInventory, room.roomInventory) && Objects.equals(puzzle, room.puzzle) && Objects.equals(monster, room.monster);
    }

    @Override
    public int hashCode() {
        return Objects.hash(RoomID, RoomName, roomDescription, navTable, visited, roomInventory, puzzle, monster);
    }
}
