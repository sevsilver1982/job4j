package lsp.parking;

import java.util.List;
import java.util.Optional;

public interface IParking {
    List<Place> getParkList();
    Optional<Place> findPlaceByName(String name);
    List<Place> findPlaceBySize(int size);
    boolean accept(ICar car);
    boolean acceptToPlace(ICar car, String number);
}