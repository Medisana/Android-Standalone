# Android-Standalone

Welcome to the Android Standalone Application for VitaDock Online.

This project is designed to demonstrate logging into and requesting data from the VitaDock Online cloud platform.

To receive data, users must first login into their VitaDock Online account. For this purpose the application has a login activity, which - at first launch - stores the Open Authentication (oAuth) credentials, so only a single login is required.

The second interactive activity launched allows users to select the type of data to be retrieved from the VitaDock Online platform.

The data returned is filtered by the application in order to call the corresponding activity to display the information.

FEATURES
- Reusable classes to embed in your own application
- Handles user authentication via external browser and custom URL scheme callback
- Securely store oAuth credentials
- Retrieve data for all data and setting modules

NOTES:
- This project was developed in Android Studio.
- A valid application token and secret to access VitaDock Online is required

SYSTEM REQUIREMENTS:
- Android Studio version 1.0 or newer
- Compile SDK Version 21
- Android version 4.0.3 (SDK 15)
- Internet access
