package jpabook.jpashopreview;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter @Setter
public class Hello {
    private String data;

//    public static void main(String[] args) {
//        Hello hello = new Hello();
//        hello.setData("hello");
//        String data = hello.getData();
//
//        System.out.println(data);
//    }
}
