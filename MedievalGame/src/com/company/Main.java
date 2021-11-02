package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/**
 * Main game execution class
 *
 */
public class Main {

    private static Input input;//object for reading data from text files

    private static Room currentRoom;//current room in the game loop
    private static Scanner keyboard;//reads the player input

    private static boolean endFlag=false;//indicates whether the game is ending or playing
    private static int commandType=1;//-2:no further action,-1:invalid command,1:valid command,
    // 2:help,3:Quit,4:restart,5:Menu,6:pickup Item,7:inspect item, 8 drop item,
    // 9: display inventory,10:explore current room
    private static int nextRoomID=1;//next room to display

    private static String itemName;
    private static Player player;
    private static Puzzle currentPuzzle;
    private static String currentCommand="";
    private static int currentMode=1;//1:game mode,2:battle mode , 3:puzzle mode
    private static HashMap<String,Integer> commandsTable;//Holds the command used in the game and its types

    /**
     * main game loop
     * first initialization is done.
     * then starts the game loop
     * The game loop is executed while the endFlag is true.
     * If the endFlag is false the game ends.
     * @param args
     */
    public static void main(String[] args) {

        //initializing the game
        initialization();

        //main game loop
        do{
            process();
        }while(!endFlag);

        //Game is ending
        end();

        System.exit(0);
    }

    /**
     * initialization of the game
     * Data is read from the file and data is processed into an internal data structure.
     * THe starting room is displayed.
     */

    public static void initialization(){

        input=new Input();//reads and creates the game data from file

        keyboard=new Scanner(System.in);
        commandsTable=input.getCommandsTable();
        player= Player.getPlayer();
        System.out.println("The player : " +player);
        System.out.println(player.map);

        player.map.getRoom(1);


        //displaying the game logo
        displayLogo();

        //displaying the main menu
        mainMenu();

        //display the first room
        displayCurrentRoom();
        //if the room has puzzles , the puzzle is processed
        processPuzzle();

    }

    /**
     * Displays welcome screen
     */

    private static void displayLogo() {
        System.out.println("+----------------------------------------------+");
        System.out.println("| Welcome to Adventure Game Haunted Mansion!!! |");
        System.out.println("+----------------------------------------------+");
        System.out.println("\nYou were wandering around the countryside");
        System.out.println("when suddenly you found yourself inside a haunted mansion.");
        System.out.println("Explore mysterious rooms, equip and use enchanting items, solve puzzles");
        System.out.println("and defeat scary monsters to finally grab some treasures from the secret vault.\n");
    }

    /**
     * Main program execution.
     * The command type is validated and according to the command type
     * each process is executed.
     * If the command entered is invalid , it is notified to the player as "invalid command"
     */
    private static void process() {

        String command=getCommand();//reads the command from user
        getRoomID(command);//figure outs the next room to move and processes other commands

        switch(commandType){
            case -2://No further processing

                break;
            case -1:
                System.out.print("Invalid command.\n");
                break;
            case 1:

                displayCurrentRoom();//displays current room
                processPuzzle();//puzzle logic

                break;
            case 2 :
                displayHelp();//displays help
                break;
            case 3:
                quitRoutine();//quit command logic
                break;
            case 4:
                restartRoutine();//restart command logic
                break;
            case 5:
                mainMenu();//displays main menu
                break;
            case 6:
                player.displayInventory();//displays the inventory if any items are picked up
                break;
            case 7:
                currentCommand = "explore";
                player.exploreCurrentRoom(currentCommand);
                currentCommand = "";
                break;
            case 8:
                currentMode = player.attack();//the combat between the player and monster
                if (currentMode == -1) {
                    gameOverRoutine();//The monster defeated the player

                }
                break;
            case 9://the player leaves the combat.
                currentMode = 1;
                player.ignore();
                break;
            case 10:
                player.pickupItem(itemName);// the player picks up an item
                break;
            case 11:
                player.inspectItem(itemName);//player inspects an item
                break;
            case 12:
                player.dropItem(itemName);//player drops an item
                break;
            case 13:
                player.equipItem(itemName);//player is equipping an item
                break;
            case 14:
                player.unEquipItem(itemName);//The player is unequipping the item
                break;
            case 15:
                player.consume(itemName);//The player is consuming an item
                break;
            case 16:
                player.examineMonster(itemName);//THe player examines the monster
                break;
            case 17:
                player.displayHP(itemName);//The health point of player is displayed
                break;
            case 18:
                puzzleRoutine();//The puzzle is processed
                break;
            default:
                break;
        }


    }
    /**
     * At any point The player can quit the game.
     * This quitRoutine make sure the player wants to quit and
     * indicates the game is ending with an endFlag.
     */
    private static void quitRoutine() {

        System.out.print("Are you sure you want to Quit? (Y/N) : ");
        String choice = keyboard.nextLine();
        choice = choice.toUpperCase();
        if(choice.compareTo("Y")==0 || choice.compareTo("YES")==0){

            endFlag=true;//The game is ending

        }
    }

