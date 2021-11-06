package com.company;

import java.util.*;
/**
 * A subclass of the GameCharacter class encapsulating the functions of the player character
 */
public class Player extends GameCharacter {
    private int playerID;
    private ArrayList<Item> playerInventory;
    private Room currentRoom;

    private static int roomID;
    Map map= new Map();//The map object containing all room objects with puzzles,items and monsters.

    int roundCount=1;

    private static Player player=null;

    public Player() {

        this.playerInventory = new ArrayList<>();

    }

    public static void setPlayer(Player player) {
        Player.player = player;
    }

    public static Player getPlayer(){
        if(player==null){
            player=Input.getPlayer();
        }
        return player;
    }

    /**
     * The function that lets the player to equip with an equippable item.
     * @param itemName
     */
    public void equipItem(String itemName){
        boolean found=false;
        for (int i = 0; i < playerInventory.size(); i++) {
            if (playerInventory.get(i).getItemName().compareTo(itemName) == 0) {//checks if the item exists in the player inventory
                Item item = playerInventory.get(i);
                if(playerInventory.contains(item)) {
                    if (item.getItemType().compareTo("equippable") == 0) {//checks whether the item is equippable
                        Equippable currentItem = ((Equippable) item);
                        if (!currentItem.isEquipped()) {
                            //this.equippedItems.add(item);
                            currentItem.setEquipped(true);
                            System.out.println("You are successfully equipped with " + item.getItemName() + ".");

                        } else{
                            System.out.println("The item is already equipped.");
                        }
                    } else
                    {
                        System.out.println("This item is not equippable.");
                    }

                }
                else{
                    System.out.println("You are not picked up the item yet.");
                }

                found=true;//item exists in the player inventory
            }
        }
        if(!found) {//The item does not exists in the player inventory
            System.out.println("The " + itemName + " is/are not picked up yet.");
        }



    }

    /**
     * The function lets the player to unequip an equipped item.
     * @param itemName
     */
    public void unEquipItem(String itemName){
        boolean found=false;
        for (int i = 0; i < playerInventory.size(); i++) {
            if (playerInventory.get(i).getItemName().compareTo(itemName) == 0) {//checks if the item exists in the player inventory
                Item item = playerInventory.get(i);
                if(item.getItemType().compareTo("equippable") == 0) {
                    Equippable currentItem = ((Equippable) item);
                    if (currentItem.isEquipped()) {
                        currentItem.setEquipped(false);

                        System.out.println("The "+currentItem.getItemName()+" is/are successfully unequipped.");

                    }else{
                        System.out.println("The item is not equipped yet.");
                    }

                }else{
                    System.out.println("This item is not equippable.");
                }
                found=true;
            }
        }
        if(!found) {
            System.out.println("The " + itemName + " is/are not picked up yet.");
        }

    }

    /**
     * This function lets the player to consume an item
     * so that the player health point is increased
     * @param itemName
     */
    public void consume(String itemName){
        //System.out.println("inside consume item fn.");
        boolean found=false;
        Item currentItem=null;
        for (int i = 0; i < playerInventory.size(); i++) {
            if (playerInventory.get(i).getItemName().compareTo(itemName) == 0) {//checks if the item exists in the player inventory
                currentItem = playerInventory.get(i);
                if(playerInventory.contains(currentItem)) {
                    if (currentItem.getItemType().compareTo("consumable") == 0) {

                        ((Consumable) currentItem).setConsumed(true);
                        System.out.println("You are successfully consumed the "+ currentItem.getItemName()+".");
                        this.setHP(this.getHP()+ ((Consumable) currentItem).getHealthEffect());
                        System.out.println("Your health is now "+ this.getHP()+".");
                        playerInventory.remove(currentItem);
                    }else{
                        System.out.println("This item is not consumable.");
                    }
                }
                else{
                    System.out.println("You are not picked up the item yet.");
                }
                found=true;
            }
        }
        if(!found) {
            System.out.println("The " + itemName + " is/are not picked up yet.");
        }


    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public static int getPlayerLocation(){
        return roomID;
    }
    public static void setPlayerLocation(int roomid){
        roomID=roomid;
    }

    public ArrayList<Item> getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(ArrayList<Item> playerInventory) {
        this.playerInventory = playerInventory;
    }

    public ArrayList<Equippable> getEquippedItems(){
        ArrayList<Equippable> equippedItems= new ArrayList();
        for(int i=0; i<playerInventory.size();i++){
            if(playerInventory.get(i).getItemType().compareTo("equippable")==0){
                if(((Equippable)(playerInventory.get(i))).isEquipped()){
                    equippedItems.add(((Equippable)(playerInventory.get(i))));
                }

            }
        }
        return equippedItems;
    }

    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }

