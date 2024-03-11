package com.codenetworkz.productservice.utility;

public class StringUtility {

    /**
     * @param name
     * @return
     *
     */
   public static boolean isNotEmptyString(String name){
        Boolean flag = Boolean.TRUE;
        if(name==null  || name.isEmpty() ) {
            flag=Boolean.FALSE;
        }
        return flag;
    }

}
