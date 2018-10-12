package com.FittingRoomServer.FittingRoomServer.Utils;

public class EpcConvertToPlu {
    //10 to 2
    public static String hexStringToByte(String hex) {
        int i = Integer.parseInt(hex, 16);
        String str2 = Integer.toBinaryString(i);
        return str2;
    }

    //2 to 10
    public static int ByteToDecimal(String bytes) {
        return Integer.valueOf(bytes, 2);
    }

    public static String epcConvertToPlu(String epc){

        //取得每一个字符
        char[] c = epc.toCharArray();
        String finalByte = "";
        for(int i=0;i<c.length;i++){
            //16进制转2进制
            String byteString = hexStringToByte(String.valueOf(c[i]));
            //补齐4位0
            String temp = "";
            for(int j=0;j<4-byteString.length();j++){
                temp = "0"+temp;
            }
            temp = temp + byteString;
            System.out.println(temp);
            finalByte = finalByte + temp;
        }
        System.out.println(finalByte.substring(14,38));
        System.out.println(finalByte.substring(38,58));
        //2进制转10进制
        System.out.println(ByteToDecimal(finalByte.substring(14,38)));
        System.out.println(ByteToDecimal(finalByte.substring(38,58)));
        int digits24 = ByteToDecimal(finalByte.substring(14,38));
        int digits20 = ByteToDecimal(finalByte.substring(38,58));
        String digits24OfString = String.valueOf(digits24);
        String digits20OfString ;
        if(String.valueOf(digits20).length()<5){
            String temp = "";
            for(int i=0;i<(5-String.valueOf(digits20).length());i++){
                temp = "0"+temp;
            }
            temp = temp + String.valueOf(digits20);
            digits20OfString = temp;
        }else {
            digits20OfString = String.valueOf(digits20);
        }
        System.out.println(digits24OfString);
        System.out.println(digits20OfString);
        String combineString = digits24OfString+digits20OfString;
        System.out.println(combineString);
        //奇数位数字全部相加，偶数位数字乘以3相加，奇数取6个，偶数取6个，多余的舍去，最后的和取个位数字，然后10减去个位数字再和combineString相拼就是最后得到的plu
        int count = 0;
        for(int i = 0;i<12;i++){
            if(i%2==0){
                count = count + Character.getNumericValue(combineString.charAt(i));;
            }else {
                count = count + Character.getNumericValue(combineString.charAt(i))*3;
            }
            System.out.println(Character.getNumericValue(combineString.charAt(i)));
        }
        System.out.println(combineString+String.valueOf(10-count%10));
        String result = combineString+String.valueOf(10-count%10);
        return result;
    }
}
