package app.cli.ListCommand;

import app.cli.Command;
import core.PixelCollection;
import domain.Pixel;
import java.util.NoSuchElementException;

public class RemoveCollectionCommand implements Command {
    private final PixelCollection collection;
    public RemoveCollectionCommand(PixelCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso incorreto: REMOVE-COLLECTION <index>");
            return;
        }
        try {
            int index = Integer.parseInt(args[1]);
            Pixel removedPixel = collection.removeAt(index);
            System.out.printf("Pixel '%s' (ID: %d) removido da coleção no índice %d.\n",
                    removedPixel.getName(), removedPixel.getId(), index);
        } catch (NumberFormatException e) {
            System.out.println("O índice deve ser um número inteiro.");
        } catch (NoSuchElementException e) {
            System.out.println("Erro " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
}