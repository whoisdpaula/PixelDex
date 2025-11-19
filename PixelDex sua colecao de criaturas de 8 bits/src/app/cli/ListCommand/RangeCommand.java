package app.cli.ListCommand;

import app.cli.Command;
import core.PixelIndex;
import domain.Pixel;
import java.util.List;

public class RangeCommand implements Command {
    private final PixelIndex index;

    public RangeCommand(PixelIndex index) {
        this.index = index;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 3) {
            System.out.println("Uso incorreto: RANGE <NomeInicial> <NomeFinal>");
            return;
        }

        String nomeA = args[1];
        String nomeB = args[2];

        List<Pixel> result = index.range(nomeA, nomeB);

        if (result.isEmpty()) {
            System.out.printf("Nenhuma Pixel encontrado no range entre %s e %s.\n", nomeA, nomeB);
            return;
        }

        System.out.printf("\n--- PIXELS (RANGE %s a %s) ---\n", nomeA, nomeB);
        for (Pixel p : result) {
            System.out.printf("ID: %d | Nome: %s | Raridade: %s | Poder: %d\n",
                    p.getId(), p.getName(), p.getRarity().toString(), p.getPoder());
        }
        System.out.println("Total de Pixels encontrados: " + result.size());
    }
}