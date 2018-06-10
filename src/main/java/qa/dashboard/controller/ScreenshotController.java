package qa.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qa.dashboard.model.Screenshot;
import qa.dashboard.repository.ScreenshotRepository;

@Controller
public class ScreenshotController {

    @Autowired
    ScreenshotRepository screenshotRepository;

    @RequestMapping("/screenshot")
    public String screenshot(Model model) {
        model.addAttribute("screenshots", screenshotRepository.findAll());
        return "screenshot";
    }

    @RequestMapping("/screenshot-create")
    public String screenshotCreate() {
        return "screenshot-create";
    }

    @RequestMapping("/screenshot-save")
    public String save(@RequestParam String screenshotName, @RequestParam String screenshotDesc, @RequestParam String screenshotUrl) {
        Screenshot screenshot = new Screenshot();
        screenshot.setScreenshotName(screenshotName);
        screenshot.setScreenshotDesc(screenshotDesc);
        screenshot.setScreenshotUrl(screenshotUrl);
        screenshotRepository.save(screenshot);

        return "redirect:/screenshot-show/" + screenshot.getId();
    }

    @RequestMapping("/screenshot-show/{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("screenshot", screenshotRepository.findById(id).get());
        return "screenshot-show";
    }

    @RequestMapping("/screenshot-delete")
    public String delete(@RequestParam String id) {
        Screenshot screenshot = screenshotRepository.findById(id).get();
        screenshotRepository.delete(screenshot);

        return "redirect:/screenshot";
    }

    @RequestMapping("/screenshot-edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("screenshot", screenshotRepository.findById(id).get());
        return "screenshot-edit";
    }

    @RequestMapping("/screenshot-update")
    public String update(@RequestParam String id, @RequestParam String screenshotName, @RequestParam String screenshotDesc, @RequestParam String screenshotUrl) {
        Screenshot screenshot = screenshotRepository.findById(id).get();
        screenshot.setScreenshotName(screenshotName);
        screenshot.setScreenshotDesc(screenshotDesc);
        screenshot.setScreenshotUrl(screenshotUrl);
        screenshotRepository.save(screenshot);

        return "redirect:/screenshot-show/" + screenshot.getId();
    }
}
