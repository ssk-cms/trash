package com.trash.collection.trash.product.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ManageApplication.class)
class DonationGoodsServiceTest {

    @Test
    void getDonationGoodsList() {
        // 如果不返回false，将返回的字符串放入数组
        HashSet set = new HashSet();
        while(set.size()<100){
            HashSet val = getSubject();
            if(!(val == null)){
                for(Object i : val) {
                    set.add(i);
                }
            }
        }
        set.forEach(System.out::println);
    }

    public static HashSet getSubject() {
        HashSet s = new HashSet();
        Random random = new Random();
        // 生成a1、a2两个整数
        int a1 = random.nextInt(20);
        int a4 = random.nextInt(20);
        int result = a4 - a1;
        // 生成a2,不论正负
        int a2 = random.nextInt(20);
        int a3;
        if (Math.abs(result-a2)<21) {
            a3 = result-a2;
            //生成第一个算术题
            String sub = "()";

            if(a2>=0){
                sub += "+";
            }
            sub += String.valueOf(a2);
            if(a3>=0){
                sub += "+";
            }
            sub += String.valueOf(a3);

            sub += "=";
            sub += String.valueOf(a4);
            s.add(sub);

            //生成第二个算术题
            sub = String.valueOf(a1);
            if(a2>=0){
                sub += "+";
            }
            sub += String.valueOf(a2);
            if(a3>=0){
                sub += "+";
            }
            sub += String.valueOf(a3);
            sub += "=()";
            s.add(sub);

            // 生成第三个算术题
            sub = String.valueOf(a1);
            if(a2>=0){
                sub += "+()";
            }else{
                sub += "-()";
            }
            if(a3>=0){
                sub += "+";
            }
            sub += String.valueOf(a3);
            sub += "=";
            sub += String.valueOf(a4);
            s.add(sub);

            // 生成第四个算术题
            sub = String.valueOf(a1);
            if(a2>=0){
                sub += "+";
            }
            sub += String.valueOf(a2);
            if(a3>=0){
                sub += "+()";
            }else{
                sub += "-()";
            }
            sub += "=";
            sub += String.valueOf(a4);
            s.add(sub);
            return s;

        } else if(Math.abs(result+a2)<21) {
            a3 = result+a2;
            a2 = -a2;

            //生成第一个算术题
            String sub = "()";

            if(a2>=0){
                sub += "+";
            }
            sub += String.valueOf(a2);
            if(a3>=0){
                sub += "+";
            }
            sub += String.valueOf(a3);

            sub += "=";
            sub += String.valueOf(a4);
            s.add(sub);

            //生成第二个算术题
            sub = String.valueOf(a1);
            if(a2>=0){
                sub += "+";
            }
            sub += String.valueOf(a2);
            if(a3>=0){
                sub += "+";
            }
            sub += String.valueOf(a3);
            sub += "=()";
            s.add(sub);

            // 生成第三个算术题
            sub = String.valueOf(a1);
            if(a2>=0){
                sub += "+()";
            }else{
                sub += "-()";
            }
            if(a3>=0){
                sub += "+";
            }
            sub += String.valueOf(a3);
            sub += "=";
            sub += String.valueOf(a4);
            s.add(sub);

            // 生成第四个算术题
            sub = String.valueOf(a1);
            if(a2>=0){
                sub += "+";
            }
            sub += String.valueOf(a2);
            if(a3>=0){
                sub += "+()";
            }else{
                sub += "-()";
            }
            sub += "=";
            sub += String.valueOf(a4);
            s.add(sub);
            return s;
        } else {
            return null;
        }
    }

    @Test
    void set(){
        //三个数加减法
        //20以内；a1,a2,a3,result<20;a1+a2<20;a1+a3<20;a1+result<20;a2+a3<20;a3+result<20;a3+result<20

    }
}