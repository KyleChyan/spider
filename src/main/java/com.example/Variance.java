package com.example;

public class Variance {
    public static double VarianceCheck(double[] x) {
        int m=x.length;
        double sum=0;
        for(int i=0;i<m;i++){//求和
            sum+=x[i];
        }
        double dAve=sum/m;//求平均值
        System.out.println(dAve);
        double dVar=0;
        for(int i=0;i<m;i++){//求方差
            dVar+=(x[i]-dAve)*(x[i]-dAve);
        }
        return dVar/m;
    }
    public static void main(String[] args) {
        double[] all={10000,3000,3000,3000,5000,5000,5000,5000,5000,5000,5000,5000,5000,5000,8000};
        double v = VarianceCheck(all);
        System.out.println(v);

    }
}

