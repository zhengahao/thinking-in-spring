package com.example.thinking.test.algorithm;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Author：zzh
 * @Description: 黄金分割计算
 * @Date: 2021/3/15 2:53 下午
 */
public class TradeCalculation {

    public static void main(String[] args) {

        caculateGoldenSection(Variety.GOlD_TD, 358, 364);
        caculateGoldenSection(Variety.AG_TD, 5396, 5220);
        caculateGoldenSection(Variety.XAGUUSD, 25, 25.257);
        caculateGoldenSection(Variety.XAUUSD, 1682, 1715);
        caculateGoldenSection(Variety.XAUUSD, 1763, 1790);

        caculateGoldenSection(Variety.USDJPY, 108.76, 109.37);
        caculateGoldenSection(Variety.CIOIL, 57.2, 67.2);
        caculateGoldenSection(Variety.GBPUSD, 1.42, 67.2);

        //caculateAbEqualsCd(57.2, 61.3, 57.4);

    }

    private static void caculateAbEqualsCd(double a, double b, double c) {

        BigDecimal a1 = new BigDecimal(a);
        BigDecimal b1 = new BigDecimal(b);
        BigDecimal c1 = new BigDecimal(c);

        BigDecimal d1 = b1.subtract(a1).add(c1).setScale(3, RoundingMode.HALF_UP);
        System.out.println("a+b-c=" + d1);

    }

    private static void caculateGoldenSection(Variety variety, double low1, double high1) {
        System.out.println("=======品种：" + variety.name() + "==========");
        System.out.println("最低价：" + low1 + "，最高价：" + high1);
        BigDecimal low = new BigDecimal(low1);
        BigDecimal hight = new BigDecimal(high1);

        BigDecimal num882 = hight.subtract((hight.subtract(low)).multiply(new BigDecimal(0.882))).setScale(2, RoundingMode.HALF_DOWN);
        BigDecimal num618 = hight.subtract((hight.subtract(low)).multiply(new BigDecimal(0.618))).setScale(2, RoundingMode.HALF_DOWN);
        BigDecimal num50 = hight.subtract((hight.subtract(low)).multiply(new BigDecimal(0.5))).setScale(2, RoundingMode.HALF_DOWN);
        BigDecimal num382 = hight.subtract((hight.subtract(low)).multiply(new BigDecimal(0.382))).setScale(2, RoundingMode.HALF_DOWN);

        System.out.println("882位置: " + num882);
        System.out.println("618位置: " + num618);
        System.out.println("50位置: " + num50);
        System.out.println("382位置: " + num382);
    }
}
