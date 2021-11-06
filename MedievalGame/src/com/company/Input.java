package com.company;

import com.company.Room;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Handles reading from text files and processing the data into Hashmaps
 */
public class Input {

    private static HashMap<Integer,Item> items;//stores Item details
    private static HashMap<Integer,Room> rooms;//Stores Room details
    private static HashMap<Integer,Puzzle> puzzles;//Stores puzzle details
    private static HashMap<Integer,Monster> monsters;//Stores monster details
    private static HashMap<String,Integer> commandsTable;//Stores command details
    private static Player player;//stores player details

    private static ArrayList<String> playerRawData;//Stores data from Player.txt file
    private static ArrayList<String> roomRawData;//Stores data from Room.txt file
    private static ArrayList<String> itemRawData;//Stores data from Items.txt file
    private static ArrayList<String> puzzleRawData;//Stores data from Puzzles.txt file
    private static ArrayList<String> monsterRawData;//Stores data from Monsters.txt file
    private static ArrayList<String> commandsRawData;//Stores data from Monsters.txt file

    public Input(){

        playerRawData= getDataFromFile("Player.txt");
        //displayDataFile(playerRawData);
        processRawPlayerData();
        ///System.out.println();

        itemRawData= getDataFromFile("Items.txt");
       // displayDataFile(itemRawData);
        processRawItemData();
        //System.out.println(items);

        puzzleRawData= getDataFromFile("Puzzles.txt");
        //displayDataFile(puzzleRawData);
        processRawPuzzleData();
        //System.out.println(puzzles);

        monsterRawData= getDataFromFile("Monster.txt");
        //displayDataFile(monsterRawData);
        processRawMonsterData();
         //System.out.println(monsters);

        roomRawData= getDataFromFile("Room.txt");
        //displayDataFile(roomRawData);
        processRawRoomData();
       // System.out.println(rooms);

        commandsRawData= getDataFromFile("Commands.txt");
        //displayDataFile(commandsRawData);
        processRawCommandsData();
        //System.out.println(commandsTable);

    }

    /**
     * after reading the commands data from commands.txt
     * the data is processed and saved in an internal structure Hashmap called
     * commandsTable
     */
    private void processRawCommandsData() {
        commandsTable = new HashMap<String,Integer>();
        for(int i=1;i<commandsRawData.size();i++) {
            String[] commandWords=commandsRawData.get(i).split(",");
            commandsTable.put(commandWords[0],Integer.parseInt(commandWords[1]));


        }

    }

