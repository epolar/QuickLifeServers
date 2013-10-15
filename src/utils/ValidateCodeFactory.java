package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 
 * ���ڣ�
 *	��֤�빤��
 * @author AF130310  �޸����ڣ�2013-10-13
 *
 */
public class ValidateCodeFactory {
	
	/**
	 * ���������ĸ��Դ
	 * ��ĸΪ26����ĸ��10������
	 */
	private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	
	private static final Random random = new Random();
	
	private static ValidateCodeFactory factory = new ValidateCodeFactory();
	
	public static ValidateCodeFactory getFactory() {
		return factory;
	}
	
	/**
	 * ���������ĸ��ɵ��ַ���
	 * @return
	 */
	public String randomLetter() {
		return randomLetter(4);
	}
	
	/**
	 * ���������ĸ��ɵ��ַ���
	 * @return
	 */
	public String randomLetter(int quantity) {
		StringBuilder strBuilder = new StringBuilder(quantity);
		int index = 0;
		for (int i = 0; i < quantity; i ++) {
			index = random.nextInt(LETTERS.length());
			strBuilder.append(LETTERS.charAt(index));
		}
		return strBuilder.toString();
	}
	
	/**
	 * �ַ���ת��ΪͼƬ
	 * @return BufferedImage���͵�ͼƬ
	 */
	public Image str2Image(String str) {
		int width = 200;
		int height = 50;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		Graphics g = image.getGraphics();
		Color oldColor = g.getColor();
		
		// ��ɫ��������
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		// ��ĸ����
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
		// ��100��Ϊ�˿��Ʊ߾�
		int w = (width - 50) / str.length();
		int x, y;
		for (int i = 0; i < str.length(); i ++) {
			x = random.nextInt(w) + (i * w);
			y = random.nextInt(20) + 30;
			g.drawString(str.charAt(i) + "", x, y);
		}
		
		// ���߻���
		for (int i = 0; i < 10; i ++) {
			g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
		}
		g.setColor(oldColor);
		
		return image;
	}
}
