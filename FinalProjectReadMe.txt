================================
README FOR COBRA TEXT BASED GAME
================================

List of Commands
================
====Movement==== (will show error message if movement choice is impossible)

north or n..........moves to the north adjacent room
south or s..........moves to the south adjacent room
east or e...........moves to the east adjacent room
west or w ..........moves to the adjacent west room

====Inspect=====

inspect item......system will display item information
inspect monster...system will display monster information
inspect room......system will display room information
inspect puzzle....system will display puzzle information

List of Classes
================
Main -          a simple class that contains the main method and a call to the MedievalGame object class.
                Also contains most of the inner game logic, player decisions and map information.
                Receives input from the text files and parses the information into game objects.
                This class also contains the main game loop, and represents the "model" in
                the MVC format. The MedievalGame class has reference the other classes, but they
                do not contain a similar reference.

 

Player - contains player information, including hp, currentRoom, inventory. also includes the ArrayList for Inventory,
          as well as equip and unequip item methods.

Input - uses a FileReader to parse the txt files into game objects. processess monster, puzzle, item, player, and room data.

Output - a class used to save/load the game. This feature outputs the current game state into a new text file

Room - contains room information, including roomID, roomName, roomDescription

Map - creates the HashMap that holds the map information, such as which exits are available from which room

Puzzle - puzzle.java containing puzzle information and parameters

Item - a parent class that holds basic item information, itemID, itemName, description
->Equippable - a subclass of Item that includes equip information, a modifier to the player's stats
->Usable - a subclass of Item that includes use information, which are a one time occurance upon the player's command

Monster - contains information about monster

List of Files
===============
Puzzles.txt
Monsters.txt
Items.txt
Rooms.txt
