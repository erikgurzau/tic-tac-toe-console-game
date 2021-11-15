# tris-console-terminal
Java tris (tic-tac-toe) game using console terminal.

The project was created with the aim of creating something fun and more customizable.

To run the game you need to have:
- Versions after Java 8

## The Game
With command above you can already start the game and be taken to the initial screen of presentation

![App Screenshot](./media/screenshot_menu.png)

>You can type '1' (classic game 3x3) or '2' (custom game max 20x20 with max 20 players) or '0' to exit

After choosing the type of game, the empty game grid appears waiting for the player’s turn (shown in blue)

![App Screenshot](./media/screenshot_empty.png)

To place your own symbol just enter the row and column number.

It is not possible to enter the symbol in an already occupied box or to enter values out of range 0 - N, where N is the number of rows or columns in the grid

![App Screenshot](./media/screenshot_error.png)


The end of the game:
- The winner is the one who places his symbol three times in line on the same row or column or on one of the two diagonals of the grid.
- Draw if no one has been able to align their symbol or finish the available grid spaces

![App Screenshot](./media/screenshot_win.png) ![App Screenshot](./media/screenshot_pareggio.png)


## Authors

- [@katherinepeterson](https://www.github.com/erik3101)

