package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;
import static tutoraid.ui.DetailLevel.LOW;

import tutoraid.model.Model;
import tutoraid.ui.DetailLevel;

/**
 * Lists all students in TutorAid to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all students";

    private final DetailLevel detailLevel;

    /**
     * Default constructor that lists students without showing fields.
     */
    public ListCommand() {
        this.detailLevel = LOW;
    }

    /**
     * Constructor for a list command that lists students. Fields are shown if {@code viewAll} is true.
     *
     * @param detailLevel Whether to show fields
     */
    public ListCommand(DetailLevel detailLevel) {
        this.detailLevel = detailLevel;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        model.viewList(detailLevel);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
