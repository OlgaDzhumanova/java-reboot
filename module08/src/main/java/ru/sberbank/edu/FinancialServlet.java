package ru.sberbank.edu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Hello world!
 *
 */
@WebServlet(value = "/module08/*", loadOnStartup = 1)
public class FinancialServlet extends HttpServlet
{
    private Income income;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);

        if ("/finance".equals(action)) {
            req.getServletContext().getRequestDispatcher("/finance.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);
        if ("/finance".equals(action)) {
            System.out.println("***POST request: " + action);
            String sum = req.getParameter("sum");
            String percentage = req.getParameter("percentage");
            String years = req.getParameter("years");
            if (!sum.matches("[0-9]{1,13}(\\.[0-9]*)?") || !percentage.matches("[0-9]{1,13}(\\.[0-9]*)?")
                    || !years.matches("[0-9]{1,2}")) {
                req.getServletContext().getRequestDispatcher("/invalidFormat.jsp").forward(req, resp);
            }
            if (Double.parseDouble(sum) < 50000) {
                req.getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            }
            income = new Income(Double.parseDouble(sum), Double.parseDouble(percentage), Integer.parseInt(years));

            req.setAttribute("totalAmount", income.getTotalAmount());
            req.getServletContext().getRequestDispatcher("/counter.jsp").forward(req, resp);
        }
    }

    private String getAction (HttpServletRequest request){
        String controllerName = "/module08";
        String url = request.getRequestURI();
        int controllerStartIndex = url.indexOf(controllerName);

        return url.substring(controllerStartIndex + controllerName.length());
    }
}

