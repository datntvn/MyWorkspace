using System;
namespace DatNT.Services
{
    public interface IPlatformGoogleAuthService
    {
        void InitAuthConfiguration();
        void Authenticate();
        string GetToken();
        void RetrieveEmail();
        string GetEmail();
    }
}
