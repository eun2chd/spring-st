package com.example.spring_boot_default.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RestController {

    @GetMapping("/get/res1")
    public String getRes1(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "age") int age,
            Model model) {
        // @RequestParam 어노테이션
        // - String query 중에서 name key에 대한 value를 String name에 매핑 (?key=value)
        // - required=true가 기본값으로 요청 URL에서 name key를 필수로 보냄.
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "req"; // res1.jsp 또는 res1.html로 연결
    }

    @GetMapping("/get/res2")
    public String getRes2(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "age") int age,
            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "req"; // res2.jsp 또는 res2.html로 연결
    }

    @GetMapping("/get/res3/{param1}/{param2}")
    public String getRes3(
            @PathVariable("param1") String param1,
            @PathVariable("param2") int age,
            Model model) {
        // @PathVariable 어노테이션
        // - URL 경로 변수 {param1}과 {param2}를 매개변수로 매핑
        model.addAttribute("name", param1);
        model.addAttribute("age", age);
        return "req"; // res3.jsp 또는 res3.html로 연결
    }

    // 선택적으로 받아오는 PathVariable 있다면 경로를 여러 개 설정해야함.
    @GetMapping({"/get/res4/{name}/{age}","get/res4/{name}/{age}"})
    public String getRes4(
            @PathVariable String name,
            @PathVariable(required = false) Integer age,
            Model model) {
        // @PathVariable 어노테이션

        // - URL 경로 변수 {param1}과 {param2}를 매개변수로 매핑
        // required = faslse 옵션
        // - name (필수), age(선택)
        // - option 한 paramter 가 있다면 맨 뒤에 오도록 설정
        // 참고. Integer age 라고 지정한 이유
        // - age 는 optional 한 값 즉, null 이 될 수도 있기 때문에 primitive type 이 아닌 reference type 인 래퍼 객체 사용
        model.addAttribute("name", name);
        model.addAttribute("age", age);

        return "req"; // res3.jsp 또는 res3.html로 연결
    }

    @GetMapping("/introduce/{name}")
    public String introduce(@PathVariable("name") String name, Model model) {
        model.addAttribute("name", name);
        return "introduce";  // "introduce.html" 템플릿을 반환
    }

    @GetMapping("/introduce2")
    public String introduce2(@RequestParam("name") String name, @RequestParam("age") int age, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "introduce2";  // "introduce.html" 템플릿을 반환
    }

//    Post 요청
// - Post 로 전달할 때 controller 에서 받는 방법은 @RequestParam
    @PostMapping("/post/res1")
    public String postRes1(@RequestParam String name, @RequestParam int age, Model model) {
        model.addAttribute("name",name);
        model.addAttribute("name",age);

        return "res";
    }

    @PostMapping("/post/res2")
    public String postRes2(@RequestParam String name, @RequestParam(required = false) Integer age, Model model) {
        model.addAttribute("name",name);
        model.addAttribute("name",age);

        return "res";
    }

    // 여기까지는 return 이 항상 Template View 만약에 API 에서 데이터 자체를 응답하고 싶다면
    // => @RequestBody
    @PostMapping("/post/res3")
    @ResponseBody
    public String postRes3(@RequestParam String name, @RequestParam int age, Model model) {
        model.addAttribute("name",name);
        model.addAttribute("name",age);

        return "res";
    }

    @PostMapping("/post/res4")
    // response 쓰면 페이지 랜더딩 안됨 -> json 데이터 받을때만
    public String postRes4(@RequestParam("name") String name,
                           @RequestParam("gender") String gender,
                           @RequestParam("birthdate") String birthdate,
                           @RequestParam("interest") List<String> interest,
                           Model model) {

        model.addAttribute("name",name);
        model.addAttribute("gender",gender);
        model.addAttribute("birthdate",birthdate);
        model.addAttribute("interest", interest);
        return "signup";
    }
}
