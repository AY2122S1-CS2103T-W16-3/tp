package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.lesson.UniqueLessonList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueLessonList lessons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        lessons = new UniqueLessonList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons and Lessons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the lesson list with {@code lessons}.
     * {@code lessons} must not contain duplicate lessons.
     */
    public void setLessons(List<Lesson> lessons) {
        this.lessons.setLessons(lessons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setLessons(newData.getLessonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// lesson-level operations

    /**
     * Returns true if a lesson with the same identity as {@code lesson} exists in the address book.
     */
    public boolean hasLesson(Lesson lesson) {
        requireNonNull(lesson);
        return lessons.contains(lesson);
    }

    /**
     * Adds a lesson to the address book.
     * The lesson must not already exist in the address book.
     */
    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }

    /**
     * Replaces the given lesson {@code target} in the list with {@code editedLesson}.
     * {@code target} must exist in the address book.
     * The lesson identity of {@code editedLesson} must not be the same as another existing lesson in the address book.
     */
    public void setLesson(Lesson target, Lesson editedLesson) {
        requireNonNull(editedLesson);

        lessons.setLesson(target, editedLesson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeLesson (Lesson key) {
        lessons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons, "
                + lessons.asUnmodifiableObservableList().size() + " lessons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Lesson> getLessonList() {
        return lessons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons)
                && lessons.equals(((AddressBook) other).lessons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode() + lessons.hashCode();
    }
}
