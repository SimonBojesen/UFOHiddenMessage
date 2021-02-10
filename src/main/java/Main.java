import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File("7fe3c3f6-Stego.png"));
        marchThroughImage(bufferedImage);

        //int[][] allPixels = getImageToPixels(bufferedImage);
        //System.out.println(getMessageFromIntArray(allPixels));
    }


    public static String toBitString(final byte val) {
        return String.format("%8s", Integer.toBinaryString(val & 0xFF))
                .replace(' ', '0');
    }

    private static void marchThroughImage(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        System.out.println("width, height: " + w + ", " + h);

        String extracted = "";

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                //System.out.println("x,y: " + j + ", " + i);
                int pixel = image.getRGB(j, i);
                int blue = (pixel) & 0xff;
                String binary = Integer.toBinaryString(blue);
                //System.out.println(binary);
                extracted += binary.substring(binary.length()-1);
            }
        }

        System.out.println(extracted);

        String[] bytes = new String[1160];
        for(int i = 0, x=0; i < extracted.length()-8; i+= 8, x++)
        {
            bytes[x] = extracted.substring(i, i+8);
        }
        StringBuilder sb = new StringBuilder();
        for(String b : bytes){

            if (b.equals("00000000")) break;
            sb.append(b);
            System.out.println("bytevalue: " + b + " ---- char: " +(char)Integer.parseInt(sb.reverse().toString(),2));
            sb.setLength(0);

        }


    }


    public static int[][] getImageToPixels(BufferedImage bufferedImage) {
        if (bufferedImage == null) {
            throw new IllegalArgumentException();
        }
        int h = bufferedImage.getHeight();
        int w = bufferedImage.getWidth();
        int[][] pixels = new int[h][w];
        for (int i = 0; i < h; i++) {
            bufferedImage.getRGB(0, i, w, 1, pixels[i], 0, w);
        }
        return pixels;
    }

    /*public static String getMessageFromIntArray(int[][] pixels) {
        String msg = "";

        ArrayList<Integer> selectedPixels = new ArrayList<>();
        for (int s = 0; s < pixels.length; s++) {
            selectedPixels.add(pixels[s][0]);
        }

        StringBuilder sb = new StringBuilder();

        //Printer Blå værdien for alle pixels (0.0), (1.0), (2.0) osv.
        for (int pixel : pixels) {
            int blue = (pixel) & 0xff;
            String binary = Integer.toBinaryString(blue);
            sb.append(binary.substring(7));
            //System.out.println(binary + ", " + blue);
        }
        System.out.println(sb.reverse());
        char[] chars = new char[256];
        sb.getChars(0, 72, chars,0);

        System.out.println(chars);

        return msg;
    }*/

}
