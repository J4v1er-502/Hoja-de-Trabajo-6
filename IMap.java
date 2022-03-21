public interface IMap<K, V> {

    public void put(K key, V value);
    public V get(K key);
    public void delete(K key);
    public int size();
    
}