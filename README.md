# Valentine's Garage

An Android app for managing truck check-ins, collaborative repair jobs, and mechanic accountability at a garage. Built with Kotlin, Firebase, and MVVM.

## Overview

Valentine's Garage helps shop staff:

- **Check in trucks** with registration, odometer, condition rating, and notes
- **Track repair work** on shared, real-time task checklists
- **Attribute completed work** to specific mechanics for owner reporting

The app supports two roles:

| Role | Access |
|------|--------|
| **Mechanic** | Login, check-in, view open jobs, add/complete tasks |
| **Admin** | All mechanic features plus reports and adding new mechanic accounts |

## Features

- Email/password authentication (Firebase Auth)
- Truck check-in with condition ratings (`EXCELLENT` → `CRITICAL`)
- Open jobs list with live Firestore updates
- Per-job task checklist (subcollection) synced across devices
- Admin reports: view completed tasks by mechanic
- Admin: create new mechanic accounts
- Camera and gallery permissions for vehicle photos (Storage upload supported in repository layer)

## Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Kotlin |
| UI | XML layouts, View Binding, Material Components, Navigation Component |
| Architecture | MVVM (Fragments + ViewModels + Repositories) |
| DI | Hilt |
| Backend | Firebase Auth, Cloud Firestore, Firebase Storage |
| Async | Kotlin Coroutines, StateFlow |
| Image loading | Glide |
| Build | Gradle 8.9, AGP 8.7.3, Java 21 |

**Requirements:** Android SDK 24+ (target SDK 35), Android Studio Ladybug / Panda or newer recommended.

## Project Structure

```
ValentineGarage/
├── app/
│   ├── src/main/
│   │   ├── java/com/valentinesgarage/
│   │   │   ├── di/                 # Hilt modules (Firebase, repositories)
│   │   │   ├── data/
│   │   │   │   ├── model/          # User, Truck, Job, Task
│   │   │   │   └── repository/     # Firestore & Auth access
│   │   │   └── ui/
│   │   │       ├── auth/           # Login
│   │   │       ├── checkin/        # Truck check-in
│   │   │       ├── jobs/           # Job list & detail
│   │   │       ├── reports/        # Admin reports
│   │   │       └── admin/          # Add mechanic
│   │   └── res/
│   │       ├── layout/             # Screen & list item layouts
│   │       ├── navigation/         # nav_graph.xml
│   │       └── menu/               # Bottom nav & toolbar menus
│   └── src/test/                   # Unit tests
├── build.gradle.kts
└── settings.gradle.kts
```

## Firestore Data Model

| Collection | Description |
|------------|-------------|
| `Users/{uid}` | Staff profile: name, email, role (`ADMIN` / `MECHANIC`) |
| `trucks/{truckId}` | Check-in record: registration, km, condition, photos |
| `jobs/{jobId}` | Repair job linked to a truck (`OPEN`, `COMPLETED`, `CANCELLED`) |
| `jobs/{jobId}/tasks/{taskId}` | Task checklist with completion attribution |

Check-in creates a truck and an open job atomically in a single Firestore batch write.

## Getting Started

### Prerequisites

