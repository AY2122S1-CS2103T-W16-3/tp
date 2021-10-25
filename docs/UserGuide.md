---
layout: page
title: User Guide
---

* Table of Contents
  {:toc}

# 1. Introduction
Thank you for downloading **TutorAid**! TutorAid is a desktop app **for private tutors to track their students' contacts**.

The main features of TutorAid include:
1. Storing contacts of students and their parents
2. Storing and managing lesson details
3. Tracking students' progress
4. Tracking students' payment status

With TutorAid, you have a one-stop solution for keeping track of your students' contacts and staying up-to-date on their progress. TutorAid is optimised for use via a **Command Line Interface** (CLI) while retaining the benefits of a visually-appealing Graphical User Interface (GUI). If you can type fast, TutorAid can help you to manage your tutoring tasks more efficiently than traditional GUI apps.

We hope that TutorAid will be helpful in adding more organisation to your tutoring career. To get TutorAid up and running, check out the [Quick Start](#3-quick-start) section.<hr>

# 2. About
This section contains information and terms that may help you to understand the user guide.

## 2.1 User Guide formatting

Formats discussed in this section may be used in relevant areas of this user guide.

**Tips**

Tips are helpful suggestions that may improve your experience using TutorAid.

> :bulb: This is helpful.

**Warnings**

Warnings can inform you of things that should be followed carefully to prevent unwanted behaviour.

> :exclamation: **This is important!**

**Commands**

Commands in these highlighted boxes are typically used to show what you should enter into TutorAid.

`command`<hr>

## 2.2 TutorAid visual components
This section details the various components in TutorAid and how they will be referred to throughout the guide.
![Labelled Ui](images/labelled-ui.jpg)

#### Menu Bar
This is an area where some features can be found. As TutorAid primarily interacts with you through the command box, this area is infrequently used.

#### Command Box
The Command Box is a field in which you can type instructions (commands) to TutorAid. Text in this user guide that is `highlighted` should typically be copied into the command box exactly (including spaces). After you have typed a command into this box, you can press ENTER :leftwards_arrow_with_hook: on your keyboard to tell TutorAid to execute your command. 

If your command is of the wrong [format](#23-command-format), TutorAid will try to show you the correct format using the Console if it is able to guess what command you had intended to provide.

#### Console
The Console is used by TutorAid to communicate with you. Whenever a command is carried out, TutorAid will let you know whether the operation was successful. It may also provide you with details about the changes made during the operation. 

#### Student Panel
The Student Panel is where your students are listed. There are two modes for this panel: **Full** and **Minimal**. Full view means that all details about each student is listed, whereas Minimal view allows you to see only their name and their index number. These modes can be set via [the list command](#listing-all-students--list). In the labelled screenshot, the Student Panel is in Minimal view.

> :bulb: The **index number** is important for many commands in TutorAid.

#### Lesson Panel
The Lesson Panel is where your lessons are listed. Just like the Student Panel, there are the **Full** and **Minimal** modes which determine how much information is displayed. In the labelled screenshot, the Lesson Panel is in Full view.

> :bulb: The **index number** of a lesson is important too, and is used in commands just like the index number for students.

#### Status Bar
The status bar shows the path where you can find the save file for TutorAid.<hr>

## 2.3 Command Format
Commands are text that you can enter into the Command Box to tell TutorAid to perform an operation. Some commands have many components, each of which serve different purposes. The following diagram depicts the components of a command.

![](images/command-syntax.png)

#### Command Word
The command word is how you can tell TutorAid what kind of operation you want to do. These command words are listed [here](#6-command-summary). All commands must contain a command word. 

In the example above, `edit` tells TutorAid to perform an *edit* operation.

#### Flag
The flag is used to differentiate between variants of the same operation. For example, the `edit` command word can be used to edit the details of a student or a lesson. To differentiate between these usages, you should pass a flag to TutorAid - `edit -s` to edit a student, or `edit -l` to edit a lesson.

In the example above, `-s` tells TutorAid to perform the edit operation on *students*.

#### Index Number
Some commands perform operations on a specific student or lesson. You should give TutorAid an index number to specify a student or a lesson. The index number can be found by looking at the respective panels - the [Student Panel](#student-panel) or the [Lesson Panel](#lesson-panel). 

In the example above, `3` tells TutorAid to perform the edit operation on the *third student*. 

#### Parameter
A parameter contains the *specifics* of the command to be executed. There can be **multiple** parameters for a single command, depending on the type of command that you wish to perform. Arguments are prefixed with a few characters followed by a slash (`sn/` and `sp/` for this example). These prefixes help TutorAid to differentiate parameters.

> :bulb: `sn/` is the prefix for Student Name and `sp/` is the prefix for Student Phone. 

In the example above, `sn/Matthew Judge` tells TutorAid that the third student should be edited *by changing their name to Matthew Judge*. Similarly, `sp/91263740` tells TutorAid that the third student should be edited *by changing their student mobile number to the specified number*. 

### 2.3.1 Command Syntax in this Guide
In this guide, the syntax / format of a command is shown like this:

`edit -s INDEX_NUMBER [sn/STUDENT_NAME] [sp/STUDENT_PHONE] [pn/PARENT_NAME] [pp/PARENT_PHONE]`

* Words in `UPPER_CASE` are the parameters to be supplied by the user and can contain spaces.
* Items in square brackets are optional.
* Parameters can be in any order.
* If a parameter is expected only once in the command but if you specify it multiple times, only the last occurrence of the parameter will be taken.
* Extraneous parameters for commands that do not take in parameters will be ignored.
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.
<hr>

# 3. Quick Start

If you've just decided to give TutorAid a try, relax. This is going to be easy.

1. Ensure that you have Java `11` or above installed in your Computer.

2. Download the latest `tutoraid.jar` from [here](https://github.com/AY2122S1-CS2103T-W16-3/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your TutorAid.

4. Double-click the file to start the app. An application window similar to the one below should appear in a few seconds. Note how the app contains some sample data.<br><br>
   ![Ui](images/Ui.png)

5. Type the command in the Command Box and press ENTER to execute it. For example, typing `help` and pressing ENTER will open the help window.<br>

6. Refer to the [Features](#6-command-summary) below to see which commands TutorAid understands.
<hr>

# 4. Features

This section lists the types of commands that TutorAid can execute. For more information about how to interpret these commands, check out the [Command Format](#23-command-format) and [Command Syntax](#231-command-syntax-in-this-guide) sections.

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpWindow.png)

Format: `help`

### Adding a student: `add`
Adds a new student to TutorAid.

Format: `add -s sn/STUDENT_NAME [sp/STUDENT_PHONE] [pn/PARENT_NAME] [pp/PARENT_PHONE]`

Examples:
* `add -s sn/John Does sp/81234567 pn/Mrs Doe pp/91234567`

> :bulb: The student's phone number, parent's name and parent's phone number are optional details for tutors to include.


### Listing all students : `list`

Shows a list of all students in TutorAid in the order that they were added. Use the `-a` flag to display all fields, otherwise fields are hidden by default.

Format: `list [-a]`

Examples:

- `list` displays all students in TutorAid while showing only their name and list index
- `list -a` displays all students in TutorAid while showing all of their data such as their contact number, payment status and so on.

### Deleting a student : `delete`
Deletes the specified student with the given student index from TutorAid.

Format: `del -s STUDENT_INDEX`

* Deletes the student at the specified STUDENT_INDEX.
* The index refers to the index number shown in the displayed student list.
* The index must be a positive integer 1,2,3, …​

Example:
* `del -s 2` deletes the 2nd student in TutorAid.

### Editing a student : `edit`

Edits the specified student with the given student index from TutorAid.

Format: `edit STUDENT_INDEX [sn/STUDENT_NAME] [sp/STUDENT_PHONE] [pn/PARENT_NAME] [pp/PARENT_PHONE]`

* Edits the student at the specified STUDENT_INDEX.
* The index refers to the index number shown in the displayed student list.
* The index must be a positive integer 1,2,3, …​
* At least one of the optional fields should be present

Example:

* `edit 2 pp/91112222` changes the 2nd student's parent contact number in TutorAid to 91112222.

### Viewing a student : `view`

Displays the specified student’s name, phone number, progress and payment status, along with their parent’s name and phone number.

Format: `view STUDENT_INDEX`

* Display details of the student at the specified STUDENT_INDEX.
* The index refers to the index number shown in the displayed student list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `view 2` shows the details associated with the 2nd student


### Clearing all entries : `clear`

Clears all entries from TutorAid.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

TutorAid data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

TutorAid data are saved as a JSON file `[JAR file location]/data/tutoraid.json`. Advanced users are welcome to update data directly by editing that data file.


> :exclamation: **If your changes to the data file makes its format invalid, TutorAid will discard all data and start with an empty data file at the next run.**


### Adding progress for a student : `add -p`

Adds a given string representing progress to a student with a given student index.

Format: `add -p STUDENT_INDEX PROGRESS`

* Adds `PROGRESS` for the student at the specified `STUDENT_INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `add -p 2 completed homework` adds `completed homework` to the 2nd student in the list.

### Deleting progress from a student : `del -p`

Removes the string representing progress from the student with a given student index.

Format: `del -p STUDENT_INDEX`

* Deletes the `PROGRESS` for the student at the specified `STUDENT_INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `del -p 2` deletes the progress of the 2nd student in the list.

### Setting payment made: `paid`

Sets the payment status of the specified student to `Paid for the current month`.

Format: `paid STUDENT_INDEX`

- Sets the payment status for the student at the specified `STUDENT_INDEX` as `Paid for the current month`.
- The index refers to the index number shown in the displayed student list.
- The index must be a **positive integer** 1, 2, 3, ...

Example:

- `paid 3` updates the 3rd student's payment status to `Paid for the current month`.

### Unsetting payment made: `unpaid`

Sets the payment status of the specified student to `Has not paid for the current month`.

Format: `unpaid STUDENT_INDEX`

- Sets the payment status for the student at the specified `STUDENT_INDEX` as `Has not paid for the current month`.
- The index refers to the index number shown in the displayed student list.
- The index must be a **positive integer** 1, 2, 3, …

Examples:

- `unpaid 3` updates the 3rd student's payment status to `Has not paid for the current month`.
<hr>

### Adding a lesson: `add -l`

Adds a new lesson to TutorAid.

Format: `add -l n/LESSON_NAME [c/LESSON_CAPACITY] [p/LESSON_PRICE] [t/LESSON_TIMING]`

* The lesson name should only contain alphanumeric characters and spaces.
* If provided, the lesson's capacity must be a **positive integer** 1, 2, 3, …
* If provided, the lesson's price must be a **non-negative number** with either 0 or 2 decimal places. Examples of a valid price are `80` and `85.50`.

Examples:
* `add -l n/P6 Maths c/20 p/80 t/Monday 1200-1400`

> :bulb: The lesson's capacity, price and timing are optional details for tutors to include.

### Deleting a lesson : `del -l`

Deletes the specified lesson with the given lesson index from TutorAid.

Format: `del -l LESSON_INDEX`

* Deletes the lesson at the specified `LESSON_INDEX`.
* `LESSON_INDEX` refers to the index number shown in the displayed lesson list.
* `LESSON_INDEX` must be a **positive integer** 1,2,3, …​

### Locating students or lessons by name: `find -s` / `find -l`

Finds students or lessons whose names contain any of the given keywords. Use `-s` flag to search for students and
`-l` to search for lessons.

Format: `find FLAG KEYWORD [MORE_KEYWORDS]`

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Partial keywords will be matched e.g. `Han` will match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find -s John` returns `john`, `John Doe` and `Johnny Liu`
* `find -l maths` returns `maths`, `Maths 1` and `Mathematics`
* `find -s alex david` returns `Alex Yeoh`, `David Li`<br>


# 5. FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous TutorAid home folder.<hr>

# 6. Command summary

Action | Format, Examples
--------|------------------
**Add student** | `add -s sn/STUDENT_NAME [sp/STUDENT_PHONE] [pn/PARENT_NAME] [pp/PARENT_PHONE]…​` <br> e.g., `add -s sn/John Does sp/81234567 pn/Mrs Doe pp/91234567`
**Clear** | `clear`
**Delete student** | `del -s STUDENT_INDEX`<br> e.g., `delete 3`
**Edit student** | `edit STUDENT_INDEX [sn/STUDENT_NAME] [sp/STUDENT_PHONE] [pn/PARENT_NAME] [pp/PARENT_PHONE]`<br>e.g., `edit 2 pp/91112222`
**List** | `list [-a]`<br>e.g., `list`, `list -a`
**Help** | `help`
**Set payment made** | `paid STUDENT_INDEX`<br>e.g., `paid 3`
**Unset payment made** | `unpaid STUDENT_INDEX`<br>e.g., `unpaid 3`
**Add Progress** | `add -p STUDENT_INDEX PROGRESS` <br> e.g., `add -p 2 completed homework`
**Delete Progress** | `del -p STUDENT_INDEX` <br> e.g., `del -p 2`
**View** | `view STUDENT_INDEX`<br> e.g., `view 2`
**Find student** | `find -s KEYWORD [MORE_KEYWORDS]`<br>e.g., `find -s roy`
**Find lesson** | `find -l KEYWORD [MORE_KEYWORDS]`<br>e.g., `find -l maths`
**Exit** | `exit`
