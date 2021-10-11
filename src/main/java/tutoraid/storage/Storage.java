package tutoraid.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import tutoraid.commons.exceptions.DataConversionException;
import tutoraid.model.ReadOnlyStudentBook;
import tutoraid.model.ReadOnlyUserPrefs;
import tutoraid.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TutorAidStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getStudentBookFilePath();

    @Override
    Optional<ReadOnlyStudentBook> readStudentBook() throws DataConversionException, IOException;

    @Override
    void saveStudentBook(ReadOnlyStudentBook studentBook) throws IOException;

}