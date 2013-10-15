package utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 
 * 关于：
 *	验证码工厂
 * @author AF130310  修改日期：2013-10-13
 *
 */
public class ValidateCodeFactory {
	
	/**
	 * 随机出的字母来源
	 * 字母为26个字母加10个数字
	 */
	private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	
	private static final Random random = new Random();
	
	private static ValidateCodeFactory factory = new ValidateCodeFactory();
	
	public static ValidateCodeFactory getFactory() {
		return factory;
	}
	
	/**
	 * 产生随机字母组成的字符串
	 * @return
	 */
	public String randomLetter() {
		return randomLetter(4);
	}
	
	/**
	 * 产生随机字母组成的字符串
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
	 * 字符串转换为图片
	 * @return BufferedImage类型的图片
	 */
	public Image str2Image(String str) {
		int width = 200;
		int height = 50;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		Graphics g = image.getGraphics();
		Color oldColor = g.getColor();
		
		// 白色背景绘制
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		// 字母绘制
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));
		// 减100是为了控制边距
		int w = (width - 50) / str.length();
		int x, y;
		for (int i = 0; i < str.length(); i ++) {
			x = random.nextInt(w) + (i * w);
			y = random.nextInt(20) + 30;
			g.drawString(str.charAt(i) + "", x, y);
		}
		
		// 噪线绘制
		for (int i = 0; i < 10; i ++) {
			g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
		}
		g.setColor(oldColor);
		
		return image;
	}
}
