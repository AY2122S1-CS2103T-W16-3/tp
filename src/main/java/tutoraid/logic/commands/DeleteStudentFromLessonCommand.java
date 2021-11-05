package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.logic.commands.util.StudentLessonUtil;
import tutoraid.model.Model;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.student.Student;


/**
 * Deletes a student from a lesson in TutorAid.
 */
public class DeleteStudentFromLessonCommand extends DeleteCommand {

    public static final String COMMAND_FLAG = "-sl";

    public static final String MESSAGE_USAGE = COMMAND_FLAG + ": Delete a student from a lesson "
            + "by the student index number used in the last student list "
            + "and the lesson index number used in the last lesson listing.\n"
            + "Parameters: "
            + PREFIX_STUDENT + "STUDENT_INDEX (must be a positive integer) "
            + PREFIX_LESSON + "LESSON_INDEX (must be a positive integer) "
            + "Example: " + COMMAND_FLAG
            + " s/1"
            + " l/2";

    public static final String MESSAGE_SUCCESS = "Removed student:\n%1$s\nFrom this lesson:\n%2$s";

    private final Index studentIndex;
    private final Index lessonIndex;

    /**
     * Constructs a DeleteStudentFromLessonCommand.
     *
     * @param studentIndex of the student in the filtered student list to add to the lesson
     * @param lessonIndex  of the lesson in the filtered lesson list to add to the student
     */
    public DeleteStudentFromLessonCommand(Index studentIndex, Index lessonIndex) {
        requireNonNull(studentIndex);
        requireNonNull(lessonIndex);
        this.studentIndex = studentIndex;
        this.lessonIndex = lessonIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Student studentToRemoveFromLesson = StudentLessonUtil.getStudent(model, studentIndex);
        Lesson lessonToRemoveFromStudent = StudentLessonUtil.getLesson(model, lessonIndex);

        if (!studentToRemoveFromLesson.hasLesson(lessonToRemoveFromStudent)) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_NOT_IN_LESSON);
        }

        lessonToRemoveFromStudent.removeStudent(studentToRemoveFromLesson);
        studentToRemoveFromLesson.removeLesson(lessonToRemoveFromStudent);

        model.updateFilteredStudentList(student -> student.equals(studentToRemoveFromLesson));
        model.updateFilteredLessonList(lesson -> lesson.equals(lessonToRemoveFromStudent));

        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToRemoveFromLesson, lessonToRemoveFromStudent));
    }
}
