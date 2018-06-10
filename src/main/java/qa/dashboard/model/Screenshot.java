package qa.dashboard.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "screenshots")
public class Screenshot {

    @Id
    String id;
    String screenshotName;
    String screenshotDesc;
    String screenshotUrl;

    public Screenshot() {
    }

    public Screenshot(String screenshotName, String screenshotDesc, String screenshotUrl) {
        this.screenshotName = screenshotName;
        this.screenshotDesc = screenshotDesc;
        this.screenshotUrl = screenshotUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScreenshotName() {
        return screenshotName;
    }

    public void setScreenshotName(String screenshotName) {
        this.screenshotName = screenshotName;
    }

    public String getScreenshotDesc() {
        return screenshotDesc;
    }

    public void setScreenshotDesc(String screenshotDesc) {
        this.screenshotDesc = screenshotDesc;
    }

    public String getScreenshotUrl() {
        return screenshotUrl;
    }

    public void setScreenshotUrl(String screenshotUrl) {
        this.screenshotUrl = screenshotUrl;
    }
}
