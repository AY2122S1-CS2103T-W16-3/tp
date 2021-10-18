package tutoraid.model.student;

import java.util.ArrayList;

/**
 * Represents a student's list of progress in TutorAid.
 */
public class ProgressList {

    public static final String MESSAGE_CONSTRAINTS =
            "ProgressList constructor either takes in no argument "
            + "or takes in a String ArrayList of size no more than 10";

    public final ArrayList<Progress> progressList;

    /**
     * Constructs a {@code ProgressList}.
     */
    public ProgressList() {
        progressList = new ArrayList<>();
    }

    /**
     * Constructs a {@code ProgressList}.
     *
     * @param progressListInStringArrayList ProgressList of the student
     */
    public ProgressList(ArrayList<String> progressListInStringArrayList) {
        this.progressList = new ArrayList<>();

        if (!isValidProgressList(progressListInStringArrayList)) {
            return;
        }

        for (int i = 0; i < progressListInStringArrayList.size(); i++) {
            Progress currentProgress = new Progress(progressListInStringArrayList.get(i));

            if (!currentProgress.isEmptyProgress()) {
                this.addProgress(currentProgress);
            }
        }
    }

    /**
     * Checks if a given string ArrayList is a valid list of progress descriptions.
     *
     * @param progressListInStringArrayList an ArrayList of strings
     * @return true if all elements are valid progress description, false otherwise
     */
    public static boolean isValidProgressList(ArrayList<String> progressListInStringArrayList) {
        if (progressListInStringArrayList.size() > 10) {
            return false;
        }

        for (int i = 0; i < progressListInStringArrayList.size(); i++) {
            if (!Progress.isValidProgress(progressListInStringArrayList.get(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * Adds a new progress.
     *
     * @param progressToAdd the progress to be added
     */
    public void addProgress(Progress progressToAdd) {
        while (progressList.size() > 10) {
            progressList.remove(0);
        }

        if (progressList.size() == 10) {
            progressList.remove(0);
            progressList.add(progressToAdd);
        } else {
            progressList.add(progressToAdd);
        }
    }

    /**
     * Deletes the latest progress.
     */
    public void deleteLatestProgress() {
        if (progressList.size() == 0) {
            return;
        } else {
            progressList.remove(progressList.size() - 1);
        }
    }

    /**
     * Returns the latest progress if there is at least one progress, else returns an EMPTY_PROGRESS.
     */
    public Progress getLatestProgress() {
        return progressList.size() == 0
                ? Progress.getEmptyProgress()
                : progressList.get(progressList.size() - 1);
    }

    /**
     * Returns a string Array that contains all the progress description in the correct order.
     */
    public ArrayList<String> getAllProgressAsStringArrayList() {
        ArrayList<String> allProgressAsStringArrayList = new ArrayList<>();
        for (int i = 0; i < progressList.size(); i++) {
            String currentProgressDescription = progressList.get(i).toString();
            allProgressAsStringArrayList.add(currentProgressDescription);
        }
        return allProgressAsStringArrayList;
    }

    @Override
    public String toString() {
        if (progressList.size() == 0) {
            return "No Progress";
        }

        String allProgress = "";

        for (int i = 1; i < progressList.size() + 1; i++) {
            String progressToPrint = progressList.get(i - 1).toString();
            allProgress = allProgress + i + ". " + progressToPrint + "\n";
        }
        return allProgress;
    }

    @Override
    public boolean equals(Object other) {
        System.out.println(this.progressList.equals(((ProgressList) other).progressList));
        return other == this // short circuit if same object
                || (other instanceof ProgressList // instanceof handles nulls
                && this.progressList.equals(((ProgressList) other).progressList)); // state check
    }

    @Override
    public int hashCode() {
        return progressList.hashCode();
    }
}
