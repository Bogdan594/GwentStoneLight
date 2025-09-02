# GwentStoneLight

First OOP Project

## What is this project about?

This project simulates a game based on two Trading Card Games: Hearthstone and Gwent. The game has an already defined input, a list of commands.

It includes all the elements from the two games: a deck, a hero, mana, and a 4x5 board. There are two types of cards: minions and heroes.

## Classes
### Card
I used the mandatory fields from the problem statement: name, attackDamage, health, description, colors, and mana. Additionally, I used two more fields:

1. isFrozen - to track if a card is frozen (1 if it is, 0 by default if not).

2. hasAttackedThisTurn - to track if a card has attacked or used its ability during the current turn (1 for yes, 0 for no).

Methods

Besides the constructors, setters, and getters, the class contains the following methods:

1. attackAnotherCard - This method takes another Card as a parameter, sets the hasAttackedThisTurn field to 1, and decreases the selected card's health by the current card's attackDamage.

2. useAbilityDisciple, useAbilityMiraj, useAbilityTheCursedOne, useAbilityTheRipper - Each of these methods takes another Card as a parameter and applies the current card's ability to that card.

3. attackHero - This method takes a Hero as a parameter, sets the hasAttackedThisTurn field to 1, and decreases the selected hero's health by the current card's attackDamage.

### Deck 

This class is designed to hold a player's decks.

It has the following fields: the number of cards in the decks, the number of decks, and the decks themselves.

As for methods, I modified the default constructor to accept a DecksInput argument. This allows the class to read data from the input files.

### Actions

This is probably the most complex and longest class in the entire project.

The fields are the same as those in the input files.

Methods

1. Constructor: It takes an ActionsInput parameter and handles cases based on the input:

a. Both the attacking card and the card being attacked are not null, for commands like cardUsesAttack.

b. The attacking card is not null and the card being attacked is null, for commands like useAttackHero.

c. Anything else, for commands like endPlayerTurn, etc.

2. doAction: This method takes the following parameters: ArrayNode (for output), OutputsActions, OutputCommands, Game, Stats, HeroUsesAbility, GameUpdate, and objectMapper.

This method checks the name of the current command and performs the corresponding output for each case.

### Constants 

This class stores the constants that I needed throughout the project.

### Coordinatess 

Used for reading input.

### Game

This class holds the details of a game.

Fields:

Start

A list of actions

PrepareGame

playerTurn

Gameboard

pl1Mana and pl2Mana to keep track of each player's mana

The number of rounds and turns

Methods:

There is only one method, changeTurn. This method switches the playerTurn field from 1 to 2 or vice versa, depending on the current player. It also increments the number of turns. If the number of turns is a multiple of 2, it increments the round number and adds the corresponding mana. If the game is past round 10, it adds only 10 mana per round.

### Gameboard

This class effectively holds the game board where cards are placed.

Fields:

The fields are a board, represented as a 4x5 matrix, and playerTurn.

Methods:

placeCard: This method takes a card and playerMana as arguments. It checks if there's an available spot on the player's rows, as certain cards can only be placed on specific rows (front or back). The method returns 0 for a successful placement, 1 if the player has insufficient mana, and -1 if the row is full.

getCardsOnTable: This method iterates through the entire board and returns an array of all the cards currently on the board.

getCard: This method takes two numbers, x and y, as parameters and returns the card at board[x][y].

updateGameOnCard: This method takes a card and two numbers, x and y, as arguments. Since any action applied to a card on the board is performed on a copy, this method is used to update the card's health, attack damage, or any other necessary fields on the actual board. If the card's health is 0 or less, it places null on the board; otherwise, it places the updated card.

rearrange: This method iterates through each row of the board. If there is a null spot between two different cards, it moves the card on the right to the empty spot on the left.
afterTurnResetAttackIdx: At the end of a player's turn, this method iterates through every spot on the board and sets the hasAttackedThisTurn field to 0.

isTank: This method determines whether a card is a tank.

tankExistance: This method checks if an attackable tank card exists on the board, which would need to be targeted instead of the initially selected card.

unfroze: This method iterates through the player's rows and sets the isFrozen field of their cards to 0 after their turn.

getFrozenCardsOnTable: This method iterates through the board and returns an array of all cards whose isFrozen field is set to 1.

### GameUpdate

This class stores the players' decks and the cards in their hands.

Methods

getInHandPl1/2: This method takes the first card from a player's deck and places it in their hand.

removePl1/2CardFromHand: This method removes the card at a given index number from a player's hand array. This method was primarily used when a card was placed on the board.

### Hero

This class inherits the Card class.

Methods

Constructor: It uses the Card class constructor and sets the hero's health to 30.

isAttacked: This method takes a Card as an argument and decreases the hero's health by the attacking card's attackDamage.

isDead: This method checks if the hero's health is less than or equal to 0.

### Player

This is a class that's used less frequently. It stores a player's decks, the number of games played, and the number of wins.

### PrepareGame

The PrepareGame Class

This is another very important class that "prepares" the game.

Its fields include two Player objects, one for each player, as well as the number of decks and the **number of cards in each deck.

It has only one method, which reads from the input.

### Start

This is the class that starts the match.

Fields:

It contains the shuffleSeed, the deck index for each player, and the starting player.

Methods:

The methods are all getters and setters, and a constructor that takes a StartGameInput argument.

### Stats

This class stores the statistics for multiple matches between two players: games played and games won.
The constructor initializes all of these to 0.

### OutputsActions

This class contains only methods for handling invalid action cases. For example, it handles situations where a non-tank card is attacked, even though an attackable tank card exists on the opponent's rows.

For each method, a new node is created. The node includes the command (obtained via action.getcommand()), any other relevant fields for that action (obtained through the action's getters), and the corresponding error message.

### OutputsCommands

This class contains methods for displaying debug commands, such as getPlayerMana and getPlayerDeck.
For each method, a new node is created. The node includes the command (obtained via action.getcommand()), any other relevant fields for that action (obtained through the action's getters), and the corresponding error message.

### classes from the "heroes" package

All of these classes inherit from the Hero class and, in addition to its methods, they each have a useAbility method. Here’s what each method does:

EmpressThorina: This method iterates through the entire board, finds the card with the highest health, and kills it.

KingMudface: This method iterates through a row on the board and adds 1 to the health field of each card in that row.

GeneralKocioraw: This method iterates through a row on the board and adds 1 to the attackDamage field of each card in that row.

LordRoyce: This method iterates through a row on the board and sets the isFrozen field of each card in that row to 1.

### Main

I initialize the Stats class and iterate through all the games.

For each game, I initialize the Game class and the Start class within the Game object.

The actions and decks for each player are copied from the input. They are then shuffled, the command classes are initialized, and each action is iterated through and executed.
