package servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ValidateCodeFactory;

public class ValidateCode extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String vc = req.getParameter("validateCode");
		if (vc == null) {
			resp.setContentType("image/png");
			resp.setHeader("cache-control", "no-cache");
			resp.setHeader("pragma", "no-cache");
			ValidateCodeFactory validateCodeFactory = ValidateCodeFactory.getFactory();
			String validateCode = validateCodeFactory.randomLetter();
			// …Ë÷√ session
			req.getSession().setAttribute("validateCode", validateCode);
			
			//  ‰≥ˆÕº∆¨
			BufferedImage img = (BufferedImage) validateCodeFactory.str2Image(validateCode);
			ImageIO.write(img, "png", resp.getOutputStream());
		} else {
			resp.getWriter().println("{\"validateCode\":" + vc.toUpperCase().equals(req.getSession().getAttribute("validateCode")) + "}");
		}
		
	}
	
}
