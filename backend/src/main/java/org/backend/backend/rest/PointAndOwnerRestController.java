package org.backend.backend.rest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.backend.backend.model.Owner;
import org.backend.backend.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.backend.backend.service.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class PointAndOwnerRestController {

    @Autowired
    private OwnerService ownerService;

//    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Point> getPoints(@PathVariable("id") Long pointId) {
//        if (pointId == null) {
//            return new ResponseEntity<Point>(HttpStatus.BAD_REQUEST);
//        }
//        Point point = pointsService.getById(pointId);
//        if (point == null) {
//            return new ResponseEntity<Point>(HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<Point>(point, HttpStatus.OK);
//        }
//    }
//
//    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Point> savePoint(@RequestBody @Validated Point point,
//                                           @RequestBody @Validated Owner owner){
//        if (point == null || owner == null){
//            return new ResponseEntity<Point>(HttpStatus.BAD_REQUEST);
//        }
//        pointsService.save(point, owner);
//        return new ResponseEntity<Point>(point, HttpStatus.OK);
//    }

    @RequestMapping(value= "owner", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Owner> addUser(@RequestBody @Validated Owner owner){
        if (owner == null) return new ResponseEntity<Owner>(HttpStatus.BAD_REQUEST);
        if (ownerService.addNewOwner(owner))
            return new ResponseEntity<Owner>(owner, HttpStatus.OK);
        else
            return new ResponseEntity<Owner>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "hello")
    public String sayHello(@RequestParam(value = "name", defaultValue = "World") String name){
        return "Hello " + name;
    }
    @PostMapping("login")
    public ResponseEntity<Owner> login(@RequestBody Owner owner){
        HttpHeaders headers = new HttpHeaders();
        if (ownerService.existsOwnerByNameAndPassword(owner.getUsername(), owner.getPassword())){
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
