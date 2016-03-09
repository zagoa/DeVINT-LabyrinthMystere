package dvt.labyrinth.model;

import javax.swing.*;
import static dvt.labyrinth.ConstantesLabyrinth.*;

import dvt.labyrinth.ConstantesLabyrinth;
import dvt.labyrinth.Tile;
import dvt.labyrinth.Tray;

import java.util.ArrayList;

public class Pawn extends Item {
    String name;

    public Pawn(String name, RESSOURCES res) {
        super(res);

        this.name = name;
    }

   }