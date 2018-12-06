
# react-native-react-socket

## Getting started

`$ npm install react-native-react-socket --save`

### Mostly automatic installation

`$ react-native link react-native-react-socket`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNReactSocketPackage;` to the imports at the top of the file
  - Add `new RNReactSocketPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-react-socket'
  	project(':react-native-react-socket').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-react-socket/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-react-socket')
  	```


## Usage
```javascript
import RNReactSocket from 'react-native-react-socket';

// TODO: What to do with the module?
RNReactSocket;
```
  