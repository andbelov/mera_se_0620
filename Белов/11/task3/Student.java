package task3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;

import static util.Util11.giveRandom;

public class Student {
    private final String name;
    private final Set<Lecture> lectSet = new HashSet<>();
    private final int PREFERRED_LECTURES_NUM;
    public Student(final String name, final List<Lecture> availLectures) {
        this.name = name;
        PREFERRED_LECTURES_NUM = giveRandom(availLectures.size());
        for(int i=PREFERRED_LECTURES_NUM; 0<=--i; ){
            lectSet.add(availLectures.get(giveRandom(availLectures.size())));
        }
    }
    //this constructor only for test cases
    public Student(final String name) {
        this.name = name;
        PREFERRED_LECTURES_NUM = 777;
    }

    public String getName() {
        return name;
    }
    public Set<Lecture> getLectures(){
        return lectSet;
    }
    public String giveLectures(){
        final StringBuilder sb = new StringBuilder();
        sb.append("Student ").append(name).append(" takes ")
                .append(PREFERRED_LECTURES_NUM).append(" lectures");
        lectSet.forEach(l -> sb.append('\n').append(l.getLectureTitle()));
        return sb.toString();
    }
    public String giveLecturesAndDates(){
        String accStr = "Student "+ name + " takes "
                + PREFERRED_LECTURES_NUM + " lectures";
        return lectSet.stream().map(Lecture::info).reduce(accStr, new BinaryOperator<String>(){
            @Override
            public String apply(String accumStr, String streamString){
                return accumStr + '\n' + streamString;
            }
        });
    }
    @Override
    public String toString(){
        return "Student{" +
                "name='" + name + '\'' +
                ", lectSet=" + lectSet +
                ", PREFERRED_LECTURES_NUM=" + PREFERRED_LECTURES_NUM +
                '}';
    }
}
