package jku.dke.followrelation.followRelation.controller;

import jku.dke.followrelation.followRelation.model.FollowRelationData;
import jku.dke.followrelation.followRelation.service.FollowRelationService;
import jku.dke.followrelation.followRelation.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/follows")
    public ResponseEntity<List<FollowRelationData>> getAllUserFollows(HttpServletRequest request) {
        String jwtToken = request.getHeader("Authorization");
        if(jwtToken == null || (!JwtUtils.isJwtTokenValid(jwtToken))) {
            System.err.println("No authorization-header set or invalid jwtToken provided.");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String username = JwtUtils.getUsernameFromJwtToken(jwtToken);
        List<FollowRelationData> newList = followRelationService.getAllIFollow(username);
        return new ResponseEntity<>(newList, HttpStatus.OK);
    }

    @GetMapping("/follow")
    public ResponseEntity<List<FollowRelationData>> getAllFollowUser(HttpServletRequest request) {
        String jwtToken = request.getHeader("Authorization");
        if(jwtToken == null || (!JwtUtils.isJwtTokenValid(jwtToken))) {
            System.err.println("No authorization-header set or invalid jwtToken provided.");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String username = JwtUtils.getUsernameFromJwtToken(jwtToken);
        List<FollowRelationData> newList = followRelationService.getAllFollowMe(username);
        return new ResponseEntity<>(newList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<FollowRelationData> addFollowRelationData(@RequestBody String username) throws Exception {
        FollowRelationData newFollowRelationData = followRelationService.addFollowRelationData(username);
        return new ResponseEntity<>(newFollowRelationData, HttpStatus.CREATED);
    }

    @PostMapping("/followRelation")
    public ResponseEntity addFollowRelation(HttpServletRequest request, @RequestBody String user) {
        String jwtToken = request.getHeader("Authorization");
        if(jwtToken == null || (!JwtUtils.isJwtTokenValid(jwtToken))) {
            System.err.println("No authorization-header set or invalid jwtToken provided.");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String username = JwtUtils.getUsernameFromJwtToken(jwtToken);
        this.followRelationService.addFollowRelation(username, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteFollowRelation/{user}")
    public ResponseEntity removeFollowRelation(HttpServletRequest request, @PathVariable String user) {
        String jwtToken = request.getHeader("Authorization");
        if(jwtToken == null || (!JwtUtils.isJwtTokenValid(jwtToken))) {
            System.err.println("No authorization-header set or invalid jwtToken provided.");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String username = JwtUtils.getUsernameFromJwtToken(jwtToken);
        this.followRelationService.removeFollowRelation(username, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateUsername")
    public ResponseEntity updateUsername(HttpServletRequest request, @RequestBody String newUsername) {
        String jwtToken = request.getHeader("Authorization");
        if(jwtToken == null || (!JwtUtils.isJwtTokenValid(jwtToken))) {
            System.err.println("No authorization-header set or invalid jwtToken provided.");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String username = JwtUtils.getUsernameFromJwtToken(jwtToken);
        this.followRelationService.updateUsername(username, newUsername);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
