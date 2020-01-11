package travel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {

    public List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress).distinct().sorted(new SortByAddressASC()).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Address> addressList = new Profiles().collect(Arrays.asList(
                new Profile(new Address("city1", "street1", 1, 1)),
                new Profile(new Address("city1", "street1", 5, 5)),
                new Profile(new Address("city1", "street1", 1, 1)),
                new Profile(new Address("city1", "street1", 2, 2)),
                new Profile(new Address("city1", "street1", 4, 4)),
                new Profile(new Address("city1", "street1", 3, 3))
            ));
        addressList.forEach(System.out::println);
    }

}