1. A list consisting of reminders the users want to be aware of. The application must allow
users to add reminders to a list, delete reminders from a list, and edit the reminders in
the list.

	I included a class List which will hold a list of reminders.
	There is a method to add a reminder.
	I also created a class for the reminder objects which will be able to be deleted or edited.

2. The application must contain a database (DB) of reminders and corresponding data.

	I didn't include the database portion since it wasn't necessary for the diagram.

3. Users must be able to add reminders to a list by picking them from a hierarchical list,
where the first level is the reminder type (e.g., Appointment), and the second level is the
name of the actual reminder (e.g., Dentist Appointment).

	I included classes for reminder type and specific reminders.

4. Users must also be able to specify a reminder by typing its name. In this case, the
application must look in its DB for reminders with similar names and ask the user
whether that is the item they intended to add. If a match (or nearby match) cannot be
found, the application must ask the user to select a reminder type for the reminder, or
add a new one, and then save the new reminder, together with its type, in the DB.

	I included a method for a user to be able to specify a reminder.

5. The reminders must be saved automatically and immediately after they are modified.

	Not necessary to include in diagram.

6. Users must be able to check off reminders in the list (without deleting them.

	Each reminder has a method to check it off.

7. Users must also be able to clear all the check-off marks in the reminder list at once.

	Each list has a method to check all.

8. Check-off marks for the reminder list are persistent and must also be saved immediately.

	Not necessary for the diagram.

9. The application must present the reminders grouped by type.

	Not necessary for the diagram, this is specific to the UI.

10. The application must support multiple reminder lists at a time (e.g., “Weekly”, “Monthly”,
“Kid’s Reminders”). Therefore, the application must provide the users with the ability to
create, (re)name, select, and delete reminder lists.

	A user can invoke the createList method any number of times.

11. The application should have the option to set up reminders with day and time alert. If this
option is selected allow option to repeat the behavior.

	A reminder has an include alert method.
	The alert has a date and time as well as the frequencey (recurrence rate) to inicate how often the alert happens.
