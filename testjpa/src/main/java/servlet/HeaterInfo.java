package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.Heater;
import domain.Home;
import jpa.EntityManagerHelper;

@WebServlet(name="heaterInfo",urlPatterns={"/HeaterInfo"})
public class HeaterInfo extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();

		out.println("<HTML>\n<BODY>\n" +
				"<H1>Recapitulatif des informations</H1>\n" +
				"<UL>\n" +			
				" <LI>Nom : "
				+ request.getParameter("name") + "\n" +
				" <LI>Consommation moyenne : "
				+ request.getParameter("AvgCons") + "\n" +
				" <LI>Résidence : "
				+ request.getParameter("home") + "\n" +
				"</UL>\n");				
		EntityTransaction tx = EntityManagerHelper.getEntityManager().getTransaction();
		tx.begin();
		try {
			Home home0 = new Home("résidence principale",90, 6, null,null,null);
			Heater heater6=new Heater(request.getParameter("name"), Integer.parseInt(request.getParameter("AvgCons")), home0);
			EntityManagerHelper.getEntityManager().persist(home0);
			EntityManagerHelper.getEntityManager().persist(heater6);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		out.println("Enregistrement effectué</BODY></HTML>");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String query = "select h from Heater as h";
		List<Heater> result2 = EntityManagerHelper.getEntityManager().createQuery(query).getResultList();
		out.println("<HTML>\n<BODY>\n" +
				"<H1>Recapitulatif des informations</H1>\n" +
				"<UL>\n");
				for (Object enregistrement : result2) {
					out.println("<LI> enregistrement : " + enregistrement+"\n");
				}
				out.println("</UL>\n" +				
				"</BODY></HTML>");
	}
}
