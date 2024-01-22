import java.util.Random;
class Room {
    private String name;
    private Dragon[] dragons;

    public Room(String name, int numDragons) {
        this.name = name;
        this.dragons = new Dragon[numDragons];
        initializeDragons();
    }

    public String getName() {
        return name;
    }

    public Dragon[] getDragons() {
        return dragons;
    }

    private void initializeDragons() {
        Random random = new Random();
        for (int i = 0; i < dragons.length; i++) {
            int dragonLevel = random.nextInt(3) + 1;
            dragons[i] = new Dragon(dragonLevel);
        }
    }
}