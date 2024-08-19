
package com.mycompany.ecommerce.helper;

/**
 *
 * @author rishan
 */
public class Helper {
    public static String get10Words(String description){
        String[] strs = description.split(" ");
        
        if(strs.length>10){
            String res="";
            for (int i = 0; i < 10; i++) {
                res=res+strs[i]+" ";
            }
            return res+"...";
            
        }else{
            return description+"...";
        }
    }
}
