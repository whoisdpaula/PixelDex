package app.cli.ListCommand;

import app.cli.Command;
import core.PixelIndex;
import domain.Pixel;

public class FindCommand implements Command {
    private final PixelIndex index;

    public FindCommand(PixelIndex index) {
        this.index = index;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso incorreto: FIND <nome> ou FIND <id>");
            return;
        }

        String key = args[1];
        Pixel foundPixel = null;
        boolean isNumeric = false;

        try {
            int id = Integer.parseInt(key);
            isNumeric = true;
            System.out.println("Busca por ID");
            foundPixel = index.searchById(id);

        } catch (NumberFormatException e) {
            System.out.println("Busca por Nome");
            foundPixel = index.searchByName(key);
        }
        if (foundPixel != null) {
            System.out.println("Pixel encontrado!");
            String rarityName = (foundPixel.getRarity() != null) ? foundPixel.getRarity().toString() : "N/A";

            System.out.printf("ID: %d | Nome: %s | Raridade: %s | Poder: %d\n",
                    foundPixel.getId(), foundPixel.getName(), rarityName, foundPixel.getPoder());
        } else {
            System.out.printf("Pixel com chave '%s' não encontrado no índice.\n", key);
        }
    }
}