# ContactList

The application is basically a Contact List which requires a minimum API level of 23(Android 6.0). There are 3 screens namely, Contacts, Contact Details and Contact Profile. The contacts list contains the name of the people in the contact list and a checkbox for each contact. The user of the application can add a new contact and he/she can also delete contacts by clicking on multiple contacts at once. For adding a contact, after clicking on the add button, the application will take the user to another screen(by accessing another activity), “Contact Details”. The Contact Details page has the following fields: Name, Phone Number of the contact and whether there is a Relationship of the contact with the user(projected via clicking on a check box). After entering the required fields, the user has to click on “Add Person” button to get the contact on his/her Contact List. There is also a feature here, where if the user clicks on the contact name in the relationship field, then a new screen will open up(by accessing another activity), which is the “Contact Profile”. The Contact Profile has the following fields: Name, Phone Number and Relationship(similar to Contact Details). This screen should display the profile of whichever contact name the user clicks.
A code has been written to store the names of the contacts in the memory of the application, without using any file or database.
The above has been designed in a portrait and landscape view, using fragments. In landscape view, the contact list is on the left with the contact details on the right. The left fragment occupies 2/5 of the screen width and the right occupies 3/5 of the screen width.