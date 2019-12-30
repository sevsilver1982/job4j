package pseudo;

public class Square implements Shape {

    @Override
    public String draw() {
        return new StringBuilder()
                .append("+++++++\n")
                .append("+     +\n")
                .append("+     +\n")
                .append("+++++++").toString();
    }

}
