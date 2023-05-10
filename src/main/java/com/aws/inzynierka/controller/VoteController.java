package com.aws.inzynierka.controller;

import com.aws.inzynierka.model.dto.VoteDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes/")
@AllArgsConstructor
public class VoteController {


    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody VoteDto voteDto) {
//        voteProceed(voteDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
