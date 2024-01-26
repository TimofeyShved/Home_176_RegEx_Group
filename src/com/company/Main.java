package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
	    // Работа с группами в RegEx

        Pattern p1 = Pattern.compile("Set(Value)?"); // можно повторно обратиться к группе по номеру
        // А здесь можно отключить ссылку, то есть без повтора
        //Pattern p1 = Pattern.compile("Set(?:Value)?");
        Matcher m1 = p1.matcher("SetValue");
        while (m1.find()){
            System.out.println(m1.start() +" "+ m1.group()+" ");
        }

        // Выбор из набора выражений
        Pattern p2 = Pattern.compile("Edit (Lite|Pro)");
        Matcher m2 = p2.matcher("Edit Pro version");
        while (m2.find()){
            System.out.println(m2.start() +" "+ m2.group()+" ");
        }

        // замена строки
        System.out.println("Edit Lite".replaceAll("Edit (Lite|Pro)", "$0 version"));
        System.out.println("Edit Lite".replaceAll("Edit (Lite|Pro)", "$1 version"));

        // найти все теги
        // сначало он найдет <, потом сам тег, и в конечном итоге закончит на >
        // \\1 равна ([A-Z][A-Z0-9]*)
        Pattern p3 = Pattern.compile("<([A-Z][A-Z0-9]*)[^>]*>.*?</\\1>");
        Matcher m3 = p3.matcher("This is a <EM> first</EM> test");
        // Если "This is a <EM><B> first </B></EM> test", то найдет <EM><B> first </B></EM>
        while (m3.find()){
            System.out.println(m3.start() +" "+ m3.group()+" ");
        }

        // позволяет обращатся уже к вызванной группе
        // в данном случае если он найдет "а", то выражение будет выглядеть как [a-c]xаxа
        Pattern p4 = Pattern.compile("([a-c])x\\1x\\1");
        Matcher m4 = p4.matcher("axaxa axbxa bxbxb");
        while (m4.find()){
            System.out.println(m4.start() +" "+ m4.group()+" ");
        }

        // При вызове \\1 в первом случее возьмёт слово, а во втором букву
        Pattern p5 = Pattern.compile("([a-c]+)"); //cab
        //Pattern p5 = Pattern.compile("([a-c])+"); //b
        Matcher m5 = p5.matcher("cab");
        while (m5.find()){
            System.out.println(m5.start() +" "+ m5.group()+" ");
        }

        // убрать повторы
        System.out.println("the the the".replaceAll("\\b(\\w+)\\s+\\1\\b", "$1"));
    }
}
