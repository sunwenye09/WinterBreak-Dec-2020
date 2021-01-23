//380. Insert Delete GetRandom O(1)
class RandomizedSet {

    /** Initialize your data structure here. */
    HashMap<Integer, Integer> map;      //<value, location>
    ArrayList<Integer> lst;
    public RandomizedSet() {
        map = new HashMap<Integer, Integer>();
        lst = new ArrayList<Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, lst.size());
        lst.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int location = map.get(val);
        if (location < lst.size() - 1) {
            int tail = lst.get(lst.size() - 1);
            map.put(tail, location);
            lst.set(location, tail);
        }
        map.remove(val);
        lst.remove(lst.size() - 1);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        Random random = new Random();
        return lst.get(random.nextInt(lst.size()));
    }
}

//381. Insert Delete GetRandom O(1) - Duplicates allowed

class RandomizedCollection {

    /** Initialize your data structure here. */
    HashMap<Integer, Set<Integer>> map;        //<value, locations>
    List<Integer> lst;
    public RandomizedCollection() {
        map = new HashMap<>();
        lst = new ArrayList<>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map.containsKey(val)) {
            map.put(val, new LinkedHashSet<Integer>());
        }
        map.get(val).add(lst.size());
        lst.add(val);
        return map.get(val).size() == 1;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val) || map.get(val).size() == 0) {
            return false;
        }
        Set<Integer> locations = map.get(val);
        int location = locations.iterator().next();
        locations.remove(location);
        
        int tail = lst.get(lst.size() - 1);
        lst.set(location, tail);
        map.get(tail).add(location);
        map.get(tail).remove(lst.size() - 1);
        
        lst.remove(lst.size() - 1);
        
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        Random random = new Random();
        return lst.get(random.nextInt(lst.size()));
    }
}
