package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.PaymentStatus;
import seedu.address.model.person.Person;

/**
 * Sets the payment status of the student identified using the displayed index in TutorAid, to paid.
 */
public class PaidCommand extends Command {

    public static final String COMMAND_WORD = "paid";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sets the payment status of the student identified by the index number used in the "
            + "displayed student list, to paid.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SET_TO_PAID_SUCCESS = "Set payment status to 'paid': %1$s";

    private final Index targetIndex;

    public PaidCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person studentToEdit = lastShownList.get(targetIndex.getZeroBased());
        Person editedStudent = new Person(
                studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
                studentToEdit.getAddress(), new PaymentStatus(true), studentToEdit.getTags());

        model.setPerson(studentToEdit, editedStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SET_TO_PAID_SUCCESS, editedStudent));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PaidCommand // instanceof handles nulls
                && targetIndex.equals(((PaidCommand) other).targetIndex)); // state check
    }
}
