package ds.hash;

public class IntHashTable<V> {
    private int[] keys;
    private V[] values;
    private boolean [] used;
    private int size;
    private boolean[] deleted;
    private static final double loadFactor = 0.7;

    public IntHashTable() {
        keys = new int[10];
        values = (V[]) new Object[10];
        used = new boolean[10];
        deleted = new boolean[10];
    }
    private int hash(int key){
        return Math.abs(key) % keys.length;
    }
    public void put(int key, V value){
        if((double) size / keys.length >= loadFactor){
            resize();
        }
        int index = hash(key);
        while(used[index] && !deleted[index] && keys[index] != key){
            index = (index + 1) % keys.length;
        }
        if(!used[index] || deleted[index]){
            size++;
        }
        used[index] = true;
        deleted[index] = false;
        keys[index] = key;
        values[index] = value;
    }
    public V get(int key){
        int index = hash(key);

        while(used[index]){
            if(!deleted[index] && keys[index] == key){
                return values[index];
            }
            index = (index + 1) % keys.length;
        }
        return null;
    }
    public boolean remove(int key){
        int index = hash(key);

        while(used[index]){
            if(!deleted[index] && keys[index] == key){
                deleted[index] = true;
                size--;
                return true;
            }
            index = (index + 1) % keys.length;
        }
        return false;
    }
    private void resize(){
        int[] oldKeys = keys;
        V[] oldValues = values;
        boolean[] oldUsed = used;
        boolean[] oldDeleted = deleted;
        int newCapacity = oldKeys.length * 2;
        keys = new int[newCapacity];
        values = (V[]) new Object[newCapacity];
        used = new boolean[newCapacity];
        deleted = new boolean[newCapacity];
        size = 0;

        for(int i = 0; i < oldKeys.length; i++){
            if(oldUsed[i] && !oldDeleted[i]){
                put(oldKeys[i], oldValues[i]);
            }
        }
    }
    public int size(){
        return size;
    }
}