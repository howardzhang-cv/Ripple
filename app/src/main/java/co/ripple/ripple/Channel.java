/** Enumeration class for possible Channels for users
 *  to push, view, and respond to requests from.
 *  @author Raylen Li
 *  */

enum Channel {

    /** Preset list of possible channels users
     * may subscribe and push requests to. */
    CS61A("CS 61A", 1), CS70("CS 70", 2), M54("Math 54", 3);

    /** This channel's display name. */
    private String _name;

    /** This channel's integer ID. */
    private int _id;

    /** A channel with display name CHANNELNAME and id CHANNELID*/
    Channel(String channelName, int channelID) {
        _name = channelName;
        _id = channelID;
    }

    /** Return this channel's display name. */
    String name() {
        return _name;
    }

    /** Return this channel's id. */
    int id() {
        return _id;
    }
}