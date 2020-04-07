package parking;

import java.util.List;

public class ReportTrucks implements IReport {

    @Override
    public String generate(List<Place> list) {
        StringBuilder result = new StringBuilder();
        list.stream()
                .filter(place -> place.getCar().isPresent() && place.getCar().get().getSize() > 1)
                .forEach(place ->
                        result.append(
                                String.format("Place name: %s; size: %s; Status: %s;",
                                        place.getName(),
                                        place.getSize(),
                                        place.getCar().isEmpty() ? "empty place" : String.format("[Car: %s - %s]", place.getCar().get().getName(), place.getCar().get().getNumber())
                                )
                        )
                );
        return result.toString();
    }

}