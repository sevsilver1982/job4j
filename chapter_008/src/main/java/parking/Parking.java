package parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Parking implements IParking {
    private String name;
    private List<Place> placeList;

    public Parking(String name, List<Place> placeList) {
        this.name = name;
        this.placeList = placeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Place> findPlaceBySize(int size) {
        List<Place> list = new ArrayList<>();
        for (int i = 0; i < placeList.size(); i++) {
            list.clear();
            int tmp = size;
            for (int j = i; j < placeList.size(); j++) {
                Place place = placeList.get(j);
                if (place.getCar().isEmpty() && place.getSize() <= tmp) {
                    tmp -= place.getSize();
                    list.add(place);
                    if (tmp == 0) {
                        return list;
                    }
                } else {
                    break;
                }
            }
        }
        return list;
    }

    @Override
    public Optional<Place> findPlaceByName(String name) {
        return placeList.stream().filter(place -> place.getName().equals(name)).findFirst();
    }

    @Override
    public List<Place> getParkList() {
        return new ArrayList<>(placeList);
    }

    public List<Place> getParkList(Predicate<Place> filter) {
        return placeList.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public boolean acceptToPlace(ICar car, String name) {
        Optional<Place> place = findPlaceByName(name);
        if (place.isPresent()) {
            place.get().setCar(car);
            return true;
        }
        return false;
    }

    /**
     * Добавление автомобиля на стоянку.
     * Если автомобиль не присутствует на стоянке, то ищем свободное место удовлетворяющее размерам автомобиля.
     * Если такое место отсутствует, то ищем несколько мест удовлетворяющие размерам автомобиля.
     * @param car car
     * @return add result
     */
    @Override
    public boolean accept(ICar car) {
        if (placeList.stream().noneMatch(p -> p.getCar().isPresent() && p.getCar().get().equals(car))) {
            Optional<Place> place = placeList.stream().filter(p -> p.getCar().isEmpty() && p.getSize() == car.getSize()).findFirst();
            if (place.isPresent()) {
                place.get().setCar(car);
                return true;
            }
            List<Place> list = findPlaceBySize(car.getSize());
            if (!list.isEmpty()) {
                list.forEach(p -> p.setCar(car));
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder().append(String.format("Parking name: %s", name)).append(System.lineSeparator());
        placeList.forEach(place -> text
                .append(String.format("Place name: %s; size: %s; Status: %s; ", place.getName(), place.getSize(), place.getCar()))
                .append(System.lineSeparator())
        );
        return text.toString();
    }

}