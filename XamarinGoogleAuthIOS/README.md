# # DatNT Code snipet that support for Xamarin project to perform OAuth with Google!

This is not a complete project, just the needed source code that you can use to add into your own project and run.

Here is how I organize my project that you could use it as a reference to restructure the code as your need.


[Big project] : This one nested other smaller project  

&nbsp;&nbsp;&nbsp;&nbsp;[Mobile platforms] : Within this one, we have ios, and android specific project  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Your project.Android]  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Your project.iOS]  
&nbsp;&nbsp;&nbsp;&nbsp;[Common project] : This project stores the code act as common library for Android, and iOS  
&nbsp;&nbsp;&nbsp;&nbsp;[Your xamarin] : This project stores the code for UI (files like XAML, XAML.cs)  
  
NOTE:  
* You have to install NugetPackage: Xamarin.Auth for each of your projects  
* You also have to install NugetPackage: Newtonsoft.Json  
  
I had used, Xamarin.Auth (1.6.0.4), Newtonsoft.json(11.0.2)  

  
You need to add below xml content into your info.plist file in order for IOS app to handle redirect_url. For more information, you can find on google with key work: ios "deep link", or "url scheme"
  
```xml
  <key>CFBundleURLTypes</key>
  <array>
    <dict>
      <key>CFBundleURLName</key>
      <string><TODO: your IOS bundle id></string>
      <key>CFBundleURLSchemes</key>
      <array>
        <string>com.googleusercontent.apps.<TODO: your google credential data at REVERSED ID></string>
      </array>
      <key>CFBundleURLTypes</key>
      <string>Viewer</string>
    </dict>
  </array>
```

