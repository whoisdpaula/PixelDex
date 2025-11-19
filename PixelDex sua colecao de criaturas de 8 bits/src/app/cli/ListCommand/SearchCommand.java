package app.cli.ListCommand;

import app.cli.Command;
import core.PixelIndex;
import domain.Pixel;

public class SearchCommand implements Command {
    private final PixelIndex index;
    public SearchCommand(PixelIndex index) {
        this.index = index;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso incorreto: SEARCH <nome_ou_id>");
            return;
        }
        String query = args[1];
        Pixel result = null;
        try {
            int id = Integer.parseInt(query);
            result = index.searchById(id);
            if (result != null) {
                System.out.println("Resultado (Busca por ID na Hash Table): " + formatPixel(result));
                return;
            }
        } catch (NumberFormatException e) {

        }
        result = index.searchByName(query);
        if (result != null) {
            System.out.println("Resultado (Busca por Nome na BST): " + formatPixel(result));
            return;
        }
        System.out.println("❌ Pixel '" + query + "' não encontrado nos índices.");
    }
    private String formatPixel(Pixel p) {
        return String.format("ID: %d | Nome: %s | Raridade: %s | Poder: %d",
                p.getId(), p.getName(), p.getRarity().toString(), p.getPoder());
    }
}