package input;

import editor.PositionCalculator;
import editor.PositionCode;
import instance.Trade;
import reader.ReadTrade;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputTrade {

    public static String dateTime(){
        String dateTime;
        while (true){
            System.out.println("取引日を例にならって入力してください(例:2002/03/06)");
            System.out.print("取引日>");
            dateTime = new Scanner(System.in).nextLine();

            String correctFormat = "([0-9]){4}/([0-9]){2}/([0-9]){2}";
            Pattern pattern = Pattern.compile(correctFormat);
            Matcher matcher = pattern.matcher(dateTime);

            if (!matcher.matches()){
                System.out.println("正しいフォーマットで入力してください。");
                continue;
            }

            String[] date = dateTime.split("/");

            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);

            try {
                LocalDateTime now = LocalDateTime.now();
                LocalDate acceptedDate = LocalDate.of(year,month,day);
                LocalDate nowDate = LocalDate.of(now.getYear(),now.getMonth(),now.getDayOfMonth());

                if (acceptedDate.isAfter(nowDate)){
                    System.out.println("未来の日付は入力できません。");
                    continue;
                }

                if (DayOfWeek.SATURDAY.equals(acceptedDate.getDayOfWeek()) || DayOfWeek.SUNDAY.equals(acceptedDate.getDayOfWeek())){
                    System.out.println("平日の取引を入力してください。");
                    continue;
                }
            } catch (DateTimeException e){
                System.out.println("有効な日付を入力してください。");
                continue;
            }

            break;
        }
        return dateTime;
    }

    public static String time(String date){
        String time;
        while (true){
            System.out.println("取引時間を例にならって半角で入力してください。(例:09:09)");
            System.out.print("取引時間>");
            time = new Scanner(System.in).nextLine();

            String correctFormat = "([0-9]){2}:([0-9]){2}";
            Pattern pattern = Pattern.compile(correctFormat);
            Matcher matcher = pattern.matcher(time);

            if (!matcher.matches()){
                System.out.println("正しいフォーマットで入力してください。");
                continue;
            }

            String[] tradeDay = date.split("/");

            int year = Integer.parseInt(tradeDay[0]);
            int month = Integer.parseInt(tradeDay[1]);
            int day = Integer.parseInt(tradeDay[2]);

            String[] tradeTime = time.split(":");
            int hour = Integer.parseInt(tradeTime[0]);
            int minute = Integer.parseInt(tradeTime[1]);

            try{
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime acceptedDate = LocalDateTime.of(year,month,day,hour,minute);
                LocalDateTime nowDate = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(),now.getHour(),now.getMinute());

                if (acceptedDate.isAfter(nowDate)){
                    System.out.println("未来の日付は入力できません。");
                    continue;
                }
                if (hour<9 || hour>14){
                    if (hour==15 && minute==0){
                        break;
                    } else {
                        System.out.println("09:00～15:00の取引を入力してください");
                        continue;
                    }
                }
            } catch (DateTimeException e){
                System.out.println("有効な時間を入力してください。");
                continue;
            }

            break;
        }
        return time;
    }
    public static String code(){
        String code;
        while (true){
            System.out.println("銘柄コードを半角数字で入力してください。");
            System.out.print("銘柄コード>");
            code = new Scanner(System.in).nextLine();

            String correctFormat = "([0-9]){4}";
            Pattern pattern = Pattern.compile(correctFormat);
            Matcher matcher = pattern.matcher(code);

            if (!matcher.matches()){
                System.out.println("正しいフォーマットで入力してください。");
                continue;
            }
            break;
        }
        return code;
    }
    public static String buyOrSell(String code){
        String buyOrSell;
        LinkedHashSet<String> codeList = PositionCode.positionCode();
        List<Integer> quantityList = PositionCalculator.quantity();

        while (true){
            System.out.println("売買区分を番号から選択し、入力してください。");
            System.out.println("1. 売 : 2. 買");
            buyOrSell = new Scanner(System.in).nextLine();
            int i = 0;

            if ("1".equals(buyOrSell) || "１".equals(buyOrSell)){
                if (coincide(code) == null) {
                    System.out.println("この銘柄で取引は行われていません。");
                    buyOrSell = null;
                } else {
                    for (String codes:codeList){
                        if (Objects.equals(code, codes)){
                            if (quantityList.get(i)==0){
                                System.out.println("現在ポジションを保有していません。");
                                buyOrSell = null;
                            } else {
                                buyOrSell = "SELL";
                            }
                        }
                        i++;
                    }
                }
                break;
            } else if ("2".equals(buyOrSell) || "２".equals(buyOrSell)) {
                buyOrSell = "BUY";
                break;
            } else {
                System.out.println("1.2の番号から選択し、入力してください。");
            }
        }
        return buyOrSell;
    }
    public static String quantity(String code,String buyOrSell){
        LinkedHashSet<String> codeList = PositionCode.positionCode();
        List<Integer> quantityList = PositionCalculator.quantity();
        String quantity;
        while (true){
            System.out.println("取引数量を例にならって、100株単位の半角数字で入力してください。(100株→1、1500株→15)");
            System.out.print("取引数量>");
            quantity = new Scanner(System.in).nextLine();

            String correctFormat = "([0-9])+";
            Pattern pattern = Pattern.compile(correctFormat);
            Matcher matcher = pattern.matcher(quantity);

            if (!matcher.matches()){
                System.out.println("正しいフォーマットで入力してください。");
                continue;
            } else if (quantity.length()>6 || "0".equals(quantity)){
                System.out.println("適切な数字を入力してください。");
                continue;
            }
            int i = 0;
            for (String codes:codeList){
                if (code.equals(codes)){
                    if (quantityList.get(i)<Integer.parseInt(quantity) && "SELL".equals(buyOrSell)){
                        System.out.println("保有数量を越えています。");
                        System.out.println("保有数量 : "+quantityList.get(i));
                        quantity = null;
                    }
                }
                i++;
            }
            if (quantity==null){
                continue;
            }
            break;
        }
        return quantity;
    }
    public static BigDecimal price(){
        BigDecimal price;
        String tradePrice;
        while (true){
            System.out.println("取引単価を小数第二位まで、半角数字で入力してください。");
            System.out.print("取引単価>");
            tradePrice = new Scanner(System.in).nextLine();

            String correctFormat = "^[0-9]+\\.([0-9]{2})$";
            Pattern pattern = Pattern.compile(correctFormat);
            Matcher matcher = pattern.matcher(tradePrice);

            if (!matcher.matches()){
                System.out.println("正しいフォーマットで入力してください。");
                continue;
            }
            break;
        }
        price = new BigDecimal(tradePrice);
        return price;
    }
    public static String coincide(String code){
        String coincide = null;
        List<Trade> trades = ReadTrade.fileReader();
        for (Trade codes : trades){
            if (codes.getCode().equals(code)) {
                coincide = "登録済";
                break;
            }
        }
        return coincide;
    }
}
