🎬 CineFlow - Clean Architecture Movie App

CineFlow is a sample Android application built using Clean Architecture principles, Kotlin, Jetpack Compose, and other modern Android development libraries.

🛠️ Features

Jetpack Compose UI

Modular Clean Architecture (data, domain, presentation)

MVI pattern with State & Intent

Movie listing (Trending)

Watchlist management (Add / Remove)

Movie detail screen

Dark/Light mode toggle

Hilt for Dependency Injection

Retrofit for Networking

Room for Local Database

Flow & Coroutines

Unit & UI testing

📦 Modules

├── app
├── core
│   ├── common
│   ├── database
│   ├── domain
│   ├── model
│   ├── network
│   └── testutils
├── feature
│   ├── trending
│   ├── movie
│   └── watchlist

📱 Screens

SplashScreen – simple animated splash

HomeScreen – bottom tab with Trending & Watchlist

TrendingScreen – shows popular movies

WatchlistScreen – saved movies locally

MovieDetailScreen – full movie info with add/remove functionality

🔑 API

The app uses The Movie Database (TMDB) API.

You need a valid API key in local.properties:

TMDB_API_KEY=your_api_key_here

🧪 Testing

ViewModel unit tests with MockK and Turbine

UI tests using Compose Testing APIs


🚀 Getting Started

git clone https://github.com/farzanejavadi1993/CineFlow.git
open with Android Studio
sync & run

📄 License

This project is for learning purposes only and not affiliated with TMDB.

