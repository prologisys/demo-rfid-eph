package com.prolosys.rfid.common.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;

public class UtilsSCL {
	
	
	public static String getPrefixFromKey(String key, String separator) {
		
		String[] parts = key.split(separator);
		try {
			return parts[0];
		} catch (Exception e) {
			System.out.println("No se puede extraer del prefijo de "+ key);
			return "NoKey";
		}
		
	}
	
	public static String getNumberFromKey(String key,  String separator) {
		
		String[] parts = key.split(separator);
		try {
			return stringToNumberString(parts[1]);
		} catch (Exception e) {
			System.out.println("No se puede extraer del consescutivo de "+ key);
			return "-1";
		}
	}
	

	
	public static String stringToNumberString(String string) {
		String result;
		try {
			result = Long.toString(Long.parseLong(string));
		} catch (Exception e) {
			result  = string;
		}
		return result;
	}
	
	
	public static int stringNumberToInt(String string) {
		int result;
		try {
			result = Integer.parseInt(string);
		} catch (Exception e) {
			result  = -1;
		}
		return result;
	}
	
	
	
	public static String buildCode(String prefix, String separator , String consecutive, int length) {
		
		StringBuilder key = new StringBuilder();

		int ceros = length -(prefix.length() + separator.length() +consecutive.length());
		key.append(prefix+separator);
		for (int i = 0; i < ceros; i++) {
			key.append("0");
		}
		key.append(consecutive);

		return key.toString();
		
	}
	
	
	
	public static Image generateQRCodeImage(String text, int width, int height){
		
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        Image iTextImage = null;
        
		try {
			Map<EncodeHintType, Object> hints = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			hints.put(EncodeHintType.MARGIN, 2); /* default = 4 */
			
			bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
			
			
			BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			
			ImageIO.write(bufferedImage, "png", baos);
			
			iTextImage = Image.getInstance(baos.toByteArray());
			
		} catch (BadElementException | IOException | WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       return iTextImage;
       
    }
	
	
	public double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }
	
	
	

}
