package ru.bstrdn.voting.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bstrdn.voting.model.Vote;
import ru.bstrdn.voting.security.UserDetailsImpl;
import ru.bstrdn.voting.service.VoteService;

//@RestController
//@RequestMapping(value = VoteRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class VoteRestController {
//    static final String REST_URL = "/rest/restaurants";

//    @Autowired
//    VoteService voteService;




//    @PostMapping("/{id}")
//    public Vote vote(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable int id) {
//        log.info("voting");
//        return voteService.toVote(id, userDetails.getUser());
//    }
}
