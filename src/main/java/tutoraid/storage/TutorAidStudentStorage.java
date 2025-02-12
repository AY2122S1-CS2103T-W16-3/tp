package tutoraid.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import tutoraid.commons.exceptions.DataConversionException;
import tutoraid.model.ReadOnlyLessonBook;
import tutoraid.model.ReadOnlyStudentBook;
import tutoraid.model.StudentBook;

/**
 * Represents a storage for {@link StudentBook}.
 */
public interface TutorAidStudentStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getStudentBookFilePath();

    /**
     * Returns StudentBook data as a {@link ReadOnlyStudentBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyStudentBook> readStudentBook(ReadOnlyLessonBook lessonBook)
            throws DataConversionException, IOException;

    /**
     * @see #getStudentBookFilePath()
     */
    Optional<ReadOnlyStudentBook> readStudentBook(Path filePath, ReadOnlyLessonBook lessonBook)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyStudentBook} to the storage.
     *
     * @param studentBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveStudentBook(ReadOnlyStudentBook studentBook) throws IOException;

    /**
     * @see #saveStudentBook(ReadOnlyStudentBook)
     */
    void saveStudentBook(ReadOnlyStudentBook studentBook, Path filePath) throws IOException;

}
