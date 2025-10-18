package dogapi;

import java.util.ArrayList;
import java.util.List;
// this comment will be my second commit. it will not have an effect on the code

public class Main {

    public static void main(String[] args) throws BreedFetcher.BreedNotFoundException {
        String breed = "hound";
        BreedFetcher breedFetcher = new CachingBreedFetcher(new BreedFetcherForLocalTesting());
        DogApiBreedFetcher thingy = new DogApiBreedFetcher();
        List<String> thing = thingy.getSubBreeds(breed);
        System.out.println(thing);
        int result = getNumberOfSubBreeds(breed, breedFetcher);
        System.out.println(breed + " has " + result + " sub breeds");

        breed = "cat";
        result = getNumberOfSubBreeds(breed, breedFetcher);
        System.out.println(breed + " has " + result + " sub breeds");
    }

    /**
     * Return the number of sub breeds that the given dog breed has according to the
     * provided fetcher.
     * @param breed the name of the dog breed
     * @param breedFetcher the breedFetcher to use
     * @return the number of sub breeds. Zero should be returned if there are no sub breeds
     * returned by the fetcher
     */
    public static int getNumberOfSubBreeds(String breed, BreedFetcher breedFetcher) throws BreedFetcher.BreedNotFoundException {
        // TODO Task 3 implement this code so that it is entirely consistent with its provided documentation.
        // return statement included so that the starter code can compile and run.
        DogApiBreedFetcher api = new DogApiBreedFetcher();
        try{
        List<String> listy = breedFetcher.getSubBreeds(breed);
        return listy.size();}catch (BreedFetcher.BreedNotFoundException e){
            return 0;
        }
    }
}
