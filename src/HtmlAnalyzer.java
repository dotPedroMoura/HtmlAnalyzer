import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HtmlAnalyzer {
    public static void main(String[] args) {
        try {
            String html = GetHTML(URI.create(args[0]));
            System.out.println(findDeepestElement(html));

        } catch (Exception e) {
            System.out.println("URL connection error");;
        }
    }

    private static String GetHTML (URI uri) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader((uri.toURL()).openStream()));
        StringBuilder sb = new StringBuilder();
        while (br.ready()) {
            sb.append(br.readLine().indent(0));
        }
        return sb.toString();
    }

    private static String findDeepestElement(String html) {
            List<String> lines = Arrays.asList(html.split("\n\s*"));
            LinkedList<String> tags = new LinkedList<>();
            String deepestElement = "";
            int maxDepth = 0, currentDepth = 0;

            for (String line : lines) {
                if (line.startsWith("<")) {
                    if(line.endsWith(">")) {
                        if (line.startsWith("</")) {
                            if (tags.isEmpty() || !tags.contains(line.substring(2, line.length() - 1))) {
                                return("malformed HTML");

                            }
                            tags.pop();
                            currentDepth--;
                        } else {
                            tags.push(line.substring(1, line.length() - 1));
                            currentDepth++;
                        }
                    } else {
                        return("malformed HTML");
                    }
                } else {
                    if (currentDepth > maxDepth) {
                        maxDepth = currentDepth;
                        deepestElement = line;
                    }
                }

            }

            return tags.isEmpty() ? deepestElement : "malformed HTML";
    }
}