    /**
     * reads the data from the given text file
     * @param s-the file name
     * @return list of strings , one string corresponding to each line in the text file.
     */
    private ArrayList<String> getDataFromFile(String s) {

        ArrayList<String> rawData= new ArrayList<>();

        try {
            File roomFile = new File(s);
            Scanner myReader = new Scanner(roomFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                rawData.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(s+" not found." );
            e.printStackTrace();
        }
        return rawData;
    }



    public static Player getPlayer(){
        processRawPlayerData();
        return player;
    };
    public static HashMap<String,Integer> getCommandsTable() {
        return commandsTable;
    }
    public static HashMap<Integer,Room> getRooms() {
        return rooms;
    }
    public static HashMap<Integer,Item> getItems() {
        return items;
    }
    public static HashMap<Integer,Puzzle> getPuzzles() {
        return puzzles;
    }
    public static HashMap<Integer,Monster> getMonsters() {
        return monsters;
    }

    /**
     * process the player data and creates player object
     */
    public static void processRawPlayerData() {
        for (int i = 1; i < playerRawData.size(); i++) {
            player = new Player();

            player.setPlayerID(Integer.parseInt(playerRawData.get(i)));
            i++;
            player.setHP(Integer.parseInt(playerRawData.get(i)));
            i++;
            player.setDealDamage(Integer.parseInt(playerRawData.get(i)));
            i++;


        }
        Player.setPlayer(player);
    }

    /**
     * Once the data is read from file,
     * the data is processed and placed to internal structure and
     * add to an HashMap called Items.
     */
    public static void processRawItemData() {
        items = new HashMap<Integer,Item>();
        for (int i = 1; i < itemRawData.size(); i++) {//The arrayList index starts at 1 because the first line(index 0)
                                                      // is the format of the file

            //Item item = new Item();
            String itemType=itemRawData.get(i);
            i++;
            if(itemType.compareTo("equippable")==0){//processing the equippable items
                Equippable item = new Equippable();
                item.setItemType(itemType);
                item.setItemID(Integer.parseInt(itemRawData.get(i)));
                i++;
                item.setItemName(itemRawData.get(i));
                i++;
                item.setItemDescription(itemRawData.get(i));
                i++;
                item.setDamageEffect(Integer.parseInt(itemRawData.get(i)));
                i++;
                item.setEquipped(false);
                i++;

                //adding the item to items HashMap
                items.put(item.getItemID(),item);
            }else{//processes Consumable items
                Consumable item = new Consumable();
                item.setItemType(itemType);
                item.setItemID(Integer.parseInt(itemRawData.get(i)));
                i++;
                item.setItemName(itemRawData.get(i));
                i++;
                item.setItemDescription(itemRawData.get(i));
                i++;
                item.setHealthEffect(Integer.parseInt(itemRawData.get(i)));
                i++;
                item.setConsumed(false);
                i++;

                //adding the item to items HashMap
                items.put(item.getItemID(),item);
            }
            //System.out.println(item.getRoomID()+ " The item added room" +room);

        }
    }

    /**
     * processes the list of data read from the Monster data file and
     * creates Monster objects and SOres in Monster HashMap
     */
    public void processRawMonsterData() {
        monsters = new HashMap<Integer,Monster>();
        for(int i=1;i<monsterRawData.size();i++){
            Monster monster=new Monster();

            monster.setMonsterID(Integer.parseInt(monsterRawData.get(i)));
            i++;
            monster.setMonsterName(monsterRawData.get(i));
            i++;
            String str=monsterRawData.get(i);
            ArrayList<String> descriptions = new ArrayList<>();
            while(str.compareTo("----")!=0){
                descriptions.add(str);
                i++;
                str=monsterRawData.get(i);
            }
            i++;
            monster.setMonsterDescription(descriptions);

            //processing defeated description
            str=monsterRawData.get(i);
            ArrayList<String> descriptions1 = new ArrayList<>();
            while(str.compareTo("****")!=0){
                descriptions1.add(str);
                i++;
                str=monsterRawData.get(i);
            }
            i++;
            monster.setDefeatedDescription(descriptions1);
            monster.setHP(Integer.parseInt(monsterRawData.get(i)));
            i++;
            monster.setDealDamage(Integer.parseInt(monsterRawData.get(i)));
            i++;
            monster.setFixNumber(Integer.parseInt(monsterRawData.get(i)));
            i++;
            monster.setDefeated(Boolean.parseBoolean(monsterRawData.get(i)));
            i++;


            //adding the room to rooms arraylist
            monsters.put(monster.getMonsterID(),monster);
            //System.out.println("From process room data : "+room);
        }
    }
    /**
     * Once the data is read from file,
     * the data is processed and placed to internal structure and
     * add to an HashMap called Puzzles.
     */
    public static void processRawPuzzleData() {
        puzzles = new HashMap<Integer,Puzzle>();
        for (int i = 1; i < puzzleRawData.size(); i++) {//The arrayList index starts at 1 because the first line(index 0)
                                                        // is the format of the file

            Puzzle puzzle = new Puzzle();
            puzzle.setPuzzleID(Integer.parseInt(puzzleRawData.get(i)));
            i++;
            puzzle.setPuzzleName(puzzleRawData.get(i));
            i++;
            puzzle.setPuzzleDescription(puzzleRawData.get(i));
            i++;
            puzzle.setAnswer(puzzleRawData.get(i));
            i++;
            puzzle.setNoOfAttempts(Integer.parseInt(puzzleRawData.get(i)));
            i++;
            puzzle.setSolved(Boolean.parseBoolean(puzzleRawData.get(i)));
            i++;

            //adding the puzzle to the puzzles HashMap
            puzzles.put(puzzle.getPuzzleID(),puzzle);

        }


    }
    /**
     * Once the data is read from file,
     * the data is processed and placed to internal structure and
     * add to an HashMap called Rooms.
     */
    public static void processRawRoomData() {

        rooms = new HashMap<Integer,Room>();
        for(int i=1;i<roomRawData.size();i++){
            Room room=new Room();

            HashMap<String,Integer> navData= new HashMap<>();
            room.setRoomID(Integer.parseInt(roomRawData.get(i)));
            i++;
            room.setRoomName(roomRawData.get(i));
            i++;
            String str=roomRawData.get(i);
            ArrayList<String> descriptions = new ArrayList<>();
            while(str.compareTo("----")!=0){
                descriptions.add(str);
                i++;
                str=roomRawData.get(i);
            }
            i++;
            room.setRoomDescription(descriptions);
            //The room is not visited yet.
            room.setVisited(Boolean.parseBoolean(roomRawData.get(i)));
            i++;
            StringTokenizer st = new StringTokenizer(roomRawData.get(i),",");
            while (st.hasMoreTokens()) {

                //Navigation Table
                navData = new HashMap<>();
                int value=Integer.parseInt(st.nextToken());
                navData.put("north",value);
                navData.put("n",value);
                value=Integer.parseInt(st.nextToken());
                navData.put("east",value);
                navData.put("e",value);
                value=Integer.parseInt(st.nextToken());
                navData.put("south",value);
                navData.put("s",value);
                value=Integer.parseInt(st.nextToken());
                navData.put("west",value);
                navData.put("w",value);
            }
            i++;
            //adding Navigation table to the room
            room.setNavTable(navData);

            //adding the item
            int itemID=Integer.parseInt(roomRawData.get(i));
            i++;
            //boolean result=itemID==0?false:room.getRoomInventory().add(items.get(itemID));
            if(itemID > 0){
                room.getRoomInventory().add(items.get(itemID));
            }

            //adding the puzzle
            int puzzleID=Integer.parseInt(roomRawData.get(i));
            i++;
            if (puzzleID != 0) {
                room.setPuzzle(puzzles.get(puzzleID));
            }
            //adding the monster
            int monsterID=Integer.parseInt(roomRawData.get(i));
            i++;
            if (monsterID != 0) {
                room.setMonster(monsters.get(monsterID));
            }

            //adding the room to rooms HashMap
            rooms.put(room.getRoomID(),room);
            //Adding the room to the Map class, rooms HashMap
            Map.addRoom(room);
            //System.out.println("From process room data : "+room);
        }
    }
    /**
     * Displays the lines from text file
     * @param arrayList of lines from text file
     */
    private static void displayDataFile(ArrayList<String> arrayList) {

        for(int i=0;i<arrayList.size();i++){
            System.out.println(arrayList.get(i));
        }
    }
}
