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

    public FollowRelationData getFollowRelationDataByUsername(String username) {
        return this.followRelationRepository.getFollowRelationDataByUsername(username);
    }

    public FollowRelationData addFollowRelationData(String username) throws Exception {
        FollowRelationData newData = followRelationRepository.getFollowRelationDataByUsername(username);
        if (newData != null) {
            throw new Exception("User already exists!");
        }
        return this.followRelationRepository.addFollowRelationData(username);
    }

    public List<FollowRelationData> getAllIFollow(String username) {
        return this.followRelationRepository.getAllIFollow(username);
    }

    public List<FollowRelationData> getAllFollowMe(String username) {
        return this.followRelationRepository.getAllFollowMe(username);
    }

    public void addFollowRelation(String firstUser, String secondUser){
        this.followRelationRepository.addFollowRelation(firstUser, secondUser);
    }

    public void removeFollowRelation(String firstUser, String secondUser) {
        this.followRelationRepository.removeFollowRelation(firstUser, secondUser);
    }

    public List<FollowRelationData> getAllFollowRelationData() {
        return this.followRelationRepository.getAllFollowRelationData();
    }
}
