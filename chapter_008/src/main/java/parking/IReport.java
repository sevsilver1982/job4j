package parking;

import java.util.List;

public interface IReport {
    String generate(List<Place> list);
}