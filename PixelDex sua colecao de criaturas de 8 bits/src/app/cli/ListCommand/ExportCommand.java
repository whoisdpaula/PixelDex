package app.cli.ListCommand;

import app.cli.Command;
import core.PixelCollection;
import domain.Pixel;
import io.JsonIO;
import java.io.IOException;

public class ExportCommand implements Command {
    private final PixelCollection collection;

    public ExportCommand(PixelCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso incorreto: EXPORT <caminho/para/arquivo.json>");
            return;
        }

        String path = args[1];

        try {
            Pixel[] dataToSave = collection.toArray();

            if (dataToSave.length == 0) {
                System.out.println("A coleção está vazia. Nada para exportar.");
                return;
            }

            JsonIO.exportToJson(dataToSave, path);
            System.out.printf("Sucesso! %d Pixel(s) exportado(s) para o arquivo: %s\n", dataToSave.length, path);

        } catch (IOException e) {
            System.out.println("Erro de I/O: Não foi possível escrever o arquivo. Verifique permissões ou caminho.");
        } catch (Exception e) {
            System.out.println("Erro ao exportar dados: " + e.getMessage());
        }
    }
}