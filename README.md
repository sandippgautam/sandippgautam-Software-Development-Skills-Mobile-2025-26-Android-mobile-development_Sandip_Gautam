# 📱 DevPortfolio Hub

> A modern Android portfolio app with integrated note-taking and time tracking features

<p align="center">
  <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android"/>
  <img src="https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin"/>
  <img src="https://img.shields.io/badge/Jetpack_Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" alt="Jetpack Compose"/>
</p>

## 🎯 About The Project

**DevPortfolio Hub** is an innovative Android application that combines a professional portfolio showcase with practical productivity tools. Built with modern Android development practices, it demonstrates proficiency in Kotlin, Jetpack Compose, and Material Design 3, featuring an elegant iOS-inspired UI design.

### ✨ Key Features

#### 📂 Portfolio Showcase
- **Home Dashboard** - Quick stats and navigation with animated cards
- **About Me** - Professional profile with skills, education, and contact information
- **Projects Gallery** - Showcase of development projects with tech stack badges
- **Clean Design** - Apple-inspired UI with smooth animations

#### 📝 Smart Notes
- **Quick Note Creation** - Fast and intuitive note-taking interface
- **Category Organization** - Organize notes by Work, Study, Ideas, and Code
- **Real-time Search** - Find notes instantly with live search functionality
- **Color-coded Cards** - Visual categorization for easy identification
- **CRUD Operations** - Full create, read, update, and delete functionality

#### ⏱️ Time Tracker
- **Activity Tracking** - Track time spent on different activities
- **Live Timer** - Real-time countdown display with start/stop controls
- **Session History** - View past tracking sessions with durations
- **Statistics Dashboard** - Total time and session count metrics
- **Activity Types** - Predefined categories (Coding, Learning, Meeting, Break, Research)

#### ⚙️ Settings & Customization
- **Notification Controls** - Toggle push notifications
- **App Information** - Version, developer, and build details
- **Data Management** - Clear all data option with confirmation

## 🛠️ Technical Stack

### Core Technologies
- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Design System**: Material Design 3
- **Architecture**: MVVM (Model-View-ViewModel)
- **Navigation**: Jetpack Navigation Compose
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

### Key Libraries & Dependencies
```kotlin
// Jetpack Compose
implementation("androidx.compose.ui:ui")
implementation("androidx.compose.material3:material3")
implementation("androidx.activity:activity-compose")

// Navigation
implementation("androidx.navigation:navigation-compose")

// ViewModel & Lifecycle
implementation("androidx.lifecycle:lifecycle-viewmodel-compose")
implementation("androidx.lifecycle:lifecycle-runtime-ktx")

// Extended Icons
implementation("androidx.compose.material:material-icons-extended")
```

## 📐 Architecture

The app follows the **MVVM (Model-View-ViewModel)** architecture pattern:

```
app/
├── data/                    # Data models
│   ├── Note.kt             # Note data class & categories
│   ├── Project.kt          # Project data & samples
│   └── TimeEntry.kt        # Time tracking entries
├── viewmodel/              # Business logic
│   ├── NotesViewModel.kt   # Notes management
│   └── TimeTrackerViewModel.kt
├── ui/
│   ├── components/         # Reusable UI components
│   │   ├── AppleCard.kt
│   │   └── AppleButton.kt
│   ├── screens/            # App screens
│   │   ├── HomeScreen.kt
│   │   ├── AboutScreen.kt
│   │   ├── ProjectsScreen.kt
│   │   ├── NotesScreen.kt
│   │   ├── AddEditNoteScreen.kt
│   │   ├── TimeTrackerScreen.kt
│   │   └── SettingsScreen.kt
│   └── theme/              # App theming
│       ├── Color.kt        # Apple-inspired colors
│       ├── Theme.kt
│       └── Type.kt
├── navigation/             # Navigation setup
│   └── Screen.kt
└── MainActivity.kt         # App entry point
```

## 🎨 Design Philosophy

### Apple-Inspired UI/UX
- **Minimalist Design**: Clean, spacious layouts with plenty of whitespace
- **Rounded Corners**: Soft 12-16dp corner radius on all cards and buttons
- **Subtle Shadows**: Light elevation for depth without distraction
- **Color Psychology**: Purposeful use of blue, green, orange, and purple
- **Typography**: Clear hierarchy with SF-style font weights
- **Smooth Animations**: Natural, physics-based transitions
- **Bottom Navigation**: Easy thumb-reach navigation pattern

