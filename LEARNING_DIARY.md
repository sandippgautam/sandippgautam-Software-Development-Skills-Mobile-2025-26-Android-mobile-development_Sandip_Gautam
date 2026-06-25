Lappeenranta University of Technology
School of Business and Management
Software Development Skills: Mobile, Online course

Sandip Gautam, 002240570

LEARNING DIARY, MOBILE DEVELOPMENT MODULE

---

## Week 1: June 12 - June 18, 2026

### Date: June 12, 2026
**Activity:** Course introduction and environment setup

**Learning outcome:** 
I started the mobile development module by reading through the course materials and understanding the requirements. The main goal is to create an Android app that showcases my skills with multiple views, interactive components, and data display features. I decided to build "DevPortfolio Hub" - a combination of portfolio app with functional note-taking and time tracking features. This choice came because I wanted something unique that I could actually use and show to potential employers, not just another typical task manager app.

I spent time setting up Android Studio on my Windows machine. The installation was straightforward but took a while to download all the SDK components. I learned about the project structure in Android Studio and how Gradle manages dependencies. I also made my first commit to Git repository, setting up the initial project structure.

### Date: June 13, 2026
**Activity:** Learning Jetpack Compose basics and Material Design 3

**Learning outcome:**
Today I focused on understanding Jetpack Compose, which is totally different from the traditional XML-based Android UI. It took me a while to wrap my head around the declarative UI concept - you describe what the UI should look like rather than how to build it step by step. I watched several tutorials on Compose basics like @Composable functions, remember, and state management.

I also researched Material Design 3 guidelines and decided to go with an Apple-inspired design for a cleaner, more premium look. I learned about the color system and created a custom color palette with AppleBlue, AppleGreen, AppleOrange, and ApplePurple. Setting up the theme properly was tricky at first, but after reading the documentation I understood how ColorScheme works in Material3.

### Date: June 14, 2026
**Activity:** Building reusable UI components and project architecture

**Learning outcome:**
I learned about the MVVM (Model-View-ViewModel) architecture pattern today. This was a new concept for me. I understood that ViewModels hold the UI state and business logic, while the composable functions just display the data. This separation makes the code much cleaner and testable.

I created reusable components like AppleCard and AppleButton to maintain consistency across the app. I learned how to use Modifiers properly to style components - things like padding, fillMaxWidth, and clip. The Shadow and RoundedCornerShape modifiers were particularly useful for achieving that iOS-style card look.

One problem I faced was with the card shadows not looking right. After some googling, I found that I needed to use the shadow modifier before the background modifier, not after. This kind of ordering matters in Compose which was a good lesson.

### Date: June 15, 2026
**Activity:** Implementing navigation and multiple screens

**Learning outcome:**
Today I tackled navigation in Jetpack Compose using Navigation Compose library. I learned how to set up NavHost, define routes using a sealed class, and navigate between screens using navController.navigate(). The concept of navigation backstack was interesting - understanding how to properly handle back button presses.

I implemented the bottom navigation bar with 5 tabs (Home, Projects, Notes, Timer, Settings). Getting the bottom nav to stay visible on certain screens but hide on others required conditional rendering based on the current route. I learned about observing the navigation backstack using currentBackStackEntryAsState().

There was a frustrating bug where clicking the same tab multiple times kept adding to the backstack. I fixed it by using launchSingleTop = true in the navigation options. Small things like this really matter for good UX.

---

## Week 2: June 19 - June 25, 2026

### Date: June 19, 2026
**Activity:** Building the Notes feature with CRUD operations

**Learning outcome:**
I implemented the smart notes feature today, which was the most complex part so far. I learned about using mutableStateListOf in ViewModel to hold a list of notes that automatically triggers recomposition when modified. This was way easier than the old LiveData approach.

I created the Note data class with properties like title, content, category, and timestamp. I learned about Kotlin enums to define the NoteCategory with different types (Work, Study, Ideas, Code). Each category has its own color which makes the UI more organized and visually appealing.

Implementing CRUD operations (Create, Read, Update, Delete) taught me how to properly manage state in ViewModels. The addNote, deleteNote, and updateNote functions modify the state list, and the UI automatically updates. This reactive programming style is really powerful once you get used to it.

### Date: June 20, 2026
**Activity:** Implementing search functionality and LazyColumn

**Learning outcome:**
Today I added the search feature to the notes screen. I learned about TextField component and how to manage its state with remember and mutableStateOf. The real-time filtering was achieved by filtering the notes list based on the search query every time it changes.

LazyColumn was new to me - it's like RecyclerView but much simpler in Compose. I learned that it only composes the visible items, which makes scrolling through large lists efficient. Using the items() function to iterate through the list was straightforward. I also learned about providing a key parameter to help Compose identify items when the list changes.

One issue I encountered was the search not updating immediately. I realized I needed to use derivedStateOf for the filtered list so it recalculates whenever the search query or notes list changes. Understanding when to use remember vs derivedStateOf vs mutableStateOf is important.

### Date: June 21, 2026
**Activity:** Building the Time Tracker with live updates

**Learning outcome:**
The time tracker feature was challenging because it required real-time updates. I learned about Kotlin Coroutines and how to use LaunchedEffect to run side effects in Compose. The LaunchedEffect with a while loop that runs every second updates the timer display - this was a clever solution I found after researching.