    /**
     * THis function carries the combat between the player and the monster
     * @return a status value 1: navigation mode that means the battle ended
     * 2: combat mode that means the battle continues
     */
    public int attack(){
        currentRoom=map.getRooms().get(getPlayerLocation());
        if(currentRoom.getMonster().isDefeated()){
            System.out.println("This room has no monsters.");
            return 1;//navigation mode
        }
        int oldHP=currentRoom.getMonster().getHP();
        Scanner keyboard= new Scanner(System.in);
        Monster monster= currentRoom.getMonster();
        Random rand=new Random(oldHP);

        System.out.println("Your Health Point : "+ player.getHP()+"\nThe "+monster.getMonsterName()+" Health Point: "
        +monster.getHP()+" Deal damage : "+ monster.getDealDamage());
        System.out.println("================= Round "+ roundCount++ +" =================");
        int playerDamage =player.getDealDamage();
        int totalDamage= 0;
        for(Equippable equippedItem:getEquippedItems()){//calculating the deal damage caused by equipped items
            totalDamage+=equippedItem.getDamageEffect();
        }
        playerDamage=playerDamage+totalDamage;
        System.out.println("The player attack for : "+ playerDamage+".");
        for(Equippable equippedItem:getEquippedItems()){
            playerInventory.remove(equippedItem);
        }
        monster.setHP(monster.getHP()-playerDamage);
        if (monster.getHP()<=0){//player defeated the monster
            System.out.println("Congratulations! You defeated the monster.");
            roundCount=1;
            for(String s : monster.getDefeatedDescription()){
                System.out.println(s);
            }
            currentRoom.getMonster().setDefeated(true);
            return 1;//return to navigation mode that means leaving the combat mode
        }

        System.out.println("The "+ monster.getMonsterName()+" has "+ monster.getHP() + " HP left.");
        int monsterDamage = currentRoom.getMonster().getDealDamage();
        int randValue=rand.nextInt(50);
        if(monster.getFixNumber()>randValue){
            monsterDamage*=2;
        }
        System.out.println("The "+monster.getMonsterName()+" attacks you for "+monsterDamage+".");
        this.setHP(this.getHP()-monsterDamage);

        if(this.getHP() <=0 ){//Monster defeated the player
            System.out.println("You got killed by the "+ monster.getMonsterName());
            roundCount=1;
            return -1;// the game ended , and Game over routine should be executed
        }
        System.out.println("You have "+this.getHP() + " HP left.");
        return 2;//still in the combat mode, The combat continues

    }



    /**
     * Displays the description and deal damage of the monster.
     */
    public void examineMonster(String itemName){

        if (itemName.toLowerCase().compareTo("monster") == 0) {
            currentRoom=map.getRooms().get(getPlayerLocation());
            Monster monster = currentRoom.getMonster();
            if(monster.isDefeated()){
                System.out.println("This room has no monsters.");
            }else{
                for(String s : monster.getMonsterDescription()){
                    System.out.println(s);
                }
                System.out.println("The monster has "+monster.getHP()+
                        " HP and deals "+ monster.getDealDamage()+" damage.");
            }

        }else{
            System.out.println("Invalid command.");
        }


    }
    /**
     * displays the health points of the player
     */
    public void displayHP(String itemName) {
        if (itemName.toLowerCase().compareTo("hp") == 0) {
            System.out.println("The player has " + player.getHP() + " HP.");
        } else {
            System.out.println("Invalid command.");
        }
    }
    /**
     * Fleeing from the combat and the monster will not be available in the room.
     */
    public void ignore(){
        currentRoom=map.getRooms().get(getPlayerLocation());
        if(currentRoom.getMonster().isDefeated()){
            System.out.println("You are not in a combat.");
        }else{
            System.out.println("You are leaving the combat.");
            currentRoom.getMonster().setDefeated(true);
        }

    }

