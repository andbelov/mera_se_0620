package task3;

import java.time.LocalDate;
import java.util.Objects;

public class Lecture{
    public enum LectureType{
        MATH("МатАнализ"      ),
        PHIL("ФилософиЯ"      ),
        ENGL("Английскй"),
        HIST("ИсторияНН"        ),
        PHYS("Физ-Культ"    );
        private final String title;
        LectureType(final String title){
            this.title = title;
        }
        public static int size(){
            return LectureType.values().length;
        }
        public String getLectureTitle(){return title;}
    }
    private final LectureType lectureType;
    private final LocalDate date;
    public Lecture(final LectureType lectureType, final LocalDate date){
        this.lectureType = lectureType;
        this.date = date;
    }
    public LectureType getLectureType(){return lectureType;}
    public String getLectureTitle(){return lectureType.getLectureTitle();}
    public LocalDate getDate(){return date;}
    public String info(){
        return "Lecture " + lectureType.title + " scheduled at " + date;
    }
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Lecture)) return false;
        Lecture lecture = (Lecture) o;
        return lectureType == lecture.lectureType &&
                Objects.equals(date, lecture.date);
    }
    @Override
    public int hashCode(){
        return Objects.hash(lectureType, date);
    }
    @Override
    public String toString(){
        return "Lecture{" +
                "lectureType=" + lectureType +
                ", date=" + date +
                '}';
    }
}
