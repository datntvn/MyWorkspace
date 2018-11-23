# # DatNT Code snipet that support for Xamarin project to perform OAuth with Google!

This is not a complete project, just the needed source code that you can use to add into your own project and run.

Here is how I organize my project that you could use it as a reference to restructure the code as your need.

<Big project> : This one nested other smaller project
  <Mobile platforms> : Within this one, we have ios, and android specific project
    <Your project.Android>
    <Your project.iOS>
  <Common project> : This project stores the code act as common library for Android, and iOS
  <Your xamarin> : This project stores the code for UI (files like XAML, XAML.cs)
