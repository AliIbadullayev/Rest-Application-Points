package org.backend.backend.rest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.backend.backend.exception.PointValidationException;
import org.backend.backend.model.Owner;
import org.backend.backend.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.backend.backend.service.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/rest")
public class PointAndOwnerRestController {

    @Autowired
    private EntitiesService entitiesService;

    @RequestMapping(value = "getpoints", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Point>> getPoints() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Point> points = entitiesService.getAllPoints(username);
        if (points.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(points, HttpStatus.OK);
    }
//
    @RequestMapping(value = "addpoint", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Point> savePoint(@RequestBody @Validated Point point){
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            if (point == null || username == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            entitiesService.savePoint(point, username);
        } catch (PointValidationException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(point, HttpStatus.OK);
    }

    @RequestMapping(value= "register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Owner> addUser(@RequestBody @Validated Owner owner){
        if (owner == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (entitiesService.addNewOwner(owner))
            return new ResponseEntity<>(owner, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "hello")
    public String sayHello(@RequestParam(value = "name", defaultValue = "World") String name){
        return "Hello " + name;
    }

    @PostMapping("login")
    public ResponseEntity<Owner> login(@RequestBody Owner owner){
        HttpHeaders headers = new HttpHeaders();
        if (entitiesService.existsOwnerByNameAndPassword(owner.getUsername(), owner.getPassword())){
            String token = getJWTToken(owner.getUsername());
            headers.add(HttpHeaders.AUTHORIZATION, token);
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private String getJWTToken(String username) {
        String secret = "MySecretKey";
             String token = Jwts
                .builder()
                .setId("Example Jwt App")
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 6000000))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes(StandardCharsets.UTF_8))
                .compact();
        return "Bearer " + token;
    }
}
