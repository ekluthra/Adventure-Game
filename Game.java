/* COMP SCI CPT MADE BY EKAGRA LUTHRA
 * this code is a cpt made in java. it is a game that involves strategy and intuition. When you start the game, it puts 
 * the user in the shoes of a random adventurer, and throws them into a dungeon where they must explore, fight enemies, 
 * avoid traps, and look for loot like weapons, health potions, and the holy grail. 
 * 
 * NOTE: SOMETIMES JAVA WORKS STRANGELY WITH GRAPHICS, if the screen shows blank at first, then you have to close the window, 
 * then rerun the program. you have to keep trying this until the main screen shows up with my name, the game name, and a start button
 */
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.util.Random;
//main class
public class Game {
     //main window that opens when you start the game
     JFrame window;
     //container that holds everything that you put into the window
     Container con;
     //panels that are like boxes to hold things like text
     JPanel titleNamePanel, startButtonPanel, myNamePanel, mainTextPanel, choiceButtonPanel, playerPanel;
     //text
     JLabel titleNameLabel, myNameLabel, healthLabel, healthLabelNumber, weaponLabel, weaponLabelName, potionLabel, potionLabelNumber;
     //"Dungeon Adventure" font on the main screen
     Font titleFont = new Font("Times New Roman", Font.PLAIN, 52);
     //main font used all throughout the game
     Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);
     //small font for the top of the game screen that shows player status
     Font smallFont = new Font("Times New Roman", Font.PLAIN, 22);
     //buttons
     JButton startButton, choice1, choice2, choice3, choice4;
     //text area that holds most dialogue
     JTextArea mainTextArea;
     //random "rand" helps choose a random name for the main character, enemy healths, and the types of enemies
     Random rand = new Random();
     //arrays to assign names to numbers
     String[] firstNameOptions = {"Robert", "Thunder", "John", "Nick", "Sayid", "Storm", "Chase", "Rayon", "Bad"};
     String[] lastNameOptions = {"Thundertamer", "Firefortune", "Whitewalker", "Swordswinger", "Bondbreaker", "Carcasscreator", "Sharpshooter", "Miller", "Bongo"};
     //randomly decides first name and last name for character
     String firstName = firstNameOptions[rand.nextInt(firstNameOptions.length)];
     String lastName = lastNameOptions[rand.nextInt(lastNameOptions.length)];
     //the max amount of health an enemy can have
     int maxEnemyHealth = 75;
     int minEnemyHealth = 25;
     //the min and max damage an enemy can do
     int minEnemyAttackDamage = 15;
     int maxEnemyAttackDamage = 50; 
     //other ints
     int playerHealth, enemyAttackDamage, numPotions, enemyHealth, damageDealt, potionDrop;
     //strings for weapons, player position, and enemies, which are declared later on
     String weapon, position, enemy;
     //types of enemies
     String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin"};
     //min and max amount of damage each weapon can do
     int minDaggerAttackDamage = 15;
     int maxDaggerAttackDamage = 40;
     int minWoodSwordAttackDamage = 25;
     int maxWoodSwordAttackDamage = 45; 
     int minMetalSwordAttackDamage = 30;
     int maxMetalSwordAttackDamage = 50;
     //number of health potions the player starts with
     int numHealthPotions = 3;
     //the amount that each potion heals 
     int healthPotionHealAmount = 30;
     //boolean for telling if the main characer is moving or not
     boolean running = true;
     //different methods for handling different screens
     TitleScreenHandler tsHandler = new TitleScreenHandler();
     ChoiceHandler choiceHandler = new ChoiceHandler();
     //main method
     public static void main(String[] args) { 
          new Game();
     }
     //method for whole game
     public Game(){
          //game window
          window = new JFrame();
          //size of window
          window.setSize(800,600);
          window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          //background color for window
          window.getContentPane().setBackground(Color.black);
          //the layout manager usually helps with size and allignment, but i chose to work without it because it makes things complicated
          window.setLayout(null);
          //makes sure the window is visible
          window.setVisible(true);
          //container
          con = window.getContentPane();
          
          //title name 
          //declaration
          titleNamePanel = new JPanel();
          //boundaries
          titleNamePanel.setBounds(100,100,600,150);
          //background color
          titleNamePanel.setBackground(Color.black);
          //text in panel
          titleNameLabel = new JLabel("DUNGEON ADVENTURE");
          //text color
          titleNameLabel.setForeground(Color.white);
          //font 
          titleNameLabel.setFont(titleFont);
          
          //my name 
          //declaration
          myNamePanel = new JPanel();
          //boundaries
          myNamePanel.setBounds(240,160,300,50);
          //background color
          myNamePanel.setBackground(Color.black);
          //text
          myNameLabel = new JLabel("BY EKAGRA LUTHRA");
          //color of text
          myNameLabel.setForeground(Color.white);
          //font
          myNameLabel.setFont(normalFont);
          
          //start button
          startButtonPanel = new JPanel();
          //button bounds
          startButtonPanel.setBounds(300,400,200,100);
          //button panel background color
          startButtonPanel.setBackground(Color.black);
          //text and declarion
          startButton = new JButton("START");
          //button colors
          startButton.setBackground(Color.black);
          startButton.setForeground(Color.white);
          //font
          startButton.setFont(normalFont);
          //calls on tsHandler to take it to certain screen
          startButton.addActionListener(tsHandler);
          //removes some ugly lines that pop up when the mouse scrolls over the text
          startButton.setFocusPainted(false);
          
          //adding all of them to their respective panels and containers
          titleNamePanel.add(titleNameLabel);
          myNamePanel.add(myNameLabel);
          startButtonPanel.add(startButton);
          con.add(titleNamePanel);
          con.add(startButtonPanel);
          con.add(myNamePanel);
     }
     
     //method for game screen
     public void createGameScreen(){
          //gets rid of previous screen text and buttons
          titleNamePanel.setVisible(false);
          startButtonPanel.setVisible(false);
          myNamePanel.setVisible(false);
          
          //creates new text panel
          mainTextPanel = new JPanel();
          mainTextPanel.setBounds(100,100,600,250);
          mainTextPanel.setBackground(Color.black);
          //adds new text panel to container
          con.add(mainTextPanel);
          //creates area in panel for text
          mainTextArea = new JTextArea();
          //bounds
          mainTextArea.setBounds(100,100,600,250);
          //colors
          mainTextArea.setBackground(Color.black);
          mainTextArea.setForeground(Color.white);
          //font
          mainTextArea.setFont(normalFont);
          //makes it so that the sentence automatically goes to the next line when one is full
          mainTextArea.setLineWrap(true);
          mainTextArea.setWrapStyleWord(true);
          //sets it so that text cannot be edited in window
          mainTextArea.setEditable(false);
          //adding textarea to panel
          mainTextPanel.add(mainTextArea);
          
          //creates panel for choice buttons to be used throughout the game
          choiceButtonPanel = new JPanel();
          //bounds
          choiceButtonPanel.setBounds(250,350,300,150);
          //colors
          choiceButtonPanel.setBackground(Color.black);
          //grid layout, 4 rows with 1 column
          choiceButtonPanel.setLayout(new GridLayout(4,1));
          con.add(choiceButtonPanel);
          
          //first button
          choice1 = new JButton();
          //colors
          choice1.setBackground(Color.black);
          choice1.setForeground(Color.white);
          //font
          choice1.setFont(normalFont);
          //removes mouse hovering lines
          choice1.setFocusPainted(false);
          //responds to choiceHandler
          choice1.addActionListener(choiceHandler);
          //when choice 1 is clicked, a string (declared later on) is set to "c1" that is used in if statements to tell what has been clicked
          choice1.setActionCommand("c1");
          //adding choice1 to panel of buttons
          choiceButtonPanel.add(choice1);
          
          //second button
          choice2 = new JButton();
          //colors
          choice2.setBackground(Color.black);
          choice2.setForeground(Color.white);
          //font
          choice2.setFont(normalFont);
          //removes extra lines
          choice2.setFocusPainted(false);
          //responds to choiceHandler
          choice2.addActionListener(choiceHandler);
          //sets string to "c2" to let the program know that button 2 has been clicked
          choice2.setActionCommand("c2");
          //adding to panel
          choiceButtonPanel.add(choice2);
          
          //third button
          choice3 = new JButton();
          //colors
          choice3.setBackground(Color.black);
          choice3.setForeground(Color.white);
          //font 
          choice3.setFont(normalFont);
          //removing extra lines
          choice3.setFocusPainted(false);
          //responds to choiceHandler
          choice3.addActionListener(choiceHandler);
          //"c3" is set as the string that corresponds to choice 3
          choice3.setActionCommand("c3");
          //addnig choice3 to choice buttons panel
          choiceButtonPanel.add(choice3);
          
          //fourth button
          choice4 = new JButton();
          //colors
          choice4.setBackground(Color.black);
          choice4.setForeground(Color.white);
          //font
          choice4.setFont(normalFont);
          //removes extra lines
          choice4.setFocusPainted(false);
          //responds to choiceHandler
          choice4.addActionListener(choiceHandler);
          //"c4" is the string corresponding to choice4
          choice4.setActionCommand("c4");
          //adding choice4 to button panel
          choiceButtonPanel.add(choice4);
          
          //player status area
          playerPanel = new JPanel();
          //bounds
          playerPanel.setBounds(15,15,890,50);
          //color
          playerPanel.setBackground(Color.black);
          //grid layout, 1 row, 6 columns
          playerPanel.setLayout(new GridLayout(1,6));
          //adding playerpanel to container
          con.add(playerPanel);
          
          //dsplays player health
          healthLabel = new JLabel("Health: ");
          //font
          healthLabel.setFont(smallFont);
          //font color
          healthLabel.setForeground(Color.white);
          //adding health label to player panel area
          playerPanel.add(healthLabel);
          
          //displays player health amount
          healthLabelNumber = new JLabel();
          //font
          healthLabelNumber.setFont(smallFont);
          //text color
          healthLabelNumber.setForeground(Color.white);
          //adding to player panel
          playerPanel.add(healthLabelNumber);
          
          //displays weapon
          weaponLabel = new JLabel("Weapon: ");
          //font
          weaponLabel.setFont(smallFont);
          //foreground color
          weaponLabel.setForeground(Color.white);
          //adding to player panel
          playerPanel.add(weaponLabel);
          
          //displays the weapon the player has
          weaponLabelName = new JLabel();
          //font
          weaponLabelName.setFont(smallFont);
          //text color
          weaponLabelName.setForeground(Color.white);
          //adding to player panel
          playerPanel.add(weaponLabelName);
          
          //displays potion
          potionLabel = new JLabel("Health Pots: ");
          //font
          potionLabel.setFont(smallFont);
          //font color
          potionLabel.setForeground(Color.white);
          //adding to player panel
          playerPanel.add(potionLabel);
          
          //displays amount of potions the player has
          potionLabelNumber = new JLabel();
          //font 
          potionLabelNumber.setFont(smallFont);
          //font color
          potionLabelNumber.setForeground(Color.white);
          //adding to player panel
          playerPanel.add(potionLabelNumber);
          
          //calls playerSetup to this method
          playerSetup();
     }
     
     //instructionScreen to show player how the game works
     public void instructionScreen(){
          position = "instructionScreen";
          mainTextArea.setText("\t     Instructions:\nThe game is simple, to play you just have to read the text that is in this area. The top bar is your status (health, weapon, and potions). The four boxes will have choices at each screen and each choice will land you in a specific screen. \n0 health = death");
          choice1.setText("Play");
          choice2.setText("Quit");
          choice3.setText("");
          choice4.setText("");
     }
     
     //playerSetup method for all player status
     public void playerSetup(){
          //player startup health
          playerHealth = 100;
          //player starting weapon
          weapon = "Dagger";
          //number of potions the player starts with
          numPotions = 3;
          //displays all three
          weaponLabelName.setText(weapon);
          healthLabelNumber.setText(""+playerHealth);
          potionLabelNumber.setText(""+numPotions);
          //adds very first screen to this method
          instructionScreen();
     }
     //first screen in the game
     public void dungeonEntry(){
          //setting player health, weapon, and potions in case they restart the game and displaying them
          playerHealth = 100;
          weapon = "Dagger";
          numPotions = 3;
          weaponLabelName.setText(weapon);
          healthLabelNumber.setText(""+playerHealth);
          potionLabelNumber.setText(""+numPotions);
          //position for if statements to know what the player chose
          position = "dungeonEntry";
          //text for main area
          mainTextArea.setText("Your name is " +firstName+ " " +lastName+ ". You are an adventurer. You find yourself at the entrance of a large cave, possibly containing lots of loot, possibly containing lots of traps.\nWhat do you do?");
          //choices
          choice1.setText("Explore");
          choice2.setText("Walk away");
          choice3.setText("");
          choice4.setText("");
     }
     //this is what happens when the player walks away
     public void walkAway(){
          //player loses the game
          position = "walkAway";
          //picks random enemy
          String enemy = enemies[rand.nextInt(enemies.length)];
          //main text saying how the player died
          mainTextArea.setText("You walk away from the Dungeon and get killed by a " +enemy+"\nYou should have just explored, like a REAL adventurer.\n\t    YOU LOSE");
          //choices
          choice1.setText("Start Again");
          choice2.setText("Close Game");
          choice3.setText("");
          choice4.setText("");
     }
     //this is what happens when the player explores
     public void explore(){
          //allows player to fight against random enemy with random health or avoid them
          position = "explore";
          //picks random enemy and enemy health
          enemy = enemies[rand.nextInt(enemies.length)];
          enemyHealth = rand.nextInt(maxEnemyHealth - minEnemyHealth ) + minEnemyHealth;
          //main text
          mainTextArea.setText(enemy + " with " +enemyHealth+ " health has appeared!\nWhat do you do?");
          //choices
          choice1.setText("Fight");
          choice2.setText("Avoid");
          choice3.setText("");
          choice4.setText("");
     }
     //this is if they avoid the enemy the first time around
     public void explore2(){
          //this time, they have to fight the enemy
          position = "explore2";
          //picks random enemy and enemy health
          enemy = enemies[rand.nextInt(enemies.length)];
          enemyHealth = rand.nextInt(maxEnemyHealth - minEnemyHealth) + minEnemyHealth;
          //text and choices
          mainTextArea.setText(enemy + " with " +enemyHealth+ " health has appeared!\nWhat do you do?");
          choice1.setText("Fight");
          choice2.setText("");
          choice3.setText("");
          choice4.setText("");
     }
     //this is what happens when the player fights the enemy
     public void fight(){
          //gives player options on what to do while fighting
          position = "fight";
          //text and choices
          mainTextArea.setText(enemy+" health: " +enemyHealth+"\nDamage taken from last attack: " +enemyAttackDamage);
          //refreshes player status
          weaponLabelName.setText(weapon);
          healthLabelNumber.setText(""+playerHealth);
          potionLabelNumber.setText(""+numPotions);
          choice1.setText("Attack");
          choice2.setText("Heal");
          choice3.setText("Run");
          choice4.setText("");
     }
     //this is what happens if the player chooses to heal
     public void heal(){
          position = "heal";
          //heals the player by 30 health if their health is below 71 and takes away one potion
          if(((playerHealth+healthPotionHealAmount) < 100) && (numPotions > 0)){
               playerHealth+= healthPotionHealAmount;
               numPotions--;
          }
          //if player doesnt have potions or health is at 100, it doesnt heal them
          else if((numPotions == 0) || (playerHealth == 100)){
               fight();
          }
          //if player health is between 71 and 100, potion just heals them to 100 and takes away potion
          else{
               while((playerHealth < 100) && (numPotions > 0)){
                    playerHealth++;
               }
               numPotions--;
          }
          //refeshes the player status
          weaponLabelName.setText(weapon);
          healthLabelNumber.setText(""+playerHealth);
          potionLabelNumber.setText(""+numPotions);
          //choices
          choice1.setText("Attack");
          choice2.setText("Heal");
          choice3.setText("Run");
          choice4.setText("");
          //sends player back to fight screen
          fight();
     }
     //this is what happens when the player attacks 
     public void attack(){
          position = "attack";
          //if the weapon attacks with a dagger, it will do less damage than a wooden sword, but by a random amount
          if(weapon.equals("Dagger")){
          damageDealt = rand.nextInt(maxDaggerAttackDamage - minDaggerAttackDamage +1) + minDaggerAttackDamage;
          }
          else if(weapon.equals("Wood Sword")){
          damageDealt = rand.nextInt(maxWoodSwordAttackDamage - minWoodSwordAttackDamage+1)+minWoodSwordAttackDamage;
          }
          //enemy attacks player and takes away certain amount of health while the player does the same to the enemy
          enemyAttackDamage = rand.nextInt(maxEnemyAttackDamage-minEnemyAttackDamage)+minEnemyAttackDamage;
          playerHealth -= enemyAttackDamage;
          enemyHealth -= damageDealt;
          //if the enemy is dead then there's a 50/50 chance they drop a potion and it sends the player to the screen that tells them that the enemy was defeated
          if(enemyHealth <= 0){
               potionDrop = rand.nextInt(2);
               switch(potionDrop){
                    case 1: numPotions++; break; 
               }
               enemyDefeated();
          }
          //if player health is less than or equal to 0, they are dead and get sent to the respective screen
          else if (playerHealth <= 0){
               diedScreen();
               //sets player health to 0 to ensure that player health doesnt end up on a negative number
               for(int i = playerHealth; i<0;i++){
                    playerHealth++;
               }
          }
          //continues fight if nobody has lost the fight yet
          else{
          mainTextArea.setText("You dealt " + damageDealt + " damage to the "+enemy+"\nEnemy health: " +enemyHealth+ "\nThe " +enemy+" dealt " +enemyAttackDamage+" damage to you");
          fight();
          }
          //updates player status
          weaponLabelName.setText(weapon);
          healthLabelNumber.setText(""+playerHealth);
          potionLabelNumber.setText(""+numPotions);
     }
     //this is the screen that shows up when the player dies
     public void diedScreen(){
          //position to help with if statements
          position = "diedScreen";
          //text area and choices 
          mainTextArea.setText("The " +enemy+ " dealt " + enemyAttackDamage+ " damage to you and you died\n\t    YOU LOSE");
          choice1.setText("Start Again");
          choice2.setText("Close Game");
          choice3.setText("");
          choice4.setText("");
     }
     //this shows up when the player runs from the enemy
     public void run(){
          position = "run";
          //text area and choices
          mainTextArea.setText("You got away from the " +enemy+ "... but you were careless while running and fell into a death trap\n\t    YOU LOSE");
          choice1.setText("Start Again");
          choice2.setText("Close Game");
          choice3.setText("");
          choice4.setText("");
          //updates player status
          weaponLabelName.setText(weapon);
          healthLabelNumber.setText(""+playerHealth);
          potionLabelNumber.setText(""+numPotions);
     }
     //this shows up if the player won the fight
     public void enemyDefeated(){
          position = "enemyDefeated";
          //puts player haelth to 1 if they have defeated the enemy but the enemy does enough damage to get them to the negatives at the end
          if(playerHealth < 0){
               for(int i = playerHealth; i<1; i++){
                    playerHealth++;
               }
          }
          //updates player status
          weaponLabelName.setText(weapon);
          healthLabelNumber.setText(""+playerHealth);
          potionLabelNumber.setText(""+numPotions);
          //text area and choices
          mainTextArea.setText("You dealt " + damageDealt + " damage to the " + enemy+ " and defeated it but it hit you with " +enemyAttackDamage+ " damage before dying\nThe "+enemy+ " dropped " + potionDrop + " health potions" );
          choice1.setText("Turn left");
          choice2.setText("Turn right");
          choice3.setText("Go Straight");
          choice4.setText("Go Back");
     }
     //player finds wooden sword if they first avoid the enemy
     public void findWoodSword(){
          position = "findWoodSword";
          //gives player a new weapon
          weapon = "Wood Sword";
          //text area informing them of the weapon
          mainTextArea.setText("You found a wooden sword. Its minimum attack damage is " + minWoodSwordAttackDamage+ " and max is " + maxWoodSwordAttackDamage);
          //updates player status
          weaponLabelName.setText(weapon);
          healthLabelNumber.setText(""+playerHealth);
          potionLabelNumber.setText(""+numPotions);
          //choices
          choice1.setText("Keep Moving");
          choice2.setText("");
          choice3.setText("");
          choice4.setText("");
     }
     //this is what happens when the player turns left after defeating the enemy
     public void leftTurn(){
          //position for if statements
          position = "leftTurn";
          //gives player a new weapon
          weapon = "Metal Sword";
          //text area informing player of new weapon
          mainTextArea.setText("You turn left and find a metal sword lying in a chest. Its minimum attack damage is " + minMetalSwordAttackDamage+ " and max is "+maxMetalSwordAttackDamage);;
          //refreshes player status
          weaponLabelName.setText(weapon);
          healthLabelNumber.setText(""+playerHealth);
          potionLabelNumber.setText(""+numPotions);
          //choices
          choice1.setText("Fight an enemy");
          choice2.setText("Avoid an enemy");
          choice3.setText("");
          choice4.setText("");
     }
     //this is what happens when the player turns right
     public void rightTurn(){
          position = "rightTurn";
          //main text area informing player about found health potion
          mainTextArea.setText("You turn right and find a health potion. It can heal you for " +healthPotionHealAmount + ". Where do you want to go now?");
          //adds one to the number of health potions
          numPotions++;
          //choices
          choice1.setText("Fight an enemy");
          choice2.setText("Avoid an enemy");
          choice3.setText("");
          choice4.setText("");
          //refreshes player status
          weaponLabelName.setText(weapon);
          healthLabelNumber.setText(""+playerHealth);
          potionLabelNumber.setText(""+numPotions);
     }
     //what happens when the player avoids the second battle
     public void avoid2(){
          position = "avoid2";
          //text area
          mainTextArea.setText("While avoiding the enemy you fell into a trap... or what you thought was a trap.You actually found the holy grail, worth millions of dollars. You can sell this to the King's men and become rich. Your goal is complete.\n\tYOU WIN!");
          //choices
          choice1.setText("Start Again");
          choice2.setText("Close Game");
          choice3.setText("");
          choice4.setText("");
          //player status
          weaponLabelName.setText(weapon);
          healthLabelNumber.setText(""+playerHealth);
          potionLabelNumber.setText(""+numPotions);
     }
     //what happens when the player accepts the second battle
     public void fight2(){
          //position of player
          position = "fight2";
          //main text
          mainTextArea.setText(enemy+" health: " +enemyHealth+"\nDamage taken from last attack: " +enemyAttackDamage);
          //player status
          weaponLabelName.setText(weapon);
          healthLabelNumber.setText(""+playerHealth);
          potionLabelNumber.setText(""+numPotions);
          //choices
          choice1.setText("Attack");
          choice2.setText("Heal");
          choice3.setText("");
          choice4.setText("");
     }
     //what happens when the player attacks during the second battle
     public void attack2(){
          position = "attack2";
          //if the player attacks with a dagger, it will do less damage than a wooden sword, but by a random amount
          if(weapon.equals("Dagger")){
          damageDealt = rand.nextInt(maxDaggerAttackDamage - minDaggerAttackDamage +1) + minDaggerAttackDamage;
          }
          //if the player attacks with a wood sword, it will do more than a dagger by a random amount
          else if(weapon.equals("Wood Sword")){
          damageDealt = rand.nextInt(maxWoodSwordAttackDamage - minWoodSwordAttackDamage+1)+minWoodSwordAttackDamage;
          }
          //if player attacks with a metal sword, it will do more than a wood sword by a random amount
          else if(weapon.equals("Metal Sword")){
          damageDealt = rand.nextInt(maxMetalSwordAttackDamage - minMetalSwordAttackDamage + 1) + minMetalSwordAttackDamage;
          }
          //enemy attacks player and takes away certain amount of health while the player does the same to the enemy
          enemyAttackDamage = rand.nextInt(maxEnemyAttackDamage-minEnemyAttackDamage)+minEnemyAttackDamage;
          playerHealth -= enemyAttackDamage;
          enemyHealth -= damageDealt;
          //if the enemy is dead then there's a 50/50 chance they drop a potion and it sends the player to the screen that tells them that the enemy was defeated
          if(enemyHealth <= 0){
               potionDrop = rand.nextInt(2);
               switch(potionDrop){
                    case 1: numPotions++; break; 
               }
               //sends them to the endgame screen
               gameFin1();
          }
          //if player health is less than or equal to 0, they are dead and get sent to the respective screen
          else if (playerHealth <= 0){
               diedScreen();
               //sets player health to 0 to ensure that player health doesnt end up on a negative number
               for(int i = playerHealth; i<0;i++){
                    playerHealth++;
               }
          }
          //continues fight if nobody has lost the fight yet
          else{
               //informs player of damage taken and done
          mainTextArea.setText("You dealt " + damageDealt + " damage to the "+enemy+"\nEnemy health: " +enemyHealth+ "\nThe " +enemy+" dealt " +enemyAttackDamage+" damage to you");
          //takes them to the second fight screen
          fight2();
          }
          //updates player status
          weaponLabelName.setText(weapon);
          healthLabelNumber.setText(""+playerHealth);
          potionLabelNumber.setText(""+numPotions);
     }
     //end game screen
     public void gameFin1(){
          position = "gameFin1";
          //puts player health to 1 if it goes into the negatives because sometimes it ends up negative but the player defeats the enemy anyway
          if(playerHealth < 0){
               for(int i = playerHealth; i<1; i++){
                    playerHealth++;
               }
          }
          //text area and choices
          weaponLabelName.setText(weapon);
          healthLabelNumber.setText(""+playerHealth);
          potionLabelNumber.setText(""+numPotions);
          mainTextArea.setText("The enemy that you killed actually dropped the holy grail. Now you can go and sell this to the King's men for millions of dollars. Your goal is complete.\n\tYOU WIN!");
          choice1.setText("Start Again");
          choice2.setText("Close Game");
          choice3.setText("");
          choice4.setText("");
     }
     //what happens when the player heals during the second battle
     public void heal2(){
          position = "heal2";
          //heals the player by 30 health if their health is below 71 and takes away one potion
          if(((playerHealth+healthPotionHealAmount) < 100) && (numPotions > 0)){
               playerHealth+= healthPotionHealAmount;
               numPotions--;
          }
          //if player doesnt have potions or health is at 100, it doesnt heal them
          else if((numPotions == 0) || (playerHealth == 100)){
               fight2();
          }
          //if player health is between 71 and 100, potion just heals them to 100 and takes away one potion
          else{
               while((playerHealth < 100) && (numPotions > 0)){
                    playerHealth++;
               }
               numPotions--;
          }
          //refeshes the player status
          weaponLabelName.setText(weapon);
          healthLabelNumber.setText(""+playerHealth);
          potionLabelNumber.setText(""+numPotions);
          choice1.setText("Attack");
          choice2.setText("Heal");
          choice3.setText("Run");
          choice4.setText("");
          //sends player back to fight screen
          fight2();
     }
     //this is what happens when the player goes forward
     public void forward(){
          position = "forward";
          //picks radom enemy with random health
          enemy = enemies[rand.nextInt(enemies.length)];
          enemyHealth = rand.nextInt(maxEnemyHealth - minEnemyHealth ) + minEnemyHealth;
          mainTextArea.setText(enemy + " with " +enemyHealth+ " health has appeared!\nWhat do you do?");
          choice1.setText("Fight");
          choice2.setText("Avoid");
          choice3.setText("");
          choice4.setText("");
          //player status
          weaponLabelName.setText(weapon);
          healthLabelNumber.setText(""+playerHealth);
          potionLabelNumber.setText(""+numPotions);
     }
     //this is what happens when the player goes back
     public void back(){
          position = "back";
          //text area and choices
          mainTextArea.setText("You can't go back yet, there's more loot to find");
          choice1.setText("Turn Left");
          choice2.setText("Turn Right");
          choice3.setText("Go Straight");
          choice4.setText("");
     }
     // a class for handling the main game screen
     public class TitleScreenHandler implements ActionListener{
          public void actionPerformed(ActionEvent event){
               //goes to main game screen if start button is pressed
               createGameScreen();
          }
     }
     // a class for handling choices 
     public class ChoiceHandler implements ActionListener{
          public void actionPerformed(ActionEvent event){
               //each choice corresponds to a string now, so being at a certain position in the game and clicking a choice will send the player to a set method
               String yourChoice = event.getActionCommand();
               /*
                *These are sets of if statements that work by checking the player's position, which is updated every time they pick a choice. 
                * they work by checking the player's poition, which lets them know what choices the player has infront of them. then, if one of those
                * choices is clicked at that specific position, it will go to that method. for example, if the player has just entered the dungeon, their position
                * is "dungeonEntry", giving them choices to explore (c1) or walk away (c2). this way the program knows that position = dungeonEntry
                * and yourChoice is either c1 or c2, which have their own respective methods
                */
               //when the player chooses to explore or walk away at dungeonEntry
               if(position.equals("dungeonEntry")){
                    if(yourChoice.equals("c1")){
                         explore();
                    }
                    else if(yourChoice.equals("c2")){
                         walkAway();
                    }
               }
               //when the player chooses to avoid or fight the enemy while exploring
               else if(position.equals("explore")){
                    if(yourChoice.equals("c1")){
                         fight();
                    }
                    else if(yourChoice.equals("c2")){
                         findWoodSword();
                    }
               }
               //when the player finds the wooden sword
               else if (position.equals("findWoodSword")){
                    if(yourChoice.equals("c1")){
                         explore2();
                    }
               }
               //when the player chooses to avoid the first time around and find the wood sword
               else if(position.equals("explore2")){
                    if(yourChoice.equals("c1")){
                         fight();
                    }
               }
               //when the player chooses to attack, heal, or run during a fight
               else if(position.equals("fight")){
                    if(yourChoice.equals("c1")){
                         attack();
                    }
                    else if(yourChoice.equals("c2")){
                         heal();
                    }
                    else if(yourChoice.equals("c3")){
                         run();
                    }
               }
               //when the player defeats the enemy and has to choose their path
               else if(position.equals("enemyDefeated")){
                    if(yourChoice.equals("c1")){
                         leftTurn();
                    }
                    else if(yourChoice.equals("c2")){
                         rightTurn();
                    }
                    else if(yourChoice.equals("c3")){
                         forward();
                    }
                    else if(yourChoice.equals("c4")){
                         back();
                    }
               }
               //when the player decides to go back but then has to take a left, right, or go forward
               else if(position.equals("back")){
                    if(yourChoice.equals("c1")){
                         leftTurn();
                    }
                    else if(yourChoice.equals("c2")){
                         rightTurn();
                    }
                    else if(yourChoice.equals("c3")){
                         forward();
                    }
               }
               //when the player moves forward and chooses to either avoid or fight the enemy
               else if(position.equals("forward")){
                    if(yourChoice.equals("c1")){
                         fight2();
                    }
                    else if(yourChoice.equals("c2")){
                         avoid2();
                    }
               }
               //when the player turns left or right and now have to move forward or avoid an enemy
               else if((position.equals("rightTurn")) || (position.equals("leftTurn"))){
                    if(yourChoice.equals("c1")){
                         forward();
                    }
                    else if(yourChoice.equals("c2")){
                         avoid2();
                    }
               }
               //when the player has to fight an enemy but cannot run, only heal or attack
               else if(position.equals("fight2")){
                    if(yourChoice.equals("c2")){
                         heal2();
                    }
                    else if(yourChoice.equals("c1")){
                         attack2();
                    }
               }
               //if the player walks away from the start of the game and dies and must either restart the game or close the game
               else if(position.equals("walkAway")){
                    if(yourChoice.equals("c1")){
                         dungeonEntry();
                    }
                    else if(yourChoice.equals("c2")){
                         window.dispose();
                    }
               }
               //when the player dies during a battle and must either restart the game or close the game
               else if(position.equals("diedScreen")){
                    if(yourChoice.equals("c1")){
                         dungeonEntry();
                    }
                    else if(yourChoice.equals("c2")){
                         window.dispose();
                    }
               }
               //when the player runs from a battle and dies and must either restart the game or close the game
               else if(position.equals("run")){
                    if(yourChoice.equals("c1")){
                         dungeonEntry();
                    }
                    else if(yourChoice.equals("c2")){
                         window.dispose();
                    }
               }
               //when the player avoids an enemy the second time around and wins the game and must either restart the game or close the game
               else if(position.equals("avoid2")){
                    if(yourChoice.equals("c1")){
                         dungeonEntry();
                    }
                    else if(yourChoice.equals("c2")){
                         window.dispose();
                    }
               }
               //the PROPER ending, where the player wins fair and square and must either restart the game or close the game
               else if(position.equals("gameFin1")){
                    if(yourChoice.equals("c1")){
                         dungeonEntry();
                    }
                    else if(yourChoice.equals("c2")){
                         window.dispose();
                    }
               }
               //when the player clicks start at the main screen, they are sent to the instruction screen. they can either play the game or close it
               else if(position.equals("instructionScreen")){
                    if(yourChoice.equals("c1")){
                         dungeonEntry();
                    }
                    else if(yourChoice.equals("c2")){
                         window.dispose();
                    }
               }
          }
     }
}