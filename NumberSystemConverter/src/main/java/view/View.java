/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.JOptionPane;

/**
 * Class provides interface to communicate with user
 * @author Quecheri
 * @version 1.3
 */
public class View {
   
    
    /**
    * method displays error popup with chosen message
    * @param message displays message within messagebox
    */
    public void printErrorMessage(String message)
    {
        JOptionPane.showMessageDialog(null, "Error: " + message, 
                "Results", JOptionPane.ERROR_MESSAGE);
    }
    /**
    * method displays input popup 
    * @return string - number provided by user
    */
    public String getNumber()
    {
       String num = JOptionPane.showInputDialog(null, "What number to convert?", 
                "Number", JOptionPane.DEFAULT_OPTION);
       return num;
    }
    /**
     * Method displays a messagebox with calculated results
     * @param numberBefore - number provied by user
     * @param numberAfter - number converted into second number system
     */
    public void printResults(String numberBefore,String numberAfter)
    {
                JOptionPane.showMessageDialog(null, "The number was "+numberBefore + " converted to "+ numberAfter, 
                "Results", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
    * method displays message popup with chosen message
    * if provided more messages, method concatenates them together
    * @param message displays message within messagebox
    */
    public void showMessageBox(String... message)
    {
        if(message.length==1)
                JOptionPane.showMessageDialog(null, message[0], 
                "Listen here", JOptionPane.WARNING_MESSAGE);
        else
        {
            String result="[";
            for(String mes: message)
                result+=mes+", ";
            result=result.substring(0,result.length()-2)+"]";
            JOptionPane.showMessageDialog(null, result, 
            "Concatenated message", JOptionPane.INFORMATION_MESSAGE);
                
        }
    }
}
