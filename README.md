# MusicApp
<div align="center"><h2>MusicApp</h2> </div>


 <p>The MusicApp is an application that uses the LastFm API to enable users to search for music. Users can search for their desired artist by entering their name in the search field and access all their albums, which can be downloaded to their device. The application is built using Retrofit, Glide, Navigation, MVVM architecture, Hilt, and Coroutine technologies. It consists of one activity and four fragments, and modularity is ensured as much as possible. In addition, Dao operations have been tested with Unit Test, and all add, delete, and list operations are working successfully. Retrofit tests have also been passed successfully. Tests are performed based on possible scenarios, such as the case where the same album is added, and the list size is expected to be 1. This enhances the stability and resilience of the application against potential user and API errors.</p>

## Features
  <List>
        <li>Album Search</li>
        <li>Music Search</li>
        <li>Album Download</li>
        <li>Lots of Music</li>
        <li>Album Information</li>
        <liClean Interface</li>
      </List>

## Technologies

- [SQLite](https://www.sqlite.org/)
- [Room Database](https://developer.android.com/topic/libraries/architecture/room)
- [Navigation Component](https://developer.android.com/guide/navigation)
- [MVVM (Model-View-ViewModel)](https://developer.android.com/jetpack/docs/guide#recommended-app-arch)
- [Dagger](https://dagger.dev)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Glide](https://github.com/bumptech/glide)
- [Coroutine](https://developer.android.com/kotlin/coroutines)
- [Flows](https://developer.android.com/kotlin/flow)

## Setup
1. Clone or download this repository
2. Open the project with Android Studio
3. Enter your Last Fm API key
4. Build and run the project


## Screenshots

![home-removebg-preview](https://user-images.githubusercontent.com/100201401/225551188-efa1470b-868c-47c1-bff4-4ae298ec5165.png)
![search-removebg-preview](https://user-images.githubusercontent.com/100201401/225551200-107cd1d9-fccb-416b-9239-d5191cd321f5.png)
  </br>  </br>
![topalbum-removebg-preview](https://user-images.githubusercontent.com/100201401/225551324-80a8ea77-e12c-4ee6-85ce-95dc06d4a6fe.png)
![detail-removebg-preview](https://user-images.githubusercontent.com/100201401/225551358-270bf6b5-6fef-4b29-95d3-0285832beaa8.png)
