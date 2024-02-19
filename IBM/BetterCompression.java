class BetterCompression {
    public static void main(String[] args) {
        System.out.println(betterCompression("a3c9b2c1").equals("a3b2c10") ? "Passed" : "Failed");
        System.out.println(betterCompression("a1a1a1a1").equals("a4") ? "Passed" : "Failed");
        System.out.println(betterCompression("a10").equals("a10") ? "Passed" : "Failed");
        System.out.println(betterCompression("z1z1z1z1").equals("z4") ? "Passed" : "Failed");
        System.out.println(betterCompression("a5c5b5").equals("a5b5c5") ? "Passed" : "Failed");

        // Edge cases
        System.out.println(betterCompression("").equals("") ? "Passed" : "Failed");
        System.out.println(betterCompression("a0").equals("") ? "Passed" : "Failed");
        System.out.println(betterCompression("a0b0c0").equals("") ? "Passed" : "Failed");
        System.out
                .println(betterCompression("a1b2c3d4e5f6g7h8i9j0").equals("a1b2c3d4e5f6g7h8i9") ? "Passed" : "Failed");
    }

    public static String betterCompression(String s) {
        if (s.length() == 0)
            return s;
        int[] frequency_map = new int[26];
        char[] chars = s.toCharArray();
        int l = 0;
        while (l < chars.length) {
            char cur_char = chars[l];
            int cur_char_idx = cur_char - 'a';
            int r = l + 1;
            while (r < chars.length && Character.isDigit(chars[r])) {
                r++;
            }
            int value = Integer.parseInt(new String(chars, l + 1, r - 1 - (l + 1) + 1));
            frequency_map[cur_char_idx] += value;
            l = r;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (frequency_map[i] != 0) {
                sb.append((char) ('a' + i));
                sb.append(frequency_map[i]);
            }
        }
        System.out.print(sb.toString());
        return sb.toString();
    }
}
