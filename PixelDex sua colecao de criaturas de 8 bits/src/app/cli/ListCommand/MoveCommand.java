package app.cli.ListCommand;

import app.cli.Command;
import core.PixelCollection;

public class MoveCommand implements Command {
    private final PixelCollection collection;

    public MoveCommand(PixelCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 3) {
            System.out.println("Uso incorreto: MOVE <fromIndex> <toIndex>");
            return;
        }

        try {
            int fromIndex = Integer.parseInt(args[1]);
            int toIndex = Integer.parseInt(args[2]);
            collection.move(fromIndex, toIndex);
            System.out.printf("Pixel movido da posição %d para a posição %d na coleção.\n", fromIndex, toIndex);

        } catch (NumberFormatException e) {
            System.out.println("Ambos os índices devem ser números inteiros.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Erro: Índices fora dos limites da coleção. Use um valor entre 0 e " + (collection.size() - 1) + ".");
        } catch (UnsupportedOperationException e) {
            System.out.println("Ação não suportada. O método 'move' deve ser implementado na LinkedList.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}