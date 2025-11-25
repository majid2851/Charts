# 📦 Library Publishing Guide

Complete guide for publishing the Compose Charts Library to various repositories.

## Table of Contents

1. [Prerequisites](#prerequisites)
2. [Local Testing](#local-testing)
3. [JitPack Publishing](#jitpack-publishing)
4. [GitHub Packages](#github-packages)
5. [Maven Central](#maven-central)
6. [Version Management](#version-management)

---

## Prerequisites

### Required Tools

- Android Studio (latest version)
- Gradle 8.0+
- Git
- GitHub account (for JitPack/GitHub Packages)
- Sonatype account (for Maven Central)

### Setup

Ensure all files are in place:
- ✅ `charts-library/build.gradle.kts` with publishing config
- ✅ `charts-library/proguard-rules.pro`
- ✅ `charts-library/consumer-rules.pro`
- ✅ `charts-library/src/main/AndroidManifest.xml`
- ✅ `charts-library/README.md`
- ✅ `charts-library/LICENSE`

---

## Local Testing

### 1. Publish to Maven Local

Test your library locally before publishing:

```bash
# Navigate to project root
cd "D:\Java\Android Studio\Apply_Journey\Charts"

# Build and publish to local Maven repository
./gradlew :charts-library:publishToMavenLocal
```

**Windows PowerShell:**
```powershell
cd "D:\Java\Android Studio\Apply_Journey\Charts"
.\gradlew.bat :charts-library:publishToMavenLocal
```

### 2. Test in Another Project

Create a new test project or use existing one:

```kotlin
// settings.gradle.kts
repositories {
    mavenLocal()
    google()
    mavenCentral()
}

// app/build.gradle.kts
dependencies {
    implementation("com.majid2851:compose-charts:1.0.0")
}
```

### 3. Verify Installation

```kotlin
import com.majid2851.charts.ui.components.line.line_chart.LineChart
import com.majid2851.charts.domain.model.LineChartData

@Composable
fun TestChart() {
    LineChart(
        data = LineChartData(/* ... */),
        modifier = Modifier.fillMaxWidth().height(300.dp)
    )
}
```

---

## JitPack Publishing

### Overview

JitPack is the **easiest way** to publish Android libraries. It builds from your GitHub repository automatically.

### Prerequisites

1. GitHub account
2. Public repository (or JitPack paid plan for private repos)

### Steps

#### 1. Commit and Push

```bash
git add .
git commit -m "Release v1.0.0"
git push origin main
```

#### 2. Create a Release Tag

```bash
# Create and push tag
git tag v1.0.0
git push origin v1.0.0
```

**Or via GitHub UI:**
1. Go to your repository
2. Click "Releases" → "Create a new release"
3. Tag version: `v1.0.0`
4. Release title: `Version 1.0.0`
5. Description: Copy from CHANGELOG.md
6. Click "Publish release"

#### 3. Trigger JitPack Build

Visit: `https://jitpack.io/#majid2851/charts`

JitPack will automatically:
- Detect the release
- Build the library
- Make it available

#### 4. Usage

Users can now add:

```kotlin
// settings.gradle.kts
repositories {
    maven { url = uri("https://jitpack.io") }
}

// app/build.gradle.kts
dependencies {
    implementation("com.github.majid2851:charts:1.0.0")
}
```

### Troubleshooting JitPack

**Build Failed?**
- Check JitPack logs at `https://jitpack.io/com/github/majid2851/charts/1.0.0/build.log`
- Ensure `build.gradle.kts` is valid
- Verify all dependencies are available

**Version Not Found?**
- Wait a few minutes for JitPack to build
- Clear cache: Add `?nocache=1` to JitPack URL
- Check tag exists: `git tag -l`

---

## GitHub Packages

### Overview

GitHub Packages allows you to publish packages to GitHub's registry.

### Prerequisites

1. GitHub account
2. Personal Access Token (PAT) with `write:packages` scope

### Steps

#### 1. Create GitHub Personal Access Token

1. Go to GitHub Settings → Developer settings → Personal access tokens
2. Click "Generate new token (classic)"
3. Select scopes:
   - ✅ `write:packages`
   - ✅ `read:packages`
   - ✅ `delete:packages`
4. Copy the token

#### 2. Configure Gradle Properties

Create/edit `~/.gradle/gradle.properties`:

```properties
gpr.user=majid2851
gpr.key=YOUR_GITHUB_TOKEN
```

**Windows:**
```
C:\Users\YourUsername\.gradle\gradle.properties
```

#### 3. Publish to GitHub Packages

```bash
./gradlew :charts-library:publish
```

**Windows:**
```powershell
.\gradlew.bat :charts-library:publish
```

#### 4. Usage

Users need to authenticate:

```kotlin
// settings.gradle.kts
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/majid2851/charts")
        credentials {
            username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
            password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
        }
    }
}

// app/build.gradle.kts
dependencies {
    implementation("com.majid2851:compose-charts:1.0.0")
}
```

---

## Maven Central

### Overview

Maven Central is the standard repository for Java/Kotlin/Android libraries. Most complex but most professional.

### Prerequisites

1. Sonatype JIRA account: https://issues.sonatype.org/
2. Verified domain or GitHub Pages
3. GPG key for signing

### Setup (One-time)

#### 1. Create Sonatype Account

1. Go to https://issues.sonatype.org/
2. Click "Sign up"
3. Create an account

#### 2. Create a New Project Ticket

1. Create new ticket: https://issues.sonatype.org/secure/CreateIssue.jspa
2. Project: Community Support - Open Source Project Repository Hosting
3. Issue Type: New Project
4. Group Id: `com.majid2851` or `io.github.majid2851`
5. Project URL: `https://github.com/majid2851/charts`
6. SCM URL: `https://github.com/majid2851/charts.git`

Wait for approval (usually 1-2 business days).

#### 3. Generate GPG Key

```bash
# Generate key
gpg --gen-key

# List keys
gpg --list-keys

# Export public key to keyserver
gpg --keyserver keyserver.ubuntu.com --send-keys YOUR_KEY_ID

# Export private key
gpg --export-secret-keys > secring.gpg
```

#### 4. Configure Gradle

Add to `~/.gradle/gradle.properties`:

```properties
signing.keyId=YOUR_KEY_ID
signing.password=YOUR_GPG_PASSWORD
signing.secretKeyRingFile=/path/to/secring.gpg

ossrhUsername=YOUR_SONATYPE_USERNAME
ossrhPassword=YOUR_SONATYPE_PASSWORD
```

#### 5. Update build.gradle.kts

```kotlin
plugins {
    id("maven-publish")
    id("signing")
}

signing {
    sign(publishing.publications)
}

publishing {
    publications {
        create<MavenPublication>("release") {
            // ... existing config ...
        }
    }
    
    repositories {
        maven {
            val releasesRepoUrl = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            val snapshotsRepoUrl = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            
            credentials {
                username = project.findProperty("ossrhUsername") as String?
                password = project.findProperty("ossrhPassword") as String?
            }
        }
    }
}
```

### Publish to Maven Central

```bash
# Build and publish
./gradlew :charts-library:publish

# Close and release staging repository
./gradlew closeAndReleaseRepository
```

### Usage

After approval (can take hours to days):

```kotlin
// settings.gradle.kts
repositories {
    mavenCentral()
}

// app/build.gradle.kts
dependencies {
    implementation("com.majid2851:compose-charts:1.0.0")
}
```

---

## Version Management

### Semantic Versioning

Follow SemVer: `MAJOR.MINOR.PATCH`

- **MAJOR**: Breaking changes (2.0.0)
- **MINOR**: New features, backward compatible (1.1.0)
- **PATCH**: Bug fixes (1.0.1)

### Version Update Checklist

Before releasing a new version:

- [ ] Update version in `charts-library/build.gradle.kts`
- [ ] Update CHANGELOG.md
- [ ] Update README.md with new version number
- [ ] Test all features
- [ ] Run tests: `./gradlew :charts-library:test`
- [ ] Build library: `./gradlew :charts-library:build`
- [ ] Update API.md if API changed
- [ ] Commit changes
- [ ] Create git tag
- [ ] Push to GitHub
- [ ] Publish to repository

### Example: Release 1.1.0

```bash
# 1. Update version in build.gradle.kts
# groupId = "com.majid2851"
# artifactId = "compose-charts"
# version = "1.1.0"

# 2. Update CHANGELOG.md
# Add new section for 1.1.0

# 3. Commit
git add .
git commit -m "Release v1.1.0: Add animations support"

# 4. Tag
git tag v1.1.0

# 5. Push
git push origin main
git push origin v1.1.0

# 6. Publish (choose method)
./gradlew :charts-library:publishToMavenLocal  # Test
./gradlew :charts-library:publish              # GitHub Packages / Maven Central
# Or let JitPack build automatically from tag
```

---

## Automation with GitHub Actions

### Create `.github/workflows/publish.yml`

```yaml
name: Publish Library

on:
  push:
    tags:
      - 'v*'

jobs:
  publish:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      
      - name: Set up JDK 11
        uses: actions/setup-java@v3
        with:
          java-version: '11'
          distribution: 'temurin'
      
      - name: Grant execute permission for gradlew
        run: chmod +x gradlew
      
      - name: Build library
        run: ./gradlew :charts-library:build
      
      - name: Publish to GitHub Packages
        env:
          USERNAME: ${{ github.actor }}
          TOKEN: ${{ secrets.GITHUB_TOKEN }}
        run: ./gradlew :charts-library:publish
```

This automatically publishes when you push a tag!

---

## Best Practices

### Before Publishing

1. ✅ Test library locally
2. ✅ Run all tests
3. ✅ Check ProGuard rules
4. ✅ Verify dependencies
5. ✅ Update documentation
6. ✅ Create git tag
7. ✅ Write release notes

### After Publishing

1. ✅ Verify installation in new project
2. ✅ Update examples
3. ✅ Announce on social media
4. ✅ Monitor issue tracker
5. ✅ Respond to feedback

---

## Quick Commands Reference

```bash
# Test locally
./gradlew :charts-library:publishToMavenLocal

# Build library
./gradlew :charts-library:build

# Run tests
./gradlew :charts-library:test

# Publish to GitHub Packages
./gradlew :charts-library:publish

# Clean build
./gradlew clean :charts-library:build

# Create tag
git tag v1.0.0
git push origin v1.0.0

# List tags
git tag -l

# Delete tag (if mistake)
git tag -d v1.0.0
git push --delete origin v1.0.0
```

---

## Troubleshooting

### Common Issues

**Signing Failed**
```
Solution: Check GPG key and password in gradle.properties
```

**Publish Failed**
```
Solution: Verify credentials and repository URL
```

**Version Already Exists**
```
Solution: Increment version number
```

**Build Failed**
```
Solution: Run ./gradlew clean build and check errors
```

---

## Resources

- [JitPack Documentation](https://jitpack.io/docs/)
- [GitHub Packages Guide](https://docs.github.com/en/packages)
- [Maven Central Guide](https://central.sonatype.org/publish/publish-guide/)
- [Semantic Versioning](https://semver.org/)

---

## Support

Need help publishing?
- 📧 Email: majid2851@example.com
- 💬 [GitHub Discussions](https://github.com/majid2851/charts/discussions)
- 🐛 [GitHub Issues](https://github.com/majid2851/charts/issues)

---

Good luck with your library! 🚀





