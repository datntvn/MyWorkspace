using System;
namespace DatNT.iOS
{
    public static class Configuration
    {
        public const string ClientId = "...<your google credential data>...apps.googleusercontent.com";
        public const string Scope = "email";
        public const string RedirectUrl = "com.googleusercontent.apps....<your google credential data>...:/oauth2redirect";
        /**
        RedirectUrl has 2 parts: <Part 1: Your Google's REVERSED ID>:/<Part 2: oauth2redirect>
          If you do not have any special need, just remain part 2 as: oauth2redirect
        CRITICAL NOTE: you must edit your info.plist file, add a deep link, in which
        - URL Schemes value shoudl be: com.googleusercontent.apps.<... your google credential data ...>
         */
    }
}
