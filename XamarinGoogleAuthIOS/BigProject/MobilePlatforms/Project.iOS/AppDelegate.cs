using System;
using System.Collections.Generic;
using System.Linq;

using Foundation;
using UIKit;
// DAT - begin - lib for platform specific - for google auth
using DatNT.Services;
using DatNT.iOS.Services;
// DAT - end - lib for platform specific - for google auth

namespace DatNT.iOS
{
    [Register("AppDelegate")]
    public partial class AppDelegate : global::Xamarin.Forms.Platform.iOS.FormsApplicationDelegate
    {
        public override bool FinishedLaunching(UIApplication app, NSDictionary options)
        {
            // removed non-related source code ....
            PlatformGoogleAuthManager.Current = new PlatformGoogleAuthServiceImpl(); // DAT - code for platform specific - for google auth
            LoadApplication(new App());

            return base.FinishedLaunching(app, options);
        }
        // DAT - adding for google auth - begin
        // CRITICAL NOTE: this method is the key for your app to catch the redirect_url from SFSafariViewController
        public override bool OpenUrl(UIApplication application, NSUrl url, string sourceApplication, NSObject annotation)
        {
            var uri_netfx = new Uri(url.AbsoluteString);
            PlatformGoogleAuthServiceImpl.Auth?.OnPageLoading(uri_netfx);

            return true;
        }
        // DAT - adding for google auth - end
    }
}