I learned about StateFlow for observing the current tracking activity. The difference between StateFlow and regular State is that StateFlow is better for complex state management and can be collected from multiple places. I used MutableStateFlow in the ViewModel and exposed it as a read-only StateFlow.

Calculating the duration in milliseconds and formatting it to HH:MM:SS format taught me about time manipulation in Kotlin. I wrote a formatDuration() function that converts milliseconds to a readable time string using String.format().

### Date: June 22, 2026
**Activity:** Creating the Portfolio and About screens

**Learning outcome:**
Today I built the visual portfolio sections. I learned how to create a nice profile header with a circular avatar using Box, Modifier.clip(CircleShape), and proper alignment. The animated stats cards use Row with weights to distribute space evenly, which was a good lesson in Compose layouts.

I created the tech stack badges using Surface with rounded corners and custom colors. The key learning was about using FlowRow (or in my case, chunked lists with multiple Rows) to wrap items automatically. I learned about Arrangement.spacedBy() to add consistent spacing between items.

For the projects list, I practiced working with sample data by creating a ProjectData object. I learned it's good practice to separate data from UI code. The project cards show how to combine Icon, Text, and layout components to create a cohesive design.

### Date: June 23, 2026
**Activity:** Implementing dialogs and user confirmations

**Learning outcome:**
Today I added AlertDialog for delete confirmations and settings actions. I learned how to properly manage dialog state with remember { mutableStateOf(false) } and how to show/hide dialogs based on this state. The onDismissRequest callback was important for handling when users tap outside the dialog.

I learned about different button types in Material3 - Button, OutlinedButton, TextButton, and IconButton. Each has its own use case. TextButton is perfect for dialog actions because it's less prominent than a filled button.

One important UX lesson was about destructive actions. For the delete button, I used AppleRed color to indicate danger, and always added a confirmation dialog. Never let users accidentally delete data without warning. I also learned about using ButtonDefaults.textButtonColors() to customize button colors.

### Date: June 24, 2026
**Activity:** Polish, animations, and final touches

**Learning outcome:**
Today was all about making the app feel premium. I learned about animation APIs in Compose like AnimatedVisibility and animateContentSize(). I added subtle entrance animations to cards using LaunchedEffect to delay their appearance.

I learned about handling different states in UI - loading, empty, error, and success states. For example, the notes screen shows an empty state with an icon and message when there are no notes. This is much better UX than showing a blank screen.

I spent time adjusting padding, spacing, and font sizes to match the Apple design language. I learned that consistency is key - using the same corner radius (12-16dp), padding (16-20dp), and spacing (8-12dp) throughout the app creates visual harmony. Small details like icon sizes (24dp for nav, 20dp for buttons) really matter.

### Date: June 25, 2026
**Activity:** Testing, debugging, and build optimization

**Learning outcome:**
Final day was about making sure everything works. I learned how to use Android Studio's built-in emulator and also tested on my real device (Redmi Note 9 Pro Max). Testing on a real device showed issues I didn't see in the emulator - like performance with many notes.

I learned about Gradle build configuration and dependency management. There was a compilation error related to SDK versions - some libraries required compileSdk 35 but my project was on 34. I learned how to check library requirements and downgrade to compatible versions (changed from androidx.core:core-ktx:1.17.0 to 1.13.1).

I learned how to use adb (Android Debug Bridge) commands to install and launch the app from command line. The ./gradlew assembleDebug command builds the APK, and installDebug installs it on the connected device. This will be useful for future projects.

Most importantly, I learned that building a real app requires attention to detail - every button, every text field, every transition needs to feel right. It's not just about making it work, it's about making it work well.

---

## Overall Reflection

This two-week journey taught me modern Android development from scratch. Moving from no knowledge of Jetpack Compose to building a complete, functional app with multiple features was challenging but rewarding. 

The biggest takeaway is that mobile development is about understanding the platform - how Android manages lifecycle, how Compose handles state, how to architect code properly. It's not just about writing code that works, but writing code that's maintainable, efficient, and follows best practices.

I'm proud of what I built. DevPortfolio Hub is something I can show in job interviews - it demonstrates technical skills (Kotlin, Compose, MVVM, Navigation) and also shows I can design clean, user-friendly interfaces. The Apple-inspired design makes it stand out from typical Android apps.

The course structure of "learn by doing" worked well for me. Having to solve real problems (like state management, navigation, real-time updates) forced me to deeply understand the concepts rather than just following tutorials. I've learned to read documentation, search for solutions, and debug effectively.

Going forward, I want to add Room database for data persistence, implement dark mode properly, and maybe add some animation polish. But for now, I have a solid foundation in Android development and a portfolio piece I'm genuinely happy with.

---

**Total Hours Invested:** ~80 hours over 2 weeks
**Key Technologies Mastered:** Kotlin, Jetpack Compose, Material Design 3, MVVM Architecture, Navigation Compose
**Project Outcome:** Fully functional Android app with 6 screens, note-taking, time tracking, and portfolio showcase features

Sandip Gautam
Student Number: 002240570
LUT University - Mobile Development Course
Completed: June 25, 2026
