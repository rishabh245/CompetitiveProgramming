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

    public HashMap(){
        bucket = new ArrayList<MapNode<K,V>>();
        bucketSize = 20;
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
        newNode.next = bucket.get(bucketIndex);
        bucket.set(bucketIndex,newNode);
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

    }
}