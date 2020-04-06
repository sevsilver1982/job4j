package lsp.parking;

import java.util.List;

public class ReportEmptyPlaces implements IReport {

    @Override
    public String generate(List<Place> list) {
        StringBuilder result = new StringBuilder();
        list.stream().filter(place -> place.getCar().isEmpty()).forEach(place ->
                result.append(
                        String.format("Place name: %s; size: %s; Status: %s;",
                            place.getName(),
                            place.getSize(),
                            "empty place"
                        )
            )
        );
        return result.toString();
    }

}