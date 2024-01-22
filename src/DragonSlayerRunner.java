import java.util.Scanner;
public class DragonSlayerRunner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name:");
        String playerName = scanner.nextLine();

        Player player = new Player(playerName);
        Room[] rooms = {new Room("cave", 3), new Room("rooftop", 2), new Room("the den", 1), new Room("The house", 3),
                new Room("The bed", 3) };


        Game dragonSlayerGame = new Game(player, rooms);
        dragonSlayerGame.start();
    }
}