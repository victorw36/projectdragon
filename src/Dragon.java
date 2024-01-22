import java.util.Random;
class Dragon {
    private int level;
    private int health;

    public Dragon(int level) {
        this.level = level;
        this.health = 100;
    }

   public int attack() {
        Random random = new Random();
        return random.nextInt(10) + 1 * this.level;
    }
    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.max(0, health);
    }
}

