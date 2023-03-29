import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP
        API api = API.NASA;

        String url = api.getUrl();
        ExtratorDeConteudo extrator = api.getExtrator();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados 
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var gerador = new GeradorDeFigurinhas();

        var diretorio = new File("figurinhas/");
        diretorio.mkdir();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.titulo() + ".png";

            gerador.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.titulo());
            System.out.println();
        }
    }
}
