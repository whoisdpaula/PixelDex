

# PixelDex, sua coleção de criaturas 8-bit
 O Intuito do PixelDex é gerar uma coleção de Pixels(criaturas de 8 bits) usando lista encadeada genérica para az coleção do usuário  e BST como índice global.

---
# Comandos da CLI 
---
````
ADD <id> <nome> <raridade> <poder>
FIND <nome|poder|id>
REMOVE-COLLECTION <index>
REMOVE-INDEX <key>
REVERSE
UNIQUE
MOVE <fromIndex> <toIndex>
SLICE <from> <to>
LIST
LIST-INDEX <INORDER|PREORDER|POSTORDER>
RANGE <a> <b>
IMPORT <caminho-json>           # opcional
EXPORT <caminho-json>           # opcional
HELP
EXIT


`````````

# Exemplos de uso
---
`````````txt
> ADD 157 charizardshiny  RARO 51
OK: Pixel(157, charizardshiny, RARO, 51) adicionado

> ADD 202 drakonauta RARO 87
OK

> LIST
[0] Pixel(157,charizardshiny , RARO, 51)
[1] Pixel(202, drakonauta, RARO, 87)

> LIST-INDEX INORDER
Pixel(157,charizardshiny , RARO, 51)
Pixel(202, drakonauta, RARO, 87)

> FIND picozinho
Encontrado: Pixel(157,charizardshiny , RARO, 51)

> RANGE a m
Pixel(157,charizardshiny , RARO, 51)

> REVERSE
OK

> MOVE 1 0
OK

> UNIQUE
OK (duplicatas removidas)

> REMOVE-INDEX picozinho
OK (removido do índice). Dica: atualize exibição da coleção.

> EXPORT pixels.json
Salvo em pixels.json

> EXIT
`````````

---

# Arquitetura PixelDex
---
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

