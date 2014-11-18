import java.util.List;
import java.util.LinkedList;

public class SeparateChainingHashTable<T> {

  private static final int DEFAULT_TABLE_SIZE = 101;
  private List<T>[] theLists;
  private int currentSize;

  public SeparateChainingHashTable() {
    this (DEFAULT_TABLE_SIZE);
  }
  public SeparateChainingHashTable(int size) {
    theLists = new LinkedList[nextPrime(size)];
    for(int i = 0; i < theLists.length; i++){
      theLists[i] = new LinkedList<T>();
    }
  }

  public void insert(T data) {
    List<T> whichList = theLists[myHash(data)];
    if(!whichList.contains(data)) {
      whichList.add(data);
      if(++currentSize > theLists.length){
        reHash();
      }
    }
  }
  public void remove(T data) {
    List<T> whichList = theLists[myHash(data)];
    if(whichList.contains(data)){
      whichList.remove(data);
      currentSize--;
    }
  }
  public boolean contains(T data) {
    List<T> whichList = theLists[myHash(data)];
    return whichList.contains(data);
  }
  public void makeEmpty() {
    for(int i = 0; i < theLists.length; i++){
      theLists[i].clear();
    }
    currentSize = 0;
  }


  private void reHash() {
    List<T>[] oldLists = theLists;
    theLists = new List[ nextPrime(2 * theLists.length + 1)];
    for(int j = 0; j < theLists.length; j++){
      theLists[j] = new LinkedList<>();
    }
    currentSize = 0;
    for(int i = 0; i < oldLists.length; i++){
      for(T item : oldLists[i]){
        insert(item);
      }
    }
  }
  private int myHash(T data) {
    int hashVal = data.hashCode();

    hashVal %= theLists.length;

    if (hashVal < 0) {
      hashVal += theLists.length;
    }

    return hashVal;
  }

  private static int nextPrime(int n){
    for(int i = n+1; i < n + 10000; i = i + 2){
      if(isPrime(i)){
        return i;
      }
    }
    return n;
  }
  private static boolean isPrime(int n) {
    if(n == 2) {
      return true;
    }
    else if(n >= 3) {
      for(int i = 3; i < Math.sqrt(n); i=i+2){
        if(n % i == 0){
          return true;
        }
      }
      return false;
    }
    return false;
  }
}
