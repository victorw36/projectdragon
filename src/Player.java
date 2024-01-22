import java.util.Random;

class Player {
    private int health;
    private Sword sword;
    private int gold;
    private int roomIndex;
    private int topScore;

    private String name;


    Player(String name) {
        this.health = 100;
        this.sword = new Sword();
        this.gold = 0;
        this.roomIndex = 0;
        this.topScore = 0;
        this.name = name;
    }

    String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }
    public Sword getSword() {
        return sword;
    }

    public int getGold() {
        return gold;
    }
    public void setGold(int newGold) {
        gold = newGold;
    }

    public int getRoomIndex() {
        return roomIndex;
    }

    public void setRoomIndex(int roomIndex) {
        this.roomIndex = roomIndex;
    }

    public int getTopScore() {
        return topScore;
    }

    public void setTopScore(int topScore) {
        this.topScore = Math.max(0, topScore);
    }

    public void searchRoom() {
        Random random = new Random();
        int chance = random.nextInt(100) + 1;

        if (chance <= 25) {

            int healthPotAmount = random.nextInt(25) + 1;
            System.out.println("You found a health pot! Refills " + healthPotAmount + " health.");
            this.health = Math.min(100, this.health + healthPotAmount);
        } else {
            System.out.println("You search the room and find nothing.");
        }
    }
    public void showActions() {
        System.out.println("\nAvailable Actions:");
        System.out.println("1. attack dragon");
        System.out.println("2. inspect dragon's level");
        System.out.println("3. view current health");
        System.out.println("4. view current weapon's stats");
        System.out.println("5. view current score");

    }


    void handleDragonDefeat(Dragon dragon) {
        Random random = new Random();
        int outcome = random.nextInt(4);

        switch (outcome) {
            case 0:
                int goldAmount = random.nextInt(100) + 1;
                this.gold += goldAmount;
                System.out.println("The dragon drops " + goldAmount + " gold. You pick it up.");
                break;
            case 1:
                int attackIncrease = random.nextInt(5) + 1;
                int dodgeIncrease = random.nextInt(5) + 1;
                this.sword.upgrade(attackIncrease, dodgeIncrease);
                System.out.println("The dragon drops a sword upgrade. Your sword is now stronger!");
                break;
            case 2:
                int healthRecovered = random.nextInt(25) + 1;
                this.health = Math.min(100, this.health + healthRecovered);
                System.out.println("You recover " + healthRecovered + " health from the dragon's remains.");
                break;
            default:
                System.out.println("The dragon leaves empty-handed. Nada!");
        }
    }
}