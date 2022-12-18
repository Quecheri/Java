/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.NSC;
import java.util.Arrays;
import model.Model;
import view.View;
import view.Window;

/**
 * Main class of aplication which controls program flow
 * @author Quecheri
 * @version 1.4
 */
public class NSC {
    
 /**
 * Main method of the application.
 *
 * @param args first arg - Provided number
 * If first argument is '?' prints values of roman numbers
 * more arguments will result in error
 */
    public static void main(String[] args) 
    {
    View view=new View();
    Model mod = new Model();
    boolean error=false;
        if(args.length==0)
        {
          
            Window window=new Window(mod,view);
    
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 window.setVisible(true);
            }
        });
           
        }
        else if(args.length==1)
        {
            if(args[0].equals("?")){
                Object[] array = mod.GetMap().entrySet().toArray();
                
                //Heavy and overbuild version with string array
                String[] stringArray = Arrays.stream(array).map(Object::toString).toArray(String[]::new);
                view.showMessageBox(stringArray);
                
                
                //Simplified version with single string
                //view.showMessageBox(Arrays.toString(array));
                
                error=true;
            }
            else mod.setNumber(args[0]); 
        }
        else if(args.length>1)
        {
            view.printErrorMessage("To many arguments");
            error=true;
        }
        if(error==false)
        {
           if(mod.isRomanNumber())
           {
                view.showMessageBox("You have provided roman number");
                mod.changeToArabic();
           }
           else if(mod.isArabicNumber())
           {
               view.showMessageBox("You have provided arabic number");
               try
               {
                    mod.changeToRoman();
               }
               catch (model.NumberOutOfBoundsExeption ex)
               {
                   view.printErrorMessage(ex.getMessage());
                   error=true;
               }
               catch (NumberFormatException ex)
               {
                   view.printErrorMessage("Number wasn't an integer");  
                   error=true;
               }
           }
           else
           {
               view.showMessageBox("This is something beyond me!");
               error=true;
           }
           if (error==false)
           {
           if(mod.getConvertedNumber().equals(""))view.printErrorMessage("Provided number was wrong");               
           else view.showMessageBox("your number was " + mod.getNumber()+" and it was converted to "+mod.getConvertedNumber());
           }
           
        }
        
    }
    
    
}
