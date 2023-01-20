/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package javaServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myModel.Model;
import org.apache.commons.lang3.tuple.Pair;

/**
 *
 * @author Quecheri
 * @version 1.1
 */
@WebServlet("/TableGenerator")
public class TableGeneratorServlet extends HttpServlet {
     /**
     * Constructor Prepares values for roman numbers in List
     */
    Model mod=Model.getInstance();
    TableGeneratorServlet()
    {
        list=mod.LoadTable();
    }
    /**
     * Processes requests for both HTTP &Lt;code&Gt;GET&Lt;/code&Gt; and &Lt;code&Gt;POST&Lt;/code&Gt;
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
    * cotains list of pairs key,value
    * Keys are from within roman namespace
    */
    private List<Pair<String, String>> list = new ArrayList<>();
    /**
     * Method fillst list with correct pairs &Lt;key , value&Gt; from within roman namespace
     */
    private void fillList()
    {
        list.add(Pair.of("I", "1"));
        list.add(Pair.of("II", "2"));
        list.add(Pair.of("III", "3"));
        list.add(Pair.of("IV", "4"));
        list.add(Pair.of("V", "5"));
        list.add(Pair.of("VI", "6"));
        list.add(Pair.of("VII", "7"));
        list.add(Pair.of("VIII", "8"));
        list.add(Pair.of("IX", "9"));
        list.add(Pair.of("X", "10"));
        list.add(Pair.of("XX", "20"));
        list.add(Pair.of("XXX", "30"));
        list.add(Pair.of("XL", "40"));
        list.add(Pair.of("L", "50"));
        list.add(Pair.of("LX", "60"));
        list.add(Pair.of("LXX", "70"));
        list.add(Pair.of("LXXX", "80"));
        list.add(Pair.of("XC", "90"));
        list.add(Pair.of("C", "100"));
        list.add(Pair.of("CC", "200"));
        list.add(Pair.of("CCC", "300"));
        list.add(Pair.of("CD", "400"));
        list.add(Pair.of("D", "500"));
        list.add(Pair.of("DC", "600"));
        list.add(Pair.of("DCC", "700"));
        list.add(Pair.of("DCCC", "800"));
        list.add(Pair.of("CM", "900"));
        list.add(Pair.of("M", "1000"));
        list.add(Pair.of("MM", "2000"));
        list.add(Pair.of("MMM", "3000"));
        list.add(Pair.of("MMMCMXCIX", "3999"));
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        list=mod.LoadTable();
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
                out.println("<head>");
                    out.println("<meta http-equiv='Content-Type' content='text/html'; charset='UTF-8'>");
                    out.println("<link rel='stylesheet' href='Css/FrontStyle.css'>");
                    out.println("<title>Servlet FormServlet</title>");            
                out.println("</head>");
                out.println("<body>");
                    out.println("<div class='container'>");
                        out.println("<h1 class='h-header'>Number System Converter</h1>");
                        out.println("<h2 class='h-header'> Tabela wykonanych operacji (Baza danych)</h1>");
                        out.println("<div class='center center-div'>");
                        out.println("<div class='table-container'>");
                            out.println("<table>");
                                out.println("<tr>");
                                    out.println("<th>Znak</th>");
                                    out.println("<th>Wartość</th>");
                                    out.println("</tr>");
                                for (Pair<String, String> pair : list) {
                                    out.println("<tr>");
                                    out.println("<td>"+pair.getLeft()+"</td>");
                                    out.println("<td>"+pair.getRight()+"</td>");
                                    out.println("</tr>");
                                 }

                            out.println("</table>");
                        out.println("</div>");
                        out.println("<form>");
                        out.    println("<input type='button' class='center' value='Go back!' onclick='history.back()'>");
                        out.println("</form>");
                        out.println("</div>");
                    out.println("</div>");
                out.println("</body>");
            out.println("</html>");
            
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP &Lt; code &Gt; GET &Lt; /code &Gt; method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP &Lt;code&Gt;POST&Lt;/code&Gt; method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
