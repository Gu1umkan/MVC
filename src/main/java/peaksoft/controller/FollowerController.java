package peaksoft.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import peaksoft.service.FollowerService;

@Controller
@RequestMapping("follower")
@RequiredArgsConstructor
public class FollowerController {
    private final FollowerService followerService;

//    @GetMapping
}
