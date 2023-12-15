package getA;

public class RecordedNote {
    String pitch;
    float duration;
    long timestamp;
    String soundFile; // 추가된 필드: 소리 파일 경로 저장

    public RecordedNote(String pitch, long timestamp, String soundFile) {
        this.pitch = pitch;
        this.timestamp = timestamp;
        this.soundFile = soundFile; // 추가된 필드 초기화
    }
}