### Color Palette
```kotlin
AppleBlue     = #007AFF  // Primary actions
AppleGreen    = #34C759  // Success/positive
AppleOrange   = #FF9500  // Warnings/highlights
ApplePurple   = #5856D6  // Creative/alternative
AppleRed      = #FF3B30  // Destructive actions
AppleBackground = #F2F2F7  // App background
AppleCardBackground = #FFFFFF  // Card surfaces
```

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or newer
- JDK 11 or higher
- Android SDK 34
- Gradle 8.7

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/sandipgautam/devportfolio-hub.git
   cd devportfolio-hub
   ```

2. **Open in Android Studio**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned directory

3. **Sync Project**
   - Click "Sync Project with Gradle Files"
   - Wait for dependencies to download

4. **Run the App**
   - Connect an Android device or start an emulator
   - Click the "Run" button (▶️) or press `Shift+F10`

### Building APK

```bash
# Debug build
./gradlew assembleDebug

# Release build (requires signing config)
./gradlew assembleRelease
```

The APK will be generated at:
`app/build/outputs/apk/debug/app-debug.apk`

## 📱 Screenshots

### Home Dashboard
Beautiful animated dashboard with quick stats cards and navigation options.

### Smart Notes
Category-organized note-taking with search functionality and color-coded cards.

### Time Tracker
Real-time activity tracking with session history and statistics.

### Projects Showcase
Professional project gallery with tech stack badges and GitHub links.

## 🎓 Learning Outcomes

This project demonstrates proficiency in:

- ✅ **Modern Android Development** - Jetpack Compose, Material Design 3
- ✅ **Architecture Patterns** - MVVM, separation of concerns
- ✅ **State Management** - ViewModel, StateFlow, remember
- ✅ **Navigation** - Multi-screen navigation with bottom nav bar
- ✅ **UI Components** - Custom reusable components, cards, buttons
- ✅ **List Display** - LazyColumn for efficient scrolling lists
- ✅ **User Input** - Text fields, dropdowns, toggles, search
- ✅ **Data Operations** - CRUD functionality for notes
- ✅ **Time Management** - Real-time timer with coroutines
- ✅ **Responsive Design** - Adaptive layouts for different screen sizes
- ✅ **Code Organization** - Clean, maintainable, well-documented code

## 📋 Course Requirements Fulfilled

### Required Components ✓
1. **Functionality with components** ✅
   - Buttons (Add, Delete, Start/Stop, Navigation)
   - Text Fields (Search, Title, Content)
   - Toggles (Notifications switch)
   - Dropdowns (Activity selector)
   - Cards (Projects, Notes, Stats)

2. **Multiple views** ✅
   - 6 main screens with navigation
   - Modal dialogs for confirmations
   - Bottom navigation bar

3. **Component to display information** ✅
   - LazyColumn for projects list
   - LazyColumn for notes list
   - LazyColumn for time tracking history
   - Custom card layouts for each

### Additional Features 🌟
- Real-time search functionality
- Animated UI transitions
- Time tracking with live updates
- Professional portfolio presentation
- Clean architecture (MVVM)
- Comprehensive error handling
- User-friendly confirmations

## 🔧 Configuration

### Customization

#### Update Personal Information
Edit `AboutScreen.kt`:
```kotlin
// Line 70-80: Update profile information
Text(text = "Sandip Gautam")
Text(text = "Android Developer • Tech Enthusiast")
Text(text = "your.email@example.com")
Text(text = "github.com/yourusername")
```

#### Modify Sample Projects
Edit `Project.kt`:
```kotlin
// Add/edit projects in ProjectData.getSampleProjects()
Project(
    name = "Your Project Name",
    description = "Project description",
    techStack = listOf("Tech1", "Tech2", "Tech3"),
    githubUrl = "github.com/yourrepo"
)
```

#### Change Theme Colors
Edit `Color.kt`:
```kotlin
val AppleBlue = Color(0xFF007AFF)  // Modify hex values
val AppleGreen = Color(0xFF34C759)
// Add custom colors as needed
```

## 🐛 Known Issues

- Dark mode is prepared but not fully implemented (Coming soon)
- Room database integration pending (data resets on app restart)
- GitHub link buttons are visual only (not clickable)

## 🗺️ Roadmap

- [ ] Implement Room Database for data persistence
- [ ] Add Dark Mode support
- [ ] Enable note editing functionality
- [ ] Add image support for notes
- [ ] Implement data export/import (JSON)
- [ ] Add notification reminders for notes
- [ ] Create widgets for quick access
- [ ] Implement data backup to cloud

## 👨‍💻 Developer

**Sandip Gautam**
- Email: sandip.gautam@example.com
- GitHub: [@sandipgautam](https://github.com/sandipgautam)
- LinkedIn: [sandipgautam](https://linkedin.com/in/sandipgautam)

## 📄 License

This project was created as part of a mobile development course assignment at LUT University.

## 🙏 Acknowledgments

- **LUT University** - Mobile Development Course (2025-2026)
- **Material Design 3** - Design guidelines and components
- **Jetpack Compose** - Modern Android UI toolkit
- **Apple** - Design inspiration



<p align="center">
  Made by Sandip Gautam<br/>
  DevPortfolio Hub © 2026
</p>
