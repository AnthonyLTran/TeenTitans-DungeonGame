package com.company;

import java.util.HashMap;

/**
 * The class holds the details of rooms in "rooms" HashMap and
 * encapsulating the functionality to navigate between rooms
 */
public class Map {

    public static HashMap<Integer, Room> rooms = new HashMap<>();//HashMap holds the room information including
    //monsters,puzzles, and items

    /**
     * adds the room to rooms HashMap
     * @param room - the room to add
     */
    public static void addRoom(Room room){
        rooms.put(room.getRoomID(),room);

    }
    public static HashMap<Integer,Room> getRooms(){
        return rooms;
    }

    /**
     * returns the name of the room whose ID is provided
     * @param roomID
     * @return room name
     */
    public String getRoomName(int roomID){

        return rooms.get(roomID).getRoomName();

    }
    /**
     * Moves the player to the room in the specified direction if possible
     * @param direction -North,East,South,West
     */
    public static void getRoom(int direction){
        if(direction==0){
            System.out.println("You can't go in this direction.");
        }
        else if(direction>0) {
            int playerLocation= direction;
            if(rooms.get(playerLocation).isVisited()){
                System.out.println("This looks familiar!");
            }else{
                rooms.get(playerLocation).setVisited(true);
            }

            Player.setPlayerLocation(playerLocation);

        }
    }


    @Override
    public String toString() {
        return "The map of the rooms : \n"+rooms+"\n";
    }
}
