package qa.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import qa.dashboard.model.Screenshot;
import qa.dashboard.repository.ScreenshotRepository;

@Controller
@RequestMapping("/screenshot")
public class ScreenshotController {

    private final ScreenshotRepository screenshotRepository;

    @Autowired
    public ScreenshotController(ScreenshotRepository screenshotRepository) {
        this.screenshotRepository = screenshotRepository;
    }

    @GetMapping()
    public String screenshot(Model model) {
        model.addAttribute("screenshots", screenshotRepository.findAll());
        return "screenshot/screenshot";
    }

    @GetMapping("/create")
    public String screenshotCreate() {
        return "screenshot/screenshot-create";
    }

    @PostMapping("/save")
    public String save(@RequestParam String screenshotName, @RequestParam String screenshotDesc, @RequestParam String screenshotUrl) {
        Screenshot screenshot = new Screenshot();
        screenshot.setScreenshotName(screenshotName);
        screenshot.setScreenshotDesc(screenshotDesc);
        screenshot.setScreenshotUrl(screenshotUrl);

        ModelAndView mav = save(screenshot);

        return mav.getViewName();
    }

    @PostMapping("/create")
    public ModelAndView save(@RequestBody Screenshot screenshot) {
        screenshotRepository.save(screenshot);

        ModelAndView mav = new ModelAndView();
        mav.addObject("screenshot", screenshot);
        mav.setViewName("redirect:/screenshot/show/" + screenshot.getId());

        return mav;
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("screenshot", screenshotRepository.findById(id).get());
        return "screenshot/screenshot-show";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String id) {
        Screenshot screenshot = screenshotRepository.findById(id).get();
        screenshotRepository.delete(screenshot);

        return "redirect:/screenshot";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("screenshot", screenshotRepository.findById(id).get());
        return "screenshot/screenshot-edit";
    }

    @GetMapping("/update")
    public String update(@RequestParam String id, @RequestParam String screenshotName, @RequestParam String screenshotDesc, @RequestParam String screenshotUrl) {
        Screenshot screenshot = screenshotRepository.findById(id).get();
        screenshot.setScreenshotName(screenshotName);
        screenshot.setScreenshotDesc(screenshotDesc);
        screenshot.setScreenshotUrl(screenshotUrl);
        screenshotRepository.save(screenshot);

        return "redirect:/screenshot/show/" + screenshot.getId();
    }
}
