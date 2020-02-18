package com.example.hibernatedemo.batching;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mohd.waseem on 03/02/20.
 */
@RestController
@RequestMapping(value="batch")
public class BatchingController {

    @Autowired
    private BatchingService batchingService;

    @GetMapping(value = "save")
    public String saveBatch(@RequestParam String prefix, @RequestParam Integer size, @RequestParam Boolean isIdentity) {
        return batchingService.saveBatch(prefix, size, isIdentity);
    }

    @GetMapping(value = "get")
    public String fetchBatch(@RequestParam Long col1) {
        return batchingService.getBatch(col1);
    }
}
