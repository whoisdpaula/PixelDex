package app.cli.ListCommand;

import app.cli.Command;
import core.PixelCollection;

public class UniqueCommand implements Command {
    private final PixelCollection collection;
    public UniqueCommand(PixelCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 1) {
            System.out.println("Uso incorreto: UNIQUE (comando não aceita argumentos)");
            return;
        }
        try {
            int removedCount = collection.unique();

            if (removedCount > 0) {
                System.out.printf("Remoção de duplicatas concluída. Total de %d Pixel(s) removido(s).\n", removedCount);
            } else {
                System.out.println("Nenhuma duplicata encontrada na coleção.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao tentar remover duplicatas: " + e.getMessage());
        }
    }
}