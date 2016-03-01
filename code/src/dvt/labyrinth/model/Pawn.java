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

    public void moveToTheFront(Pawn pawn){
        pawn.x +=1;
    }

    public void moveBackwards(Pawn pawn){
        pawn.x -=1;
    }

    public void moveRight(Pawn pawn){
        pawn.y +=1;
    }

    public void moveLeft(Pawn pawn){
        pawn.y -=1;
    }
}