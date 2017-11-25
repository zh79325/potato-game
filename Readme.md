## Build
```aidl
clean install -DskipTests
```
## Update Project version
```aidl
build-helper:parse-version versions:set -DnewVersion=${parsedVersion.majorVersion}.${parsedVersion.minorVersion}.${parsedVersion.nextIncrementalVersion} versions:commit
```
