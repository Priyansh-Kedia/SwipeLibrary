# SwipeLibrary

SwipeLibrary provides extension functions for handling swipe actions in RecyclerView. 

You can add **Swipe To Delete** and **Drag and Shift** like functionalities with just one line of code.


# Extension Functions

SwipeLibrary contains the following functions:
- [Swipe To Delete](#swipe-to-delete)
- [Drag to Shift](#drag-to-shift)

## Swipe To Delete
Add swipe to delete RecyclerView's items from the list, which a single line of code.

Use *addSwipeToDelete* as RecyclerView's extension function, to add this functionality to your RecyclerView. 
This extension function has **two** optional parameters. 
> The first parameter is the list of directions in which swipe should be allowed. The directions can be either **TOP**, **BOTTOM**, **RIGHT**, **LEFT**.
The list of directions can be made like this,
		`val list = listOf(SwipeToDelete.DIRECTION.LEFT,SwipeToDelete.DIRECTION.RIGHT)`
If no value is passed for the list, by default, **RIGHT** is the direction for swipe.


> The second parameter is the *listener* for the interface method. The by default value of this parameter is null. If passed, the activity/fragment should implement `SwipeToDelete.OnSwiped`. This method will be called when the RecyclerView item is swiped out, and it returns the position of the element that was swiped. 
**Implementation of interface method**
In a notes app, if you wish to delete the note from your database, after it is swiped, then you can used this method to perform the action. The note can be deleted by getting the *NOTE* using the adapter position inside the list passed to RecyclerView Adapter.


## Drag to Shift

