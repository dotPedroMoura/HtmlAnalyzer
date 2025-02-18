import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlAnalyzer {
    public static void main(String[] args) {
        try {
            String teste = GetHTML(URI.create(args[0]));
            Pattern pattern = Pattern.compile("<(.*?)>", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(teste);
            List<String> tags = new ArrayList<String>();
            Iterator<String> iterator;

            while (matcher.find()) {
                tags.add(matcher.group(1));
                iterator = tags.iterator();
                if (tags.contains("html") && tags.contains("/html")) {
                    System.out.println(tags.toString());
                }
            }

//            System.out.println(teste);
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