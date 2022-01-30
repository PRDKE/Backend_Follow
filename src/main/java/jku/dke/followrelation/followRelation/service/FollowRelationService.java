package jku.dke.followrelation.followRelation.service;

import jku.dke.followrelation.followRelation.model.FollowRelationData;
import jku.dke.followrelation.followRelation.repository.FollowRelationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowRelationService {
    private final FollowRelationRepository followRelationRepository;

    public FollowRelationService(FollowRelationRepository followRelationRepository) {
        this.followRelationRepository = followRelationRepository;
    }

    // search for followRelationData by a username
    // this method is only for test purposes
    public FollowRelationData getFollowRelationDataByUsername(String username) {
        return this.followRelationRepository.getFollowRelationDataByUsername(username);
    }

    // adds a new followRelationData
    // throws exception if the given username already exists
    public FollowRelationData addFollowRelationData(String username) throws Exception {
        FollowRelationData newData = followRelationRepository.getFollowRelationDataByUsername(username);
        if (newData != null) {
            throw new Exception("User already exists!");
        }
        return this.followRelationRepository.addFollowRelationData(username);
    }

    // retrieves all the followRelationData objects that the given user follows
    public List<FollowRelationData> getAllIFollow(String username) {
        return this.followRelationRepository.getAllIFollow(username);
    }

    // retrieves all the followRelationData objects that follow the given user
    public List<FollowRelationData> getAllFollowMe(String username) {
        return this.followRelationRepository.getAllFollowMe(username);
    }

    // creates a new follow relation between two users
    public void addFollowRelation(String firstUser, String secondUser){
        this.followRelationRepository.addFollowRelation(firstUser, secondUser);
    }

    // removes a follow relation between two users
    public void removeFollowRelation(String firstUser, String secondUser) {
        this.followRelationRepository.removeFollowRelation(firstUser, secondUser);
    }

    // retrieves all followRelationData objects
    public List<FollowRelationData> getAllFollowRelationData() {
        return this.followRelationRepository.getAllFollowRelationData();
    }

    // updates the username of a user with the 'currentUsername' to 'newUsername'
    public void updateUsername(String currentUsername, String newUsername) {
        this.followRelationRepository.updateUsername(currentUsername, newUsername);
    }
}
