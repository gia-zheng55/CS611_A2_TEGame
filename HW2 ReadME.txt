# CS611-Assignment 1
## Game Infrastructure
---------------------------------------------------------------------------
Janki Hemant Chauhan
jankihc@bu.edu
U87928510

Zhi Zheng


## Files
---------------------------------------------------------------------------



## How to compile and run
---------------------------------------------------------------------------
javac Main.java
java Main


## Input/Output Example
---------------------------------------------------------------------------
Welcome to the Trianta Ena
Please enter the number of players (3-7)
3
Please enter the name of player 1
J
Please enter the name of player 2
H
Please enter the name of player 3
G
Which player will become the banker(dealer)?
1. J
2. H
3. G

2
PlayerJ gets a face down card
Your cards: 3-Spade 
PlayerG gets a face down card
Your cards: 7-Heart 
DealerH gets a face up card
Your cards: 5-Dianmond 
---------Current Cards---------
J: * 
G: * 
H: 5-Dianmond 

PlayerJ, do you want bet for this round? (y/n)
Your cards: 3-Spade 
Y
Invalid input, please enter y or n
Y
Invalid input, please enter y or n
y
PlayerG, do you want bet for this round? (y/n)
Your cards: 7-Heart 
y
PlayerJ gets two face up card
Your cards: 3-Spade 8-Heart 6-Spade 
PlayerG gets two face up card
Your cards: 7-Heart 2-Heart 3-Dianmond 
DealerH gets two face up card
Your cards: 5-Dianmond 7-Spade K-Club 
---------Current Cards---------
J: * 8-Heart 6-Spade 
G: * 2-Heart 3-Dianmond 
H: 5-Dianmond 7-Spade K-Club 

PlayerJ, do you want to hit? (y/n)
h
Invalid input, please enter y or n
y
PlayerJ gets one face up card
Your cards: 3-Spade 8-Heart 6-Spade J-Club 
PlayerJ, do you want to hit? (y/n)
y
PlayerJ gets one face up card
Your cards: 3-Spade 8-Heart 6-Spade J-Club K-Heart 
PlayerG, do you want to hit? (y/n)
y
PlayerG gets one face up card
Your cards: 7-Heart 2-Heart 3-Dianmond 5-Spade 
PlayerG, do you want to hit? (y/n)
n
DealerH gets one face up card
Your cards: 5-Dianmond 7-Spade K-Club 10-Dianmond 
The winners of the game are: 
G
---------Current Cards---------
J: 3-Spade 8-Heart 6-Spade J-Club K-Heart 
G: 7-Heart 2-Heart 3-Dianmond 5-Spade 
H: 5-Dianmond 7-Spade K-Club 10-Dianmond 

Would you like to cash out?(y/n)
y
Would you like to cash out?(y/n)
y
Would you like to cash out?(y/n)
y
Amount remaining for Player J: 90
Amount remaining for Player G: 110
Amount remaining for Player H: 300
              