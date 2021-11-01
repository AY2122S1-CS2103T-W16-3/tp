package tutoraid.logic.commands;

import static java.util.Objects.requireNonNull;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_CAPACITY;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_NAME;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_PRICE;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON_REMARK;
import static tutoraid.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.Optional;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.commons.util.CollectionUtil;
import tutoraid.logic.commands.exceptions.CommandException;
import tutoraid.model.Model;
import tutoraid.model.lesson.Capacity;
import tutoraid.model.lesson.Lesson;
import tutoraid.model.lesson.LessonName;
import tutoraid.model.lesson.Price;
import tutoraid.model.lesson.Remark;
import tutoraid.model.lesson.Students;
import tutoraid.model.student.Student;

public class EditLessonCommand extends EditCommand {

    public static final String COMMAND_FLAG = "-l";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the lesson specified "
            + "by the index number in the Lesson Panel. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_LESSON_NAME + "LESSON NAME "
            + PREFIX_LESSON_PRICE + "LESSON PRICE (non-negative number with 0 or 2 decimal places) "
            + PREFIX_LESSON_REMARK + "LESSON REMARK (any format) "
            + PREFIX_LESSON_CAPACITY + "LESSON CAPACITY (positive integer) "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LESSON_NAME + "Math 1 "
            + PREFIX_LESSON_PRICE + "19.90 "
            + PREFIX_LESSON_REMARK + "10.00 AM to 12.00 PM every Monday "
            + PREFIX_LESSON_CAPACITY + "10 ";

    public static final String MESSAGE_EDIT_LESSON_SUCCESS = "Edited Lesson: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_LESSON = "This lesson already exists in TutorAid";

    private final Index targetIndex;
    private final EditLessonDescriptor editLessonDescriptor;

    /**
     * @param targetIndex          Index of the lesson in the filtered lesson list that is to be edited
     * @param editLessonDescriptor details to edit the lesson with
     */
    public EditLessonCommand(Index targetIndex, EditLessonDescriptor editLessonDescriptor) {
        requireNonNull(targetIndex);
        requireNonNull(editLessonDescriptor);

        this.targetIndex = targetIndex;
        this.editLessonDescriptor = new EditLessonDescriptor(editLessonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        List<Student> studentList = model.getFilteredStudentList();
        List<Lesson> lastShownLessonList = model.getFilteredLessonList();

        if (targetIndex.getZeroBased() >= lastShownLessonList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_LESSON_DISPLAYED_INDEX);
        }

        Lesson lessonToEdit = lastShownLessonList.get(targetIndex.getZeroBased());
        Lesson editedLesson = createEditedLesson(lessonToEdit, editLessonDescriptor);

        if (!lessonToEdit.isSameLesson(editedLesson) && model.hasLesson(editedLesson)) {
            throw new CommandException(MESSAGE_DUPLICATE_LESSON);
        }

        model.setLesson(lessonToEdit, editedLesson);
        Student.updateStudentLessonLink(studentList, lessonToEdit, editedLesson);
        model.viewLesson(editedLesson);
        model.updateFilteredStudentList(editedLesson::hasStudent);

        return new CommandResult(String.format(MESSAGE_EDIT_LESSON_SUCCESS, editedLesson));
    }

    /**
     * Creates and returns a {@code Lesson} with the details of {@code lessonToEdit}
     * edited with {@code editLessonDescriptor}.
     */
    private static Lesson createEditedLesson(Lesson lessonToEdit, EditLessonDescriptor editLessonDescriptor) {
        requireNonNull(lessonToEdit);
        LessonName updatedLessonName = editLessonDescriptor.getLessonName().orElse(lessonToEdit.getLessonName());
        Capacity updatedCapacity = editLessonDescriptor.getCapacity().orElse(lessonToEdit.getCapacity());
        Price updatedPrice = editLessonDescriptor.getPrice().orElse(lessonToEdit.getPrice());
        Remark updatedRemark = editLessonDescriptor.getRemark().orElse(lessonToEdit.getRemark());
        Students students = lessonToEdit.getStudents();

        return new Lesson(updatedLessonName, updatedCapacity, updatedPrice, students, updatedRemark);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditLessonCommand)) {
            return false;
        }

        // state check
        EditLessonCommand e = (EditLessonCommand) other;
        return targetIndex.equals(e.targetIndex)
                && editLessonDescriptor.equals(e.editLessonDescriptor);
    }

    /**
     * Stores the details to edit the lesson with. Each non-empty field value will replace the
     * corresponding field value of the lesson.
     */
    public static class EditLessonDescriptor {
        private LessonName lessonName;
        private Remark remark;
        private Price price;
        private Capacity capacity;

        public EditLessonDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditLessonDescriptor(EditLessonDescriptor toCopy) {
            setLessonName(toCopy.lessonName);
            setRemark(toCopy.remark);
            setPrice(toCopy.price);
            setCapacity(toCopy.capacity);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(lessonName, remark, price, capacity);
        }

        public void setLessonName(LessonName lessonName) {
            this.lessonName = lessonName;
        }

        public Optional<LessonName> getLessonName() {
            return Optional.ofNullable(lessonName);
        }

        public void setRemark(Remark remark) {
            this.remark = remark;
        }

        public Optional<Remark> getRemark() {
            return Optional.ofNullable(remark);
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        public Optional<Price> getPrice() {
            return Optional.ofNullable(price);
        }

        public void setCapacity(Capacity capacity) {
            this.capacity = capacity;
        }

        public Optional<Capacity> getCapacity() {
            return Optional.ofNullable(capacity);
        }

        /**
         * Returns true if both descriptors represent lessons that have the same identity and data fields.
         * This defines a stronger notion of equality between two lessons.
         */
        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditLessonDescriptor)) {
                return false;
            }

            EditLessonDescriptor e = (EditLessonDescriptor) other;
            EditLessonDescriptor otherDescriptor = (EditLessonDescriptor) other;
            return otherDescriptor.getLessonName().equals(getLessonName())
                    && otherDescriptor.getRemark().equals(getRemark())
                    && otherDescriptor.getPrice().equals(getPrice())
                    && otherDescriptor.getCapacity().equals(getCapacity());
        }
    }


}
