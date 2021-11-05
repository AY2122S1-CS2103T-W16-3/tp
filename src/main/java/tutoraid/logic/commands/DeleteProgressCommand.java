package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;
import static tutoraid.model.Model.PREDICATE_SHOW_ALL_LESSONS;

import java.util.List;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.student.Progress;
import tutoraid.model.student.Student;


/**
 * Delete progress of an existing student in TutorAid.
 */
public class DeleteProgressCommand extends DeleteCommand {

    public static final String COMMAND_FLAG = "-p";

    public static final String MESSAGE_USAGE = COMMAND_FLAG + ": Deletes a progress from a student in TutorAid "
            + "identified by the index number used in the last student listing.\n"
            + "Parameters: STUDENT_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_FLAG + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted progress: %1$s\nOf this student: %2$s";

    private final Index targetIndex;

    /**
     * @param targetIndex of the student in the filtered student list to delete progress
     */
    public DeleteProgressCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Student> lastShownStudentList = model.getFilteredStudentList();

        model.updateFilteredLessonList(PREDICATE_SHOW_ALL_LESSONS);
        List<Lesson> lessonList = model.getFilteredLessonList();

        if (targetIndex.getZeroBased() >= lastShownStudentList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownStudentList.get(targetIndex.getZeroBased());
        Lesson.updateStudentLessonLink(lessonList, studentToEdit, studentToEdit);

        if (studentToEdit.isProgressListEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NO_PROGRESS_TO_DELETE);
        }

        Progress progressToDelete = studentToEdit.deleteLatestProgress();

        model.viewStudent(studentToEdit);

        return new CommandResult(String.format(MESSAGE_SUCCESS, progressToDelete, studentToEdit));
    }
}
