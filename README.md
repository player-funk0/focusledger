# FocusLedger: Your Offline Focus Companion

![FocusLedger Logo](https://via.placeholder.com/150/000000/FFFFFF?text=FocusLedger)

## 🚀 Introduction

FocusLedger is a powerful, privacy-focused productivity application designed to help you master deep work and minimize distractions. Built with a 100% offline architecture, FocusLedger ensures your data remains private and your focus uninterrupted by external dependencies. It combines an immersive focus timer with a gamified reward system, extensive customization options, and robust session tracking to transform your productivity habits.

## ✨ Features

FocusLedger offers a rich set of features tailored for an optimal focus experience:

*   **100% Offline Architecture**: No internet connection, no APIs, no servers. Your data stays on your device, ensuring complete privacy and uninterrupted functionality.
*   **Immersive Focus Timer**: Choose between the classic Pomodoro Technique or a flexible Free Flow mode. The minimalist, full-screen timer helps you stay in the zone.
*   **Gamified Reward System**: Earn XP and tokens for successful focus sessions. Unlock new gradients, fonts, and dynamic titles as you progress, making productivity a rewarding journey.
*   **Advanced Customization**: Personalize your app experience with:
    *   **Dynamic Gradients**: Choose from a variety of visually appealing background gradients.
    *   **Unique Fonts**: Select fonts that enhance readability and aesthetics.
    *   **Avatar Selection**: Pick a unique avatar to represent your profile.
*   **Custom Music Upload**: Upload any song from your device to create your perfect focus ambiance. The app automatically retrieves and displays the song title.
*   **Daily Reminders**: Set personalized daily reminders with AM/PM selection to consistently engage with your focus goals.
*   **Do Not Disturb (DND) Integration**: Automatically activate DND during focus sessions to block notifications and minimize interruptions, with intelligent state restoration.
*   **Session Tracking & Analytics**: The Ledger screen provides a clear overview of your focus history, including a dynamic chart visualizing your weekly output distribution.
*   **Productivity Card Export**: Generate and export a shareable image of your personalized productivity ID card, showcasing your stats and achievements.

## 🛠️ Technical Stack

FocusLedger is built entirely with modern Android technologies, emphasizing performance and a native user experience:

*   **Kotlin**: A concise and expressive programming language for Android development.
*   **Jetpack Compose**: Android's modern toolkit for building native UI, enabling a declarative and efficient development process.
*   **MVVM Architecture**: A clean and scalable architectural pattern that separates concerns, making the codebase maintainable and testable.
*   **Room Persistence Library**: Provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
*   **DataStore**: A modern and improved data storage solution that stores key-value pairs or typed objects with protocol buffers, ensuring data consistency and reliability.
*   **Coroutines & Flow**: For asynchronous programming and reactive data streams, ensuring a smooth and responsive user interface.

## 🏗️ Architecture Overview

FocusLedger follows the Model-View-ViewModel (MVVM) architectural pattern:

*   **Model**: Represents the data layer, including `UserStatsEntity` and `FocusSessionEntity` managed by `FocusDatabase` (Room) and `PreferencesManager` (DataStore). The `FocusRepository` acts as a single source of truth for data operations.
*   **View**: Composed of Jetpack Compose `Composable` functions (e.g., `HomeScreen`, `FocusScreen`, `SettingsScreen`) that observe `ViewModel` data and render the UI.
*   **ViewModel**: (`FocusViewModel`) Exposes UI-related data streams and handles business logic, interacting with the `Repository` to update data and manage application state (e.g., timer logic, reward calculations, DND toggling).

## ⚙️ Setup and Installation

To get a local copy up and running, follow these simple steps.

### Prerequisites

*   Android Studio (Bumblebee or newer recommended)
*   Android SDK (API Level 21+)
*   A physical Android device or emulator for testing

### Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/your-username/FocusLedger.git
    cd FocusLedger
    ```
2.  **Open in Android Studio:**
    Open the `FocusLedger` project in Android Studio.
3.  **Build and Run:**
    Sync the Gradle project, then build and run the application on your connected device or emulator.

## 🚀 Usage

1.  **Start a Focus Session**: Navigate to the Home screen, choose your focus duration (Pomodoro or Free Flow), and tap "Start Focus".
2.  **Customize Your Experience**: Visit the Settings screen to enable DND, set daily reminders, select custom music, and manage other preferences.
3.  **Track Your Progress**: The Ledger screen provides insights into your focus sessions and productivity trends.
4.  **Personalize Your Profile**: Update your username, bio, and avatar in the Profile screen.

## 🤝 Contributing

Contributions are what make the open-source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1.  Fork the Project
2.  Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the Branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request

## 📄 License

Distributed under the MIT License. See `LICENSE` for more information.

## 📧 Contact

Made by obrynex 
site for commpany: https://web.obrynex.workers.dev/
