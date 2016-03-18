package com.meeting.zhangtao21.meetingmanagement.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/3/17 0017.
 */
public class Validator {
    /**
     * 判断用户输入的时间格式是否正确
     */
    public static boolean checkDateTime(String inputDate) {
        String DATE_TIME_FORMAT = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                DATE_TIME_FORMAT, Locale.CHINA);
        simpleDateFormat.setLenient(false);
        boolean check = false;
        try {
            simpleDateFormat.parse(inputDate);
            check = true;
        } catch (Exception ex) {
            check = false;
            ex.printStackTrace();
        }

        return check;
    }

    /**
     * 此方法判断输入字符是否为数字0-9 是返回true不是返回false
     *
     * @param c char
     * @return boolean
     */
    public static boolean isDigit(char c) {
        return (('0' <= c) && (c <= '9'));
    }

    public static boolean isDigit(String inputStr) {
        char tempChar;
        for (int i = 0; i < inputStr.length(); i++) {
            tempChar = inputStr.charAt(i);
            // 如果字符中有一个字符不是数字则返回false
            if (!isDigit(tempChar)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 此方法判断输入字符是否为字母a-z或A-Z 是返回true不是返回false
     *
     * @param c char
     * @return boolean
     */
    public static boolean isAlpha(char c) {
        return ((('a' <= c) && (c <= 'z')) || (('A' <= c) && (c <= 'Z')));
    }

    public static boolean isAlpha(String inputStr) {
        char tempChar;
        for (int i = 0; i < inputStr.length(); i++) {
            tempChar = inputStr.charAt(i);
            if (!isAlpha(tempChar)) { // 如果字符中有一个字符不是字母则返回false
                return false;
            }
        }

        return true;
    }

    /**
     * 此方法用于检查密码或用户名是否合法，用户名密码只能使用英文字母、数字，并且首字符必须为字母或数字
     *
     * @param inputStr 输入
     * @return boolean
     */
    public static boolean checkUserNamePassword(String inputStr) {
        for (int nIndex = 0; nIndex < inputStr.length(); nIndex++) {
            char cCheck = inputStr.charAt(nIndex);
            if (!(isDigit(cCheck) || isAlpha(cCheck))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 此方法检查email有效性 返回提示信息
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        // 电子邮件
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        boolean isMatched = matcher.matches();

        return isMatched;
    }

    /**
     * 此方法检查输入的身份证号有效性 返回提示信息 如果返回布尔类型则通过验证
     *
     * @param IDNumber 身份证号
     * @return String
     */
    public static boolean checkIDNumber(String IDNumber) {
        boolean result = IDNumber.matches("[0-9]{15}|[0-9]{17}[0-9X]");
        if (result) {
            int year, month, date;
            if (IDNumber.length() == 15) {
                year = Integer.parseInt(IDNumber.substring(6, 8));
                month = Integer.parseInt(IDNumber.substring(8, 10));
                date = Integer.parseInt(IDNumber.substring(10, 12));
            } else {
                year = Integer.parseInt(IDNumber.substring(6, 10));
                month = Integer.parseInt(IDNumber.substring(10, 12));
                date = Integer.parseInt(IDNumber.substring(12, 14));
            }
            switch (month) {
                case 2:
                    result = (date >= 1)
                            && (year % 4 == 0 ? date <= 29 : date <= 28);
                    break;
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    result = (date >= 1) && (date <= 31);
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    result = (date >= 1) && (date <= 31);
                    break;
                default:
                    result = false;
                    break;
            }
        }
        return result;
    }


    /**
     * 判断大陆地区固话及小灵通
     * 区号：010,020,021,022,023,024,025,027,028,029
     *
     * @param tel 电话号码
     * @return 是否合法
     */
    public static boolean isTel(String tel) {
        try {
            String check = "^0(10|2[0-5789]|\\d{3})\\d{7,8}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(tel);

            return matcher.matches();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 验证是否包含中文
     *
     * @param str
     * @return 是否包含中文:含有中文-true，没有中文-false
     */
    public static boolean containChinese(String str) {
        //        String check = "^[\u4e00-\u9fa5]{1,}$";

        String check = "^[\\u4e00-\\u9fa5]+?";

        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(str);

        return matcher.find();
    }

    /**
     * 验证是否包含空格
     *
     * @param str
     * @return 是否包含空格
     */
    public static boolean containBlank(String str) {
        if (str.length() > str.replace(" ", "").length()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证用户名是否只含中英文和数字
     *
     * @param userName 用户名
     * @return 是否合法
     */
    public static boolean isUserName(String userName) {
        String check = "^[\\u4E00-\\u9FA5A-Za-z0-9]+$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(userName);

        return matcher.matches();
    }

    /**
     * ======================================================================
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param strDate 字符串
     * @return
     */
    public boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }

    }

}