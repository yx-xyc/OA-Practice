import java.io.*;
import java.util.*;

public class FastIO {
    private BufferedReader br;
    private BufferedWriter bw;
    private StringTokenizer st;

    public FastIO() {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    // Check if there's another line available in the input
    public boolean hasNextLine() throws IOException {
        br.mark(1000); // Mark the current position in the stream
        if (br.readLine() == null) {
            br.reset(); // Reset back to the marked position if there's no next line
            return false;
        }
        br.reset(); // Reset back to the marked position after checking
        return true;
    }

    // Read a line as a string
    public String readLine() throws IOException {
        return br.readLine();
    }

    // Helper method to ensure the tokenizer is ready to provide tokens
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

    // Check if there's another token available
    public boolean hasNextToken() throws IOException {
        ensureToken();
        return st != null && st.hasMoreTokens();
    }

    // Read a token as a string
    public String readToken() throws IOException {
        ensureToken();
        return st != null ? st.nextToken() : null;
    }

    // Read a line and split it into an array of tokens
    public String[] readTokens() throws IOException {
        String line = br.readLine();
        if (line == null) {
            return null; // Return null if end of input is reached
        }
        return line.split("\\s+"); // Split the line by whitespace to get tokens
    }

    // Write a string with a newline
    public void writeLine(String s) throws IOException {
        bw.write(s);
        bw.newLine();
    }

    // Flush the BufferedWriter
    public void flush() throws IOException {
        bw.flush();
    }

    // Close the BufferedReader and BufferedWriter
    public void close() throws IOException {
        br.close();
        bw.close();
    }

    public static void main(String[] args) throws IOException {

    }
}
