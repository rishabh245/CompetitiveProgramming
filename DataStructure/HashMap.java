import java.util.ArrayList;
import java.util.Scanner;

class MapNode<K,V>{
    K key;
    V value;
    MapNode<K,V> next;

    public MapNode(K key, V value){
        this.key = key;
        this.value = value;
    }
}



class HashMap<K,V>{

    private ArrayList<MapNode<K,V>> bucket ;
    private int bucketSize;
    private int size;

    public HashMap(){
        bucket = new ArrayList<MapNode<K,V>>();
        bucketSize = 10;
        size = 0;
        for(int i=0;i<20;i++){
            bucket.add(null);
        }
    }

    private int getBucketIndex(K key){
        int hashCode = key.hashCode();
        int bucketIndex = hashCode%bucketSize;
        return bucketIndex;
    }

    public void put(K key , V value){
        int bucketIndex = getBucketIndex(key);
        MapNode<K,V> head = bucket.get(bucketIndex);
        while(head!=null){
            if(head.key.equals(key)){
                head.value = value;
                return;
            }
            head = head.next;
        }
        MapNode<K,V> newNode = new MapNode<K,V>(key,value);
        size++;
        newNode.next = bucket.get(bucketIndex);
        bucket.set(bucketIndex,newNode);
        double loadFactor = loadFactor();
        if(loadFactor>0.7){
            rehash();
        }
    }

    private void rehash(){
        bucketSize *= 2;
        size = 0;
        ArrayList<MapNode<K,V>> temp = bucket;
        bucket = new ArrayList<MapNode<K,V>>();
        for(int i=0;i<bucketSize;i++){
            bucket.add(null);
        } 
        for(int i=0;i<temp.size();i++){
            MapNode<K,V> head = temp.get(i);
            while(head!=null){
                K key = head.key;
                V value = head.value;
                put(key,value);
                head = head.next;
            }
        }
    }
    
    public double loadFactor(){
        double loadFactor = (1.0 * size)/bucketSize;
        return loadFactor;
    }


    public V get(K key){
        int bucketIndex = getBucketIndex(key);
        MapNode<K,V> head = bucket.get(bucketIndex);
            while(head!=null){
                if(head.key.equals(key)){
                    return head.value;
                }
                head = head.next;
            }
            return null;

    }

    public boolean containsKey(K key){
        int bucketIndex = getBucketIndex(key);
        MapNode<K,V> head = bucket.get(bucketIndex);
        while(head!=null){
            if(head.key.equals(key)){
                return true;
            }
        }
        return false;
    }


    public V removeKey(K key){
        int bucketIndex = getBucketIndex(key);
        MapNode<K,V> head = bucket.get(bucketIndex);
        if(head.key.equals(key)){
            size--;
            bucket.set(bucketIndex,head.next);
            return head.value;
        }
        MapNode<K,V> prev = head;
        MapNode<K,V> current = head.next;
        while(current!=null){
            if(current.key.equals(key)){
                prev.next = current.next;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }



}




public class Main {
    public static void main(String args[]) {
       HashMap<String,Integer> map = new HashMap<String,Integer>();
       map.put("abc",1);
       System.out.println("1: " + map.get("abc"));
       map.put("def",2); 
       System.out.println("2: " + map.get("def"));
       map.put("abc",4);
       System.out.println("3: " + map.get("abc"));
       if(map.containsKey("abc")){
            System.out.println("4: " + map.removeKey("abc"));
       }
       System.out.println("5: " + map.containsKey("abc"));
       map.put("pqr",6);
       System.out.println("Load Factor: " + map.loadFactor());
       map.put("tvb",7);
       System.out.println("Load Factor: " + map.loadFactor());
       map.put("oio",8);
       System.out.println("Load Factor: " + map.loadFactor());
       map.put("gsg",9);
       System.out.println("Load Factor: " + map.loadFactor());
       map.put("rty",6);
       System.out.println("Load Factor: " + map.loadFactor());
       map.put("tvk",7);
       System.out.println("Load Factor: " + map.loadFactor());
       map.put("owe",8);
       System.out.println("Load Factor: " + map.loadFactor());
       map.put("gdf",9);
       System.out.println("Load Factor: " + map.loadFactor());
    }
}


/* Output

1: 1
2: 2
3: 4
4: 4
5: false
Load Factor: 0.2
Load Factor: 0.3
Load Factor: 0.4
Load Factor: 0.5
Load Factor: 0.6
Load Factor: 0.7
Load Factor: 0.4
Load Factor: 0.45

*/