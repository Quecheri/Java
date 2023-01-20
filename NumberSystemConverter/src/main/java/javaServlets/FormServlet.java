/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package javaServlets;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myModel.Model;

/**
 *
 * @author Quecheri
 * @version 1.2
 */
@WebServlet("/Form")
public class FormServlet extends HttpServlet {
    Model mod=Model.getInstance();
    /**
     * Processes requests for both HTTP &lt;code&Gt;GET&lt;/code&Gt; and &lt;code&Gt;POST&lt;/code&Gt;
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String number)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Cookie[] cookies = request.getCookies();
        int atemptNumber=0;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("Attempt")) {
                    String value = cookie.getValue();
                    if(value.matches("[1-9,-][\\d]*$"))
                    {
                        atemptNumber=Integer.parseInt(value);
                        Cookie newCookie = new Cookie("Attempt", Integer.toString(atemptNumber+1));
                        newCookie.setMaxAge(24*60*60);
                        response.addCookie(newCookie);
                    }
                    else
                    {
                        Cookie newCookie = new Cookie("Attempt", "1");
                        newCookie.setMaxAge(24*60*60);
                        newCookie.setMaxAge(24*60*60);
                        response.addCookie(newCookie);
                    }
                    
                }
            }
        }
        else
        {
            Cookie newCookie = new Cookie("Attempt", "1");
            newCookie.setMaxAge(24*60*60);
            response.addCookie(newCookie); 
        }
        
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
            out.println("<h1 class='h-header'>Number System Converter</h1>");;
            out.println("<h2 class='h-header'>Its my "+(mod.getCurrentRepetition()+1) + " repetition (Application counter)"
                    + " and your "+atemptNumber+" attempt (Cookie)"
                    + "</h2>");
            out.println("<div class='center center-div'>");
            
            
            
            out.println("<p>");
            Boolean fail=false;
            mod.setConvertedNumber("");
            mod.setNumber(number);
            if(mod.isRomanNumber())
            {
                mod.changeToArabic();
            }
            else if(mod.isArabicNumber())
            {
               try
               {
                    mod.changeToRoman();
               }
               catch (myModel.NumberOutOfBoundsExeption ex)
               {           
                   fail=true;
                   out.println("Number out of proper range");
               }
               catch (NumberFormatException ex)
               {
                   fail=true;
                   out.println("Number out of proper range");
               }
            }
            else
            {
              fail=true;
              out.println("This is something beyond me!");
            }
            if(!fail)
            {
                if(mod.getConvertedNumber().equals(""))out.println("Provided number was wrong");               
                else out.println("your number was " + mod.getNumber()+" and it was converted to "+mod.getConvertedNumber());
            }
            out.println("<form>");
            out.println("<input type='button' class='center' value='Go back!' onclick='history.back()'>");
            out.println("</form>");
            out.println("</p>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP &lt;code&Gt;GET&lt;/code&Gt; method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String number = request.getParameter("number");
        processRequest(request, response,number);
    }

    /**
     * Handles the HTTP &lt;code&Gt;POST&lt;/code&Gt; method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String number = request.getParameter("number");
        processRequest(request, response,number);
    }
}
