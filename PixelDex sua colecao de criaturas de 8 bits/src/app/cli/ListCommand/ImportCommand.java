package app.cli.ListCommand;

import app.cli.Command;
import core.PixelCollection;
import core.PixelIndex;
import domain.Pixel;
import io.JsonIO;
import java.io.IOException;

public class ImportCommand implements Command {
    private final PixelIndex index;
    private final PixelCollection collection;

    public ImportCommand(PixelIndex index, PixelCollection collection) {
        this.index = index;
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso incorreto: IMPORT <caminho/para/arquivo.json>");
            return;
        }

        String path = args[1];

        try {
            Pixel[] importedPixels = JsonIO.importFromJson(path);

            if (importedPixels == null || importedPixels.length == 0) {
                System.out.println("ℹArquivo encontrado, mas nenhum Pixel válido para importar.");
                return;
            }

            int count = 0;
            for (Pixel p : importedPixels) {
                index.add(p);
                collection.add(p);
                count++;
            }
            System.out.printf("Sucesso! %d Pixel(s) importado(s) e adicionado(s) à coleção e ao índice.\n", count);
        } catch (IOException e) {
            System.out.println("Erro de I/O: Arquivo não encontrado ou problema de leitura no caminho: " + path);
        } catch (Exception e) {
            System.out.println("Erro ao processar o JSON: Verifique o formato do arquivo. Detalhe: " + e.getMessage());
        }
    }
}