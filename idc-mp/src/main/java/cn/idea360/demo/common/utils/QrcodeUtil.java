package cn.idea360.demo.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class QrcodeUtil {

    /**
     * 生成二维码并使用Base64编码
     *
     * @param content 需要生产二维码的字符串
     * @param width 二维码宽
     * @param height 二维码高
     * @return
     * @throws WriterException
     */
    public static String getBase64QRCode(String content, int width, int height) {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        Map hints = new HashMap();

        //设置二维码四周白色区域的大小
        hints.put(EncodeHintType.MARGIN, 3);//设置0-4之间
        //设置二维码的容错性
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        //设置编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //画二维码
        BitMatrix bitMatrix = null;
        try {
            bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            log.error(e.getMessage());
        }

        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        //注意此处拿到字节数据
        byte[] bytes = imageToBytes(image, "jpg");
        //Base64编码
        return "data:image/jpg;base64," + Base64.getEncoder().encodeToString(bytes);
    }

    private static byte[] imageToBytes(BufferedImage image, String type) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, type, out);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
        }
        return out.toByteArray();
    }
}
