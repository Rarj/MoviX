# MoviX

MoviX is an innovative application designed to enhance your movie browsing experience by providing an intuitive and user-friendly interface. Discover and explore a vast collection of movies effortlessly with MoviX's robust search and detailed information features.

## Features
- Search for movies
- View detailed information about each movie
  - Title
  - Synopsys
  - Poster
  - Reviews
  - Rating
- Filter movie by genre

https://github.com/user-attachments/assets/4047a6a4-690c-4427-b2a0-510bdab80d9f

### Technology Stack

* Kotlin
* Jetpack Compose and View System (XML)
* Jetpack Navigation
* Coroutines + FLow
* Coil Compose
* Hilt
* Paging3
* Retrofit + OkHTTP3 + GSON (converter)
* Material3

### Folder structures

| module | description | notes |
| ------ | ----------- | ----- |
| app	| Main application code | gradually migrate |
| data | Data management and network | gradually migrate |
| home |	Home page | |
| network |	Shared Network Response | |
| uikit | UI Kit for reusable components | |

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- Gradle 8.2.X
- Android Studio Hedgehog | 2023.1.1 or higher

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/Rarj/MoviX.git
   cd MoviX

2. Build Project
   ```sh
   ./gradlew build
   
3. To run the apps, use the following command
   ```sh
   ./gradlew run

