package tutoraid.logic.parser;

import static java.util.Objects.requireNonNull;
import static tutoraid.logic.parser.CliSyntax.PREFIX_LESSON;
import static tutoraid.logic.parser.CliSyntax.PREFIX_STUDENT;

import java.util.ArrayList;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.logic.commands.AddStudentsToLessonsCommand;
import tutoraid.logic.parser.exceptions.ParseException;

/**
<<<<<<< HEAD
 * Parses input arguments and creates a new PaidCommand object
=======
 * Parses input arguments and creates a new AddStudentsToLessonsCommand object
>>>>>>> master
 */
public class AddStudentsToLessonsCommandParser implements Parser<AddStudentsToLessonsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of AddStudentsToLessonsCommand
     * and returns an AddStudentsToLessonsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudentsToLessonsCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT, PREFIX_LESSON);

        // Must specify student indexes and lesson indexes so these students can be added to these lessons
        if (argMultimap.getValue(PREFIX_STUDENT).isEmpty()
                || argMultimap.getValue(PREFIX_LESSON).isEmpty()
                || !argMultimap.getPreamble().isEmpty()) {

            throw new ParseException(String.format(
                    Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddStudentsToLessonsCommand.MESSAGE_USAGE));
        }

        ArrayList<Index> studentIndexes = ParserUtil.parseMultipleIndexes(
                argMultimap.getValue(PREFIX_STUDENT).get());
        ArrayList<Index> lessonIndexes = ParserUtil.parseMultipleIndexes(
                argMultimap.getValue(PREFIX_LESSON).get());

        return new AddStudentsToLessonsCommand(studentIndexes, lessonIndexes);
    }
}
