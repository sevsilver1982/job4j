package employers;

import java.util.List;

public interface IReportGenerator {
    String generate(List<Employer> employers);
}
