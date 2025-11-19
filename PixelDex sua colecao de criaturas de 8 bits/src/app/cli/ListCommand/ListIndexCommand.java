package app.cli.ListCommand;

import app.cli.Command;
import core.PixelIndex;
import domain.Pixel;
import java.util.List;

public class ListIndexCommand implements Command {
    private final PixelIndex index;
    public ListIndexCommand(PixelIndex index) {
        this.index = index;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso incorreto: LIST-INDEX <INORDER|PREORDER|POSTORDER>");
            return;
        }
        String type = args[1].toUpperCase();
        List<Pixel> result;

        switch (type) {
            case "INORDER":
                result = index.getInOrder();
                break;
            case "PREORDER":
                result = index.getPreOrder();
                break;
            case "POSTORDER":
                result = index.getPostOrder();
                break;
            default:
                System.out.println("Tipo de travessia inválido. Use INORDER, PREORDER ou POSTORDER.");
                return;
        }
        if (result.isEmpty()) {
            System.out.println("A BST está vazia.");
            return;
        }
        System.out.println("\n--- PIXELS (" + type + " - BST) ---");
        for (Pixel p : result) {
            System.out.printf("ID: %d | Nome: %s | Raridade: %s | Poder: %d\n",
                    p.getId(), p.getName(), p.getRarity().toString(), p.getPoder());
        }
        System.out.println("Total de Pixels listados: " + result.size());
    }
}