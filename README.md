[![](https://jitpack.io/v/Priyansh-Kedia/SwipeLibrary.svg)](https://jitpack.io/#Priyansh-Kedia/SwipeLibrary)

# SwipeLibrary

SwipeLibrary provides extension functions for handling swipe actions in RecyclerView. 

You can add **Swipe To Delete**, **Drag and Shift** and **Swipe to perform** like functionalities with just one line of code.

	
# Dependency
Add this to your project level build.gradle file followed by the respective dependency

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Add the following dependency in your build.gradle file

	dependencies {
	        implementation 'com.github.Priyansh-Kedia:SwipeLibrary:<latest_version>'
	}



# Extension Functions

SwipeLibrary contains the following functions:
- [Swipe To Delete](#swipe-to-delete)
- [Drag to Shift](#drag-to-shift)
- [Swipe to Perform](#swipe-to-perform)

# Swipe To Delete

Add swipe to delete RecyclerView's items from the list, which a single line of code.

Use *addSwipeToDelete* as RecyclerView's extension function, to add this functionality to your RecyclerView. 
This extension function has **two** optional parameter. 
> The first parameter is the list of directions in which swipe should be allowed. The directions can be either **TOP**, **BOTTOM**, **RIGHT**, **LEFT**.
The list of directions can be made like this,
		`val list = listOf(DIRECTION.LEFT,DIRECTION.RIGHT)`
If no value is passed for the list, by default, **RIGHT** is the direction for swipe.

> The second parameter is the *listener* for the interface method. The by default value of this parameter is null. If passed, the activity/fragment should implement `OnSwiped`. This method will be called when the RecyclerView item is swiped out, and it returns the position of the element that was swiped. 

**Implementation of interface method**
In a notes app, if you wish to delete the note from your database, after it is swiped, then you can used this method to perform the action. The note can be deleted by getting the *NOTE* using the adapter position inside the list passed to RecyclerView Adapter.

        override fun swipeToDelete(adapterPosition: Int, swipeDirection: DIRECTION) {  
		adapter.removeItem(adapterPosition)  
                // swipeDirection gives the direction in which the item was swiped
		}
		
> The third parameter is an optional parameter, for the Color integer, if the user wants a color in the background when the view is swiped out. This is how the user can pass the Color int as the parameter, `ContextCompat.getColor(this, R.color.colorAccent)`

[![rec-2020-10-24_14.38.15.gif](https://s8.gifyu.com/images/rec-2020-10-24_14.38.15.gif)](https://gifyu.com/image/8FMg)


> The fourth parameter is an optional parameter, for the second Color integer, if the user wants two colors in the background, the user can pass both **Parameter 3 and Parameter 4**.

[![rec-2020-10-24_14.43.59.gif](https://s8.gifyu.com/images/rec-2020-10-24_14.43.59.gif)](https://gifyu.com/image/8Fpk)

### Java Implementation
All the optional parameters are null, and thus the simplest implementation is,

    SwipeToDeleteKt.addSwipeToDelete(recyclerView, null, null, null, null);
    
### Kotlin Implementation
	recyclerView.addSwipeToDelete()
	
The optional parameters can be provided if needed.    




---		


# Drag To Shift

Highlight, move and shift items of RecyclerView with just a single line of code.
Use *addDragToShift* as RecyclerView's extension function, to add this functionality to your RecyclerView. This extension has **one** optional parameter.

> The parameter is the listener for the interface method. The by default value for this parameter is null. If passed, the activity/fragment should implement `onDragged`. This method will be called when the RecyclerView item is dragged and dropped to some other position, and it returns the two positions that were swapped.	

**Implementation of interface method** :- 
In a notes app, if you wish to change positions of two notes in your database, after they are swapped, then you can used this method to perform the action. The notes can be swapped by getting the _NOTES_ using the adapter positions inside the list passed to RecyclerView Adapter.

    	override fun onPositionDragged(positionStart: Int, positionEnd: Int) {  
			adapter.moveItem(positionStart, positionEnd)  
		}
		
### Java Implementation
All the optional parameters are null, and thus the simplest implementation is,

    DragAndDropKt.addDragToShift(recyclerView,null);
    
### Kotlin Implementation
	recycler.addDragToShift()
	
The optional parameter can be provided if needed.  


---		


# Swipe to Perform

Add swipe to perform a custom task on RecyclerView's items from the list, which a single line of code.

Use *addSwipeToPerform* as RecyclerView's extension function, to add this functionality to your RecyclerView. 
This extension function has **two** optional parameter. 
> The first parameter is the list of directions in which swipe should be allowed. The directions can be either **TOP**, **BOTTOM**, **RIGHT**, **LEFT**.
The list of directions can be made like this,
		`val list = listOf(DIRECTION.LEFT,DIRECTION.RIGHT)`
If no value is passed for the list, by default, **RIGHT** is the direction for swipe.

> The second parameter is the *listener* for the interface method. The by default value of this parameter is null. If passed, the activity/fragment should implement `OnSwipeToPerform`. This method will be called when the RecyclerView item is swiped out, and it returns the position of the element that was swiped. 

**Implementation of interface method**
In a notes app, if you wish to perform a task when a note is swiped, then you can used this method to perform the action. 

        override fun swipeToPerform(adapterPosition: Int, swipeDirection: DIRECTION) {  
			// Perform your task
                        // swipeDirection gives the direction in which the item was swiped
		}
		
> The third parameter is an optional parameter, for the Color integer, if the user wants a color in the background when the view is swiped out. This is how the user can pass the Color int as the parameter, `ContextCompat.getColor(this, R.color.colorAccent)`

We have performed deletion for the particular case.

[![rec-2020-10-24_14.38.15.gif](https://s8.gifyu.com/images/rec-2020-10-24_14.38.15.gif)](https://gifyu.com/image/8FMg)


> The fourth parameter is an optional parameter, for the second Color integer, if the user wants two colors in the background, the user can pass both **Parameter 3 and Parameter 4**.

[![rec-2020-10-24_14.43.59.gif](https://s8.gifyu.com/images/rec-2020-10-24_14.43.59.gif)](https://gifyu.com/image/8Fpk)

### Java Implementation
All the optional parameters are null, and thus the simplest implementation is,

    SwipeToPerformKt.addSwipeToPerform(recyclerView, null, null, null, null);
    
### Kotlin Implementation
	recyclerView.addSwipeToPerform()
	
The optional parameters can be provided if needed.    


		
[![rec-2020-10-24_00.10.0459f8d3ce489a4f0f.gif](https://s8.gifyu.com/images/rec-2020-10-24_00.10.0459f8d3ce489a4f0f.gif)](https://gifyu.com/image/8jl5)


## Found this library useful? :heart:

Support it by joining [stargazers](https://github.com/Priyansh-Kedia/SwipeLibrary/stargazers) for this repository. :star2:
