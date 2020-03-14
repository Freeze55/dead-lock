import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class WatermarkService {

    private static final String INPUT_PICTURE = "resources/input.jpg";

    private static final String WATERMARK_PICTURE = "resources/watermark.png";

    private static final String OUTPUT_PICTURE = "resources/output.jpg";

    public void processImage() {
        try {

            File file = new File(INPUT_PICTURE);
            File watermarkFile = new File(WATERMARK_PICTURE);
            BufferedImage source = ImageIO.read(file);
            BufferedImage watermark = ImageIO.read(watermarkFile);

            watermark = resizeImage(watermark,0.3);

            if (watermark.getHeight() < source.getHeight() + 10 && watermark.getWidth() < source.getWidth()+ 10){
            synchronized (source) {

                BufferedImage finalWatermark = watermark;
                Thread one = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        for (int x = source.getWidth() - finalWatermark.getWidth() - 10; x < source.getWidth() - 10; x+=2) {
                            for (int y = source.getHeight() - finalWatermark.getHeight() - 10; y < source.getHeight() - 10; y++) {

                                Color colorWatermark = new Color(finalWatermark.getRGB(
                                        (x - source.getWidth() + finalWatermark.getWidth() + 10),
                                        (y - source.getHeight() + finalWatermark.getHeight() + 10)));

                                Color colorSource = new Color(source.getRGB(x, y));


                                int r =(int) (colorSource.getRed() + (colorWatermark.getRed() - colorSource.getRed()) *0.3);
                                int g =(int) (colorSource.getGreen() + (colorWatermark.getGreen() - colorSource.getGreen()) *0.3);
                                int b =(int) (colorSource.getBlue() + (colorWatermark.getBlue() - colorSource.getBlue()) *0.3);
                                Color resultColor = new Color(r,g,b);

                                if (colorWatermark.getRGB() != -16777216) //avoid black
                                    source.setRGB( x, y, resultColor.getRGB()
                                    );


                            }
                        }

                    }
                });

                Thread second = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        for (int x = source.getWidth() - finalWatermark.getWidth() - 10+1; x < source.getWidth() - 10; x+=2) {
                            for (int y = source.getHeight() - finalWatermark.getHeight() - 10; y < source.getHeight() - 10; y++) {

                                Color colorWatermark = new Color(finalWatermark.getRGB(
                                        (x - source.getWidth() + finalWatermark.getWidth() + 10),
                                        (y - source.getHeight() + finalWatermark.getHeight() + 10)));

                                Color colorSource = new Color(source.getRGB(x, y));


                                int r =(int) (colorSource.getRed() + (colorWatermark.getRed() - colorSource.getRed()) *0.3);
                                int g =(int) (colorSource.getGreen() + (colorWatermark.getGreen() - colorSource.getGreen()) *0.3);
                                int b =(int) (colorSource.getBlue() + (colorWatermark.getBlue() - colorSource.getBlue()) *0.3);
                                Color resultColor = new Color(r,g,b);

                                if (colorWatermark.getRGB() != -16777216) //avoid black
                                    source.setRGB( x, y, resultColor.getRGB()
                                    );


                            }
                        }
                    }
                });
                one.start();
                second.start();


            }

            } else
                System.err.println("Файл водяного знака больше размера изображения");

            File output = new File(OUTPUT_PICTURE);
            ImageIO.write(source, "jpg", output);


        } catch (IOException e) {


            System.out.println("Файл не найден или не удалось сохранить");
        }
    }


    private BufferedImage resizeImage(BufferedImage image, double resizeIndex) {

        int scaledWidth = (int) (image.getWidth() * resizeIndex);
        int scaledHeight = (int) (image.getHeight() * resizeIndex);
        BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, image.getType());


        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        return outputImage;

    }

}
