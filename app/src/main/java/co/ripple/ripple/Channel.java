package co.ripple.ripple;

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

    /** A channel with display name CHANNELNAME and id CHANNELID. */
    Channel(String channelName, int channelID) {
        _name = channelName;
        _id = channelID;
    }

    /** Return this channel's display name. */
    String getName() {
        return _name;
    }

    /** Return this channel's id. */
    int getID() {
        return _id;
    }

    /** Return the channel specified by ID, if exists.
     * Otherwise, return null. */
    static Channel byID(int id) {
        for (Channel channel : Channel.values()) {
            if (channel.getID() == id) {
                return channel;
            }
        }
        return null;
    }

    /** Return the channel specified by NAME, if exists.
     * Otherwise, return null. */
    static Channel byName(String name) {
        for (Channel channel : Channel.values()) {
            if (channel.getName() == name) {
                return channel;
            }
        }
        return null;
    }
}