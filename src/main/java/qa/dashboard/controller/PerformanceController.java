package qa.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PerformanceController {

    @RequestMapping("/performance")
    public String api() {
        return "performance";
    }
}
