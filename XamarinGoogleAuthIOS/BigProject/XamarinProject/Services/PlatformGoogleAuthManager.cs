using System;
namespace DatNT.Services
{
    public class PlatformGoogleAuthManager
    {
        public static IPlatformGoogleAuthService Current; /* this is the singleton for 
        your application that hold the reference to accessToken and many other things*/
    }
}
