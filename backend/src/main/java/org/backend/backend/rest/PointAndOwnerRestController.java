package org.backend.backend.rest;

import org.backend.backend.model.Owner;
import org.backend.backend.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.backend.backend.service.*;


@RestController
@RequestMapping("/api/points")
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
        ownerService.addNewOwner(owner);
        return new ResponseEntity<Owner>(owner, HttpStatus.OK);
    }
}
