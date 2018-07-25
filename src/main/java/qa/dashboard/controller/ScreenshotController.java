package qa.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import qa.dashboard.model.Screenshot;
import qa.dashboard.repository.ScreenshotRepository;

@Controller
public class ScreenshotController {

    private final ScreenshotRepository screenshotRepository;

    @Autowired
    public ScreenshotController(ScreenshotRepository screenshotRepository) {
        this.screenshotRepository = screenshotRepository;
    }

    @GetMapping("/screenshot")
    public String screenshot(Model model) {
        model.addAttribute("screenshots", screenshotRepository.findAll());
        return "screenshot";
    }

    @GetMapping("/screenshot-create")
    public String screenshotCreate() {
        return "screenshot-create";
    }

    @PostMapping("/screenshot-save")
    public String save(@RequestParam String screenshotName, @RequestParam String screenshotDesc, @RequestParam String screenshotUrl) {
        Screenshot screenshot = new Screenshot();
        screenshot.setScreenshotName(screenshotName);
        screenshot.setScreenshotDesc(screenshotDesc);
        screenshot.setScreenshotUrl(screenshotUrl);

        ModelAndView mav = save(screenshot);

        return mav.getViewName();
    }

    @PostMapping("/screenshot/create")
    public ModelAndView save(@RequestBody Screenshot screenshot) {
        screenshotRepository.save(screenshot);

        ModelAndView mav = new ModelAndView();
        mav.addObject("screenshot", screenshot);
        mav.setViewName("redirect:/screenshot-show/" + screenshot.getId());

        return mav;
    }

    @GetMapping("/screenshot-show/{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("screenshot", screenshotRepository.findById(id).get());
        return "screenshot-show";
    }

    @GetMapping("/screenshot-delete")
    public String delete(@RequestParam String id) {
        Screenshot screenshot = screenshotRepository.findById(id).get();
        screenshotRepository.delete(screenshot);

        return "redirect:/screenshot";
    }

    @GetMapping("/screenshot-edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("screenshot", screenshotRepository.findById(id).get());
        return "screenshot-edit";
    }

    @GetMapping("/screenshot-update")
    public String update(@RequestParam String id, @RequestParam String screenshotName, @RequestParam String screenshotDesc, @RequestParam String screenshotUrl) {
        Screenshot screenshot = screenshotRepository.findById(id).get();
        screenshot.setScreenshotName(screenshotName);
        screenshot.setScreenshotDesc(screenshotDesc);
        screenshot.setScreenshotUrl(screenshotUrl);
        screenshotRepository.save(screenshot);

        return "redirect:/screenshot-show/" + screenshot.getId();
    }
}