    /**
     * reads the command from the user
     * @return the commandString
     */

    private static String getCommand() {
        if(currentMode==1) {//The navigation mode
            System.out.println(currentRoom.getRoomName() +": Which direction do you want to go? (N,S,E,W)" +
                    "(explore,pickup,equip,attack,drop,inspect,examine,ignore)");
            HashMap<String,Integer> navTable=currentRoom.getNavTable();

            int direction=0;
            String str="MAP :";
            if((direction=navTable.get("north"))!=0){

                str+=" [North: "+player.map.getRoomName(direction)+"]";
            }
            if((direction=navTable.get("east"))!=0){
                str+=" [East: "+player.map.getRoomName(direction)+"]";
            }
            if((direction=navTable.get("south"))!=0){
                str+=" [South: "+player.map.getRoomName(direction)+"]";
            }
            if((direction=navTable.get("west"))!=0){
                str+=" [West: "+player.map.getRoomName(direction)+"]";
            }
            System.out.print(str+"\n>");
        }else if(currentMode==2){//The combat mode
            System.out.print("Attack or Heal <item name>  or Equip/unequip <itemName>: ");
        }
        String command=keyboard.nextLine();


        return command;
    }

    /**
     * figure outs the next room to move and processes other commands
     * @param command
     */
    private static void getRoomID(String command) {


        commandType = 0;
        command = command.toLowerCase();
        StringTokenizer st = new StringTokenizer(command, " ");

        if (st.countTokens() == 1) {
            processSingleWordCommand(command);//processes a command with single word like n,e,s,w,explore, inventory etc.
        } else if (st.countTokens() > 1) {
            processMultiWordCommand(st);//processes a command with multiple words like pickup item, drop item, equip item etc.

        }
    }

    /**
     * processes single word commands
     * @param command
     */
    private static void processSingleWordCommand(String command){
        HashMap<String, Integer> navTable = currentRoom.getNavTable();
        Integer commandtype = commandsTable.get(command);
        if (commandtype == null) {
            commandType = -1;//invalid command
            return;
        } else if (commandtype == 1) {//navigation comamnd
            if (currentMode == 2) {//combat mode
                System.out.println("You have to leave the combat before navigating to another room.");
                commandType = -2;
                return;
            }
            nextRoomID = navTable.get(command);
            player.map.getRoom(nextRoomID);
            commandType = (nextRoomID != 0) ? 1 : 0;
            return;
        } else if (commandtype != 1) {//not a navigation command but single worded like inventory,explore, quit,help, restart,menu etc
            commandType = commandtype;
            return;
        }
    }

    /**
     * processes commands with more than one word
     * @param st
     */
    private static void processMultiWordCommand(StringTokenizer st){
        commandType = 0;

        //command = command.toLowerCase();
        String command = st.nextToken();
        itemName = st.nextToken();

        while (st.hasMoreTokens()) {
            itemName += " " + st.nextToken();
        }
        itemName = itemName.toUpperCase();
        Integer commandtype = commandsTable.get(command);
        if (commandtype == null) {
            commandType = -1;//invalid command
            return;
        } else if (commandtype >8) {//multi worded command like pickup item, drop item, equip item , unequip item etc.
            commandType = commandtype;
            return;
        }else  {
            commandType = -1;
            return;
        }
    }

    /**
     * The monster defeated the player
     * The game is over and the player can either quit the game or restart the game
     */
    private static void gameOverRoutine() {
        System.out.println("The game is over.");
        System.out.print("You can (E)xit or (R)estart the game : ");
        String command=keyboard.nextLine().toLowerCase();
        if(command.compareTo("exit")==0|| command.compareTo("e")==0){
            exitRoutine();
        }else {
            restartRoutine();
            currentMode=1;
       }

    }
    /**
    * The player is exiting the game
    */
    private static void exitRoutine() {
        System.out.println("You are exiting the game...");
        System.exit(0);
    }

