package app.cli.ListCommand;


import app.cli.Command;
import core.PixelCollection;
import core.PixelIndex;
import domain.Pixel;
import domain.Raridade;

public class AddCommand implements Command {
    private final PixelIndex index;
    private final PixelCollection collection;


    public AddCommand(PixelIndex index, PixelCollection collection) {
        this.index = index;
        this.collection = collection;
    }


    @Override
    public void execute(String[] args) {
        if (args.length != 5) {
            System.out.println("Uso incorreto: ADD <id> <nome> <raridade> <poder>. Ex: ADD 176 Miau LENDARIO 70");
            return;
        }

        try {
            int id = Integer.parseInt(args[1]);
            String nome = args[2];
            Raridade raridade = Raridade.valueOf(args[3].toUpperCase());
            int poder = Integer.parseInt(args[4]);


            if (index.searchById(id) != null) {
                System.out.println("Erro: Pixel com ID " + id + " já existe.");
                return;
            }

            if (index.searchByName(nome) != null) {
                System.out.println("Erro: Pixel com Nome '" + nome + "' já existe.");
                return;
            }


            Pixel novoPixel = new Pixel(id, nome, raridade, poder);
            index.add(novoPixel);
            collection.add(novoPixel);

            System.out.println("Pixel '" + nome + "' (ID: " + id + ") adicionado e indexado com sucesso.");

        } catch (NumberFormatException e) {
            System.out.println("Erro de formato: ID ou Poder deve ser um número inteiro válido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de formato: Raridade inválida. Use (COMUM, INCOMUM, RARO, EPICO, LENDARIO).");
        }
    }
}