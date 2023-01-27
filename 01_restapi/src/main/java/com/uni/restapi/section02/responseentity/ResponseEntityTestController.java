package com.uni.restapi.section02.responseentity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entity")
public class ResponseEntityTestController {
    /* ResponseEntity란?
     * 결과 데이터와 http 상태 코드를 직접 제어할 수 있는 클래스이다.
     * HttpStatus, HttpHeaders, HttpBody를 포함한다.
     *
     * https://restfulapi.net/resource-naming/  네이밍참고 RESTful API를 설계할 때 복수형으로 설계하도록 권장
     *  /api/v1/뭐할지    v1은 버전
     * restapi 설계를?
     * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html
     * */
    private List<UserDTO> users;

    public ResponseEntityTestController() {
        users = new ArrayList<>();

        users.add(new UserDTO(1, "user01", "pass01", "홍길동", new java.util.Date()));
        users.add(new UserDTO(2, "user02", "pass02", "유관순", new java.util.Date()));
        users.add(new UserDTO(3, "user03", "pass03", "이순신", new java.util.Date()));
    }
    @GetMapping("/users")
    public ResponseEntity<ResponseMessage> findAllUsers() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        //dto형식으로 받아? stream.filter
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users", users);
        //user 하나임 user로 바꿔 필터 내용을 users 이 자리에 put
        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공!", responseMap);
        //builder 형태로
        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }
    @GetMapping("/users/{userNo}")
    public ResponseEntity<ResponseMessage> findUserByNumber(@PathVariable int userNo) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        //dto형식으로 받아? stream.filter
        UserDTO user = users.stream().filter(h->h.getNo()==userNo).collect(Collectors.toList()).get(0);
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("user", user);
        //user 하나임 user로 바꿔 필터 내용을 users 이 자리에 put
        ResponseMessage responseMessage = new ResponseMessage(200, "조회 성공!", responseMap);
        //builder 형태로
//        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
        return ResponseEntity.ok() //200대신 ok
                .headers(headers).body(responseMessage);
    }

    @PostMapping("/users")
    public ResponseEntity<?> registUser(@RequestBody UserDTO newUser) throws URISyntaxException {

        System.out.println(newUser);

        int lastUserNo = users.get(users.size() - 1).getNo();
        newUser.setNo(lastUserNo + 1);
        newUser.setEnrollDate(new Date());
        users.add(newUser);
        System.out.println("newUser" + newUser);


        return ResponseEntity
                .created(URI.create("/entity/users/" + users.get(users.size() - 1).getNo()))
                .build();
    }
    @PutMapping("/users/{userNo}")
    public ResponseEntity<?> modifyUser(@RequestBody UserDTO modifyInfo, @PathVariable int userNo) throws URISyntaxException {

        System.out.println(modifyInfo);
        System.out.println(userNo);
//      userNo와 일치하는 user 찾아서 정보 set
        UserDTO found = users.stream().filter(h->h.getNo()==userNo).collect(Collectors.toList()).get(0);
        found.setId(modifyInfo.getId());
        found.setPwd(modifyInfo.getPwd());
        found.setName(modifyInfo.getName());
//        modifyInfo.setNo(userNo);
//        modifyInfo.setEnrollDate(new Date());

        System.out.println("found" + found);


        return ResponseEntity
                .created(URI.create("/entity/users/" + userNo))
                .build();
    }
    @DeleteMapping("/users/{userNo}")
    public ResponseEntity<?> removeUser(@PathVariable int userNo) {

        UserDTO foundUser = users.stream().filter(user -> user.getNo() == userNo).collect(Collectors.toList()).get(0);
        users.remove(foundUser);

        return ResponseEntity
                .noContent() //204 body가 아예존재하지 않을 때   null쩌구와는 다름
                .build(); //없는데 또지우면 500
    }
}


