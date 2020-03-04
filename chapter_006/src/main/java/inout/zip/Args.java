package inout.zip;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Args {
    private String directory = "";
    private String output = "";
    private List<String> ext;

    public Args(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (i + 1 > args.length) {
                break;
            }
            switch (args[i]) {
                case ("-d"):
                    i++;
                    directory = args[i].replaceAll("\\\\", "/");
                    break;
                case ("-e"):
                    i++;
                    ext = Arrays.stream(args[i].split(";"))
                            .map(String::trim)
                            .collect(Collectors.toList());
                    break;
                case ("-o"):
                    i++;
                    output = args[i].replaceAll("\\\\", "/");
                    break;
                default:
                    break;
            }
        }
        if (directory.isEmpty() || output.isEmpty()) {
            throw new RuntimeException("Invalid parameters");
        }
    }

    public String getDirectory() {
        return directory;
    }

    public String getOutput() {
        return output;
    }

    public List<String> getExtensions() {
        return ext;
    }
}