    /**
     * displays the description, items, puzzles and monsters in the room
     */
    public void exploreCurrentRoom(String currentCommand ) {
        currentRoom=map.getRooms().get(getPlayerLocation());
        System.out.println("You are at the "+currentRoom.getRoomName());
        ArrayList<String> descriptions = currentRoom.getRoomDescription();
        for(String s : descriptions){
            System.out.println(s);
        }

        ArrayList<Item> inventory = currentRoom.getRoomInventory();
        if(!inventory.isEmpty()){
            System.out.println("Items available in this room are : ");
            for(Item item : inventory){

                System.out.println(" - "+item.getItemName() + ": "+
                        item.getItemDescription());
            }
        }
        Monster monster=currentRoom.getMonster();
        if(!monster.isDefeated()){
            System.out.println("\nThere is a monster in this room: \n"+monster.getMonsterName());
            for(String s : monster.getMonsterDescription()){

                System.out.println(s);
            }
            System.out.println("The "+monster.getMonsterName()+" Health Point: "
                    +monster.getHP()+" Deal damage : "+ monster.getDealDamage());
            System.out.println("Your health point : "+player.getHP());

        }
        if(!currentRoom.getPuzzle().isSolved()) {
            Puzzle currentPuzzle = currentRoom.getPuzzle();

            System.out.println("\nYou have a puzzle to solve : "+currentPuzzle.getPuzzleName());
        }

        currentCommand="";
    }

    /**
     * Displays the description of the item
     */
    public  void inspectItem(String itemName ) {
        for (int i = 0; i < playerInventory.size(); i++) {
            if (playerInventory.get(i).getItemName().compareTo(itemName) == 0) {
                Item currentItem = playerInventory.get(i);
                System.out.println(currentItem.getItemDescription());
                return;
            }
        }
        System.out.println("The " + itemName + " is/are not picked up yet.");

    }
    /**
     * displays the items in the inventory
     */
    public void displayInventory() {


        if(playerInventory.size() ==0 ){
            System.out.println("You didn’t pickup any items yet.");
            return;
        }else {
            System.out.println("Your inventory has the following items: ");

            for(int i=0;i<playerInventory.size();i++){

                System.out.println(" - "+playerInventory.get(i).getItemName());


            }

        }

    }

    /**
     * This function lets the player to drop an item that is already picked up
     * @param itemName : the item to drop
     */

    public void dropItem(String itemName) {
        Room currentRoom= map.getRooms().get(getPlayerLocation());
        for (int i = 0; i < playerInventory.size(); i++) {
            if (playerInventory.get(i).getItemName().compareTo(itemName) == 0) {
                Item currentItem = playerInventory.get(i);


                if (currentItem.getItemType().compareTo("equippable") == 0) {
                    if (((Equippable) currentItem).isEquipped()) {
                        System.out.println("Before dropping the " + itemName +
                                " you have to unequip it.");
                        return;
                    }

                }
                currentRoom.getRoomInventory().add(currentItem);//adding the item to the room inventory
                playerInventory.remove(currentItem);//item is removed from player inventory
                System.out.println(currentItem.getItemName() + " has been dropped successfully from " +
                        "the player inventory and placed in the " + currentRoom.getRoomName());
                return;
            }
        }
        System.out.println("The " + itemName + " is/are not picked up yet.");

        return;
    }
    /**
     * lets the player to pickup the item specified
     * The item is removed from room inventory and
     * added to Player Inventory
     */
    public void pickupItem(String itemName) {
        Room currentRoom= map.getRooms().get(getPlayerLocation());
        ArrayList<Item> roomInventory=currentRoom.getRoomInventory();
        for(int i=0;i<roomInventory.size();i++){
            if(roomInventory.get(i).getItemName().compareTo(itemName)==0){
                Item currentItem=roomInventory.get(i);
                playerInventory.add(currentItem);//the item is added to the player inventory
                currentRoom.getRoomInventory().remove(currentItem);// the item is removed from the room inventory
                System.out.println(currentItem.getItemName()+" has been picked up from the "+currentRoom.getRoomName()+
                        " and successfully added to the player inventory.");
                return;
            }
        }
        System.out.println("The item "+ itemName +" does not exist in this room.");

    }

    @Override
    public String toString() {
        return "Player{" +
                " HP : "+super.getHP()+
                ", Deal damage :"+ super.getDealDamage()+
                ",\n playerInventory=" + playerInventory +
                ",\n currentRoom=" + currentRoom +
                '}';
    }
}
