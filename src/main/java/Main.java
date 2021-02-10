import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        byte[] array = Files.readAllBytes(Paths.get("7fe3c3f6-Stego.png"));
        ArrayList<Byte> msgBytes = new ArrayList<>();

        for (Byte b : array) {
            if (b == 0) break;
            msgBytes.add(b);
            System.out.println(b);
        }
        System.out.println("--------------" + array.length);
        for (Byte b : msgBytes) {
            //System.out.println(b);

            System.out.println(Integer.toBinaryString(b));
        }
    }

    public static String toBitString(final byte val) {
        return String.format("%8s", Integer.toBinaryString(val & 0xFF))
                .replace(' ', '0');
    }


}
