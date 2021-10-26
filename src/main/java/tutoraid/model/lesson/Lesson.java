package tutoraid.model.lesson;

import static tutoraid.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import tutoraid.model.student.Student;

/**
 * Represents a Lesson in the TutorAid.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Lesson {

    // Identity Fields
    private final LessonName lessonName;

    // Data Fields
    private final Capacity capacity;
    private final Price price;
    private final Students students;
    private final Timing timing;

    /**
     * Every field must be present and not null.
     */
    public Lesson(LessonName lessonName, Capacity capacity, Price price, Students students, Timing timing) {
        requireAllNonNull(lessonName, capacity, price, students, timing);
        this.lessonName = lessonName;
        this.capacity = capacity;
        this.price = price;
        this.students = students;
        this.timing = timing;
    }

    public LessonName getLessonName() {
        return lessonName;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public Price getPrice() {
        return price;
    }

    public Students getStudents() {
        return students;
    }

    public Timing getTiming() {
        return timing;
    }

    /**
     * Checks if a student is in this lesson.
     *
     * @param student student to be checked
     * @return true if the student is in this lesson, false otherwise
     */
    public boolean containsStudent(Student student) {
        return students.containsStudent(student);
    }

    /**
     * Adds a student to this lesson.
     *
     * @param student student to be added
     */
    public void addStudent(Student student) {
        students.addStudent(student);
    }

    /**
     * Removes a student from this lesson.
     *
     * @param student student to be removed
     */
    public void removeStudent(Student student) {
        students.removeStudent(student);
    }

    /**
     * Checks if the lesson will exceed capacity if accepting more students.
     *
     * @param numOfExtraStudents the number of students to be added
     * @return true if the number of students after adding is larger than the capacity
     */
    public boolean willExceedCapacity(int numOfExtraStudents) {
        return students.numberOfStudents() + numOfExtraStudents > capacity.getCapacity();
    }

    /**
     * Returns true if both lessons have the same name.
     * This defines a weaker notion of equality between two lessons.
     */
    public boolean isSameLesson(Lesson otherLesson) {
        if (otherLesson == this) {
            return true;
        }

        return otherLesson != null
                && otherLesson.getLessonName().equals(this.getLessonName());
    }

    /**
     * Returns the name of the lesson in a string form
     *
     * @return The name of the lesson in a String
     */
    public String toNameString() {
        return this.getLessonName().toString();
    }

    /**
     * Returns true if both lessons have the same identity and data fields.
     * This defines a stronger notion of equality between two lessons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;
        return otherLesson.getLessonName().equals(getLessonName())
                && otherLesson.getCapacity().equals(getCapacity())
                && otherLesson.getPrice().equals(getPrice())
                && otherLesson.getStudents().equals(getStudents())
                && otherLesson.getTiming().equals(getTiming());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(lessonName, timing, capacity, price, students);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(getLessonName());

        if (lessonName != null) {
            builder.append("; Lesson's name: ")
                    .append(getLessonName());
        }

        if (timing != null) {
            builder.append("; Lesson's timing: ")
                    .append(getTiming());

        }

        if (capacity != null) {
            builder.append("; Lesson's capacity: ")
                    .append(getCapacity());
        }

        if (price != null) {
            builder.append("; Lesson's price: ")
                    .append(getCapacity());
        }

        if (students != null) {
            builder.append("; Lesson's students: ")
                    .append(getStudents());
        }

        return builder.toString();
    }
}
