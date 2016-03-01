package dvt.labyrinth.model;


public class Pawn extends Item {
    String name;
    String image;
    int x;
    int y;


    public Pawn(String name, String image, int x, int y) {
        this.name = name;
        this.image = image;
        this.x = x;
        this.y = y;
    }

}