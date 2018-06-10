package qa.dashboard.repository;

import org.springframework.data.repository.CrudRepository;
import qa.dashboard.model.Screenshot;

public interface ScreenshotRepository extends CrudRepository<Screenshot, String> {
    @Override
    void delete(Screenshot deleted);
}
