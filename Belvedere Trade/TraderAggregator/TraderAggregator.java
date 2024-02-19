import java.io.*;
import java.util.*;

public class TraderAggregator {
    public static void main(String[] args) throws IOException {
        FastIO io = new FastIO();
        Map<Integer, Integer> prices = new HashMap<>();
        Map<String, Integer> traders = new HashMap<>();
        while (io.hasNextLine()) {
            String[] tokens = io.readTokens();
            if (tokens[0].equals("TheoUpdate")) {
                int asset = Integer.parseInt(tokens[1]);
                int price = Integer.parseInt(tokens[2]);
                prices.put(asset, price);
            } else if (tokens[0].equals("Trade")) {
                String traderName = tokens[1];
                int asset = Integer.parseInt(tokens[2]);
                int quantity = Integer.parseInt(tokens[3]);
                int price = Integer.parseInt(tokens[4]);
                int score = calculateTradeScore(prices.get(asset), price, quantity);
                traders.put(traderName, traders.getOrDefault(traderName, 0) + score);
                StringBuilder sb = new StringBuilder();
                sb.append(traderName);
                sb.append(" ");
                sb.append(String.valueOf(traders.get(traderName)));
                io.writeLine(sb.toString());
            }
        }
        io.flush();

    }

    public static int calculateTradeScore(int theoValue, int price, int quantity) {
        if (quantity == 0)
            return 0;
        int edge = 0;
        if (quantity < 0) {
            edge = price - theoValue;
        } else if (quantity > 0) {
            edge = theoValue - price;
        }
        quantity = Math.abs(quantity);
        if (edge < 0) {
            return -1 * quantity * edge * edge;
        } else {
            return quantity * edge * edge;
        }
    }
}

class FastIO {
    private BufferedReader br;
    private BufferedWriter bw;
    private StringTokenizer st;

    public FastIO() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public boolean hasNextLine() throws IOException {
        br.mark(1000); // Mark the current position in the stream
        if (br.readLine() == null) {
            br.reset(); // Reset back to the marked position if there's no next line
            return false;
        }
        br.reset(); // Reset back to the marked position after checking
        return true;
    }

    public String readLine() throws IOException {
        return br.readLine();
    }

    private void ensureToken() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String line = br.readLine();
            if (line == null) {
                st = null;
                return;
            }
            st = new StringTokenizer(line);
        }
    }

    public boolean hasNextToken() throws IOException {
        ensureToken();
        return st != null && st.hasMoreTokens();
    }

    public String readToken() throws IOException {
        ensureToken();
        return st != null ? st.nextToken() : null;
    }

    public String[] readTokens() throws IOException {
        String line = br.readLine();
        if (line == null) {
            return null; // Return null if end of input is reached
        }
        return line.split("\\s+"); // Split the line by whitespace to get tokens
    }

    public void writeLine(String s) throws IOException {
        bw.write(s);
        bw.newLine();
    }

    public void flush() throws IOException {
        bw.flush();
    }

    public void close() throws IOException {
        br.close();
        bw.close();
    }
}
