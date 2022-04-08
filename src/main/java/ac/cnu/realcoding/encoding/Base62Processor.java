package ac.cnu.realcoding.encoding;

public class Base62Processor {
    final private static long BASE = 62;
    final private static String CODEC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String encode(long number) {
        // TODO: Implement Base62 Encoding Algorithm.
        StringBuilder sb = new StringBuilder();
        do{
            sb.append(CODEC.charAt((int)(number % BASE)));
            number /= BASE;
        }while(number>0);
        return sb.reverse().toString();
    }

    public static long decode(String encoded) {
        // TODO: Implement Base62 Decoding Algorithm.
        long sum = 0;
        for(Character ch: encoded.toCharArray()){
            sum *= BASE;
            sum += CODEC.indexOf(ch);
        }
        return sum;
    }
}
