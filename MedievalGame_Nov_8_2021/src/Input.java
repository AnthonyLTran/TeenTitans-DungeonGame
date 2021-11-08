import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Handles reading from text files and processing the data into Hashmaps
 */
public class Input {

    private static HashMap<String,Item> items;//stores Item details
    private static HashMap<String,Room> rooms;//Stores Room details
    private static HashMap<String,Puzzle> puzzles;//Stores puzzle details
    private static HashMap<String,Monster> monsters;//Stores monster details
    private static HashMap<String,Integer> commandsTable;//Stores command details
    private static Player player;//stores player details

    private static ArrayList<String> playerRawData;//Stores data from Player.txt file
    private static ArrayList<String> roomRawData;//Stores data from rooms.txt file
    private static ArrayList<String> itemRawData;//Stores data from Items.txt file
    private static ArrayList<String> puzzleRawData;//Stores data from Puzzles.txt file
    private static ArrayList<String> monsterRawData;//Stores data from Monsters.txt file
    private static ArrayList<String> commandsRawData;//Stores data from Monsters.txt file

    public Input(String playerFile,String itemFile,String puzzleFile,String monsterFile,String roomFile){
//        "OldPlayer.txt","OldItems.txt","OldPuzzles.txt",
//                "OldMonsters.txt","OldRoom.txt"
        playerRawData= getDataFromFile(playerFile);
        //displayDataFile(playerRawData);
        processRawPlayerData();
        ///System.out.println();

        itemRawData= getDataFromFile(itemFile);
        // displayDataFile(itemRawData);
        processRawItemData();
        //System.out.println(items);

        puzzleRawData= getDataFromFile(puzzleFile);
        //displayDataFile(puzzleRawData);
        processRawPuzzleData();
        //System.out.println(puzzles);

        monsterRawData= getDataFromFile(monsterFile);
        //displayDataFile(monsterRawData);
        processRawMonsterData();
        //System.out.println(monsters);

        roomRawData= getDataFromFile(roomFile);
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
        //processRawPlayerData();
        return player;
    };
    public static HashMap<String,Integer> getCommandsTable() {
        return commandsTable;
    }
    public static HashMap<String,Room> getRooms() {
        return rooms;
    }
    public static HashMap<String,Item> getItems() {
        return items;
    }
    public static HashMap<String,Puzzle> getPuzzles() {
        return puzzles;
    }
    public static HashMap<String,Monster> getMonsters() {
        return monsters;
    }

    /**
     * reads player data from Player.txt
     */
    public static void processRawPlayerData() {
        for (int i = 1; i < playerRawData.size(); i++) {
            player = new Player();
            //PlayerID,PlayerHP,PlayerDealDamage,PLayerInventory,RoomID,RoundCount
            player.setPlayerID(Integer.parseInt(playerRawData.get(i)));
            i++;
            player.setHP(Integer.parseInt(playerRawData.get(i)));
            i++;
            player.setDealDamage(Integer.parseInt(playerRawData.get(i)));
            i++;
            //adding items to player inventory
            StringTokenizer st = new StringTokenizer(playerRawData.get(i),",");
            while (st.hasMoreTokens()) {
                String itemID=st.nextToken();
                if(itemID.compareTo("0")!=0) {
                    player.getPlayerInventory().add(items.get(itemID));
                }

            }
            i++;
            player.setRoomID(playerRawData.get(i));
            i++;
            player.setRoundCount(Integer.parseInt(playerRawData.get(i)));
            i++;
            //adding items to player equipped items array list
            st = new StringTokenizer(playerRawData.get(i),",");
            while (st.hasMoreTokens()) {
                String itemID=st.nextToken();
                if(itemID.compareTo("0")!=0) {
                    player.getEquippedItems().add(((Equippable)items.get(itemID)));
                }

            }
            i++;
            player.setCurrentMode(Integer.parseInt(playerRawData.get(i)));
            i++;
            player.setMonsterOldHP(Integer.parseInt(playerRawData.get(i)));
            i++;
            Player.setPlayer(player);
        }
    }

