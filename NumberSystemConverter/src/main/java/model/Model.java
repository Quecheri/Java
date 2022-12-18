/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.*;

/**
 *
 * @author Quecheri
 * @version 1.4
 */
public class Model {
    
    /**
     * Constructor Prepares values for roman numbers in hashmap
     */
    public Model()
    {
        setUpMap();    
    }
   /**
   * cotains values of roman numbers
   */
   private Map<Character,Integer> romanIntMap= new HashMap<>();
    /**
     * cotains number in string
    */
   private String number;
   /**
    * cotains number converted to second number system in string
    */
   private String convertedNumber;
    /**
     * method returns value map
     * @return Map &lt;Character,Integer&gt;
     */
    public Map<Character,Integer> GetMap()
    {
        return romanIntMap;
    }
   /**
    * methods sets number to convert
    * @param num - new number
    */
    public void setNumber(String num)
    {
        number=num;
    }
    /**
     * method sets converted number
     * @param num - new converted number
     */
    public void setConvertedNumber(String num)
    {
        convertedNumber=num;
    }
    /**
     * method returns converted number
     * @return converted number
     */
    public String getConvertedNumber()
    {
        return convertedNumber;
    }
    /**
     * method returns pre converted number
     * @return pre converted number
     */
    public String getNumber()
    {
        return number;
    }
    /**
     * method checks whether provided string is a decent arabic number 
     * @return true if string is a arabic number
     */
    public boolean isArabicNumber()
    {

        if(number.matches("[1-9,-][\\d]*$"))
        {
            return true;
        }
        return false;
    }
    /**
     * method checks whether provided string is a decent roman number 
     * @return true if string is a roman number
     */
    public boolean isRomanNumber()
    {
        if(number.matches("M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$"))
            return true;
        return false;
    }
    /**
     * method try to convert arabic number into roman
     * @throws NumberOutOfBoundsExeption 
     * Operates only on proper arabic numbers 
     * otherwise result is undefined
     * to check if number is proper arabic syntax use
     * isArabicNumber method
     */
    public void changeToRoman() throws NumberOutOfBoundsExeption
    {
        int oldNumber=Integer.parseInt(number);
        if(checkBoundries(oldNumber)==false)
           throw new NumberOutOfBoundsExeption("Numbers are not in range of roman number system!");
        else
         convertedNumber=convertIntToRoman(oldNumber);
    }
    /**
     * method try to convert roman number into arabic
     * Operates only on proper roman numbers 
     * otherwise result is undefined
     * to check if number is proper roman syntax use
     * isRomanNumber method
     */
    public void changeToArabic()
    {
        convertedNumber=convertRomanToInt(number);
    }
    /**
     * method converts roman number into arabic using hashmap
     * @param romanNumber to convert
     * @return string - converted arabic number
     */
     private String convertRomanToInt(String romanNumber)
     {
        
        int result=0;
         for (int i=0;i<romanNumber.length();i++)
         {
             if(i>0 && romanIntMap.get(romanNumber.charAt(i))>romanIntMap.get(romanNumber.charAt(i-1)))
             {
                 result+=romanIntMap.get(romanNumber.charAt(i))- 2*romanIntMap.get(romanNumber.charAt(i-1));
             }
             else
                 result+=romanIntMap.get(romanNumber.charAt(i));
             
         }
         return Integer.toString(result);
     }
    /**
     * method checks whether provided number is within Roman number space
     * @param numberToConvert 
     * @return true if number is between 1 and 3999
     */
    private boolean checkBoundries(int numberToConvert)
    {
        if(numberToConvert>=4000)return false;
        else if(numberToConvert<=0)return false;
        else return true;
    }
    /**
     * method converts arabic number into roman
     * @param numberToConvert - arabic number to convert
     * @return string - new converter roman number
     */
    private String convertIntToRoman(int numberToConvert)
    {
        String resultNumber="";
        while(numberToConvert>0)
        {
            if(numberToConvert>=1000)
            {
                numberToConvert-=1000;
                resultNumber+="M";
            }
            else if(numberToConvert<1000&&numberToConvert>=900)
            {
                numberToConvert-=900;
                resultNumber+="CM";
            }
            else if(numberToConvert<900&&numberToConvert>=500)
            {
                numberToConvert-=500;
                resultNumber+="D";
            }
            else if(numberToConvert<500&&numberToConvert>=400)
            {
                numberToConvert-=400;
                resultNumber+="CD";
            }
            else if(numberToConvert<400&&numberToConvert>=100)
            {
                numberToConvert-=100;
                resultNumber+="C";
            }
            else if(numberToConvert<100&&numberToConvert>=90)
            {
                numberToConvert-=90;
                resultNumber+="XC";
            }
            else if(numberToConvert<90&&numberToConvert>=50)
            {
                numberToConvert-=50;
                resultNumber+="L";
            }
            else if(numberToConvert<50&&numberToConvert>=40)
            {
                numberToConvert-=40;
                resultNumber+="XL";
            }
            else if(numberToConvert<40&&numberToConvert>=10)
            {
                numberToConvert-=10;
                resultNumber+="X";
            }
            else if(numberToConvert==9)
            {
                numberToConvert-=9;
                resultNumber+="IX";
            }
            else if(numberToConvert<9&&numberToConvert>=5)
            {
                numberToConvert-=5;
                resultNumber+="V";
            }
            else if(numberToConvert==4)
            {
                numberToConvert-=4;
                resultNumber+="IV";
            }
            else if(numberToConvert==3)
            {
                numberToConvert-=3;
                resultNumber+="III";
            }
            else if(numberToConvert==2)
            {
                numberToConvert-=2;
                resultNumber+="II";
            }
            else if(numberToConvert==1)
            {
                numberToConvert-=1;
                resultNumber+="I";
            }
        }
        return resultNumber;
    }
    /**
     * method inicjalize hashmap for further use
     */
    private void setUpMap() {
     
        romanIntMap.put('M',1000);
        romanIntMap.put('D',500);
        romanIntMap.put('C',100);
        romanIntMap.put('L',50);
        romanIntMap.put('V',5);
        romanIntMap.put('I',1);
        romanIntMap.put('X',10);  
    }
}
