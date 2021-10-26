package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.student.PaymentStatus;
import tutoraid.model.student.Student;

/**
 * Sets the payment status of the student identified using the displayed index in TutorAid, to
 * 'Has not paid for the current month'.
 */
public class UnpaidCommand extends Command {

    public static final String COMMAND_WORD = "unpaid";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets the payment status of the student identified by the index number used in the "
            + "displayed student list, to 'Has not paid for the current month'.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SET_TO_UNPAID_SUCCESS =
            "Set payment status to 'Has not paid for the current month': %1$s";
    public static final String MESSAGE_ALREADY_UNPAID =
            "This student has still not paid for the current month.";

    private final Index targetIndex;

    public UnpaidCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(targetIndex.getZeroBased());

        if (!studentToEdit.getPaymentStatus().hasPaid) {
            throw new CommandException(MESSAGE_ALREADY_UNPAID);
        }

        Student editedStudent = new Student(
                studentToEdit.getStudentName(), studentToEdit.getStudentPhone(), studentToEdit.getParentName(),
                studentToEdit.getParentPhone(), studentToEdit.getProgressList(),
                new PaymentStatus(true), studentToEdit.getLessons());

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(String.format(MESSAGE_SET_TO_UNPAID_SUCCESS, editedStudent));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnpaidCommand // instanceof handles nulls
                && targetIndex.equals(((UnpaidCommand) other).targetIndex)); // state check
    }
}