    /**
     * displays the name of the current room
     */
    private static void displayCurrentRoom() {

        currentRoom=player.map.getRooms().get(player.getPlayerLocation());

        System.out.println("You are at the "+currentRoom.getRoomName());

        player.setRoundCount(1);//resets the round number in the combat mode

    }
    /**
     * executes the puzzle routine
     */
    private static void puzzleRoutine() {
        if (itemName.toLowerCase().compareTo("puzzle") == 0) {
            if (currentRoom.getPuzzle().isSolved()) {
                System.out.println("There are no puzzles to solve in this room.");
            } else {
                processPuzzle();
            }

        } else {
            System.out.println("Invalid command.");
        }
    }
    /**
     * the puzzle logic
     */
    private static void processPuzzle() {

        if(!currentRoom.getPuzzle().isSolved()) {
            currentPuzzle = currentRoom.getPuzzle();

            int availableAttempts = currentPuzzle.getNoOfAttempts();
            System.out.println("\nYou have a puzzle to solve : (number of attempts available are " +
                    availableAttempts + ".)");
            System.out.println(currentPuzzle.getPuzzleDescription());
            do {
                System.out.print(">");
                String ans = keyboard.nextLine();
                //Possible commands :  Examine,solve,ignore and the answer
                int status=processPuzzleCommand(ans);
                if(status==-1){//getting out of the puzzle mode because the player is ignoring the puzzle
                    return;
                }else if(status==0){//player entered the "examine puzzle " command
                    continue;
                }else {
                    ans.toLowerCase();

                    if (ans.compareTo(currentPuzzle.getAnswer()) == 0) {
                        System.out.println("you solved the puzzle correctly!");

                        currentPuzzle.setSolved(true);
                        return;
                    } else {
                        if (availableAttempts == 1) {
                            break;
                        }
                        System.out.println("Incorrect ." +
                                " You still have " + --availableAttempts + " attempt(s) left." +
                                " Try one more time.");
                    }
                }

            } while (availableAttempts > 0);

            System.out.println("You failed to solve the puzzle. You have no more attempts left.");
        }
    }

    /**
     * processes the commands entered by the user during the puzzle processing
     * @param ans
     * @return status
     */
    private static int processPuzzleCommand(String ans) {
            ans=ans.toLowerCase();
            StringTokenizer st = new StringTokenizer(ans," ");
            if(st.countTokens()>1){
                String command = st.nextToken();
                if(command.compareTo("examine")==0 ||command.compareTo("x")==0){
                    if(ans.compareTo("examine puzzle")==0|| ans.compareTo("x puzzle")==0){
                        System.out.println(currentPuzzle.getPuzzleDescription());
                        return 0;
                    }

                }

                else if(command.compareTo("ignore")==0 ||command.compareTo("ig")==0) {
                    if (ans.compareTo("ignore puzzle") == 0 || ans.compareTo("ig puzzle") == 0) {
                        System.out.println("You are leaving the " + currentPuzzle.getPuzzleName() + ".");
                        return -1;
                    }
                }
            }
            return 1;//this is an answer

    }

    /**
     * displays the help
     */
    private static void displayHelp() {
        System.out.println("You are at the "+currentRoom.getRoomName());
        System.out.println("+------------------------------------------------------------------------+");
        System.out.println("| 			                Help   			                             |");
        System.out.println("+------------------------------------------------------------------------+");
        System.out.println("| Player directions are North,South,East, and West	                     |");
        System.out.println("| You may pick up items by entering \"pickup <item name>\"	             |");
        System.out.println("| You may drop items by entering \"drop <item name>\"	                     |");
        System.out.println("| You may get description of the item by entering \"inspect <item name>\"  |");
        System.out.println("| You may check your inventory by entering \"inventory\"	                 |");
        System.out.println("| You may get description of the puzzle by entering \"examine <puzzle>\"   |");
        System.out.println("| You may leave the puzzle by entering \"ignore <puzzle name>\"            |");
        System.out.println("| Other valid commands are [Quit],[Restart],[Help],[Menu]                |");
        System.out.println("+------------------------------------------------------------------------+");

        HashMap<String,Integer> navTable=currentRoom.getNavTable();

        int direction=0;
        String str="The valid directions from this room are :";
        if((direction=navTable.get("north"))!=0){

            str+=" [North: "+player.map.getRoomName(direction)+"]";
        }
        if((direction=navTable.get("east"))!=0){
            str+=" [East: "+player.map.getRoomName(direction)+"]";
        }
        if((direction=navTable.get("south"))!=0){
            str+=" [South: "+player.map.getRoomName(direction)+"]";
        }
        if((direction=navTable.get("west"))!=0){
            str+=" [West: "+player.map.getRoomName(direction)+"]";
        }
        System.out.println(str);
    }

    /**
     * restarts the game resetting the rooms, items, puzzles and monsters
     */
    private static void restartRoutine() {
        initialization();

        currentMode=1;//navigation mode

    }

    /**
     * displays the main menu
     */
    private static void mainMenu() {
        System.out.println("+----------------------------------------------+");
        System.out.println("|           Main Menu                          |");
        System.out.println("+----------------------------------------------+");
        System.out.println("|           Restart                            |");
        System.out.println("|           Directions(North,East,South,West)  |");
        System.out.println("|           Help                               |");
        System.out.println("|           Quit                               |");
        System.out.println("+----------------------------------------------+");
    }

    /**
     * displays the ending message
     */
    private static void end() {

        System.out.println("Thanks for playing....");
    }
}