1. [Android Studio](https://developer.android.com/studio) with SDK 35 installed
2. A [Firebase](https://console.firebase.google.com/) project with:
   - **Authentication** → Email/Password enabled
   - **Cloud Firestore** database created
   - **Storage** (optional, for truck photos)
3. Android device or emulator (API 24+)

### Firebase Setup

1. In Firebase Console, add an **Android app** with package name:
   ```
   com.valentinesgarage
   ```
2. Download `google-services.json` and place it at:
   ```
   app/google-services.json
   ```
3. Create at least one **admin** user:
   - Add the user in Firebase Authentication (email/password)
   - In Firestore, create document `Users/{uid}` with fields:
     ```json
     {
       "uid": "<firebase-auth-uid>",
       "name": "Valentine",
       "email": "admin@example.com",
       "role": "ADMIN"
     }
     ```
4. Configure **Firestore Security Rules** appropriate for your team (restrict reads/writes by auth and role).
5. Create mechanic users via the app (admin flow) or manually with `"role": "MECHANIC"`.

> **Never commit** `google-services.json` to a public repository. It contains project credentials. Use the sample file approach below for collaborators.

### Clone and Run

```bash
git clone https://github.com/YOUR_USERNAME/ValentineGarage.git
cd ValentineGarage
```

1. Copy your Firebase config:
   ```bash
   # Place google-services.json in app/
   ```
2. Open the project in Android Studio (`ValentineGarage` folder).
3. Let Gradle sync complete.
4. Run the **app** configuration on a device or emulator.

### Build from Command Line

```bash
cd ValentineGarage
./gradlew assembleDebug
```

On Windows:

```powershell
.\gradlew.bat assembleDebug
```

Debug APK output:

```
app/build/outputs/apk/debug/app-debug.apk
```

## Configuration Files (Do Not Commit)

Add these to `.gitignore` before pushing to GitHub:

```gitignore
# Firebase & local SDK
app/google-services.json
local.properties

# Build outputs
build/
app/build/
.gradle/

# IDE
.idea/
*.iml
```

Provide teammates a `google-services.json.example` or setup instructions (this README) instead of the real file.

## User Flows

```
Login → Job List
          ├── Check In → Job Detail (new truck + job)
          ├── Tap job → Job Detail (tasks)
          └── Admin menu → Add Mechanic / Logout

Bottom nav: Jobs | Check In | Reports (admin only)
```

**Job Detail:** mechanics add tasks, mark them done (with optional notes). Completion stores `completedBy` and `completedByName` for reporting.

## Testing

Run unit tests:

```bash
./gradlew test
```

Includes `CheckInViewModelTest` for check-in validation logic.

## Team Development

This project is structured for parallel contribution. Suggested areas:

| Area | Branch | Focus |
|------|--------|--------|
| Backend / Security | `feature/backend-security` | Hilt, Firebase modules, manifest, FileProvider |
| Data Repository | `feature/data-repository` | Models, repositories, unit tests |
| ViewModels | `feature/viewmodel-business-logic` | Auth, check-in, jobs, reports, admin VMs |
| Navigation & Resources | `feature/navigation-state` | MainActivity, nav graph, themes, menus |
| UI / Frontend | `feature/ui-frontend` | Fragments, layouts, RecyclerView adapters |

### Push to GitHub (team lead)

```bash
cd ValentineGarage
git init
git remote add origin https://github.com/YOUR_USERNAME/ValentineGarage.git
git add .
git commit -m "Initial commit: Valentine's Garage Android app"
git branch -M main
git push -u origin main
```

Invite collaborators via **GitHub → Settings → Collaborators**.

## Architecture

```
┌─────────────────────────────────────────────────────────┐
│  UI (Fragments, Adapters, XML)                          │
│  Reads ViewBinding · Observes StateFlow · Calls VM      │
└───────────────────────────┬─────────────────────────────┘
                            │
┌───────────────────────────▼─────────────────────────────┐
│  ViewModels (business logic, validation)                │
└───────────────────────────┬─────────────────────────────┘
                            │
┌───────────────────────────▼─────────────────────────────┐
│  Repositories (User, Truck, Job)                        │
└───────────────────────────┬─────────────────────────────┘
                            │
┌───────────────────────────▼─────────────────────────────┐
│  Firebase Auth · Firestore · Storage                    │
└─────────────────────────────────────────────────────────┘
```

## Known Limitations

- **Room** is listed as a dependency but offline caching is not yet implemented.
- **Photo upload** on check-in: repository support exists; full UI wiring may be incomplete.
- **Add Mechanic** screen is built programmatically (no dedicated layout XML).

## License

Add your license here (e.g. MIT) if this repository is public.

## Authors

Add team member names and roles here.

---

**Package:** `com.valentinesgarage` · **Min SDK:** 24 · **Target SDK:** 35
