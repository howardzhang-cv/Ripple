package co.ripple.ripple;

public Class Request {
    private int channelID;
    private String title;
    private String body;
    private String senderAddr;
    private String senderPort;

    public Request(int channelID, String title, String body, User sender) {
        this.channelId = channelID;
        this.title = title;
        this.body = body;
        this.senderAddr = sender.address;
        this.senderPort = sender.port;
    }

    /** For TESTING purposes ONLY!
     */
    public Request(int channelID, String title, String body) {
        this.channelId = channelID;
        this.title = title;
        this.body = body;
        this.senderAddr = "127.0.0.1";
        this.senderPort = "5000";
    }

    public Request(String message) {
        deserialize(message);
    }

    /**
     * Requests will be serialized so that we store the sizes of each field of
     * the Request instance as the metadata leading the serialized request string.
     * Each field size will be delimited by a "-".
     * This is how the request is serialized:
     * "CHANNELIDSIZE-TITLESIZE-BODYSIZE-SENDERADDRSIZE-SENDERPORTSIZE-
     *  channelID title body senderAddr senderPort"
     *  NOTE: There are not actually any spaces between characters on line 28, this is
     *  just for readability.
     */
    public String serialize()  {
        String metadata;
        String cid = Integer.toString(channelID);
        String idLen = Integer.toString(cid.length());
        metadata = idLen + "-" + Integer.toString(title.length()) + "-" + Integer.toString(body.length()) +
            "-" + Integer.toString(senderAddr.length()) + "-" + Integer.toString(senderPort.length()) + "-";
        String request = cid + title + body + senderAddr + senderPort;
        request = metadata + request;
        return request;
    }

    public int getChannelId() {
        return this.channelID;
    }

    private void deserialize(String message) {
        Scanner sc = new Scanner(message).useDelimiter("-");
        int[] sizes = new int[5];
        String[] fields = new String[5];
        for (int i = 0; i < 5; i++) {
            sizes[i] = String.toInteger(curr.next());
        }
        String request = sc.next();
        int start = 0;
        int end = sizes[0];
        fields[0] = String.toInteger(request.substring(start, end));
        for (int i = 1; i < 5; i++) {
            start = end;
            end += sizes[i];
            fields[i] = request.substring(start, end);
        }
        this.channelID = fields[0];
        this.title = fields[1];
        this.body = fields[2];
        this.senderAddr = fields[3];
        this.senderPort = fields[4];
    }
}