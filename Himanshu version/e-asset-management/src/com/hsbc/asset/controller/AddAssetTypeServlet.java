package com.hsbc.asset.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.asset.model.beans.AssetType;
import com.hsbc.asset.model.service.UserService;
import com.hsbc.asset.model.utility.FactoryPattern;
import com.hsbc.asset.model.utility.Type;

/**
 * Servlet implementation class AddAssetTypeServlet
 */
@WebServlet("/AddAssetTypeServlet")
public class AddAssetTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		String typeBorrow = request.getParameter("borrow");
		String typeFine = request.getParameter("fine");
		String typeBan = request.getParameter("ban");
		int lendingPeriod = Integer.parseInt(typeBorrow);
		double fine = Double.parseDouble(typeFine);
		int ban = Integer.parseInt(typeBan);
		
		UserService service = (UserService)FactoryPattern.getInstance(Type.SERVICE);
		
		AssetType newAssetType = new AssetType();
		newAssetType.setAssetType(type);
		newAssetType.setLendingPeriod(lendingPeriod);
		newAssetType.setFine(fine);
		newAssetType.setBanDays(ban);
		
		AssetType assetType = service.addAssetType(newAssetType); 
		HttpSession session = request.getSession();
		session.setAttribute("assetTypeKey", assetType);
		
		RequestDispatcher rd = request.getRequestDispatcher("assettypeadded.jsp");
		rd.forward(request, response);
		
	}

}
