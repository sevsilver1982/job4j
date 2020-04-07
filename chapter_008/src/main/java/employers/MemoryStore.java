package employers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemoryStore implements IStore {
    private final List<Employer> employers = new ArrayList<>();

    public void add(Employer em) {
        employers.add(em);
    }

    public List<Employer> getEmployers() {
        return employers;
    }

    @Override
    public List<Employer> getEmployers(Predicate<Employer> filter) {
        return employers.stream().filter(filter).collect(Collectors.toList());
    }

}