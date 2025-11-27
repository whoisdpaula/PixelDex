

# PixelDex, sua coleção de criaturas 8-bit
 O Intuito do PixelDex é gerar uma coleção de Pixels(criaturas de 8 bits) usando lista encadeada genérica para az coleção do usuário  e BST como índice global.

---
# Comandos da CLI 
---
````

ADD <id> <nome> <raridade> <poder> 
FIND <nome|id> 
REMOVE-INDEX <nome|id> 
REMOVE-COLLECTION <index> 
REVERSE 
UNIQUE
MOVE
SLICE
LIST
LIST-INDEX <INORDER|PREORDER|POSTORDER>
RANGE <a, b> 
HEIGHT 
IMPORT <caminho-json>
EXPORT <caminho-json> 
HELP 
EXIT 



`````````

# Exemplos de uso
---
`````````txt
> ADD 157 charizardshiny RARO 51
OK: Pixel(157, charizardshiny, RARO, 51) adicionado

> ADD 202 drakonauta RARO 87
OK: Pixel(202, drakonauta, RARO, 87) adicionado

> LIST
[0] Pixel(157, charizardshiny, RARO, 51)
[1] Pixel(202, drakonauta, RARO, 87)

> LIST-INDEX INORDER
Pixel(157, charizardshiny, RARO, 51)
Pixel(202, drakonauta, RARO, 87)

> FIND charizardshiny
Encontrado: Pixel(157, charizardshiny, RARO, 51)

> RANGE c m
Pixel(157, charizardshiny, RARO, 51)
Pixel(202, drakonauta, RARO, 87)

> REVERSE
OK (ordem invertida na coleção)

> MOVE 1 0
OK (elemento movido de 1 → 0)

> UNIQUE
OK (duplicatas removidas da coleção)

> REMOVE-INDEX drakonauta
OK (removido do índice; a coleção não é alterada)

> REMOVE-COLLECTION 0
OK (elemento removido da coleção)

> SLICE 0 1
[0] Pixel(202, drakonauta, RARO, 87)

> HEIGHT
Altura atual do índice: 1

> EXPORT pixels.json
Exportado para: pixels.json

> IMPORT pixels.json
OK (dados importados)
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


`````````
# Testes Unitários
`````````txt
LinkedListTest – insere, remove, move, slice, unique
BSTTest – inserção, travessias, busca ordenada
FlowTest – simula uma sessão real de usuário executando comandos
`````````
---

# 👩‍💻 Autora
- Lais De Paula Carneiro - 10418061
