/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.util;

/**
 *
 * @author aris
 */
public class Validator {

    public static boolean IsEmpty(String val) {
        if(val == null) return true; 
        if (val.trim().equals("")) {
            return true;
        }
        return false;
    }
    
    public static boolean IsPosInt(String val){
        try{
           Integer.parseInt(val);
        }catch(NumberFormatException nfe){
            return false;
        }
        if(Integer.parseInt(val)<0){
            return false;
        }
        return true;
    }
    
    public static boolean IsPosFloat(String val){
        try{
           Float.parseFloat(val);
        }catch(NumberFormatException nfe){
            return false;
        }
        if(Float.parseFloat(val)<0){
            return false;
        }
        return true;
    }
    
    
}
