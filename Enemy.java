public class Enemy {
    int[] position = new int[2];
    String sprite = "[<>]";

    public void setPosition(int row, int column) {
        position[0] = row;
        position[1] = column;
    }

    public void getPosition() {
        System.out.print(position[0]+ " " + position[1]);
    }

}