    /**
     * Once the data is read from file,
     * the data is processed and placed to internal structure and
     * add to an HashMap called Items.
     */
    public static void processRawItemData() {
        items = new HashMap<String,Item>();
        for (int i = 2; i < itemRawData.size(); i++) {//The arrayList index starts at 1 because the first line(index 0)
            // is the format of the file

            //Item item = new Item();
            String itemType=itemRawData.get(i);
            i++;
            if(itemType.compareTo("equippable")==0){//processing the equippable items
                Equippable item = new Equippable();
                item.setItemType(itemType);
                item.setItemID(itemRawData.get(i));
                i++;
                item.setItemName(itemRawData.get(i));
                i++;
                item.setItemDescription(itemRawData.get(i));
                i++;
                item.setDamageEffect(Integer.parseInt(itemRawData.get(i)));
                i++;
                item.setEquipped(Boolean.parseBoolean(itemRawData.get(i)));
                i++;
                item.setAvailable(Boolean.parseBoolean(itemRawData.get(i)));
                i++;
                item.setUsed(Boolean.parseBoolean(itemRawData.get(i)));
                i++;

                //adding the item to items HashMap
                items.put(item.getItemID(),item);
            }else{//processes Consumable items
                Usable item = new Usable();
                item.setItemType(itemType);
                item.setItemID(itemRawData.get(i));
                i++;
                item.setItemName(itemRawData.get(i));
                i++;
                item.setItemDescription(itemRawData.get(i));
                i++;
                item.setHealthEffect(Integer.parseInt(itemRawData.get(i)));
                i++;
                item.setConsumed(Boolean.parseBoolean(itemRawData.get(i)));
                i++;
                item.setAvailable(Boolean.parseBoolean(itemRawData.get(i)));
                i++;
                item.setUsed(Boolean.parseBoolean(itemRawData.get(i)));
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
        monsters = new HashMap<String,Monster>();
        for(int i=2;i<monsterRawData.size();i++){
            Monster monster=new Monster();

            monster.setMonsterID(monsterRawData.get(i));
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
            monster.setCurrentHP(Integer.parseInt(monsterRawData.get(i)));
            i++;
            monster.setDealDamage(Integer.parseInt(monsterRawData.get(i)));
            i++;
            monster.setDroppedItem(monsterRawData.get(i));
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
        puzzles = new HashMap<String,Puzzle>();
        for (int i = 2; i < puzzleRawData.size(); i++) {//The arrayList index starts at 1 because the first line(index 0)
            // is the format of the file

            Puzzle puzzle = new Puzzle();
            puzzle.setPuzzleID(puzzleRawData.get(i));
            i++;
            puzzle.setPuzzleName(puzzleRawData.get(i));
            i++;
            String str= puzzleRawData.get(i);
            ArrayList<String> descriptions = new ArrayList<>();
            while(str.compareTo("----")!=0){
                descriptions.add(str);
                i++;
                str= puzzleRawData.get(i);
            }
            puzzle.setPuzzleDescription(descriptions);
            i++;
            puzzle.setSolvedDescription(puzzleRawData.get(i));
            i++;
            i++;
            str = puzzleRawData.get(i);
            StringTokenizer st = new StringTokenizer(str,",");
            ArrayList<String> items = new ArrayList<>();
            while (st.hasMoreTokens()) {
                items.add(st.nextToken());
            }
            puzzle.setRequiredItem(items);
            i++;
            puzzle.setRequiredString(puzzleRawData.get(i));
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

        rooms = new HashMap<String,Room>();
        for(int i=2;i<roomRawData.size();i++){
            Room room=new Room();

            HashMap<String,String> navData= new HashMap<>();
            room.setRoomID(roomRawData.get(i));
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
                String token=st.nextToken();
                navData.put("north",token);
                navData.put("n",token);
                navData.put("up",token);
                token=st.nextToken();
                navData.put("east",token);
                navData.put("e",token);
                navData.put("right",token);
                token=st.nextToken();
                navData.put("south",token);
                navData.put("s",token);
                navData.put("down",token);
                token=st.nextToken();
                navData.put("west",token);
                navData.put("w",token);
                navData.put("left",token);
            }
            i++;
            //adding Navigation table to the room
            room.setNavTable(navData);

            //adding the item
            st = new StringTokenizer(roomRawData.get(i),",");
            while (st.hasMoreTokens()) {
                String itemID=st.nextToken();
                if(itemID.compareTo("0")!=0) {
                    room.getRoomInventory().add(items.get(itemID));
                }

            }
            i++;

            //adding the puzzle
            String puzzleID=roomRawData.get(i);
            i++;
            if (puzzleID.compareTo("0") != 0) {
                room.setPuzzle(puzzles.get(puzzleID));
            }else {
                room.setPuzzle(new Puzzle());
            }
            //adding the monster
            String monsterID=roomRawData.get(i);
            i++;
            if (monsterID.compareTo("0") != 0) {
                room.setMonster(monsters.get(monsterID));
            }else{
                room.setMonster(new Monster());
            }

            room.setCanGoForward(roomRawData.get(i));
            i++;
            //adding the room to rooms HashMap
            rooms.put(room.getRoomID(),room);
            //Adding the room to the Map class, rooms HashMap
            Map.addRoom(room);
            //System.out.println("From process room data : "+room);
        }
    }

    private static void displayDataFile(ArrayList<String> arrayList) {

        for(int i=0;i<arrayList.size();i++){
            System.out.println(arrayList.get(i));
        }
    }
}
