package app.cli.ListCommand;

import app.cli.Command;
import core.PixelCollection;
import domain.Pixel;
import java.util.List;

public class SliceCommand implements Command {
    private final PixelCollection collection;
    public SliceCommand(PixelCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 3) {
            System.out.println("Uso incorreto: SLICE <fromIndex> <toIndex>");
            return;
        }
        try {
            int fromIndex = Integer.parseInt(args[1]);
            int toIndex = Integer.parseInt(args[2]);
            List<Pixel> sublist = collection.slice(fromIndex, toIndex);

            if (sublist.isEmpty()) {
                System.out.println("ℹO subconjunto (slice) está vazio.");
                return;
            }
            System.out.printf("\n--- PIXELS (SLICE: %d a %d) ---\n", fromIndex, toIndex);
            for (int i = 0; i < sublist.size(); i++) {
                Pixel p = sublist.get(i);
                System.out.printf("[%d] ID: %d | Nome: %s\n", (fromIndex + i), p.getId(), p.getName());
            }
            System.out.println("Total de Pixels no slice: " + sublist.size());

        } catch (NumberFormatException e) {
            System.out.println("Os índices devem ser números inteiros.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Erro: Índices fora dos limites da coleção. Verifique se from < to e se estão dentro do range.");
        } catch (UnsupportedOperationException e) {
            System.out.println("Ação não suportada. O método 'slice' deve ser implementado na LinkedList.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}