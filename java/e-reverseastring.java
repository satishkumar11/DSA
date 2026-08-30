class reverseastring {
    public static void main(String[] args) {
        String str = "Hello1";
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        System.out.println(sb.toString());

        /////////////////////////////////////

        char[] chars = str.toCharArray();
        int i = 0;
        int j = str.length() - 1;

        while (i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
        System.out.println(new String(chars));
    }
}
