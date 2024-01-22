import java.util.Random;
import java.util.Scanner;
class Game {
        private final Player player;
        private final Room[] rooms;

        public Game(Player player, Room[] rooms) {
                this.player = player;
                this.rooms = rooms;
        }

        public void start() {
                Scanner scanner = new Scanner(System.in);

                while (true) {
                        System.out.println("Dragon Slayer Game\n1. Start New Game\n2. Quit\n3. View Top Score");
                        int choice = scanner.nextInt();

                        switch (choice) {
                                case 1:
                                        startNewGame();
                                        break;
                                case 2:
                                        System.out.println("Thanks for playing!");
                                        System.exit(0);
                                        break;
                                case 3:
                                        System.out.println("Top Score: " + player.getTopScore());
                                        break;
                                default:
                                        System.out.println("Invalid choice. Please try again.");
                        }
                }
        }
        private String getName() {
                return player.getName();
        }

        private void startNewGame() {
                Scanner scanner = new Scanner(System.in);
                player.setHealth(100);
                player.setGold(0);
                player.setRoomIndex(0);

                while (player.getRoomIndex() < rooms.length) {
                        System.out.println(player.getName() + ", you enter " + rooms[player.getRoomIndex()].getName());
                        playRoom();
                        player.setRoomIndex(player.getRoomIndex() + 1);
                }


                System.out.println("Game Over!");
                if (player.getHealth() > 0) {
                        int score = calculateScore();
                        System.out.println("Congratulations, " + player.getName() + "! You won with a score of " + score);
                        if (score > player.getTopScore()) {
                                player.setTopScore(score);
                        }
                } else {
                        System.out.println("Sorry, " + player.getName() + ", you got slayed by the dragon.");
                }


                System.out.println("Do you want to play again? (1. Yes / 2. No)");
                int choice = scanner.nextInt();
                if (choice == 1) {
                        startNewGame();
                } else {
                        System.out.println("Thanks for playing!");
                        System.exit(0);
                }
        }

        private void playRoom() {
                Scanner scanner = new Scanner(System.in);

                for (Dragon dragon : rooms[player.getRoomIndex()].getDragons()) {
                        System.out.println("You enter " + rooms[player.getRoomIndex()].getName() + ".");

                        while (player.getHealth() > 0 && dragon.getHealth() > 0) {
                                player.showActions();
                                System.out.print("Choose an action (1-5): ");
                                int choice = scanner.nextInt();

                                switch (choice) {
                                        case 1:
                                                System.out.println("You decide to attack the dragon.");
                                                attackDragon(dragon);
                                                break;
                                        case 2:
                                                System.out.println("You decide to inspect the dragon's level.");
                                                // Inspect Dragon's Level
                                                System.out.println("it is a level " + dragon.getLevel() + " dragon." );
                                                break;
                                        case 3:
                                                System.out.println("You decide to view your current health.");
                                                System.out.print("your current health is "  + player.getHealth());
                                                break;
                                        case 4:
                                                System.out.println("You decide to view your current weapon stats.");
                                                Sword sword = player.getSword();
                                                System.out.println("Attack power: " + sword.getAttackPower() + " Dodge rating: " + sword.getDodgeRating() );
                                                break;
                                        case 5:
                                                int score = calculateScore();
                                                System.out.println("Your current score is: " + score);
                                                break;
                                        default:
                                                System.out.println("Invalid choice. Please try again.");
                                                break;
                                }

                                if (dragon.getHealth() <= 0) {
                                        System.out.println("You slay the dragon!");
                                        player.handleDragonDefeat(dragon);
                                        break;
                                }

                                if (choice == 1) {
                                        dragonTurn();
                                }
                                if (player.getHealth() <= 0) {
                                        System.out.println("You have been defeated by the dragon!");
                                        return;
                                }
                        }
                }

                // Room cleared
                System.out.println("All dragons in " + rooms[player.getRoomIndex()].getName() + " are defeated. Room cleared!");
                player.searchRoom();
        }

        private void attackDragon(Dragon dragon) {
                int playerAttack = calculatePlayerAttack();
                dragon.setHealth(dragon.getHealth() - playerAttack);
                System.out.println("You attack the dragon for " + playerAttack + " health. It has " + dragon.getHealth() + " health remaining.");
        }
        private int calculatePlayerAttack() {
                Random random = new Random();
                Sword sword = player.getSword();
                int randomMultiplier = random.nextInt(10) + 1;
                return randomMultiplier * sword.getAttackPower();
        }
        private void dragonTurn() {
                Random random = new Random();
                int dragonAttack = random.nextInt(10) + 1;
                int totalDragonAttack = dragonAttack * rooms[player.getRoomIndex()].getDragons()[0].getLevel();
                int dodgeChance = random.nextInt(100) + 1;

                if (dodgeChance <= player.getSword().getDodgeRating()) {
                        System.out.println("The dragon swipes, but you dodge!");
                } else {
                        player.setHealth(player.getHealth() - totalDragonAttack);
                        System.out.println("The dragon attacks you for " + totalDragonAttack + " damage.");
                        System.out.println("Your current health is: " + player.getHealth());
                }
        }

        private int calculateScore() {
                return player.getGold() + (player.getSword().getAttackPower() + player.getSword().getDodgeRating())
                        + player.getHealth();
        }
}