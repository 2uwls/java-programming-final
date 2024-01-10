package getA;

/**
 * The RecordedNote class represents a musical note that has been recorded during piano playback.
 * It stores information such as the pitch, duration, timestamp, and the path to the sound file.
 */
public class RecordedNote {
	// Fields to store note information
    String pitch; // The pitch or musical note (e.g., "C", "D#", "F2#")
    float duration; // The duration of the note (not used in the provided code)
    long timestamp; // The timestamp when the note was recorded
    String soundFile; // The path to the sound file associated with the note
    
    public RecordedNote(String pitch, long timestamp, String soundFile) {
        this.pitch = pitch;
        this.timestamp = timestamp;
        this.soundFile = soundFile;
    }
}
