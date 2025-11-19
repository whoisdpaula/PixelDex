package app.cli;

import app.cli.ListCommand.*;
import core.PixelCollection;
import core.PixelIndex;
import domain.Pixel;
import io.JsonIO;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        PixelIndex pixelIndex = new PixelIndex();
        PixelCollection pixelCollection = new PixelCollection();
        try {
            Pixel[] loadedPixels = JsonIO.importFromJson("pixels.json");
            if (loadedPixels != null) {
                for (Pixel p : loadedPixels) {
                    pixelIndex.add(p);
                    pixelCollection.add(p);
                }
                System.out.println( loadedPixels.length + " Pixels carregados do arquivo.");
            }
        } catch (IOException e) {
            System.out.println("Arquivo pixels.json não encontrado ou vazio. Iniciando coleção vazia.");
        } catch (Exception e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
        }
        Map<String, Command> commands = new HashMap<>();
        commands.put("ADD", new AddCommand(pixelIndex, pixelCollection));
        commands.put("FIND", (Command) new FindCommand(pixelIndex));
        commands.put("REMOVE-INDEX", (Command) new RemoveIndexCommand(pixelIndex, pixelCollection));
        commands.put("REMOVE-COLLECTION", new RemoveCollectionCommand(pixelCollection));
        commands.put("REVERSE", (Command) new ReverseCommand(pixelCollection));
        commands.put("UNIQUE", (Command) new UniqueCommand(pixelCollection));
        commands.put("MOVE", (Command) new MoveCommand(pixelCollection));
        commands.put("SLICE", (Command) new SliceCommand(pixelCollection));
        commands.put("LIST", (Command) new ListCollectionCommand(pixelCollection));
        commands.put("LIST-INDEX", (Command) new ListIndexCommand(pixelIndex));
        commands.put("RANGE", (Command) new RangeCommand(pixelIndex));
        commands.put("HEIGHT", (Command) new HeightCommand(pixelIndex));
        commands.put("IMPORT", (Command) new ImportCommand(pixelIndex, pixelCollection));
        commands.put("EXPORT", (Command) new ExportCommand(pixelCollection));

        commands.put("HELP", parts -> {
            System.out.println("\n--- Comandos PIXELDEX CLI ---");
            System.out.println("ADD <id> <nome> <raridade> <poder> ");
            System.out.println("FIND <nome|id> ");
            System.out.println("REMOVE-INDEX <nome|id> ");
            System.out.println("REMOVE-COLLECTION <index> ");
            System.out.println("REVERSE ");
            System.out.println("UNIQUE ");
            System.out.println("MOVE <fromIndex> <toIndex>");
            System.out.println("SLICE <from> <to>");
            System.out.println("LIST");
            System.out.println("LIST-INDEX");
            System.out.println("RANGE <a, b>");
            System.out.println("HEIGHT");
            System.out.println("IMPORT <caminho-json>");
            System.out.println("EXPORT <caminho-json> ");
            System.out.println("HELP ");
            System.out.println("EXIT");
            System.out.println("-----------------------------");
        });

        Scanner sc = new Scanner(System.in);
        System.out.println("\n----- PIXELDEX CLI INICIADA ----- ");
        commands.get("HELP").execute(null);

        while(true){
            System.out.print(">");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");
            String cmd = parts[0].trim().toUpperCase();
            if(cmd.equals("EXIT")) {
                try {
                    Pixel[] dataToSave = pixelCollection.toArray();
                    JsonIO.exportToJson(dataToSave, "pixels.json");
                    System.out.println( dataToSave.length + " Pixels salvos em pixels.json.");
                } catch (IOException e) {
                    System.out.println("Erro grave ao salvar dados: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Erro ao preparar dados para salvar: " + e.getMessage());
                }
                break;
            }
            Command c = commands.get(cmd);
            if(c != null){
                c.execute(parts);
            }else{
                System.out.println("Comando não encontrado. Digite 'HELP' para ver os comandos.");
            }
        }
        sc.close();
        System.out.println("Encerrando PIXELDEX CLI. " +
                "Criadora: LAIS DE PAULA CARNEIRO");
    }
}