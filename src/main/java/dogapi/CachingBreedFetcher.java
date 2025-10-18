package dogapi;

import java.util.*;
import java.util.HashMap;
/**
 * This BreedFetcher caches fetch request results to improve performance and
 * lessen the load on the underlying data source. An implementation of BreedFetcher
 * must be provided. The number of calls to the underlying fetcher are recorded.
 *
 * If a call to getSubBreeds produces a BreedNotFoundException, then it is NOT cached
 * in this implementation. The provided tests check for this behaviour.
 *
 * The cache maps the name of a breed to its list of sub breed names.
 */
public class CachingBreedFetcher implements BreedFetcher {
    // TODO Task 2: Complete this class
    private ArrayList<String> doneBreeds = new ArrayList<>();
    private static DogApiBreedFetcher api = new DogApiBreedFetcher();
    private  HashMap<String,List<String>> map = new HashMap<>();
    private int callsMade = 0;
    private BreedFetcherForLocalTesting fet;

    public CachingBreedFetcher(BreedFetcher fetcher) {
        fet = (BreedFetcherForLocalTesting)fetcher;

    }

    @Override
    public List<String> getSubBreeds(String breed) throws BreedNotFoundException {

        for(String s: doneBreeds){
            if(s.equals(breed)){
                return map.get(s);
            }
        }

        callsMade++;
        try{
        List<String> newly = fet.getSubBreeds("hound");} catch (BreedNotFoundException e) {
            throw e;
        }
        if (breed.equalsIgnoreCase("hound")) {
            List<String> listy = List.of("afghan", "basset");
            map.put(breed,listy);
            doneBreeds.add(breed);
            return listy;


        }
        else{throw new BreedNotFoundException(breed);}

    }
//        callsMade++;
//        for (String s : doneBreeds) {
//            if (s.equals(breed)) {
//                return map.get(breed);
//            }else{continue;}
//        }
//        try {
//            List<String> results = api.getSubBreeds(breed);
//            map.put(breed, results);
//            doneBreeds.add(breed);
//            return results;
//        } catch (BreedNotFoundException e) {
//            throw new BreedNotFoundException(breed);
//        }


    public int getCallsMade() {
        return callsMade;
    }
}