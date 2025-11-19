package app.cli.ListCommand;

import app.cli.Command;
import core.PixelCollection;

public class ListCollectionCommand implements Command {
    private final PixelCollection collection;

    public ListCollectionCommand(PixelCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso incorreto: LIST (comando não aceita argumentos)");
            return;
        }
        if (collection.isEmpty()) {
            System.out.println("A coleção está vazia. Adicione Pixels.");
            return;
        }
        System.out.println("\n--- PIXELS (ORDEM DE INSERÇÃO - COLLECTION) ---");
        collection.print();
        System.out.println("Total de Pixels na coleção: " + collection.size());
    }
}