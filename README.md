# Tetris Game
This project is a simple Tetris game implemented in `Java` using `Swing` for the graphical user interface. The game interface written fully with `Java Graphics API`. The game includes features such as piece movement, row clearing, score tracking etc..  

## Features
- **Piece Movement:** Move pieces left, right, and down using keyboard controls.
- **Row Clearing:** Automatically clears full rows and shifts the above rows down.
- **Score Tracking:** Keeps track of the player's score.

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Maven

### Installation
Clone the repository:  
```shell
git clone https://github.com/ISO53/Tetris.git
```

Go to the project directory:
```shell
cd Tetris
```

Build the project using Maven: 
```shell
mvn clean package
```

Run the application: 
```shell
java -jar target/tetris-game-1.0-SNAPSHOT.jar 
```

## Usage
### Controls
- `A`: Move piece left
- `D`: Move piece right
- `S`: Move piece down

## Game Mechanics
The game starts with a random piece falling from the top.
Use the controls to move the piece and fit it into the rows.
When a row is completely filled, it will be cleared, and the rows above will shift down.

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.  

## License
This project is licensed under the [GNU General Public License v3.0](LICENSE). Feel free to modify the content and structure based on your preferences and project specifics.

## Acknowledgments
Inspired by the classic Tetris game.

> [!CAUTION]
> Don't look at [this](https://github.com/ISO53/Tetris/commit/37c8e264713c1a916ae313336beadf3d35a0d3b5) commit.
