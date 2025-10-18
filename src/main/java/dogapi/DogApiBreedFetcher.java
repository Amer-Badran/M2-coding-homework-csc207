package dogapi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * BreedFetcher implementation that relies on the dog.ceo API.
 * Note that all failures get reported as BreedNotFoundException
 * exceptions to align with the requirements of the BreedFetcher interface.
 */
public class DogApiBreedFetcher implements BreedFetcher {
    private final OkHttpClient client = new OkHttpClient();
    private final String url = "https://dog.ceo/api/breed/hound/list";
    private final String success = "success";
    private static final String STATUS_CODE = "status";
    public List<String> subBreedList = new ArrayList<>();


    /**
     * Fetch the list of sub breeds for the given breed from the dog.ceo API.
     * @param breed the breed to fetch sub breeds for
     * @return list of sub breeds for the given breed
     * @throws BreedNotFoundException if the breed does not exist (or if the API call fails for any reason)
     */
    @Override
    public List<String> getSubBreeds(String breed) {
        Request request = new Request.Builder()
                .url(String.format("https://dog.ceo/api/breed/%s/list", breed))
                .build();
        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body());
            final JSONObject responseBody = new JSONObject(response.body().string());
            if (responseBody.getString(STATUS_CODE).equals(success)) {
                final JSONArray breedJSON = responseBody.getJSONArray("message");
                for (int i = 0; i < breedJSON.length(); i++) {
                    String item = breedJSON.getString(i);
                    subBreedList.add(item);}

                    return subBreedList;}
            else {
                throw new BreedNotFoundException(breed);


                    }}
        catch (IOException | BreedNotFoundException e) {
            throw new RuntimeException(e);
        }

        // TODO Task 1: Complete this method based on its provided documentation
        //      and the documentation for the dog.ceo API. You may find it helpful
        //      to refer to the examples of using OkHttpClient from the last lab,
        //      as well as the code for parsing JSON responses.
        // return statement included so that the starter code can compile and run.
//        return new ArrayList<>();

}}