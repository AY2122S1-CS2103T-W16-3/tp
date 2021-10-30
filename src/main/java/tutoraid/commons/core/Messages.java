package tutoraid.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_ADD_COMMAND = "Invalid add command format! "
            + "Must start with 'add -s', 'add -l', 'add -p', or 'add -sl'";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_DELETE_COMMAND = "Invalid delete command format! "
            + "Must start with 'del -s', 'del -l', 'del -p', or 'del -sl'";
    public static final String MESSAGE_INVALID_FIND_COMMAND = "Invalid find command format! "
            + "Must start with 'find -s' or 'find -l'";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = "The student index provided is invalid";
    public static final String MESSAGE_LIST_SUCCESS = "Listed all students and lessons";
    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_INDEX = "The lesson index provided is invalid";
    public static final String MESSAGE_INVALID_EDIT_COMMAND = "Invalid edit command format! "
            + "Must start with 'edit -s' or 'edit -l'";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_MULTIPLE_INDEXES =
            "The student indexes provided are invalid";
    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_MULTIPLE_INDEXES =
            "The lesson indexes provided are invalid";
    public static final String MESSAGE_INVALID_STUDENT_ALREADY_ATTEND_LESSON = "The students provided"
            + " must not be attending any of the lessons provided";
    public static final String MESSAGE_INVALID_STUDENT_NOT_IN_LESSON = "The students provided"
            + " must be attending all of the lessons provided";
    public static final String MESSAGE_INVALID_LESSON_FULL = "Adding all the students provided will exceed"
            + " the capacity of one or more lessons";
    public static final String MESSAGE_INVALID_VIEW_COMMAND = "Invalid view command format! "
            + "Must start with 'view -s' or 'view -l'";
    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%1$d student(s) listed!";
    public static final String MESSAGE_LESSONS_LISTED_OVERVIEW = "%1$d lesson(s) listed!";
    public static final String MESSAGES_SHOWING_HELP_MESSAGE = "Opened help window.";
}
