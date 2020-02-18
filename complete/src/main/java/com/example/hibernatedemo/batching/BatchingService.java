package com.example.hibernatedemo.batching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohd.waseem on 03/02/20.
 */
@Service
public class BatchingService {

    @Autowired
    private EntityIdentityRepository identityRepository;

    @Autowired
    private EntitySequenceRepository sequenceRepository;

    @Transactional
    public String getBatch(Long col1) {
        EntityIdentity e = identityRepository.findByCol1(col1);
        return e.getCol1();
    }

    @Transactional
    public String saveBatch(String prefix, Integer batchSize, Boolean isIdentity) {
        String response="";
        if( isIdentity) {
            List<EntityIdentity> list = new ArrayList<>();
            for(int i=0;i<batchSize;i++) {
                list.add(new EntityIdentity(prefix+"one"+i, prefix+"two"+i));
            }
            identityRepository.saveAll(list);
            for (EntityIdentity entityIdentity : list) {
                response = response+"\n"+entityIdentity.toString();
            }
        } else {
            List<EntitySequence> list = new ArrayList<>();
            for(int i=0;i<batchSize;i++) {
                list.add(new EntitySequence(prefix+"one"+i, prefix+"two"+i));
            }
            sequenceRepository.saveAll(list);
            for (EntitySequence entitySequence : list) {
                response = response+"\n"+entitySequence.toString();
            }
        }
        return response;
    }
}
