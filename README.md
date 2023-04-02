# Chess Game

This is a simple implementation of the game of chess in the terminal, written in Java. The game is designed using object-oriented principles, which allows for easy maintenance and scalability.

## Getting Started

To play the game, simply run the `ChessGame.java` file using a Java compiler. This will display the chess board on the screen, and prompt for the first move by the white player. The game uses standard algebraic notation to indicate moves, with the file (column) and rank (row) of the two squares separated by a space.

## Features

- The board is drawn on the screen using ASCII art, and the game can be played entirely in the terminal.
- Every piece knows what moves are allowed on it. If a player attempts an illegal move on a piece, the program will not execute the move and will print "Illegal move, try again".
- When a move is made, and it puts the opponent's King under check, the program will print "Check" before prompting for the opponent's move.
- If a checkmate is detected, the program will print "Checkmate".
- The game can handle special moves such as castling and pawn promotion.
- The program will display "Black wins", "White wins" or "draw" when the game ends.
- Has the ability to take a text file as an input and run the game (Need to set mode to "file").
- The user could offer a draw like this: "a2 a3 draw?"


## Contributing

Contributions are welcome. Feel free to open a pull request or an issue if you have any suggestions or bug reports.

## Authors

- [Zihao Zheng](https://github.com/zhengzihao2002)
- [Yiming Huang]

## Acknowledgments

- The ASCII art used to draw the chess board is based on the work of [Joan Stark](https://github.com/joan-stark).
- Thanks to [Your Professor's Name] for providing the assignment and guidance on the object-oriented design principles used in this project.
