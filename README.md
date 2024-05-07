
Spotify Search App
Description
This Android application allows users to search for albums, artists, playlists, and tracks using the Spotify search API. It follows the MVVM architecture pattern with a Single Activity Multiple Fragments structure. The app is offline-supported using Room Database, saving the last searched results for offline access.

Features
Search for albums, artists, playlists, and tracks.
View search results categorized by type (album, artist, playlist, track).
View details of each search result in a separate screen.
Offline support using Room Database.


Screenshots
<!-- Add screenshots of your app here -->
Technologies Used
MVVM Architecture
Room Database
Dagger for dependency injection
Retrofit for API calls
LiveData and ViewModel for UI updates
RecyclerView for displaying search results

Installation
To run this application, follow these steps:

Clone this repository to your local machine.
bash
Copy code
git clone https://github.com/your_username/SpotifySearchApp.git
Open the project in Android Studio.
Build and run the application on an Android device or emulator.
Usage
Enter search query in the search bar.
Results will be displayed categorized by type (album, artist, playlist, track).
Click on any search result to view its details in a separate screen.
Offline support: Last searched results are saved and can be accessed without internet connection.

Focused Areas
Unit tests for ViewModel and Repository.
Attention to UI/UX design.
Correctness and completeness of the solution.
Code quality, readability, and adherence to best practices.
Efficiency and optimization of algorithms.
Clear and concise documentation.
GitHub Repository

APK Download
Download APK **

Acknowledgments
Spotify API documentation: https://developer.spotify.com/documentation/web-api
