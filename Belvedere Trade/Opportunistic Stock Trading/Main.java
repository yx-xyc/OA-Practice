import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.lang.StringBuilder;

public class Main {
    private static DecimalFormat formatter = new DecimalFormat("0.00");

    public static class Stock {
        public String name;
        public Double price;

        public Stock(String name, Double price) {
            this.name = name;
            this.price = price;
        }

    }

    public static class QtyPair {
        public Integer quantity;
        public String name;

        public QtyPair(Integer quantity, String name) {
            this.quantity = quantity;
            this.name = name;
        }
    }

    public static class Bundle {
        public String name;
        public Double price;
        public Vector<QtyPair> components;

        public Bundle(String name, Double price, Vector<QtyPair> components) {
            this.name = name;
            this.price = price;
            this.components = components;
        }
    }

    public static class MarketWatchPortfolio {

        private final Vector<Bundle> bundles;
        private final HashMap<String, Stock> stocks;

        public MarketWatchPortfolio(Vector<Bundle> bundles, HashMap<String, Stock> stocks) {
            this.bundles = bundles;
            this.stocks = stocks;
        }

        public void ExecuteTrades() {
            Set<String> bundleNames = new HashSet<>();
            for (Bundle bundle : bundles) {
                bundleNames.add(bundle.name);
            }
            for (Bundle bundle : bundles) {
                String name = bundle.name;
                Double individual_price = 0.0;
                Double price = bundle.price;
                Boolean passBuy = false;
                for (QtyPair component : bundle.components) {
                    int quantity = component.quantity;
                    String component_name = component.name;
                    if (!bundleNames.contains(component_name) && !stocks.keySet().contains(component_name)) {
                        passBuy = true;
                        break;
                    } else {
                        if (bundleNames.contains(component_name)) {
                            for (Bundle inner_bundle : bundles) {
                                if (inner_bundle.name.equals(component_name)) {
                                    individual_price += inner_bundle.price * quantity;
                                }
                            }
                        } else {
                            individual_price += stocks.get(component_name).price * quantity;
                        }
                    }
                }
                if (passBuy) {
                    PrintNoTrade(name);
                    passBuy = false;
                    continue;
                }
                // System.out.println("individual price:" + individual_price);
                // System.out.println("bundle price:" + price);
                if (individual_price < price) {
                    PrintTrade(name, "I", individual_price);
                } else {
                    PrintTrade(name, "B", price);
                }
            }
        }

        private void PrintTrade(String name, String bundleOrIndividual, Double cost) {
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(" E ");
            sb.append(bundleOrIndividual);
            sb.append(" ");
            sb.append(formatter.format(cost));
            System.out.println(sb.toString());
        }

        private void PrintNoTrade(String name) {
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(" P");
            System.out.println(sb.toString());
        }
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        boolean isFirstLine = true;
        int numBundles = 0;
        Vector<Bundle> bundles = new Vector<>();
        HashMap<String, Stock> stocks = new HashMap<>();

        while ((line = in.readLine()) != null) {
            String[] parsed = line.split(" ");
            if (isFirstLine) {
                numBundles = Integer.parseInt(parsed[0]);
                isFirstLine = false;
                continue;
            }
            if (numBundles > 0) {
                Vector<QtyPair> components = new Vector<>();
                for (int i = 3; i < parsed.length - 1; i = i + 2) {
                    components.add(new QtyPair(Integer.parseInt(parsed[i + 1]), parsed[i]));
                }
                bundles.add(new Bundle(parsed[0], Double.parseDouble(parsed[1]), components));
                numBundles--;
                continue;
            }
            stocks.put(parsed[0], new Stock(parsed[0], Double.parseDouble(parsed[1])));
        }
        MarketWatchPortfolio portfolio = new MarketWatchPortfolio(bundles, stocks);
        System.out.println(bundles.size());
        portfolio.ExecuteTrades();
    }
}
