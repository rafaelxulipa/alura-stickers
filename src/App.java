import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // get data movie from API and save into the string
        //String url = "https://raw.githubusercontent.com/rafaelxulipa/alura-stickers/main/src/imdbtop250moviesdata.json";

        String url = "https://raw.githubusercontent.com/rafaelxulipa/alura-stickers/main/src/MostPopularMovies.json";

        URI uri = URI.create(url);

        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(uri).GET().build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String body = response.body();
        
        //System.out.println(body);
        
        
        // extract only the title, image and rank of the movie
        JsonParser parser = new JsonParser();
        
        List<Map<String, String>> moviesList = parser.parse(body);

        //System.out.println(moviesList.size());
        //System.out.println(moviesList.get(0));

        for (Map<String,String> movie : moviesList) {
            System.out.println("\u001b[1mTitle: \u001b[m" + "\u001b[32;1m" + movie.get("title") + " \u001b[m");
            System.out.println("\u001b[1mImage URL: \u001b[m" + "\u001b[3m" + movie.get("image") + "\u001b[m");
            //System.out.println(movie.get("imDbRating"));
            double rating = Double.parseDouble(movie.get("imDbRating"));
            int starNumber = (int) rating;

            if (starNumber <= 6) {
                for (int n = 1; n <= starNumber; n++) {
                    System.out.print("👎");
                }
            } else {
                for (int n = 1; n <= starNumber; n++) {
                    System.out.print("⭐");
                }
            }

            System.out.println("\n");
        }
        

        // show the data filtered and manipulate this data


    }
}
