package com.example.hibernatedemo.batching;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by mohd.waseem on 03/02/20.
 */
@Service
public class BatchingService {

    private final EntityIdentityRepository identityRepository;
    private final EntitySequenceRepository sequenceRepository;

    public BatchingService(EntityIdentityRepository identityRepository, EntitySequenceRepository sequenceRepository) {
        this.identityRepository = identityRepository;
        this.sequenceRepository = sequenceRepository;
    }
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
            IntStream.range(0, batchSize).forEach( i-> list.add(new EntityIdentity(prefix+"one"+i, prefix+"two"+i)));
            identityRepository.saveAll(list);
            response = list.stream().map(EntityIdentity::toString).collect(Collectors.joining("\n"));
        } else {
            List<EntitySequence> list = new ArrayList<>();
            IntStream.range(0, batchSize).forEach( i-> list.add(new EntitySequence(prefix+"one"+i, prefix+"two"+i)));
            sequenceRepository.saveAll(list);
            response = list.stream().map(EntitySequence::toString).collect(Collectors.joining("\n"));
        }
        return response;
    }
}
