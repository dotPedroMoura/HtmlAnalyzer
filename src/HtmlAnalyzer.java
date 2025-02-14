import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.CharBuffer;

public class HtmlAnalyzer {
    public static void main(String[] args) {
        try {
            String teste = GetHTML(URI.create(args[0]));
            teste.stripIndent();
            System.out.println(teste);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String GetHTML (URI uri) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader((uri.toURL()).openStream()));
            StringBuilder sb = new StringBuilder();
            while (br.ready()) {
                sb.append(br.readLine().indent(0));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new Error("URL connection error");
        }
    }
}