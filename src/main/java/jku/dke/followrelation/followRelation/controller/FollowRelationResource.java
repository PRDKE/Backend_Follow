package jku.dke.followrelation.followRelation.controller;

import jku.dke.followrelation.followRelation.model.FollowRelationData;
import jku.dke.followrelation.followRelation.service.FollowRelationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/relation")
public class FollowRelationResource {
    private final FollowRelationService followRelationService;

    public FollowRelationResource(FollowRelationService followRelationService) {
        this.followRelationService = followRelationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<FollowRelationData>> getAllFollowRelationData() {
        List<FollowRelationData> allData = this.followRelationService.getAllFollowRelationData();
        return new ResponseEntity<>(allData, HttpStatus.OK);
    }

    @GetMapping("/{username}/follows")
    public ResponseEntity<List<FollowRelationData>> getAllUserFollows(@PathVariable("username") String username) {
        List<FollowRelationData> newList = followRelationService.getAllIFollow(username);
        return new ResponseEntity<>(newList, HttpStatus.OK);
    }

    @GetMapping("/follow/{username}")
    public ResponseEntity<List<FollowRelationData>> getAllFollowUser(@PathVariable("username") String username) {
        List<FollowRelationData> newList = followRelationService.getAllFollowMe(username);
        return new ResponseEntity<>(newList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<FollowRelationData> addFollowRelationData(@RequestBody String username) throws Exception {
        FollowRelationData newFollowRelationData = followRelationService.addFollowRelationData(username);
        return new ResponseEntity<>(newFollowRelationData, HttpStatus.CREATED);
    }

    @PostMapping("/followRelation")
    public ResponseEntity addFollowRelation(@RequestBody CreateFollowRelation followRelation) {
        this.followRelationService.addFollowRelation(followRelation.getFirstUser(), followRelation.getSecondUser());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteFollowRelation")
    public ResponseEntity removeFollowRelation(@RequestBody CreateFollowRelation followRelation) {
        this.followRelationService.removeFollowRelation(followRelation.getFirstUser(), followRelation.getSecondUser());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

class CreateFollowRelation {
    private String firstUser;
    private String secondUser;

    public CreateFollowRelation()  {}

    public String getFirstUser() {
        return firstUser;
    }

    public void setFirstUser(String firstUser) {
        this.firstUser = firstUser;
    }

    public String getSecondUser() {
        return secondUser;
    }

    public void setSecondUser(String secondUser) {
        this.secondUser = secondUser;
    }
}
