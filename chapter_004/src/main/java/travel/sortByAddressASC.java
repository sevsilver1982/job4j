package travel;

import java.util.Comparator;

public class sortByAddressASC implements Comparator<Address> {

    @Override
    public int compare(Address o1, Address o2) {
        StringBuilder address1 = new StringBuilder()
                .append(o1.getCity())
                .append(o1.getStreet())
                .append(o1.getHome())
                .append(o1.getApartment());
        StringBuilder address2 = new StringBuilder()
                .append(o2.getCity())
                .append(o2.getStreet())
                .append(o2.getHome())
                .append(o2.getApartment());
        return address1.toString().compareTo(address2.toString());
    }

}