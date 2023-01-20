/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import org.apache.commons.lang3.tuple.Pair;

/**
 *
 * @author Quecheri
 * @version 1.6
 */
public class Model {
    
    /**
     * Constructor Prepares values for roman numbers in hashmap
     */
    public static boolean flag = false; 
    private static Model Instance;
    private int count = 1;
    private Connection con=null;
       
   
    private Model()
    {
        setUpMap();
        setUpConnection();
        CurrentRepetition=0;
        createTable();
        count=setIndex();
        //insertData("XX","20");
    }
    public static Model getInstance()
    {
        if(Instance==null)
        {
            Instance=new Model();
            return Instance;
        }
        return Instance;
    }
    
    
   /**
   * cotains values of roman numbers
   */
   private Map<Character,Integer> romanIntMap= new HashMap<>();
   
    /**
    * cotains number of calculations in current serwer session
    */
   private int CurrentRepetition;
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
    * methods sets CurrentRepetition number
    * @param num - new number
    */
    public void setCurrentRepetition(int num)
    {
        CurrentRepetition=num;
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
        CurrentRepetition++;
    }
     /**
     * method returns Current repetition number
     * @return int CurrentRepetition
     */
    public int getCurrentRepetition()
    {
        return CurrentRepetition;
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
        if(number.length()>0)
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
        {
            convertedNumber=convertIntToRoman(oldNumber);
            insertData(convertedNumber,number);
        }
         
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
        insertData(number,convertedNumber);
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
     /**
     * method inicjalize connection with database
     */
    private void setUpConnection()
    {
        try {
            // loading the JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            return;
        }
        //connectiong to DB
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/converterTab", "app", "app");        
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
     /**
     * Method Creates Single table
     */
    private void createTable() {

        
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE TABLE Numbers "
                    + "(id INTEGER , Roman VARCHAR(50), "
                    + "Arabic VARCHAR(50) )");        
            
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
     /**
      * Method generetes query and fetch highest index
      * @return highest index
      */
    private int setIndex()
    {
        int res=0;
        try  {
            Statement statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery("SELECT max(id) AS ile FROM Numbers");
            // Przeglądamy otrzymane wyniki
            
            while (rs.next()) {
               res = rs.getInt("ile");
            }
            
            rs.close();
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            return 0;
        }
        
        return res+1;
    }
      /**
       * Method inserts into database provided values
       * @param Roman - roman number String
       * @param Arabic - arabic number String
       */
    private void insertData(String Roman, String Arabic) {
        
        
       
        try{
            Statement statement = con.createStatement();
            
            statement.executeUpdate("INSERT INTO Numbers VALUES("+count+", '"+Roman+"', '"+Arabic+"')");
            count++;
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
    
    
    /**
     * Method fetch whole database and put data into list of pairs
     * @return List of pairs of numbers: Roman, Arabic 
     */
    public List<Pair<String, String>> LoadTable() {
        List<Pair<String, String>> list = new ArrayList<>();

        try  {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Numbers");
            // Przeglądamy otrzymane wyniki
            
            while (rs.next()) {
                list.add(Pair.of(rs.getString("Roman"), rs.getString("Arabic")));
            }
            
            rs.close();
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            return list;
        }
        return list;
    }
}
