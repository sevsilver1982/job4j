package employers;

import java.util.List;
import java.util.function.Predicate;

public interface IStore {
    List<Employer> getEmployers(Predicate<Employer> filter);
    List<Employer> getEmployers();
}