package multithreading.wget;

class Args {
    private String url = "";
    private String output = "";
    private Integer rate = 0;

    public Args(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (i + 1 > args.length) {
                break;
            }
            switch (args[i]) {
                case ("-url"):
                    i++;
                    url = args[i].replaceAll("\\\\", "/");
                    break;
                case ("-output"):
                    i++;
                    output = args[i].replaceAll("\\\\", "/");
                    break;
                case ("-rate"):
                    i++;
                    rate = Integer.parseInt(args[i]);
                    break;
                default:
                    break;
            }
        }
        if (url.isEmpty() || output.isEmpty() || rate == 0) {
            throw new RuntimeException("Invalid parameters");
        }
    }

    public String getUrl() {
        return url;
    }

    public String getOutput() {
        return output;
    }

    public Integer getRate() {
        return rate;
    }

}