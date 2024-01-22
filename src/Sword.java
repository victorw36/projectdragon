class Sword {
    private int attackPower;
    private int dodgeRating;

    public Sword() {
        this.attackPower = 10;
        this.dodgeRating = 20;
    }

    public void upgrade(int attackIncrease, int dodgeIncrease) {
        this.attackPower += attackIncrease;
        this.dodgeRating += dodgeIncrease;
    }
    public int getAttackPower() {
        return attackPower;
    }

    public int getDodgeRating() {
        return dodgeRating;
    }
}
