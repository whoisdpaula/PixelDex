package app.cli.ListCommand;

import app.cli.Command;
import core.PixelCollection;
import core.PixelIndex;
import domain.Pixel;

public class RemoveIndexCommand implements Command {
    private final PixelIndex index;
    private final PixelCollection collection;
    public RemoveIndexCommand(PixelIndex index, PixelCollection collection) {
        this.index = index;
        this.collection = collection;
    }
    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso incorreto: REMOVE-INDEX <nome> ou REMOVE-INDEX <id>");
            return;
        }
        String key = args[1];
        Pixel removedPixel = null;
        try {
            try {
                int id = Integer.parseInt(key);
                removedPixel = index.remove(id);
            } catch (NumberFormatException e) {
                removedPixel = index.remove(key);
            }
            if (removedPixel != null) {
                System.out.printf("Pixel '%s' (ID: %d) removido do índice (BST) com sucesso.\n",
                        removedPixel.getName(), removedPixel.getId());
            } else {
                System.out.printf("Pixel com chave '%s' não encontrado no índice para remoção.\n", key);
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar remover: " + e.getMessage());
        }
    }
}