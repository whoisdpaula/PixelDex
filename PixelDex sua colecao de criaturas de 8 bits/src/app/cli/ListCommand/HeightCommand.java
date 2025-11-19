package app.cli.ListCommand;

import app.cli.Command;
import core.PixelIndex;

public class HeightCommand implements Command {
    private final PixelIndex index;

    public HeightCommand(PixelIndex index) {
        this.index = index;
    }

    @Override
    public void execute(String[] args) {
        int height = index.getHeight();
        if (height == -1) {
            System.out.println("Altura da BST: 0 (A árvore está vazia).");
        } else {
            System.out.println("Altura da BST: " + height);
        }
    }
}