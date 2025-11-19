
# PixelDex, sua coleção de criaturas 8-bit
---

 O Intuito do PixelDex é gerar uma coleção de Pixels(criaturas de 8 bits) usando lista encadeada genérica para az coleção do usuário  e BST como índice global.


# Arquitetura PixelDex

```
src/
 ├── main/java/
 │    ├── app/cli/                 # Interface com o usuário
 │    │     ├── Main.java
 │    │     ├── Command
 │    │     └── ListCommand/
 │    │            ├── AddCommand.java
 │    │            ├── ExportCommand.java
 │    │            ├── FindCommand.java
 │    │            ├── FindCommand.java
 │    │            ├── HeighCommand.java
 │    │            ├── ImportCommand.java
 │    │            ├── ListCollectionCommand.java
 │    │            ├── ListIndexCommand.java
 │    │            ├── MoveCommand.java
 │    │            ├── RangeCommand.java
 │    │            ├── RemoveCollectionCommand.java
 │    │            ├── ReverseCommand.java
 │    │            ├── RemoveIndexCommand.java
 │    │            ├── SearchCommand.java
 │    │            ├── SliceCommand.java
 │    │            └── UniqueCommand.java
 │    │ 
 │    ├── core/                     # Regras de negócio
 │    │     ├── PixelCollection.java
 │    │     └── PixelIndex.java
 │    │
 │    ├── domain/                   # Entidades do modelo
 │    │     ├── Pixel.java
 │    │     └── Raridade.java
 │    │
 │    ├── ds/                       # Estruturas de dados implementadas
 │    │     ├── list/
 │    │     │     ├── LinkedList.java
 │    │     │     └── LinkedNode.java
 │    │     ├── tree/
 │    │     │     ├── BST.java
 │    │     │     └── BSTNode.java
 │    │     └── hash/
 │    │           └── IntHashTable.java
 │    │
 │    ├── io/
 │    │     └── JsonIO.java        # Persistência JSON
 │    │
 │    └── util/
 │          └── Comparators.java   # Comparadores diversos
 │
 └── test/java/
       ├── ds/list/LinkedListTest.java
       ├── ds/tree/BSTTest.java
       └── integration/FlowTest.java

