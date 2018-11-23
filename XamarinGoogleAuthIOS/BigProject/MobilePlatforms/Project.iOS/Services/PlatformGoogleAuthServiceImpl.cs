using System;
using DatNT.Services;
using DatNTCommon.Authentication;
using UIKit;
using DatNTCommon.Authentication.Services;

namespace DatNT.iOS.Services
{

    public class PlatformGoogleAuthServiceImpl : UIViewController, IPlatformGoogleAuthService, IGoogleAuthenticationDelegate
    {
        public static GoogleAuthenticator Auth;
        public static UIViewController googleAutViewer;
        public static GoogleOAuthToken accessToken;
        public static string Email;

        public string GetToken()
        {
            if (accessToken != null)
            {
                return accessToken.AccessToken;
            }
            else
            {
                return null;
            }
        }


        public async void RetrieveEmail()
        {
            var googleService = new GoogleService();
            Email = await googleService.GetEmailAsync(accessToken.TokenType, accessToken.AccessToken);
        }
        public string GetEmail()
        {
            return Email;
        }

        public void Authenticate()
        {
            Console.WriteLine("IOS: trigger method GETFeedback");
            // begin coding event handler for google authen
            var authenticator = Auth.GetAuthenticator();
            // var viewController = authenticator.GetUI();
            googleAutViewer = authenticator.GetUI();

            // DAT - add proper code to render top UI
            var window = UIApplication.SharedApplication.KeyWindow;
            var vc = window.RootViewController;
            Console.WriteLine("FIRST LEVEL viewcontroller:: " + vc.ToString());
            while (vc.PresentedViewController != null)
            {
                vc = vc.PresentedViewController;
                Console.WriteLine("each viewcontroller:: " + vc.ToString());
            }
            vc.PresentViewController(googleAutViewer, true, null);
        }

        public void InitAuthConfiguration()
        {
            Auth = new GoogleAuthenticator(Configuration.ClientId, Configuration.Scope, Configuration.RedirectUrl, this);
        }

        // DAT - begin implement methods of IGoogleAuthenticationDelegate
        public void OnAuthenticationCanceled()
        {
            // SFSafariViewController doesn't dismiss itself
            // DismissViewController(true, null); // DATNT- does not work

            var alertController = new UIAlertController
            {
                Title = "Authentication canceled",
                Message = "You didn't completed the authentication process"
            };
            // PresentViewController(alertController, true, null); /* DAT - changed this*/
            // DAT - add proper code to render top UI
            var window = UIApplication.SharedApplication.KeyWindow;
            var vc = window.RootViewController;
            while (vc.PresentedViewController != null)
            {
                vc = vc.PresentedViewController;
            }
            vc.PresentViewController(alertController, true, null);
            // DAT - add proper code to render top UI - END

            Console.WriteLine("Display UI when Auth Canceled");
        }

        public async void OnAuthenticationCompleted(GoogleOAuthToken token)
        {
            // SFSafariViewController doesn't dismiss itself
            // DismissViewController(true, null); // DATNT- does not work
            googleAutViewer.DismissViewControllerAsync(true);

            accessToken = token;

            // Dat - disable 2 line below- because they crash our app - BEGIN
            //var googleService = new GoogleService();
            //var email = await googleService.GetEmailAsync(token.TokenType, token.AccessToken);
            // Dat - disable 2 line below- because they crash our app - END

            //// GoogleLoginButton.SetTitle($"Connected with {email}", UIControlState.Normal);
            //Console.WriteLine("Connected with email:: "+ email);
            Console.WriteLine("Complete Authentication");
        }

        public void OnAuthenticationFailed(string message, Exception exception)
        {
            // SFSafariViewController doesn't dismiss itself
            // DismissViewController(true, null);

            var alertController = new UIAlertController
            {
                Title = message,
                Message = exception?.ToString()
            };
            // PresentViewController(alertController, true, null); /* DAT - changed this */
            // DAT - add proper code to render top UI
            var window = UIApplication.SharedApplication.KeyWindow;
            var vc = window.RootViewController;
            while (vc.PresentedViewController != null)
            {
                vc = vc.PresentedViewController;
            }
            // PresentViewController(viewController, true, null); 
            // PresentViewController(vc, true, null); /* change above to this */ /* tried 1. NOT WORK */ 
            vc.PresentViewController(alertController, true, null);
            Console.WriteLine("UI display when Auth failed");
            // DAT - add proper code to render top UI - END
        }
        // DAT - end implement methods of IGoogleAuthenticationDelegate
    }

}
