package app.cli.ListCommand;

import app.cli.Command;
import core.PixelCollection;

public class ReverseCommand implements Command {
    private final PixelCollection collection;
    public ReverseCommand(PixelCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso incorreto: REVERSE (comando não aceita argumentos)");
            return;
        }
        if (collection.size() <= 1) {
            System.out.println("ℹ️ A coleção precisa de pelo menos 2 Pixels para ser invertida.");
            return;
        }
        try {
            collection.reverse();
            System.out.println("A ordem da coleção foi invertida com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao inverter a coleção: " + e.getMessage());
        }
    }
}