package com.example.spring_boot_default.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class HelloController {

    @GetMapping("/hi")
    public String getHi(Model model) {
        model.addAttribute("msg","Hi~");
        model.addAttribute("age",20);

        List<String> names = Arrays.asList("kim","lee","hong","park");

        Hello hello = new Hello(25);
        model.addAttribute("classHello", hello);

        return "hi";
    }

    @GetMapping("/people")
    public String plople(Model model) {

        List<Person> p1 = new ArrayList<>();
        p1.add(new Person("kim",10));
        p1.add(new Person("lee",20));
        p1.add(new Person("hong",30));
        p1.add(new Person("park",40));
        p1.add(new Person("shin",50));

        model.addAttribute("people", p1);
        return "people";
    }

}

class Hello {
    private int age;

    public Hello(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

}

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}
