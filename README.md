# BattleShip-ServerCompatible
### Made in CS3500 at Northeastern University
An online Battleship program that can be played via connection to a server or manually in terminal with an AI.

## Contributors: @angelina-dong

# How to run BattleShip: 
- if playing solo against programmed AI Player, run the program with no command line arguments.
- if playing on server, include Host then Port in the command line arguments.
       - To play against others, join on their Computer's Host address and set Game Mode to "Multi" in the code.
       - Otherwise, to play against random AI, include Host 0.0.0.0 and Port 35001

# Skills acquired: 
- Was able to practice using delegation, Abstract classes, Interfaces, etc.
- Used JSON and Jackson formatting as well as Records and the JSON Creator features.
  - Learned how to make use of JSON and Jackson to send standardized information back and forth through the server and our own computers.
  - Learned how to serialize and deserialize JSONs
- Created our own algorithms to place ships randomly on the board (favoring the edges)
- Created our own algorithms to have an AI Player play, intelligently, against a human player OR against another AI Player
